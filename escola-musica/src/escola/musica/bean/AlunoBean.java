package escola.musica.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import escola.musica.dao.CidadeDao;
import escola.musica.dao.GenericDao;
import escola.musica.modelo.Aluno;
import escola.musica.modelo.Cidade;
import escola.musica.modelo.Estado;

@ManagedBean
//ManagedBean indica que qnd escrever alunoBean na view, vai referenciar a esta classe
@SessionScoped
public class AlunoBean implements Serializable{

	private static final long serialVersionUID = -1025252140353914359L;
	
	private Aluno aluno;
	private List<Aluno> alunos;
	private List<Estado>estados;
	private Integer idCidade;  
	
	public void iniciarBean()
	{
		alunos = new GenericDao<Aluno>(Aluno.class).listarTodos();
		estados = Arrays.asList(Estado.values());
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
		//aluno.getEndereco().setCidade(new GenericDao<Cidade>(Cidade.class).obterPorId(idCidade));
		new GenericDao<Aluno>(Aluno.class).salvar(aluno);
		aluno = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aluno cadastrado com sucesso!"));
		alunos = new GenericDao<Aluno>(Aluno.class).listarTodos();
		
	}
	public void editar(Aluno aluno)
	{
		this.aluno = aluno;
		//idCidade = aluno.getEndereco().getCidade().getId();
	}
	public List<Cidade> getCidadesDoEstado()
	{
		return CidadeDao.obterCidadeDoEstado(aluno.getEndereco().getCidade().getEstado());
	}
	
	public void enviarFoto(FileUploadEvent event)
	{
		try {
			byte[] foto = IOUtils.toByteArray(event.getFile().getInputstream());
			aluno.setFoto(foto);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}
	

}
