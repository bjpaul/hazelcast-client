package demo.getting.started.hazelcast.listner;

import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;

/**
 * Created by bijoy on 12/6/16.
 */
public class CustomLifeCycleListner implements LifecycleListener {
    @Override
    public void stateChanged(LifecycleEvent event) {
        System.out.println("::::::::: event changed to :::::::::"+event.getState());
    }
}
