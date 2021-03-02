package demo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.OnSuccess;
import akka.pattern.Patterns;
import akka.util.Timeout;
import demo.actors.FileAnalysisActor;
import demo.messages.FileAnalysisMessage;
import demo.messages.FileProcessedMessage;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AkkaApp {

    public static void main(String[] args) {
        // Create actorSystem
        ActorSystem akkaSystem = ActorSystem.create("akkaSystem");

        // Create first actor based on the specified class
        Props props = Props.create(FileAnalysisActor.class);
        ActorRef coordinator = akkaSystem.actorOf(props);

        // Create a message including the file path
        FileAnalysisMessage msg = new FileAnalysisMessage("data/dataJson.json");

        // Send a message to start processing the file. This is a synchronous call using 'ask' with a timeout.
        Timeout timeout = new Timeout(5, TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(coordinator, msg, timeout);

        // Process the results
        final ExecutionContext ec = akkaSystem.dispatcher();
        future.onSuccess(new OnSuccess<Object>() {
            @Override
            public void onSuccess(Object message) throws Throwable {
                if (message instanceof FileProcessedMessage) {
                	System.out.println("*********** print result ************");
                    printResults((FileProcessedMessage) message);

                    // Stop the actor system
                    akkaSystem.shutdown();
                }
            }

            private void printResults(FileProcessedMessage message) {
                System.out.println("================================");
                System.out.println(" JSON contente");
                System.out.println("================================");
                
                message.getIpMap().stream().forEach(fruits->{
                	fruits.getTweets().stream().forEach(fruit->{
                    	System.out.println(" User Description :"+fruit.getUserDescription() +", Number of Tweets : "+fruit.getUserNumbTweets()+" ,Web site : "+fruit.isSentFromWeb());

                	});
                	
                });               
            }
        }, ec);
    }
}
