package demo.getting.started.hazelcast.instance.factory;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ListenerConfig;
import com.hazelcast.config.NearCacheConfig;
import com.hazelcast.core.HazelcastInstance;
import demo.getting.started.hazelcast.listner.CustomDistributedObjectListner;
import demo.getting.started.hazelcast.listner.CustomLifeCycleListner;
import demo.getting.started.hazelcast.listner.CustomMembershipListner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * Created by bijoy on 11/6/16.
 */
@Component
public class ClientInstance {
    private HazelcastInstance hazelcastInstance;

    @Value("${hazelcast.server.host}")
    private String host;

    @PostConstruct
    public void init() {
        System.out.println("<================ init ====================>");
        getClientInstance();
//        addDistributedObjectListener();
    }

    public HazelcastInstance getClientInstance() {
        if (hazelcastInstance == null || !hazelcastInstance.getLifecycleService().isRunning()) {
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.setInstanceName("My Client " + UUID.randomUUID());
            clientConfig.getGroupConfig()
                    .setName("test")
                    .setPassword("test");
            clientConfig.getNetworkConfig()
                    .addAddress(host)
                    .setConnectionAttemptLimit(10) // by default 2
                    .setConnectionAttemptPeriod(1000) // by default 3000 ms
                    .setConnectionTimeout(3000) // by default 5000 ms
                    .setSmartRouting(true) // by default true
                    .setRedoOperation(true); // by default true for read only operation
//            clientConfig.setLoadBalancer(new RoundRobinLB());
//            addNearCacheConfig(clientConfig);
//            addListnerConfig(clientConfig);

            synchronized (this) {
                while (hazelcastInstance == null || !hazelcastInstance.getLifecycleService().isRunning()) {
                    try {
                        hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
                    } catch (IllegalStateException ex) {
                        System.out.println("************" + ex.getMessage());
                    }
                }
            }

        }
        return hazelcastInstance;
    }

    private void addNearCacheConfig(ClientConfig clientConfig) {
        clientConfig.addNearCacheConfig(
                new NearCacheConfig()
                        .setName("*nearCache")
                        .setMaxSize(5000) //  max no of entry per map
                        .setTimeToLiveSeconds(15 * 10000) // regardless of eviction policy
                        .setMaxIdleSeconds(15 * 1000)
                        .setEvictionPolicy(EvictionPolicy.LFU.name()) // by default LRU
                        .setInvalidateOnChange(true) // by default true
//                            .getAsReadOnly()
        );
    }

    private void addListnerConfig(ClientConfig clientConfig) {
        clientConfig.addListenerConfig(new ListenerConfig(new CustomMembershipListner()))
                .addListenerConfig(new ListenerConfig(new CustomLifeCycleListner()));
    }

    private void addDistributedObjectListener() {
        hazelcastInstance.addDistributedObjectListener(new CustomDistributedObjectListner());
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("<================ shutdown ====================>");
        hazelcastInstance.shutdown();
    }
}
