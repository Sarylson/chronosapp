package br.com.abaco.chronos.custom.listview;

public enum EnumMenuLateral {

	INICIO( br.com.abaco.chronos.menubar.R.drawable.home_dark, br.com.abaco.chronos.menubar.R.string.lb_home ),
	
	CONFIGURACAO(
			br.com.abaco.chronos.menubar.R.drawable.ic_action_settings_blank, br.com.abaco.chronos.menubar.R.string.lb_configuracao ),

	ALTERAR_MEUS_DADOS( br.com.abaco.chronos.menubar.R.drawable.ic_action_edit_blank, br.com.abaco.chronos.menubar.R.string.lb_alterar_meus_dados ),

	RELATAR_PROBLEMA( br.com.abaco.chronos.menubar.R.drawable.relatar_problema_dark, br.com.abaco.chronos.menubar.R.string.lb_relatar_problema ), 
	
	NOTIFICAO( br.com.abaco.chronos.menubar.R.drawable.notification, br.com.abaco.chronos.menubar.R.string.lb_notificacao ), 
	
	SOBRE(br.com.abaco.chronos.menubar.R.drawable.info_dark, br.com.abaco.chronos.menubar.R.string.lb_sobre ),

	SAIR( br.com.abaco.chronos.menubar.R.drawable.logout_dark, br.com.abaco.chronos.menubar.R.string.lb_sair );

	private int img;

	private int titulo;

	EnumMenuLateral( int img, int titulo ) {

		this.img = img;
		this.titulo = titulo;
	}

	public int getImg() {
		return img;
	}

	public void setImg( int img ) {
		this.img = img;
	}

	public int getTitulo() {
		return titulo;
	}

	public void setTitulo( int titulo ) {
		this.titulo = titulo;
	}
}
