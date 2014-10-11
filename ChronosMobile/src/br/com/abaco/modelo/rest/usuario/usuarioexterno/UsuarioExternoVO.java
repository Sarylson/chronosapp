package br.com.abaco.modelo.rest.usuario.usuarioexterno;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.abaco.modelo.endereco.EnderecoVO;

/**
 * The persistent class for the SWATB02_USUARIO_EXTERNO database table.
 */
public class UsuarioExternoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private EnderecoVO endereco;

	private Date dataNascimento;

	private String nome;

	private String numeroDocumento;

	private String numeroRG;

	private BigDecimal tipoDocumento;

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		UsuarioExternoVO other = (UsuarioExternoVO) obj;
		if ( dataNascimento == null ) {
			if ( other.dataNascimento != null )
				return false;
		} else if ( !dataNascimento.equals( other.dataNascimento ) )
			return false;
		if ( endereco == null ) {
			if ( other.endereco != null )
				return false;
		} else if ( !endereco.equals( other.endereco ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( nome == null ) {
			if ( other.nome != null )
				return false;
		} else if ( !nome.equals( other.nome ) )
			return false;
		if ( numeroDocumento == null ) {
			if ( other.numeroDocumento != null )
				return false;
		} else if ( !numeroDocumento.equals( other.numeroDocumento ) )
			return false;
		if ( numeroRG == null ) {
			if ( other.numeroRG != null )
				return false;
		} else if ( !numeroRG.equals( other.numeroRG ) )
			return false;
		if ( tipoDocumento == null ) {
			if ( other.tipoDocumento != null )
				return false;
		} else if ( !tipoDocumento.equals( other.tipoDocumento ) )
			return false;
		return true;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public EnderecoVO getEndereco() {
		return endereco;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public String getNumeroRG() {
		return numeroRG;
	}

	public BigDecimal getTipoDocumento() {
		return tipoDocumento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( dataNascimento == null ) ? 0 : dataNascimento.hashCode() );
		result = prime * result + ( ( endereco == null ) ? 0 : endereco.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( numeroDocumento == null ) ? 0 : numeroDocumento.hashCode() );
		result = prime * result + ( ( numeroRG == null ) ? 0 : numeroRG.hashCode() );
		result = prime * result + ( ( tipoDocumento == null ) ? 0 : tipoDocumento.hashCode() );
		return result;
	}

	public void setDataNascimento( Date dataNascimento ) {
		this.dataNascimento = dataNascimento;
	}

	public void setEndereco( EnderecoVO endereco ) {
		this.endereco = endereco;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public void setNumeroDocumento( String numeroDocumento ) {
		this.numeroDocumento = numeroDocumento;
	}

	public void setNumeroRG( String numeroRG ) {
		this.numeroRG = numeroRG;
	}

	public void setTipoDocumento( BigDecimal tipoDocumento ) {
		this.tipoDocumento = tipoDocumento;
	}

}