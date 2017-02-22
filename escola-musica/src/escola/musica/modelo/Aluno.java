package escola.musica.modelo;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

//usa do javax.persistence
@Entity
@ViewScoped
public class Aluno implements Serializable{

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
	private byte[] foto;
	private StreamedContent imagem;
	
	
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
	@Email(message = "Informe um email v�lido!")
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
	//informa que endere�o � embarcado em alunos
	@Embedded
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	@Lob
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	@Transient
	//javax.persistence
	public StreamedContent getImagem() {
		if(this.getFoto() != null)
		{
			return new DefaultStreamedContent(new ByteArrayInputStream(this.getFoto()));
		}
		
		return new DefaultStreamedContent();
	}
	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
