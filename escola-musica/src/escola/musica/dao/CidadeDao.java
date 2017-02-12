package escola.musica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import escola.musica.modelo.Cidade;
import escola.musica.modelo.Estado;

public class CidadeDao {
	
	@SuppressWarnings("unchecked")
	public static List<Cidade> obterCidadeDoEstado(Estado estado)
	{
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		List<Cidade> cidades =  entityManager.createQuery("from Cidade where estado = :uf").setParameter("uf", estado).getResultList();
		
		entityManager.close();
		
		return cidades;
	}
}
