package demo.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import akka.actor.UntypedActor;
import demo.messages.Tweet;
import demo.messages.LineProcessingResult;
import demo.messages.LogLineMessage;

public class LogLineProcessor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof LogLineMessage) {
            // What data each actor process?
//            System.out.println("Line: " + ((LogLineMessage) message).getData());
            // Uncomment this line to see the thread number and the actor name relationship
//            System.out.println("Thread ["+Thread.currentThread().getId()+"] handling ["+ getSelf().toString()+"]");

            // get the message payload, this will be just one line from the log file
            List<String> messageData = ((LogLineMessage) message).getData();
            
        	List<Tweet>  fruits = new ArrayList<>();
            
            ObjectMapper mapper = new ObjectMapper();
            for(String fruitJson  : messageData) {
                Tweet fruit = mapper.readValue(fruitJson, Tweet.class);
                fruits.add(fruit);
                
            }
         //   LineProcessingResult fruit = mapper.readValue(messageData, LineProcessingResult.class);


                // tell the sender that we got a result using a new type of message
                this.getSender().tell(new LineProcessingResult(fruits), this.getSelf());
            
        } else {
            // ignore any other message type
            this.unhandled(message);
        }
    }
}
