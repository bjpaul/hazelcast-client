package demo.getting.started.hazelcast.view;


import demo.getting.started.hazelcast.operations.CustomOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bijoy on 13/6/16.
 */
@RestController
public class ClientController {
    @Autowired
    private CustomOperations customOperations;

    @RequestMapping("/receive")
    public void receiver(){
        customOperations.receiver();
    }

}
