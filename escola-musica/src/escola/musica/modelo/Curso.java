package escola.musica.modelo;

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
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;
	private String descricao;
	private double duracao = 1;
	private TipoCurso tipo;
	private Date dataCriacao;
	
	@NotNull(message = "Selecione o campo data de criação!")
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
	@NotEmpty(message = "Preencha o campo descrição!")
	@Length(max = 254, message = "O campo descrição 254 caracteres!")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Min(value = 1, message = "A duração não pode ser menor que 1!")
	@Max(value = 10, message = "A duração não pode ser maior que 10!")
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

}
