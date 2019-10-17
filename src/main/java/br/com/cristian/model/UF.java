package br.com.cristian.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UF {

	private String sigla;
	private String nomeUF;
	
	public UF() {
		// TODO Auto-generated constructor stub
	}
	
	public UF(String sigla, String nomeUF) {
		super();
		this.sigla = sigla;
		this.nomeUF = nomeUF;
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
	
	
}
