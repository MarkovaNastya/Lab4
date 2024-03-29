package Lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ActorJS extends AbstractActor {

    private String doJSCode(String jscript, String functionName, Object[] params) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(jscript);
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(functionName, params).toString();
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(
                        ToDoJSCode.class, message -> {
                            int id = message.getTest().getKey();
                            InputJSMessage inputJSMessage = message.getTest().getValue();

                            Test test = inputJSMessage.getTests()[id];

                            String res = doJSCode(inputJSMessage.getJsScript(),
                                    inputJSMessage.getFunctionName(),
                                    test.getParams());

                            getSender().tell(new PutDataMessage(
                                            inputJSMessage.getPackageId(),
                                            res,
                                            test.getExpectedResult(),
                                            test.getParams(),
                                            test.getTestName()
                                    ),
                                    ActorRef.noSender());
                        }
                )
                .build();
    }
}
