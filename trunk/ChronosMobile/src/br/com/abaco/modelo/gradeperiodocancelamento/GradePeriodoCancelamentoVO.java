package br.com.abaco.modelo.gradeperiodocancelamento;


import java.io.Serializable;
import java.util.Date;

import br.com.abaco.modelo.gradehorario.GradeHorarioVO;
import br.com.abaco.modelo.gradeservico.GradeServicoVO;

public class GradePeriodoCancelamentoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private GradeHorarioVO gradeHorario;

	private GradeServicoVO gradeServico;

	private Date dataFinal;

	private Date dataInicial;

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		GradePeriodoCancelamentoVO other = (GradePeriodoCancelamentoVO) obj;
		if ( dataFinal == null ) {
			if ( other.dataFinal != null )
				return false;
		} else if ( !dataFinal.equals( other.dataFinal ) )
			return false;
		if ( dataInicial == null ) {
			if ( other.dataInicial != null )
				return false;
		} else if ( !dataInicial.equals( other.dataInicial ) )
			return false;
		if ( gradeHorario == null ) {
			if ( other.gradeHorario != null )
				return false;
		} else if ( !gradeHorario.equals( other.gradeHorario ) )
			return false;
		if ( gradeServico == null ) {
			if ( other.gradeServico != null )
				return false;
		} else if ( !gradeServico.equals( other.gradeServico ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		return true;
	}

	
	public Date getDataFinal() {
		return dataFinal;
	}

	
	public Date getDataInicial() {
		return dataInicial;
	}

	
	public GradeHorarioVO getGradeHorario() {
		return gradeHorario;
	}

	
	public GradeServicoVO getGradeServico() {
		return gradeServico;
	}

	
	public Long getId() {
		return id;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( dataFinal == null ) ? 0 : dataFinal.hashCode() );
		result = prime * result + ( ( dataInicial == null ) ? 0 : dataInicial.hashCode() );
		result = prime * result + ( ( gradeHorario == null ) ? 0 : gradeHorario.hashCode() );
		result = prime * result + ( ( gradeServico == null ) ? 0 : gradeServico.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		return result;
	}

	
	public void setDataFinal( Date dataFinal ) {
		this.dataFinal = dataFinal;
	}

	
	public void setDataInicial( Date dataInicial ) {
		this.dataInicial = dataInicial;
	}

	public void setGradeHorario( GradeHorarioVO gradeHorario ) {
		this.gradeHorario = gradeHorario;
	}

	public void setGradeServico( GradeServicoVO gradeServico ) {
		this.gradeServico = gradeServico;
	}

	public void setId( Long id ) {
		this.id = id;
	}

}
