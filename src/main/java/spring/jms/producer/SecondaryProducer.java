package spring.jms.producer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;

public class SecondaryProducer {
	private JmsTemplate template;
	
	
	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}


	@Transactional
	public void sendMessageToQueue(String[] groupMsg){
		final String[] listMsg=groupMsg;
		this.template.send(new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				try {
					for(String msg:listMsg){
						TextMessage indMsg=session.createTextMessage(msg);
						System.out.println("Sending msg " +indMsg.getText());
						Thread.sleep(5000);
						return indMsg;
					}
					session.commit();					
				} catch (Exception e) {
					// TODO: handle exception
				}
				return null;
			}
		});
	}
}
