package Lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinGroup;
import akka.routing.RoundRobinPool;

public class ActorMain extends AbstractActor {

    ActorRef actorJS;
    ActorRef actorData;

    public ActorMain() {
        actorJS = getContext().actorOf(new RoundRobinPool(3).props(Props.create(ActorJS.class)), "JSGroup");
        actorData = getContext().actorOf(Props.create(ActorData.class));
    }


    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(
                        InputJSMessage.class, message -> {
                            for (int i = 0; i < message.getTests().length; i++) {
                                actorJS.tell(new ToDoJSCode(i, message), actorData);
                            }
                        }
                )
                .match(
                        GetMessage.class, message -> {
                            actorData.tell(message, sender());
                        }
                )
                .build();
    }
}
