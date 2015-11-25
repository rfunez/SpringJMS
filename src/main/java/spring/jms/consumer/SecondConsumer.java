package spring.jms.consumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SecondConsumer {
	
	@JmsListener(destination="raulQueue",containerFactory="jmsConFactory")
	public void processMessages(Message msg){
		try {
			if(msg instanceof TextMessage){
				TextMessage txtMsg=(TextMessage)msg;
				System.out.println("Receiving message " +txtMsg.getText());
			}
			/*ConnectionFactory connFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
			Connection connection=connFactory.createConnection();
			Session session=connection.createSession(true, 0);
			Destination dest=new ActiveMQQueue("raulQueue");
			MessageConsumer consumer=session.createConsumer(dest);
			TextMessage msg1=(TextMessage)consumer.receive();
			System.out.println("Msg1 " +msg1.getText());
			Thread.sleep(5000);
			TextMessage msg2=(TextMessage)consumer.receive();
			System.out.println("Msg2 " +msg2.getText());
			Thread.sleep(5000);
			TextMessage msg3=(TextMessage)consumer.receive();
			System.out.println("Msg3 " +msg3.getText());
			session.commit();
			session.close();*/
			
		} catch (JMSException e) {
			// TODO: handle exception
		}		
	}
}
