package br.com.abaco.modelo.unidadeservicoatividade;

import java.io.Serializable;

import br.com.abaco.modelo.servicoatividade.ServicoAtividadeVO;
import br.com.abaco.modelo.unidadenegocio.UnidadeNegocioVO;

public class UnidadeServicoAtividadeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private ServicoAtividadeVO servicoAtividade;

	private UnidadeNegocioVO unidadeNegocio;

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		UnidadeServicoAtividadeVO other = (UnidadeServicoAtividadeVO) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( servicoAtividade == null ) {
			if ( other.servicoAtividade != null )
				return false;
		} else if ( !servicoAtividade.equals( other.servicoAtividade ) )
			return false;
		if ( unidadeNegocio == null ) {
			if ( other.unidadeNegocio != null )
				return false;
		} else if ( !unidadeNegocio.equals( other.unidadeNegocio ) )
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public ServicoAtividadeVO getServicoAtividade() {
		if ( servicoAtividade == null ) {
			return new ServicoAtividadeVO();
		}
		return servicoAtividade;
	}

	public UnidadeNegocioVO getUnidadeNegocio() {
		return unidadeNegocio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( servicoAtividade == null ) ? 0 : servicoAtividade.hashCode() );
		result = prime * result + ( ( unidadeNegocio == null ) ? 0 : unidadeNegocio.hashCode() );
		return result;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setServicoAtividade( ServicoAtividadeVO servicoAtividade ) {
		this.servicoAtividade = servicoAtividade;
	}

	public void setUnidadeNegocio( UnidadeNegocioVO unidadeNegocio ) {
		this.unidadeNegocio = unidadeNegocio;
	}

}