package br.com.abaco.chronos.agendamento;

public enum EnumAgendamento {

	UNIDADE_ORGANIZACIONAL( 1L, br.com.abaco.chronos.menubar.R.drawable.expandir_back, br.com.abaco.chronos.menubar.R.string.lb_agendamento_unidade_organizacional ),

	UNIDADE_NEGOCIO( 2L, br.com.abaco.chronos.menubar.R.drawable.expandir_back, br.com.abaco.chronos.menubar.R.string.lb_agendamento_unidade_negocio ),

	SERVICO( 3L, br.com.abaco.chronos.menubar.R.drawable.expandir_back, br.com.abaco.chronos.menubar.R.string.lb_agendamento_servico ),

	DATA( 4L, br.com.abaco.chronos.menubar.R.drawable.expandir_back, br.com.abaco.chronos.menubar.R.string.lb_agendamento_data ),

	HORARIO( 5L, br.com.abaco.chronos.menubar.R.drawable.expandir_back, br.com.abaco.chronos.menubar.R.string.lb_agendamento_horario );		


	private int img;

	private int titulo;

	private Long id;

	 EnumAgendamento( Long id, int img, int titulo ) {

		this.id = id;
		this.img = img;
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}

	public int getImg() {
		return img;
	}

	public int getTitulo() {
		return titulo;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setImg( int img ) {
		this.img = img;
	}

	public void setTitulo( int titulo ) {
		this.titulo = titulo;
	}
	
	public static EnumAgendamento valueOf( final Long requisicao ) {
		for ( final EnumAgendamento tipo : values() ) {
			if ( tipo.id.equals( requisicao ) ) {
				return tipo;
			}
		}

		return null;
	}
}
