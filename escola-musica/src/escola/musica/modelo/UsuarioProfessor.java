package escola.musica.modelo;

import javax.persistence.Entity;

@Entity
public class UsuarioProfessor extends Usuario{

	private static final long serialVersionUID = -5479955455017618076L;
	
	private String grauInstrucao;

	public String getGrauInstrucao() {
		return grauInstrucao;
	}

	public void setGrauInstrucao(String grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}

}
