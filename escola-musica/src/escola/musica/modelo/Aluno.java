package escola.musica.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

//usa do javax.persistence
@Entity
@ViewScoped
public class Aluno implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4675028755655124230L;
	private Integer id;
	private String nome;
	//usa o date do java.util
	private Date dataNascimento;
	private String cpf;
	private String email;
	private boolean ativo;
	private String telefone;
	private Endereco endereco = new Endereco();
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@NotEmpty(message = "Preencha o campo nome!")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Temporal(TemporalType.DATE)
	//usa Column qnd for nome composto e no banco for diferente
	@Column(name = "data_nascimento")
	@NotNull(message = "Informe a data de nascimento!")
	@Past(message = "Informe uma data menor ou igual a atua!")
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	@Email(message = "Informe um email válido!")
	@NotEmpty(message = "Preencha o campo email!")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	//informa que endereço é embarcado em alunos
	@Embedded
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
