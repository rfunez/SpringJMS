package spring.jms.producer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;


public class MessageProducer {
	private JmsTemplate template;
	
	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}

	public void sendMessageToQueue(String msg){
		final String MSG=msg;
		template.send(new MessageCreator() {			
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				TextMessage txtMsg=session.createTextMessage(MSG);
				return txtMsg;
			}
		});
	}
}
