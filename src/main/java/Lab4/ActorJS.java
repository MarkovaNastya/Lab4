package Lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;






public class ActorJS extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(
                        ToDoJSCode.class, message -> {

                        }
                )
                .build();
    }
}
