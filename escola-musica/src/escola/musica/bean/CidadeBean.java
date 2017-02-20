package escola.musica.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import escola.musica.modelo.Cidade;
import escola.musica.modelo.Estado;
import escola.musica.servico.CidadeService;
import escola.musica.util.Mensagem;

@Controller("cidadeBean")
@Scope("session")
@ManagedBean
public class CidadeBean implements Serializable{

	private static final long serialVersionUID = -8077768006424832717L;
	
	private Cidade cidade = new Cidade();
	private List<Cidade> cidades;
	private Cidade cidadeSelecionada;
	
	@Autowired
	private CidadeService cidadeService;
	
	public void iniciarBean()
	{
		consultar();
		//chama o método consultar
	}
	
	public void salvar()
	{
		cidadeService.salvar(cidade);
		Mensagem.mensagemSucesso("Cidade cadastrada com sucesso!");
		cidade = new Cidade();
		cidadeSelecionada = null;
		consultar();
		//RequestContext.getCurrentInstance().execute("PF('cadastroCidadeDialog').hide()");
	}
	
	public void onRowEdit(RowEditEvent event)
	{
		cidade = (Cidade) event.getObject();
		//RowEditEvent event vem como parametro da view
		//event.getObject(); pega o parematro e seta em cidade
		salvar();
	}
	
	public void onCellEdit(CellEditEvent event)
	{
		//pega o valor da célula da tabela para salvar
		DataTable table = (DataTable) event.getSource();
		cidade = (Cidade) table.getRowData();
		salvar();
	}
	
	public void consultar()
	{
		cidades = cidadeService.listarTodos();
	}
	
	public List<Estado> getEstados()
	{
		return Arrays.asList(Estado.values());
	}
	
	public void cancelar()
	{
		cidade = new Cidade();
		cidadeSelecionada = null;
	}
	
	public void excluir()
	{
		cidadeService.excluir(cidadeSelecionada);
		Mensagem.mensagemSucesso("Cidade excluida com sucesso!");
		cidade = new Cidade();
		cidadeSelecionada = null;
		consultar();
	}


	public Cidade getCidade() {
		return cidade;
	}


	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}


	public List<Cidade> getCidades() {
		return cidades;
	}


	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}
	
	
	
}
