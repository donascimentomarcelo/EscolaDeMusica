package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.modelo.Cidade;
import escola.musica.modelo.Estado;
import escola.musica.servico.CidadeService;

@Controller("cidadeService")
@Transactional
public class CidadeServiceImpl implements CidadeService{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void salvar(Cidade cidade) {
		entityManager.merge(cidade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> listarTodos() {
		return entityManager.createQuery("from Cidade order by id desc").getResultList();
	}

	@Override
	public void excluir(Cidade cidade) {
		cidade = entityManager.merge(cidade);
		entityManager.remove(cidade);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> obterCidadeDoEstado(Estado estado) {
		return entityManager.createQuery("from Cidade where estado = :uf").setParameter("uf", estado).getResultList();
	}

}
