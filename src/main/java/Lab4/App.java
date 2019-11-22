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

public class App {

    private final static String ROUTES = "routes";
    private final static String HOST = "localhost";
    private final static int PORT = 8080;
    private final static String SERVER_MESSAGE = "Server online at http://localhost:8080/\nPress RETURN to stop...";
    private final static String POST_MESSAGE = "Message posted";
    private final static String PACKAGE_ID = "packageId";
    private final static int TIME_OUT = 5000;

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create(ROUTES);

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        ActorRef actorRef = system.actorOf(Props.create(ActorMain.class));

        App app = new App();

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                app.listenerRequest(actorRef).flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost(HOST, PORT),
                materializer
        );

        System.out.println(SERVER_MESSAGE);
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
                                    return complete(POST_MESSAGE);
                                }
                        )
                ),
                get(
                        () -> parameter(PACKAGE_ID, packageId -> {
                                    Future<Object> result = Patterns.ask(
                                            actor,
                                            new GetMessage(Integer.parseInt(packageId)),
                                            TIME_OUT);
                                    return completeOKWithFuture(result, Jackson.marshaller());
                                }
                        )
                )
        );
    }
}
