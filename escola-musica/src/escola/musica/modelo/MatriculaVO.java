package escola.musica.modelo;

import java.util.Date;

public class MatriculaVO {
	
	private Integer id;
	private String numero;
	private Date data;
	private Curso nomeCurso;
	private Aluno nomeAluno;
	

	public MatriculaVO(Integer id, String numero, Date data, Curso nomeCurso,
			Aluno nomeAluno) {
		super();
		this.id = id;
		this.numero = numero;
		this.data = data;
		this.nomeCurso = nomeCurso;
		this.nomeAluno = nomeAluno;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Curso getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(Curso nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public Aluno getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(Aluno nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

}
