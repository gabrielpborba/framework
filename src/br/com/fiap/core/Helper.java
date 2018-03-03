package br.com.fiap.core;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.entity.Pedido;

public class Helper {
	private EntityManager em;
	public Helper(EntityManager em){
		this.em = em;
	}
	/*public void salvar(Funcionario funcionario) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(funcionario);
			em.getTransaction().commit();
		}
		catch (Exception e) {
			throw e;
		} finally {
			//em.close();
		}
	}*/
	//JPQL: Usando Query
	@SuppressWarnings("unchecked")
	public List<Pedido> listarPedidos(){
		Query query = em.createQuery("select p from Pedido p");
		return query.getResultList();
	}
	public Pedido buscarPedidoById(int id){
		Query query = em.createQuery("select p from Pedido p where id = :id");  
				query.setParameter("id", id);
				Pedido p = (Pedido)query.getSingleResult();
				return p;
	}

	
	
}
