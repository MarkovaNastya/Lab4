package Lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class ActorData extends AbstractActor {

    private HashMap<Integer, ArrayList<TestInfo>> storage = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(
                        PutDataMessage.class, message ->{

                        }
                )
                .build();
    }
}
