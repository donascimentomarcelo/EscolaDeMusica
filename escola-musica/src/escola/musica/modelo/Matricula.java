package escola.musica.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@NamedQueries({
	@NamedQuery(name = "Matricula.ListarTodos", query = "from Matricula"),
	@NamedQuery(name = "Matricula.ListarTodosAtivos", query = "from Matricula where ativo = true")
})
public class Matricula implements Serializable{

	private static final long serialVersionUID = 8083358052410703771L;
	
	private Integer id;
	private String numero;
	private Date dataMatricula;
	private Aluno aluno;
	private Curso curso;
	private boolean ativo = true;
	private Date dataDesativacao;
	
	public Matricula(){}
	
	public Matricula(Integer id, Date dataMatricula, String numero, String nomeCurso, String nomeAluno){
		//ou this.id = id;
		setId(id);
		setDataMatricula(dataMatricula);
		setNumero(numero);
		setAluno(new Aluno());
		this.aluno.setNome(nomeAluno);
		setCurso(new Curso());
		this.curso.setNome(nomeCurso);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@NotEmpty(message = "Preencha o campo N�mero!")
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	@NotNull(message = "Preencha o campo data da matr�cula!")
	@Temporal(TemporalType.DATE)
	public Date getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	@NotNull(message = "Selecione um aluno!")
	@ManyToOne
	@JoinColumn(name = "id_aluno")
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	@NotNull(message = "Selecione um curso!")
	@ManyToOne
	@JoinColumn(name = "id_curso")
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Date getDataDesativacao() {
		return dataDesativacao;
	}
	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}