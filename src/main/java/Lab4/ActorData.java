package Lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class ActorData extends AbstractActor {
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
