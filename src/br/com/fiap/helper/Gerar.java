package br.com.fiap.helper;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.core.Helper;
import br.com.fiap.entity.Pedido;
import br.com.fiap.gerador.GeradorNota;
import br.com.fiap.mq.Consumer;
import br.com.fiap.mq.Producer;

@WebService
public class Gerar  {
	
	@WebMethod
	public  boolean gerarPDF(int id) {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NotaFiscalDB");
	
			EntityManager em = entityManagerFactory.createEntityManager();
	
			Helper dao = new Helper(em);
			try {
				Pedido pedido = dao.buscarPedidoById(id);
				GeradorNota gerador = new GeradorNota();
				gerador.gerarPDF(pedido);
				
				Producer producer = new Producer();
				producer.onProducer();
				
				Consumer consumer = new Consumer();
				consumer.onConsumer();
				
			} catch (Exception e) {
				return false;
			}
			
			return true;
			
		}
}

