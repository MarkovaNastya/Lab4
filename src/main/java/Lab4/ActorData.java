package Lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class ActorData extends AbstractActor {

    HashMap<Integer, ArrayList<PutDataMessage>> data = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(
                        PutDataMessage.class, message ->{
                            ArrayList<PutDataMessage> testData = data.containsKey(message.getPackageId()) ?
                                    data.get(message.getPackageId()) : new ArrayList<>();
                            testData.add(message);
                            data.put(message.getPackageId(),testData);
                        }
                )
                .build();
    }
}
