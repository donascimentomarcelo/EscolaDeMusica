package escola.musica.dao;

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
}
