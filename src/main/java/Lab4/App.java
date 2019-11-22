package Lab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import scala.concurrent.Future;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class Main {

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("routes");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        ActorRef actorRef = system.actorOf(Props.create(Actor.class));

        Main main = new Main();

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                main.listenerRequest(actorRef).flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                materializer
        );

        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .

                        thenAccept(unbound -> system.terminate());
    }

    private Route listenerRequest(ActorRef actor) {

        return concat(
                post(
                        () -> entity(
                                Jackson.unmarshaller(InputJSMessage.class), message -> {
                                    actor.tell(message, ActorRef.noSender());
                                    return complete("Message posted");
                                }
                        )
                ),
                get(
                        () -> parameter("packageId", packageId -> {
                            Future<Object> result = Patterns.ask(
                                    actor,
                                    new GetMessage(Integer.parseInt(packageId)),
                                    5000);
                            return completeOKWithFuture(result, Jackson.marshaller());



                                }



                        )

                )
        );


    }


}