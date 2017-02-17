package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.modelo.Curso;
import escola.musica.servico.CursoServico;

@Service("cursoServico")
@Transactional
public class CursoServicoImpl implements CursoServico {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void salvar(Curso curso) {
		entityManager.merge(curso);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> listarTodos() {
		return entityManager.createQuery("from Curso").getResultList();
	}

	@Override
	public void excluir(Curso curso) {
		curso = entityManager.merge(curso);
		entityManager.remove(curso);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> listarCursoAccordion() {
		return entityManager.createQuery("from Curso where nome in('Violino', 'Bateria', 'Clarinete', 'Flauta', 'Guitarra', 'Viol�o', 'Obo�') order by nome").getResultList();
	}

}
