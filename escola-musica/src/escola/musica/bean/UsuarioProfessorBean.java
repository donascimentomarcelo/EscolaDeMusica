package escola.musica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import escola.musica.exception.LoginRepetidoException;
import escola.musica.modelo.UsuarioProfessor;
import escola.musica.servico.UsuarioProfessorServico;
import escola.musica.util.Mensagem;

@Controller("usuarioProfessorBean")
@Scope("view")
//para usar o escopo de visão deve ter a classe viewScope
@ManagedBean
public class UsuarioProfessorBean implements Serializable {

	private static final long serialVersionUID = -1446484411632089350L;
	
	private UsuarioProfessor usuarioProfessor;
	private List<UsuarioProfessor>usuarioProfessores;
	
	@Autowired
	private UsuarioProfessorServico usuarioProfessorServico;
	
	public void iniciarBean()
	{
		atualizaUsuarioProfessor();
	}
	
	public void novoUsuarioProfessor()
	{
		usuarioProfessor = new UsuarioProfessor();
	}
	
	public void salvar()
	{
		try {
			usuarioProfessorServico.salvar(usuarioProfessor);
			atualizaUsuarioProfessor();
			usuarioProfessor = null;
			Mensagem.mensagemSucesso("Usuário salvo com sucesso!");
		} catch (LoginRepetidoException e) {
			Mensagem.mensagemErro(e.getMessage());
		}
	}
	
	public void editar(UsuarioProfessor usuarioProfessor)
	{
		this.usuarioProfessor = usuarioProfessor;
	}
	
	public void cancelar()
	{
		this.usuarioProfessor = null;
	}
	
	public void atualizaUsuarioProfessor()
	{
		usuarioProfessores = usuarioProfessorServico.listarTodos();
	}

	public UsuarioProfessor getUsuarioProfessor() {
		return usuarioProfessor;
	}

	public void setUsuarioProfessor(UsuarioProfessor usuarioProfessor) {
		this.usuarioProfessor = usuarioProfessor;
	}

	public List<UsuarioProfessor> getUsuarioProfessores() {
		return usuarioProfessores;
	}

	public void setUsuarioProfessores(List<UsuarioProfessor> usuarioProfessores) {
		this.usuarioProfessores = usuarioProfessores;
	}
	
}
