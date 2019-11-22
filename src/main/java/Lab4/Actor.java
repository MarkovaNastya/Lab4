package Lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class Actor extends AbstractActor {

    ActorRef actorJS;
    ActorRef actorData;

    public Actor() {
        testPerformerActor = getContext().actorOf(
                new RoundRobinGroup(routeePaths).props(), "testGroup");
    }


    @Override
    public Receive createReceive() {
        return null;
    }
}
