package demo.getting.started.hazelcast.listner;

import com.hazelcast.core.DistributedObjectEvent;
import com.hazelcast.core.DistributedObjectListener;

/**
 * Created by bijoy on 12/6/16.
 */
public class CustomDistributedObjectListner implements DistributedObjectListener {
    @Override
    public void distributedObjectCreated(DistributedObjectEvent event) {
        System.out.println("!!!!!!!!!!!distributedObjectCreated!!!!!!!!!!!"+event);
    }

    @Override
    public void distributedObjectDestroyed(DistributedObjectEvent event) {
        System.out.println("!!!!!!!!!!!distributedObjectDestroyed!!!!!!!!!!!"+event);
    }
}
