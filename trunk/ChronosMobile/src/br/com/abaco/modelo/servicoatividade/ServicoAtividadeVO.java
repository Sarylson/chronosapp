package br.com.abaco.modelo.servicoatividade;



import java.io.Serializable;

import br.com.abaco.modelo.atividade.AtividadeVO;
import br.com.abaco.modelo.servico.ServicoVO;

public class ServicoAtividadeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private ServicoVO servico;

	private AtividadeVO atividade;

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		ServicoAtividadeVO other = (ServicoAtividadeVO) obj;
		if ( atividade == null ) {
			if ( other.atividade != null )
				return false;
		} else if ( !atividade.equals( other.atividade ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( servico == null ) {
			if ( other.servico != null )
				return false;
		} else if ( !servico.equals( other.servico ) )
			return false;
		return true;
	}

	public AtividadeVO getAtividade() {
		return atividade;
	}

	public Long getId() {
		return id;
	}

	public ServicoVO getServico() {
		return servico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( atividade == null ) ? 0 : atividade.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( servico == null ) ? 0 : servico.hashCode() );
		return result;
	}

	public void setAtividade( AtividadeVO atividade ) {
		this.atividade = atividade;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setServico( ServicoVO servico ) {
		this.servico = servico;
	}

}
