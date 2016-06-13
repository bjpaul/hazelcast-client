package demo.getting.started.hazelcast.listner;

import com.hazelcast.core.*;

/**
 * Created by bijoy on 12/6/16.
 */
public class CustomMembershipListner implements InitialMembershipListener {
    @Override
    public void memberAdded(MembershipEvent membershipEvent) {
        System.out.println("##################### Member added -- "+ membershipEvent.getMember().getAddress());
    }

    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {
        System.out.println("##################### Member removed -- "+ membershipEvent.getMember().getAddress());
    }

    @Override
    public void memberAttributeChanged(MemberAttributeEvent memberAttributeEvent) {
        System.out.println("##################### Member "+ memberAttributeEvent.getMember().getAddress()+", attribute "+memberAttributeEvent.getValue());
    }

    @Override
    public void init(InitialMembershipEvent event) {
        System.out.println("##################### Member List ########## ");
        for(Member member:event.getMembers()){
            System.out.println(member.getAddress()+", ");
        }
    }
}
