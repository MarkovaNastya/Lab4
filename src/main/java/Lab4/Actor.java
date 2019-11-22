package Lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinGroup;

public class Actor extends AbstractActor {

    ActorRef actorJS;
    ActorRef actorData;

    public Actor() {
        actorJS = getContext().actorOf(new RoundRobinGroup(3).props(Props.create()), "testGroup");
    }


    @Override
    public Receive createReceive() {
        return null;
    }
}
