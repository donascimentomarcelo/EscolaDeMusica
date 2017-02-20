package escola.musica.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import escola.musica.dao.CursoDAO;
import escola.musica.modelo.Curso;
import escola.musica.modelo.TipoCurso;
import escola.musica.servico.CursoServico;
import escola.musica.util.Mensagem;

@Controller("cursoBean")
@Scope("session")
public class CursoBean implements Serializable {

	private static final long serialVersionUID = -862660658464075437L;
	private Curso curso;
	//List do java util
	private List<TipoCurso> tipos;
	private List<Curso> cursos = new ArrayList<Curso>();
	private List<Curso> cursosAccordion = new ArrayList<Curso>();
	//esse método é chamado no pretty-config. Invocado após a pagina ser carregada
	//como se fossem carregado o controller na rota do angular.
	private Curso cursoExclusao;
	//esse atributo cursoExclusao serve para colocar o valor de curso.
	
	private List<Curso> cursosFiltrados;
	//Lista de cursos filtrados
	
	@Autowired
	private CursoServico cursoServico;
	
	public void iniciarBean()
	{
		cursos = cursoServico.listarTodos();
		cursosAccordion = cursoServico.listarCursoAccordion();
		tipos = Arrays.asList(TipoCurso.values());
		
	}
	
	public void novoCurso()
	{
		curso = new Curso();
	}
	
	public void voltar()
	{
		curso = null;
	}
	
	//String pq vai mudar de página
	public void salvar() throws InterruptedException
	{
		Thread.sleep(2000);
		cursoServico.salvar(curso);
		cursos = cursoServico.listarTodos();
		curso = null;
		Mensagem.mensagemSucesso("Curso salvo com sucesso!");
		
		// *** SEM SPRING
		
		/*new GenericDao<Curso>(Curso.class).salvar(curso);
		//Chama a Classe dao passando o valor para salvar
		//***new CursoDAO().salvar(curso);****
		//vai na classe DAO e executa o metodo listarTodos
		cursos = new GenericDao<Curso>(Curso.class).listarTodos();
		//***cursos = new CursoDAO().listarTodos();***
		//se o curso for != null, eu exibo o formulario
		//se o curso for ==null, eu exibo a listagem
		//Ao setar curso como nulo, eu escondo o formulario e exibo a lista.
		curso = null;
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Curso salvo com sucesso!"));
		
		//return "curso_lista?faces-redirect=true";
		 */
	}
	
	public void editar(Curso curso)
	{
		//Nessa linha eu digo que o this.curso é igual ao curso q está vindo do formulario
		//Eu passo curso como parâmetro
		this.curso = curso;
		
		//return "curso_formulario?faces-redirect=true";
	}
	
	public void prepararExclusao(Curso curso)
	{
		//Serve para que ao clicar em excluir, o confirm pegue o valor o curso, 
		//pois está fora do datatable, e sette em curso.
		//Ao invocar excluir, o método já entende que n o curso é o msm passado 
		//nessa função, e n precisa passar como parâmetro lá em excluir.
		this.cursoExclusao = curso;
	}
	//void pq vai permanecer na msm pagina
	public void excluir()
	{
		//recupero o valor de cursoExclusao do metodo prepararExclusao
		cursoServico.excluir(cursoExclusao);
		Mensagem.mensagemSucesso("Curso excluído com sucesso.");
		cursos = new CursoDAO().listarTodos();
		cursosFiltrados = null;
		
	}
	
	public String getDataAtual()
	{
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}

	public List<TipoCurso> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoCurso> tipos) {
		this.tipos = tipos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	public List<Curso> getCursosAccordion() {
		return cursosAccordion;
	}
	public void setCursosAccordion(List<Curso> cursosAccordion) {
		this.cursosAccordion = cursosAccordion;
	}

	public Curso getCursoExclusao() {
		return cursoExclusao;
	}

	public void setCursoExclusao(Curso cursoExclusao) {
		this.cursoExclusao = cursoExclusao;
	}

	public List<Curso> getCursosFiltrados() {
		return cursosFiltrados;
	}

	public void setCursosFiltrados(List<Curso> cursosFiltrados) {
		this.cursosFiltrados = cursosFiltrados;
	}
	

}
