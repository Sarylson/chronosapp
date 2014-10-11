package br.com.abaco.modelo.rest.usuario;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.abaco.modelo.rest.usuario.usuarioexterno.UsuarioExternoVO;

/**
 * The persistent class for the SWATB01_USUARIO database table.
 */
public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String codigoValidacaoCadTemporario;

	private Date dataCadastro;

	private String descricaoDica;

	private String email;

	private String login;

	private String senha;

	private BigDecimal confirmacao;

	private BigDecimal situacao;

	private BigDecimal soliticacaoAlterarSenha;

	private BigDecimal tipoUsuario;

	private BigDecimal matricula;

	private String senhaProvisoria;

	private UsuarioExternoVO usuarioExterno;

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		UsuarioVO other = (UsuarioVO) obj;
		if ( codigoValidacaoCadTemporario == null ) {
			if ( other.codigoValidacaoCadTemporario != null )
				return false;
		} else if ( !codigoValidacaoCadTemporario.equals( other.codigoValidacaoCadTemporario ) )
			return false;
		if ( confirmacao == null ) {
			if ( other.confirmacao != null )
				return false;
		} else if ( !confirmacao.equals( other.confirmacao ) )
			return false;
		if ( dataCadastro == null ) {
			if ( other.dataCadastro != null )
				return false;
		} else if ( !dataCadastro.equals( other.dataCadastro ) )
			return false;
		if ( descricaoDica == null ) {
			if ( other.descricaoDica != null )
				return false;
		} else if ( !descricaoDica.equals( other.descricaoDica ) )
			return false;
		if ( email == null ) {
			if ( other.email != null )
				return false;
		} else if ( !email.equals( other.email ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( login == null ) {
			if ( other.login != null )
				return false;
		} else if ( !login.equals( other.login ) )
			return false;
		if ( matricula == null ) {
			if ( other.matricula != null )
				return false;
		} else if ( !matricula.equals( other.matricula ) )
			return false;
		if ( senha == null ) {
			if ( other.senha != null )
				return false;
		} else if ( !senha.equals( other.senha ) )
			return false;
		if ( senhaProvisoria == null ) {
			if ( other.senhaProvisoria != null )
				return false;
		} else if ( !senhaProvisoria.equals( other.senhaProvisoria ) )
			return false;
		if ( situacao == null ) {
			if ( other.situacao != null )
				return false;
		} else if ( !situacao.equals( other.situacao ) )
			return false;
		if ( soliticacaoAlterarSenha == null ) {
			if ( other.soliticacaoAlterarSenha != null )
				return false;
		} else if ( !soliticacaoAlterarSenha.equals( other.soliticacaoAlterarSenha ) )
			return false;
		if ( tipoUsuario == null ) {
			if ( other.tipoUsuario != null )
				return false;
		} else if ( !tipoUsuario.equals( other.tipoUsuario ) )
			return false;
		if ( usuarioExterno == null ) {
			if ( other.usuarioExterno != null )
				return false;
		} else if ( !usuarioExterno.equals( other.usuarioExterno ) )
			return false;
		return true;
	}

	public String getCodigoValidacaoCadTemporario() {
		return codigoValidacaoCadTemporario;
	}

	public BigDecimal getConfirmacao() {
		return confirmacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public String getDescricaoDica() {
		return descricaoDica;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public BigDecimal getMatricula() {
		return matricula;
	}

	public String getSenha() {
		return senha;
	}

	public String getSenhaProvisoria() {
		return senhaProvisoria;
	}

	public BigDecimal getSituacao() {
		return situacao;
	}

	public BigDecimal getSoliticacaoAlterarSenha() {
		return soliticacaoAlterarSenha;
	}

	public BigDecimal getTipoUsuario() {
		return tipoUsuario;
	}

	public UsuarioExternoVO getUsuarioExterno() {
		return usuarioExterno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( codigoValidacaoCadTemporario == null ) ? 0 : codigoValidacaoCadTemporario.hashCode() );
		result = prime * result + ( ( confirmacao == null ) ? 0 : confirmacao.hashCode() );
		result = prime * result + ( ( dataCadastro == null ) ? 0 : dataCadastro.hashCode() );
		result = prime * result + ( ( descricaoDica == null ) ? 0 : descricaoDica.hashCode() );
		result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( login == null ) ? 0 : login.hashCode() );
		result = prime * result + ( ( matricula == null ) ? 0 : matricula.hashCode() );
		result = prime * result + ( ( senha == null ) ? 0 : senha.hashCode() );
		result = prime * result + ( ( senhaProvisoria == null ) ? 0 : senhaProvisoria.hashCode() );
		result = prime * result + ( ( situacao == null ) ? 0 : situacao.hashCode() );
		result = prime * result + ( ( soliticacaoAlterarSenha == null ) ? 0 : soliticacaoAlterarSenha.hashCode() );
		result = prime * result + ( ( tipoUsuario == null ) ? 0 : tipoUsuario.hashCode() );
		result = prime * result + ( ( usuarioExterno == null ) ? 0 : usuarioExterno.hashCode() );
		return result;
	}

	public void setCodigoValidacaoCadTemporario( String codigoValidacaoCadTemporario ) {
		this.codigoValidacaoCadTemporario = codigoValidacaoCadTemporario;
	}

	public void setConfirmacao( BigDecimal confirmacao ) {
		this.confirmacao = confirmacao;
	}

	public void setDataCadastro( Date dataCadastro ) {
		this.dataCadastro = dataCadastro;
	}

	public void setDescricaoDica( String descricaoDica ) {
		this.descricaoDica = descricaoDica;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setLogin( String login ) {
		this.login = login;
	}

	public void setMatricula( BigDecimal matricula ) {
		this.matricula = matricula;
	}

	public void setSenha( String senha ) {
		this.senha = senha;
	}

	public void setSenhaProvisoria( String senhaProvisoria ) {
		this.senhaProvisoria = senhaProvisoria;
	}

	public void setSituacao( BigDecimal situacao ) {
		this.situacao = situacao;
	}

	public void setSoliticacaoAlterarSenha( BigDecimal soliticacaoAlterarSenha ) {
		this.soliticacaoAlterarSenha = soliticacaoAlterarSenha;
	}

	public void setTipoUsuario( BigDecimal tipoUsuario ) {
		this.tipoUsuario = tipoUsuario;
	}

	public void setUsuarioExterno( UsuarioExternoVO usuarioExterno ) {
		this.usuarioExterno = usuarioExterno;
	}

}