package MessageBroker;

import com.rabbitmq.client.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv)
            throws java.io.IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        //String severity = getSeverity(argv);
        //String message = getMessage(argv);
        
        do {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //System.out.println("Ingrese severidad: ");
            String severity = "severidad";
            System.out.println("Ingrese mensaje: ");
            String message = br.readLine();
            
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
            
        } while (true);
        
        //channel.close();
        //connection.close();
    }
}
