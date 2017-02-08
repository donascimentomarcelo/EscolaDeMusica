package escola.musica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import escola.musica.modelo.Curso;

public class CursoDAO {

		public void salvar(Curso curso)
		{
			//Inicia o entityManager
			//Vai na classe JPAUtil e invoca o getEntityManager
			EntityManager entityManager = JPAUtil.getEntityManager();
			//"Conversa com o banco"
			entityManager.getTransaction().begin();
			//Usa-se o merge tanto para salvar como para alterar caso ja venha com ID
			entityManager.merge(curso);
			//Commita a ação no banco
			entityManager.getTransaction().commit();
			//fecha a conexão
			entityManager.close();
		}
		
		@SuppressWarnings("unchecked")
		public List<Curso> listarTodos()
		{
			EntityManager entityManager = JPAUtil.getEntityManager();
			//retorna a lista de consulta
			return entityManager.createQuery("from Curso").getResultList();
		}

		public void excluir(Curso curso) {
			EntityManager entityManager = JPAUtil.getEntityManager();

			entityManager.getTransaction().begin();
			//Faz um merge para vincular o curso ao banco
			curso = entityManager.merge(curso);
			
			entityManager.remove(curso);
			
			entityManager.getTransaction().commit();
			
			entityManager.close();
			
		}

		public List<Curso> listarCursosAccordion() {
			EntityManager entityManager = JPAUtil.getEntityManager();
			
			return entityManager.createQuery("from Curso where nome in('')").getResultList();
		}
}
