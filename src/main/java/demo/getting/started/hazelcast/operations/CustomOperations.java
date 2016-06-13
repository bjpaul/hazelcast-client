package demo.getting.started.hazelcast.operations;

import demo.getting.started.hazelcast.objects.DistributedObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bijoy on 13/6/16.
 */
@Service
public class CustomOperations {

    @Autowired
    private DistributedObjects distributedObjects;

    public void receiver(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("Received :: " + distributedObjects.getQueue("distributedQueue").take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
