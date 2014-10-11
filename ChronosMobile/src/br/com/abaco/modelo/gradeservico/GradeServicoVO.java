package br.com.abaco.modelo.gradeservico;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.abaco.modelo.gradehorario.GradeHorarioVO;
import br.com.abaco.modelo.servico.ServicoVO;

public class GradeServicoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private ServicoVO servico;

	private GradeHorarioVO gradeHorario;

	private BigDecimal quantidadeAtendente;

	private BigDecimal tempoGeracaoSenha;

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		GradeServicoVO other = (GradeServicoVO) obj;
		if ( gradeHorario == null ) {
			if ( other.gradeHorario != null )
				return false;
		} else if ( !gradeHorario.equals( other.gradeHorario ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( quantidadeAtendente == null ) {
			if ( other.quantidadeAtendente != null )
				return false;
		} else if ( !quantidadeAtendente.equals( other.quantidadeAtendente ) )
			return false;
		if ( servico == null ) {
			if ( other.servico != null )
				return false;
		} else if ( !servico.equals( other.servico ) )
			return false;
		if ( tempoGeracaoSenha == null ) {
			if ( other.tempoGeracaoSenha != null )
				return false;
		} else if ( !tempoGeracaoSenha.equals( other.tempoGeracaoSenha ) )
			return false;
		return true;
	}

	public GradeHorarioVO getGradeHorario() {
		if( gradeHorario == null){
			new GradeHorarioVO();
		}
		return gradeHorario;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getQuantidadeAtendente() {
		return quantidadeAtendente;
	}

	public ServicoVO getServico() {
		return servico;
	}

	public BigDecimal getTempoGeracaoSenha() {
		return tempoGeracaoSenha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( gradeHorario == null ) ? 0 : gradeHorario.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( quantidadeAtendente == null ) ? 0 : quantidadeAtendente.hashCode() );
		result = prime * result + ( ( servico == null ) ? 0 : servico.hashCode() );
		result = prime * result + ( ( tempoGeracaoSenha == null ) ? 0 : tempoGeracaoSenha.hashCode() );
		return result;
	}

	public void setGradeHorario( GradeHorarioVO gradeHorario ) {
		this.gradeHorario = gradeHorario;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setQuantidadeAtendente( BigDecimal quantidadeAtendente ) {
		this.quantidadeAtendente = quantidadeAtendente;
	}

	public void setServico( ServicoVO servico ) {
		this.servico = servico;
	}

	public void setTempoGeracaoSenha( BigDecimal tempoGeracaoSenha ) {
		this.tempoGeracaoSenha = tempoGeracaoSenha;
	}

}
