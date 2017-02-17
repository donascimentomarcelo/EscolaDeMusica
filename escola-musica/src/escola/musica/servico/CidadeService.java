package escola.musica.servico;

import java.util.List;

import escola.musica.modelo.Cidade;
import escola.musica.modelo.Estado;

public interface CidadeService {
	
	public void salvar(Cidade cidade);
	public List<Cidade> listarTodos();
	public void excluir(Cidade cidade);
	public List<Cidade> obterCidadeDoEstado(Estado estado);
}
