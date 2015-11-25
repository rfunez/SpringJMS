package spring.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.jdbc.tx.OperationsDAO;

public class MasterListener implements MessageListener {
	private OperationsDAO operations;
	
	public MasterListener(OperationsDAO operations) {
		// TODO Auto-generated constructor stub
		this.operations=operations;
	}

	@Transactional
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(10000);
			System.out.println("Thread for listener " +Thread.currentThread().getName());
			String bodyMessage="";
			if(message instanceof TextMessage){
				TextMessage msg=(TextMessage)message;
				bodyMessage=msg.getText();
				if(bodyMessage.startsWith("Second")){
					System.out.println("Message received at master " +msg.getText());
					//this.operations.insertTextMessage(bodyMessage);
			}
				else{
					System.out.println("Message received at master " +msg.getText());					
				}
			}
		} catch (JMSException e) {
			// TODO: handle exception
			System.out.println("Delivered error message at master " +e.getMessage());
		}
		catch (InterruptedException e1){
			
		}
		finally{
			System.out.println("im finishing");
		}
	}
}
