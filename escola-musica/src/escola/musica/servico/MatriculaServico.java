package escola.musica.servico;

import java.util.List;

import escola.musica.modelo.Matricula;

public interface MatriculaServico {
	
	public List<Matricula> listarTodos();
	public void salvar(Matricula matricula);
	public List<Matricula> listarTodosAtivas();
	public Matricula obterPorId(Integer id);

	
}
