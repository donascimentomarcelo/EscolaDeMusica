package escola.musica.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class GenericDao<T> {
	
	private final Class<T> classe;
	
	public GenericDao(Class<T> classe)
	{
		//instancio o atributo classe
		this.classe = classe;
	}
	
	public void salvar(T t)
	{
		//EntityManager é um gerenciador de entidades. Faz as consultar, inserts etc no banco.
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		entityManager.getTransaction().begin();
		
		entityManager.merge(t);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
	}
	
	public void excluir(T t)
	{
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		entityManager.getTransaction().begin();
		
		entityManager.remove(t);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
	}
	
	public List<T> listarTodos()
	{
		//getResultList: se n achar nd, retorna uma lista vazia
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		List<T> resultados = entityManager.createQuery("select c from " + classe.getName() + " c ", classe ).getResultList();
		
		entityManager.close();
		
		return resultados;
	}
	
	public T obterPorId(Integer id)
	{
		//getSingleResult vai lançar uma exceção se não achar nhm ou + de 1
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		T t = entityManager.createQuery("from " + classe + " where id = :id ", classe).setParameter(" id ", id).getSingleResult();
		
		return t;
	}

}
