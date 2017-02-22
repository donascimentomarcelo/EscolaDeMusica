package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import escola.musica.exception.LoginRepetidoException;
import escola.musica.modelo.Usuario;
import escola.musica.modelo.UsuarioProfessor;
import escola.musica.servico.UsuarioProfessorServico;
import escola.musica.servico.UsuarioServico;
import escola.musica.util.GeradorSenhaAleatoria;

public class UsuarioProfessorServicoImpl implements UsuarioProfessorServico{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UsuarioServico usuarioServico;
	
	@Override
	public void salvar(UsuarioProfessor usuarioProfessor) {
		usuarioProfessor.setLogin(usuarioProfessor.getEmail());
		Usuario usuarioSalvo = usuarioServico.obterUsuarioPeloLogin(usuarioProfessor.getLogin());
		if(usuarioSalvo != null && !usuarioSalvo.getId().equals(usuarioProfessor.getId()))
			//usuarioSalvo != null -> Se n�o encontrou o usuario cadastrado no banco
			//!usuarioSalvo.getId().equals(usuarioProfessor.getId()) -> se o usuario n�o � igual ao que esta tentado salvar
		{
			//ao escrever LoginRepetidoException, vai dar um erro. Deve-se criar uma nova classe LoginRepetidoException.java
			//Onde ser�o definidos m�todos construtores
			throw new LoginRepetidoException("J� existe um usu�rio cadastrado com este e-mail!");
		}
		
		if(usuarioProfessor.getId() == null)
		{
			String senhaGerada = GeradorSenhaAleatoria.gerarSenhaAleatoria(6);
			//TODO -> Para enviar senha por email
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioProfessor> listarTodos() {
		return entityManager.createQuery("from UsuarioProfessor").getResultList();
	}

}
