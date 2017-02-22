package escola.musica.servico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import escola.musica.exception.LoginRepetidoException;
import escola.musica.modelo.Usuario;
import escola.musica.modelo.UsuarioProfessor;
import escola.musica.servico.UsuarioProfessorServico;
import escola.musica.servico.UsuarioServico;
import escola.musica.util.GeradorSenhaAleatoria;
@Service("usuarioProfessorServicoImpl")
@Transactional
public class UsuarioProfessorServicoImpl implements UsuarioProfessorServico{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UsuarioServico usuarioServico;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	//passwordEncoder interface que tem um risquinho
	
	@Override
	public void salvar(UsuarioProfessor usuarioProfessor) {
		usuarioProfessor.setLogin(usuarioProfessor.getEmail());
		Usuario usuarioSalvo = usuarioServico.obterUsuarioPeloLogin(usuarioProfessor.getLogin());
		if(usuarioSalvo != null && !usuarioSalvo.getId().equals(usuarioProfessor.getId()))
			//usuarioSalvo != null -> Se não encontrou o usuario cadastrado no banco
			//!usuarioSalvo.getId().equals(usuarioProfessor.getId()) -> se o usuario não é igual ao que esta tentado salvar
		{
			//ao escrever LoginRepetidoException, vai dar um erro. Deve-se criar uma nova classe LoginRepetidoException.java
			//Onde serão definidos métodos construtores
			throw new LoginRepetidoException("Já existe um usuário cadastrado com este e-mail!");
		}
		
		if(usuarioProfessor.getId() == null)
		{
			String senhaGerada = GeradorSenhaAleatoria.gerarSenhaAleatoria(6);
			
			System.out.println("Senha Gerada");
			String senhaCriptografada = passwordEncoder.encodePassword(senhaGerada, null);
			usuarioProfessor.setSenha(senhaCriptografada);
			
			//TODO - Enviar email com login e senha
		}
		
		entityManager.merge(usuarioProfessor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioProfessor> listarTodos() {
		return entityManager.createQuery("from UsuarioProfessor").getResultList();
	}

}
