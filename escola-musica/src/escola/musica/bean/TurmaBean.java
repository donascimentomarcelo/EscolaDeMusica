package escola.musica.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import escola.musica.dao.GenericDao;
import escola.musica.modelo.Aluno;
import escola.musica.modelo.Matricula;
import escola.musica.servico.MatriculaServico;

@Controller("turmaBean")
@Scope("session")
@ManagedBean
public class TurmaBean implements Serializable{

	private static final long serialVersionUID = 5759339104756147807L;
	
	private List<Matricula> matriculas;
	private List<Matricula> matriculasInseridas;
	
	@Autowired
	private MatriculaServico matriculaServico;

	public void iniciarBean()
	{
		matriculas =  matriculaServico.listarTodos();
	}

	public StreamedContent getImagemAluno()
	{
		Map<String, String> mapaParametro = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String idAluno = mapaParametro.get("id_aluno");
		//id_aluno é o id da tag f:param na view
		if(idAluno != null)
		{
			Aluno alunoBanco = new GenericDao<Aluno>(Aluno.class).obterPorId(new Integer(idAluno));
			//new Integer(idAluno) converte o id no formato string para inteiro
			return alunoBanco.getImagem();
		}
		return new DefaultStreamedContent();
	}
	
	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public MatriculaServico getMatriculaServico() {
		return matriculaServico;
	}

	public void setMatriculaServico(MatriculaServico matriculaServico) {
		this.matriculaServico = matriculaServico;
	}

	public List<Matricula> getMatriculasInseridas() {
		return matriculasInseridas;
	}

	public void setMatriculasInseridas(List<Matricula> matriculasInseridas) {
		this.matriculasInseridas = matriculasInseridas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
