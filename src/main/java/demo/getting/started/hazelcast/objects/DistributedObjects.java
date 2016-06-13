package demo.getting.started.hazelcast.objects;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import demo.getting.started.hazelcast.instance.factory.ClientInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by bijoy on 12/6/16.
 */
@Component
public class DistributedObjects {

    @Autowired
    private ClientInstance clientInstance;

    public IQueue getQueue(String queue){
        return clientInstance.getClientInstance().getQueue(queue);
    }

    public HazelcastInstance clientInstance(){
        return clientInstance.getClientInstance();
    }
}
