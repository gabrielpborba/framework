package br.com.fiap.mq;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {

private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	private static String subject = "cupons";
	
	private static String sourcePath = "D:\\cupons\\producer\\pdf_nota.pdf";
	
	public void onProducer() throws JMSException{
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = session.createQueue(subject);
		
		MessageProducer producer = session.createProducer(destination);
		
		byte[] pdf = null;
		
		try {
			pdf = Files.readAllBytes(new File(sourcePath).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BytesMessage bytesMessage = session.createBytesMessage();

		bytesMessage.writeBytes( pdf ); 
				
		producer.send(bytesMessage);
						
		connection.close();
		
		File file = new File(sourcePath);
		file.delete();
	}
}
