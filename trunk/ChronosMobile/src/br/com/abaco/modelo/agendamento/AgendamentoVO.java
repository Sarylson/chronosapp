package br.com.abaco.modelo.agendamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.abaco.modelo.gradeservico.GradeServicoVO;
import br.com.abaco.modelo.horario.HorarioVO;
import br.com.abaco.modelo.rest.usuario.UsuarioVO;
import br.com.abaco.modelo.rest.usuario.usuarioexterno.UsuarioExternoVO;
import br.com.abaco.modelo.senha.SenhaVO;
import br.com.abaco.modelo.servico.ServicoVO;
import br.com.abaco.modelo.unidadenegocio.UnidadeNegocioVO;

public class AgendamentoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codgAgendamentoSeqc;

	private ServicoVO servico;

	private UnidadeNegocioVO unidadeNegocio;

	private HorarioVO horario;

	private Date dataCadastro;

	private Date dataHoraAgendamento;

	private BigDecimal canceladoUsuarioExterno;

	private BigDecimal situacao;

	private BigDecimal usuarioDiferenciado;

	private BigDecimal matricula;

	private String numeroDocRepresentante;

	private UsuarioExternoVO usuarioExterno;
	
	private SenhaVO senha;

	
	public SenhaVO getSenha() {
		return senha;
	}

	
	public void setSenha( SenhaVO senha ) {
		this.senha = senha;
	}

	public String getDataAgendamentoFormatada() {
		if ( dataHoraAgendamento != null ) {
			return new SimpleDateFormat( "dd/MM/yyyy" , new Locale( "pt","BR" )).format( dataHoraAgendamento );
		}

		return dataAgendamentoFormatada;
	}

	public void setDataAgendamentoFormatada( String dataAgendamentoFormatada ) {
		this.dataAgendamentoFormatada = dataAgendamentoFormatada;
	}

	public String getHoraAgendamentoFormatada() {
		if( dataHoraAgendamento != null){
			return new SimpleDateFormat("HH:mm",new Locale( "pt","BR" )).format( dataHoraAgendamento );
		}
		return horaAgendamentoFormatada;
	}

	public void setHoraAgendamentoFormatada( String horaAgendamentoFormatada ) {
		this.horaAgendamentoFormatada = horaAgendamentoFormatada;
	}

	private BigDecimal tipoAgendamento;

	private BigDecimal tempoEsperaEmFila;

	private GradeServicoVO gradeServico;

	private UsuarioVO usuario;

	private String dataAgendamentoFormatada;

	private String horaAgendamentoFormatada;

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		AgendamentoVO other = (AgendamentoVO) obj;
		if ( canceladoUsuarioExterno == null ) {
			if ( other.canceladoUsuarioExterno != null )
				return false;
		} else if ( !canceladoUsuarioExterno.equals( other.canceladoUsuarioExterno ) )
			return false;
		if ( codgAgendamentoSeqc == null ) {
			if ( other.codgAgendamentoSeqc != null )
				return false;
		} else if ( !codgAgendamentoSeqc.equals( other.codgAgendamentoSeqc ) )
			return false;
		if ( dataCadastro == null ) {
			if ( other.dataCadastro != null )
				return false;
		} else if ( !dataCadastro.equals( other.dataCadastro ) )
			return false;
		if ( dataHoraAgendamento == null ) {
			if ( other.dataHoraAgendamento != null )
				return false;
		} else if ( !dataHoraAgendamento.equals( other.dataHoraAgendamento ) )
			return false;
		if ( horario == null ) {
			if ( other.horario != null )
				return false;
		} else if ( !horario.equals( other.horario ) )
			return false;
		if ( matricula == null ) {
			if ( other.matricula != null )
				return false;
		} else if ( !matricula.equals( other.matricula ) )
			return false;
		if ( numeroDocRepresentante == null ) {
			if ( other.numeroDocRepresentante != null )
				return false;
		} else if ( !numeroDocRepresentante.equals( other.numeroDocRepresentante ) )
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
		if ( tempoEsperaEmFila == null ) {
			if ( other.tempoEsperaEmFila != null )
				return false;
		} else if ( !tempoEsperaEmFila.equals( other.tempoEsperaEmFila ) )
			return false;
		if ( tipoAgendamento == null ) {
			if ( other.tipoAgendamento != null )
				return false;
		} else if ( !tipoAgendamento.equals( other.tipoAgendamento ) )
			return false;
		if ( unidadeNegocio == null ) {
			if ( other.unidadeNegocio != null )
				return false;
		} else if ( !unidadeNegocio.equals( other.unidadeNegocio ) )
			return false;
		if ( usuarioDiferenciado == null ) {
			if ( other.usuarioDiferenciado != null )
				return false;
		} else if ( !usuarioDiferenciado.equals( other.usuarioDiferenciado ) )
			return false;
		if ( usuarioExterno == null ) {
			if ( other.usuarioExterno != null )
				return false;
		} else if ( !usuarioExterno.equals( other.usuarioExterno ) )
			return false;
		return true;
	}

	public BigDecimal getCanceladoUsuarioExterno() {
		return canceladoUsuarioExterno;
	}

	public Long getCodgAgendamentoSeqc() {
		return codgAgendamentoSeqc;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public Date getDataHoraAgendamento() {
		return dataHoraAgendamento;
	}

	public GradeServicoVO getGradeServico() {
		return gradeServico;
	}

	public HorarioVO getHorario() {
		return horario;
	}

	public BigDecimal getMatricula() {
		return matricula;
	}

	public String getNumeroDocRepresentante() {
		return numeroDocRepresentante;
	}

	public ServicoVO getServico() {
		return servico;
	}

	public BigDecimal getSituacao() {
		return situacao;
	}

	public BigDecimal getTempoEsperaEmFila() {
		return tempoEsperaEmFila;
	}

	public BigDecimal getTipoAgendamento() {
		return tipoAgendamento;
	}

	public UnidadeNegocioVO getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public BigDecimal getUsuarioDiferenciado() {
		return usuarioDiferenciado;
	}

	public UsuarioExternoVO getUsuarioExterno() {
		return usuarioExterno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( canceladoUsuarioExterno == null ) ? 0 : canceladoUsuarioExterno.hashCode() );
		result = prime * result + ( ( codgAgendamentoSeqc == null ) ? 0 : codgAgendamentoSeqc.hashCode() );
		result = prime * result + ( ( dataCadastro == null ) ? 0 : dataCadastro.hashCode() );
		result = prime * result + ( ( dataHoraAgendamento == null ) ? 0 : dataHoraAgendamento.hashCode() );
		result = prime * result + ( ( horario == null ) ? 0 : horario.hashCode() );
		result = prime * result + ( ( matricula == null ) ? 0 : matricula.hashCode() );
		result = prime * result + ( ( numeroDocRepresentante == null ) ? 0 : numeroDocRepresentante.hashCode() );
		result = prime * result + ( ( servico == null ) ? 0 : servico.hashCode() );
		result = prime * result + ( ( situacao == null ) ? 0 : situacao.hashCode() );
		result = prime * result + ( ( tempoEsperaEmFila == null ) ? 0 : tempoEsperaEmFila.hashCode() );
		result = prime * result + ( ( tipoAgendamento == null ) ? 0 : tipoAgendamento.hashCode() );
		result = prime * result + ( ( unidadeNegocio == null ) ? 0 : unidadeNegocio.hashCode() );
		result = prime * result + ( ( usuarioDiferenciado == null ) ? 0 : usuarioDiferenciado.hashCode() );
		result = prime * result + ( ( usuarioExterno == null ) ? 0 : usuarioExterno.hashCode() );
		return result;
	}

	public void setCanceladoUsuarioExterno( BigDecimal canceladoUsuarioExterno ) {
		this.canceladoUsuarioExterno = canceladoUsuarioExterno;
	}

	public void setCodgAgendamentoSeqc( Long codgAgendamentoSeqc ) {
		this.codgAgendamentoSeqc = codgAgendamentoSeqc;
	}

	public void setDataCadastro( Date dataCadastro ) {
		this.dataCadastro = dataCadastro;
	}

	public void setDataHoraAgendamento( Date dataHoraAgendamento ) {
		this.dataHoraAgendamento = dataHoraAgendamento;
	}

	public void setGradeServico( GradeServicoVO gradeServico ) {
		this.gradeServico = gradeServico;
	}

	public void setHorario( HorarioVO horario ) {
		this.horario = horario;
	}

	public void setMatricula( BigDecimal matricula ) {
		this.matricula = matricula;
	}

	public void setNumeroDocRepresentante( String numeroDocRepresentante ) {
		this.numeroDocRepresentante = numeroDocRepresentante;
	}

	public void setServico( ServicoVO servico ) {
		this.servico = servico;
	}

	public void setSituacao( BigDecimal situacao ) {
		this.situacao = situacao;
	}

	public void setTempoEsperaEmFila( BigDecimal tempoEsperaEmFila ) {
		this.tempoEsperaEmFila = tempoEsperaEmFila;
	}

	public void setTipoAgendamento( BigDecimal tipoAgendamento ) {
		this.tipoAgendamento = tipoAgendamento;
	}

	public void setUnidadeNegocio( UnidadeNegocioVO unidadeNegocio ) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public void setUsuario( UsuarioVO usuario ) {
		this.usuario = usuario;
	}

	public void setUsuarioDiferenciado( BigDecimal usuarioDiferenciado ) {
		this.usuarioDiferenciado = usuarioDiferenciado;
	}

	public void setUsuarioExterno( UsuarioExternoVO usuarioExterno ) {
		this.usuarioExterno = usuarioExterno;
	}

}
