package spring.jms.producer;

import javax.jms.JMSException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String MSG1="First transaction message";
		final String MSG2="Second transaction message";
		final String MSG3="Third transaction message";
		try {
			ApplicationContext context=new ClassPathXmlApplicationContext("springJms-context.xml");
			MessageProducer producer=context.getBean("producer", MessageProducer.class);
			producer.sendMessageToQueue(MSG1);
			producer.sendMessageToQueue(MSG2);
			producer.sendMessageToQueue(MSG3);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error " +e.getMessage());
		}

	}

}
