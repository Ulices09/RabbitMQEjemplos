package PipeFilter;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Send {
    private final static String QUEUE_NAME = "hello";

  public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        
        do {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingrese mensaje: ");
            String message = br.readLine();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        } while (true);
      
        //channel.close();
        //connection.close();
    }
}
