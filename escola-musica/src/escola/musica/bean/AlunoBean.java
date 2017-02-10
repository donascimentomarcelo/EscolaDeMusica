package escola.musica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import escola.musica.dao.GenericDao;
import escola.musica.modelo.Aluno;

@ManagedBean
//ManagedBean indica que qnd escrever alunoBean na view, vai referenciar a esta classe
@SessionScoped
public class AlunoBean implements Serializable{

	private static final long serialVersionUID = -1025252140353914359L;
	
	private Aluno aluno;
	private List<Aluno> alunos;
	
	public void iniciarBean()
	{
		alunos = new GenericDao<Aluno>(Aluno.class).listarTodos();
	}
	
	public void novoAluno()
	{
		aluno = new Aluno();
	}
	
	public void voltar()
	{
		aluno = null;
	}
	
	public void salvar()
	{
		new GenericDao<Aluno>(Aluno.class).salvar(aluno);
		aluno = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aluno cadastrado com sucesso!"));
		alunos = new GenericDao<Aluno>(Aluno.class).listarTodos();
		
	}
	public void editar(Aluno aluno)
	{
		this.aluno = aluno;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
