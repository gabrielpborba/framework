package br.com.fiap.mq;

import java.io.File;
import java.io.FileOutputStream;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	private static String subject = "cupons";
	
	private static String sourcePath = "D:\\cupons\\consumer\\pdf_nota.pdf";
	
	public void onConsumer() throws JMSException{
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = session.createQueue(subject);
		
		MessageConsumer consumer = session.createConsumer(destination);
		
		Message message = consumer.receive();
		
		if(message instanceof BytesMessage) {
			BytesMessage bytesMessage = (BytesMessage) message;
			onBytesMessage(bytesMessage);
			//TextMessage textMessage = (TextMessage) message;
			//System.out.println("Receivedage: "+textMessage.getText());
		}
		
		connection.close();

	}
	
	public static void onBytesMessage(BytesMessage bytesMessage) {
		   try {
		      // copy data into a byte[] buffer
		      int dataSize = (int) bytesMessage.getBodyLength();
		      byte[] buffer = new byte[dataSize];
		      bytesMessage.readBytes(buffer, dataSize);

		      // now write the buffer to a file
		      File outputFile = new File(sourcePath);
		      FileOutputStream fileOutput = new FileOutputStream(outputFile);
		      try {
		         fileOutput.write(buffer);
		      } finally {
		         fileOutput.close();
		         System.out.println("Cupom gerado com sucesso! ");
		      }
		   } catch (Exception ex) {
		      // handle exception
		   }
	}

}
