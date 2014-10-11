package br.com.abaco.modelo.endereco;



import java.io.Serializable;
import java.math.BigDecimal;

import br.com.abaco.modelo.municipio.MunicipioVO;

public class EnderecoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private MunicipioVO municipio;

	private String bairro;

	private String cep;

	private String complemento;

	private String logradouro;

	private String numero;

	private BigDecimal tipoLogradouro;

	private BigDecimal celular;

	private BigDecimal telefone;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoVO other = (EnderecoVO) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (municipio == null) {
			if (other.municipio != null)
				return false;
		} else if (!municipio.equals(other.municipio))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (tipoLogradouro == null) {
			if (other.tipoLogradouro != null)
				return false;
		} else if (!tipoLogradouro.equals(other.tipoLogradouro))
			return false;
		return true;
	}

	public String getBairro() {
		return bairro;
	}

	public BigDecimal getCelular() {
		return celular;
	}

	public String getCep() {
		return cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public Long getId() {
		return id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public MunicipioVO getMunicipio() {
		return municipio;
	}

	public String getNumero() {
		return numero;
	}

	public BigDecimal getTelefone() {
		return telefone;
	}

	public BigDecimal getTipoLogradouro() {
		return tipoLogradouro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result
				+ ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result
				+ ((municipio == null) ? 0 : municipio.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result
				+ ((tipoLogradouro == null) ? 0 : tipoLogradouro.hashCode());
		return result;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCelular(BigDecimal celular) {
		this.celular = celular;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setMunicipio(MunicipioVO municipio) {
		this.municipio = municipio;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setTelefone(BigDecimal telefone) {
		this.telefone = telefone;
	}

	public void setTipoLogradouro(BigDecimal tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	
	

}
