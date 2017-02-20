package escola.musica.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Curso implements Serializable {

	private static final long serialVersionUID = 3223617028339662203L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;
	private String descricao;
	private double duracao = 1;
	private TipoCurso tipo;
	private Date dataCriacao;
	
	@NotNull(message = "Selecione o campo data de cria��o!")
	@Past(message = "A data deve ser menor ou igual a data atual!")
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@NotEmpty(message = "Preencha o campo nome!")
	@Length(min = 2, max = 20, message = "O campo nome deve ter entre 2 e 20 caracteres!")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@NotEmpty(message = "Preencha o campo descri��o!")
	@Length(max = 255, message = "O campo descri��o 255 caracteres!")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Min(value = 1, message = "A dura��o n�o pode ser menor que 1!")
	@Max(value = 10, message = "A dura��o n�o pode ser maior que 10!")
	public double getDuracao() {
		return duracao;
	}
	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}
	@NotNull(message = "Selecione o campo tipo!")
	public TipoCurso getTipo() {
		return tipo;
	}
	public void setTipo(TipoCurso tipo) {
		this.tipo = tipo;
	}
	
	public String obterImagem()
	{
		//O m�todo poderia ser criada na classe Bean, ms como acessa diretamente os atributos de Curso
		//Ent�o � recomendado criar aqui msm
		//Este m�todo troca � por e � por a e espa�o vazio por _
		return nome.toLowerCase().replace("�", "a")
				.replace("�", "e")
				.replace(" ", "_")
				.concat(".png");
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
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
