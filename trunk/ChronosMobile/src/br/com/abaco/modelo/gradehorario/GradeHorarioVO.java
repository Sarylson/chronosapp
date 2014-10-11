package br.com.abaco.modelo.gradehorario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.abaco.modelo.unidadenegocio.UnidadeNegocioVO;

public class GradeHorarioVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private UnidadeNegocioVO unidadeNegocio;

	private Date dataGeracaoGrade;

	private Date dataGradeHorario;

	private String descricaoServicosGerados;

	private String diasGerados;

	private BigDecimal cancelado;

	private BigDecimal situacao;

	private BigDecimal tipoConfiguracao;

	private BigDecimal tipoGradeHorario;

	private Date horaFimMatutino;

	private Date horaFimVespertino;

	private Date horaInicioMatutino;

	private Date horaInicioVespertino;

	private String motivoCancelamento;

	private BigDecimal quantidadeAtendente;

	private BigDecimal quantidadeServico;

	private BigDecimal tempoGeracaoSenhaUsuarioDiferenciado;

	private String dataGradeHorarioFormatada;
	
	public String getDataGradeHorarioFormatada() {
		
		if( dataGradeHorario != null ){
			return new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","BR")).format(dataGradeHorario);
		}
		return dataGradeHorarioFormatada;
	}

	
	public void setDataGradeHorarioFormatada( String dataGradeHorarioFormatada ) {
		this.dataGradeHorarioFormatada = dataGradeHorarioFormatada;
	}
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		GradeHorarioVO other = (GradeHorarioVO) obj;
		if ( cancelado == null ) {
			if ( other.cancelado != null )
				return false;
		} else if ( !cancelado.equals( other.cancelado ) )
			return false;
		if ( dataGeracaoGrade == null ) {
			if ( other.dataGeracaoGrade != null )
				return false;
		} else if ( !dataGeracaoGrade.equals( other.dataGeracaoGrade ) )
			return false;
		if ( dataGradeHorario == null ) {
			if ( other.dataGradeHorario != null )
				return false;
		} else if ( !dataGradeHorario.equals( other.dataGradeHorario ) )
			return false;
		if ( descricaoServicosGerados == null ) {
			if ( other.descricaoServicosGerados != null )
				return false;
		} else if ( !descricaoServicosGerados.equals( other.descricaoServicosGerados ) )
			return false;
		if ( diasGerados == null ) {
			if ( other.diasGerados != null )
				return false;
		} else if ( !diasGerados.equals( other.diasGerados ) )
			return false;
		if ( horaFimMatutino == null ) {
			if ( other.horaFimMatutino != null )
				return false;
		} else if ( !horaFimMatutino.equals( other.horaFimMatutino ) )
			return false;
		if ( horaFimVespertino == null ) {
			if ( other.horaFimVespertino != null )
				return false;
		} else if ( !horaFimVespertino.equals( other.horaFimVespertino ) )
			return false;
		if ( horaInicioMatutino == null ) {
			if ( other.horaInicioMatutino != null )
				return false;
		} else if ( !horaInicioMatutino.equals( other.horaInicioMatutino ) )
			return false;
		if ( horaInicioVespertino == null ) {
			if ( other.horaInicioVespertino != null )
				return false;
		} else if ( !horaInicioVespertino.equals( other.horaInicioVespertino ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( motivoCancelamento == null ) {
			if ( other.motivoCancelamento != null )
				return false;
		} else if ( !motivoCancelamento.equals( other.motivoCancelamento ) )
			return false;
		if ( quantidadeAtendente == null ) {
			if ( other.quantidadeAtendente != null )
				return false;
		} else if ( !quantidadeAtendente.equals( other.quantidadeAtendente ) )
			return false;
		if ( quantidadeServico == null ) {
			if ( other.quantidadeServico != null )
				return false;
		} else if ( !quantidadeServico.equals( other.quantidadeServico ) )
			return false;
		if ( situacao == null ) {
			if ( other.situacao != null )
				return false;
		} else if ( !situacao.equals( other.situacao ) )
			return false;
		if ( tempoGeracaoSenhaUsuarioDiferenciado == null ) {
			if ( other.tempoGeracaoSenhaUsuarioDiferenciado != null )
				return false;
		} else if ( !tempoGeracaoSenhaUsuarioDiferenciado.equals( other.tempoGeracaoSenhaUsuarioDiferenciado ) )
			return false;
		if ( tipoConfiguracao == null ) {
			if ( other.tipoConfiguracao != null )
				return false;
		} else if ( !tipoConfiguracao.equals( other.tipoConfiguracao ) )
			return false;
		if ( tipoGradeHorario == null ) {
			if ( other.tipoGradeHorario != null )
				return false;
		} else if ( !tipoGradeHorario.equals( other.tipoGradeHorario ) )
			return false;
		if ( unidadeNegocio == null ) {
			if ( other.unidadeNegocio != null )
				return false;
		} else if ( !unidadeNegocio.equals( other.unidadeNegocio ) )
			return false;
		return true;
	}

	public BigDecimal getCancelado() {
		return cancelado;
	}

	public Date getDataGeracaoGrade() {
		return dataGeracaoGrade;
	}

	public Date getDataGradeHorario() {
		return dataGradeHorario;
	}

	public String getDescricaoServicosGerados() {
		return descricaoServicosGerados;
	}

	public String getDiasGerados() {
		return diasGerados;
	}

	public Date getHoraFimMatutino() {
		return horaFimMatutino;
	}

	public Date getHoraFimVespertino() {
		return horaFimVespertino;
	}

	public Date getHoraInicioMatutino() {
		return horaInicioMatutino;
	}

	public Date getHoraInicioVespertino() {
		return horaInicioVespertino;
	}

	public Long getId() {
		return id;
	}

	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public BigDecimal getQuantidadeAtendente() {
		return quantidadeAtendente;
	}

	public BigDecimal getQuantidadeServico() {
		return quantidadeServico;
	}

	public BigDecimal getSituacao() {
		return situacao;
	}

	public BigDecimal getTempoGeracaoSenhaUsuarioDiferenciado() {
		return tempoGeracaoSenhaUsuarioDiferenciado;
	}

	public BigDecimal getTipoConfiguracao() {
		return tipoConfiguracao;
	}

	public BigDecimal getTipoGradeHorario() {
		return tipoGradeHorario;
	}

	public UnidadeNegocioVO getUnidadeNegocio() {
		return unidadeNegocio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( cancelado == null ) ? 0 : cancelado.hashCode() );
		result = prime * result + ( ( dataGeracaoGrade == null ) ? 0 : dataGeracaoGrade.hashCode() );
		result = prime * result + ( ( dataGradeHorario == null ) ? 0 : dataGradeHorario.hashCode() );
		result = prime * result + ( ( descricaoServicosGerados == null ) ? 0 : descricaoServicosGerados.hashCode() );
		result = prime * result + ( ( diasGerados == null ) ? 0 : diasGerados.hashCode() );
		result = prime * result + ( ( horaFimMatutino == null ) ? 0 : horaFimMatutino.hashCode() );
		result = prime * result + ( ( horaFimVespertino == null ) ? 0 : horaFimVespertino.hashCode() );
		result = prime * result + ( ( horaInicioMatutino == null ) ? 0 : horaInicioMatutino.hashCode() );
		result = prime * result + ( ( horaInicioVespertino == null ) ? 0 : horaInicioVespertino.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( motivoCancelamento == null ) ? 0 : motivoCancelamento.hashCode() );
		result = prime * result + ( ( quantidadeAtendente == null ) ? 0 : quantidadeAtendente.hashCode() );
		result = prime * result + ( ( quantidadeServico == null ) ? 0 : quantidadeServico.hashCode() );
		result = prime * result + ( ( situacao == null ) ? 0 : situacao.hashCode() );
		result = prime * result + ( ( tempoGeracaoSenhaUsuarioDiferenciado == null ) ? 0 : tempoGeracaoSenhaUsuarioDiferenciado.hashCode() );
		result = prime * result + ( ( tipoConfiguracao == null ) ? 0 : tipoConfiguracao.hashCode() );
		result = prime * result + ( ( tipoGradeHorario == null ) ? 0 : tipoGradeHorario.hashCode() );
		result = prime * result + ( ( unidadeNegocio == null ) ? 0 : unidadeNegocio.hashCode() );
		return result;
	}

	public void setCancelado( BigDecimal cancelado ) {
		this.cancelado = cancelado;
	}

	public void setDataGeracaoGrade( Date dataGeracaoGrade ) {
		this.dataGeracaoGrade = dataGeracaoGrade;
	}

	public void setDataGradeHorario( Date dataGradeHorario ) {
		this.dataGradeHorario = dataGradeHorario;
	}

	public void setDescricaoServicosGerados( String descricaoServicosGerados ) {
		this.descricaoServicosGerados = descricaoServicosGerados;
	}

	public void setDiasGerados( String diasGerados ) {
		this.diasGerados = diasGerados;
	}

	public void setHoraFimMatutino( Date horaFimMatutino ) {
		this.horaFimMatutino = horaFimMatutino;
	}

	public void setHoraFimVespertino( Date horaFimVespertino ) {
		this.horaFimVespertino = horaFimVespertino;
	}

	public void setHoraInicioMatutino( Date horaInicioMatutino ) {
		this.horaInicioMatutino = horaInicioMatutino;
	}

	public void setHoraInicioVespertino( Date horaInicioVespertino ) {
		this.horaInicioVespertino = horaInicioVespertino;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setMotivoCancelamento( String motivoCancelamento ) {
		this.motivoCancelamento = motivoCancelamento;
	}

	public void setQuantidadeAtendente( BigDecimal quantidadeAtendente ) {
		this.quantidadeAtendente = quantidadeAtendente;
	}

	public void setQuantidadeServico( BigDecimal quantidadeServico ) {
		this.quantidadeServico = quantidadeServico;
	}

	public void setSituacao( BigDecimal situacao ) {
		this.situacao = situacao;
	}

	public void setTempoGeracaoSenhaUsuarioDiferenciado( BigDecimal tempoGeracaoSenhaUsuarioDiferenciado ) {
		this.tempoGeracaoSenhaUsuarioDiferenciado = tempoGeracaoSenhaUsuarioDiferenciado;
	}

	public void setTipoConfiguracao( BigDecimal tipoConfiguracao ) {
		this.tipoConfiguracao = tipoConfiguracao;
	}

	public void setTipoGradeHorario( BigDecimal tipoGradeHorario ) {
		this.tipoGradeHorario = tipoGradeHorario;
	}

	public void setUnidadeNegocio( UnidadeNegocioVO unidadeNegocio ) {
		this.unidadeNegocio = unidadeNegocio;
	}

}
