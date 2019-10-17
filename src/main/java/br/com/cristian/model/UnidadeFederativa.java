package br.com.cristian.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(schema="public", name="uf")
@SequenceGenerator(schema="public", name="uf_id_seq", sequenceName = "\"public.uf_id_seq\"", initialValue = 1, allocationSize = 1)
public class UnidadeFederativa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uf_id_seq")
	@Column(name="id")
	private Long id;
	
	@Column(name="sigla")
	private String sigla;
	
	@Column(name="nome_uf")
	private String nomeUF;
	
	@Column(name="data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	public UnidadeFederativa() {
	}
	
	public UnidadeFederativa(Long id, String sigla, String nomeUF) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nomeUF = nomeUF;
	}

	public UnidadeFederativa(String sigla, String nomeUF) {
		super();
		this.sigla = sigla;
		this.nomeUF = nomeUF;
		Calendar dataCad = Calendar.getInstance();
		this.dataCadastro = dataCad.getTime();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNomeUF() {
		return nomeUF;
	}

	public void setNomeUF(String nomeUF) {
		this.nomeUF = nomeUF;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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
		UnidadeFederativa other = (UnidadeFederativa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean validate() throws Exception{
		if(this.sigla.isEmpty() || this.sigla == null) {
			throw new Exception("O campo sigla deve ser preenchido corretamente.");
		}
		if(this.nomeUF.isEmpty() || this.nomeUF == null) {
			throw new Exception("O campo descri��o deve ser preenchido corretamente.");
		}
		return true;
	}
	
	
	
}
