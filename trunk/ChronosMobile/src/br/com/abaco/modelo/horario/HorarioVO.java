package br.com.abaco.modelo.horario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.abaco.modelo.gradehorario.GradeHorarioVO;
import br.com.abaco.modelo.servico.ServicoVO;

public class HorarioVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private ServicoVO servico;

	private GradeHorarioVO gradeHorario;

	private Date dataHoraFim;

	private Date dataHoraGradeHorario;

	private Date dataHoraInicio;

	private BigDecimal situacao;
	
	private String dataHoraGradeHorarioFormatada;

	
	public String getDataHoraGradeHorarioFormatada() {
		
		if( dataHoraGradeHorario != null ){
			return new SimpleDateFormat("HH:mm", new Locale("pt","BR")).format( dataHoraGradeHorario );
		}
		return dataHoraGradeHorarioFormatada;
	}

	
	public void setDataHoraGradeHorarioFormatada( String dataHoraGradeHorarioFormatada ) {
		this.dataHoraGradeHorarioFormatada = dataHoraGradeHorarioFormatada;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		HorarioVO other = (HorarioVO) obj;
		if ( dataHoraFim == null ) {
			if ( other.dataHoraFim != null )
				return false;
		} else if ( !dataHoraFim.equals( other.dataHoraFim ) )
			return false;
		if ( dataHoraGradeHorario == null ) {
			if ( other.dataHoraGradeHorario != null )
				return false;
		} else if ( !dataHoraGradeHorario.equals( other.dataHoraGradeHorario ) )
			return false;
		if ( dataHoraInicio == null ) {
			if ( other.dataHoraInicio != null )
				return false;
		} else if ( !dataHoraInicio.equals( other.dataHoraInicio ) )
			return false;
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
		if ( servico == null ) {
			if ( other.servico != null )
				return false;
		} else if ( !servico.equals( other.servico ) )
			return false;
		if ( situacao == null ) {
			if ( other.situacao != null )
				return false;
		} else if ( !situacao.equals( other.situacao ) )
			return false;
		return true;
	}

	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	public Date getDataHoraGradeHorario() {
		return dataHoraGradeHorario;
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	public GradeHorarioVO getGradeHorario() {
		return gradeHorario;
	}

	public Long getId() {
		return id;
	}

	public ServicoVO getServico() {
		return servico;
	}

	public BigDecimal getSituacao() {
		return situacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( dataHoraFim == null ) ? 0 : dataHoraFim.hashCode() );
		result = prime * result + ( ( dataHoraGradeHorario == null ) ? 0 : dataHoraGradeHorario.hashCode() );
		result = prime * result + ( ( dataHoraInicio == null ) ? 0 : dataHoraInicio.hashCode() );
		result = prime * result + ( ( gradeHorario == null ) ? 0 : gradeHorario.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( servico == null ) ? 0 : servico.hashCode() );
		result = prime * result + ( ( situacao == null ) ? 0 : situacao.hashCode() );
		return result;
	}

	public void setDataHoraFim( Date dataHoraFim ) {
		this.dataHoraFim = dataHoraFim;
	}

	public void setDataHoraGradeHorario( Date dataHoraGradeHorario ) {
		this.dataHoraGradeHorario = dataHoraGradeHorario;
	}

	public void setDataHoraInicio( Date dataHoraInicio ) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public void setGradeHorario( GradeHorarioVO gradeHorario ) {
		this.gradeHorario = gradeHorario;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setServico( ServicoVO servico ) {
		this.servico = servico;
	}

	public void setSituacao( BigDecimal situacao ) {
		this.situacao = situacao;
	}

}
