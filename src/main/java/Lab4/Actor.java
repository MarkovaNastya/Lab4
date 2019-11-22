package Lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class Actor extends AbstractActor {

    ActorRef actorJS;
    ActorRef actorData;

    public Actor() {
        
    }


    @Override
    public Receive createReceive() {
        return null;
    }
}
