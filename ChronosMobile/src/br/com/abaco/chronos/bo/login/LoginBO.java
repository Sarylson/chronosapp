package br.com.abaco.chronos.bo.login;

import android.content.Context;

public class LoginBO {

	final Context context;

	public LoginBO( final Context context ) {
		this.context = context;
	}

	public String validarLogin( final String login, final String senha ) {
		if ( login == null || login.trim().equals( "" ) ) {
			return context.getString( br.com.abaco.chronos.menubar.R.string.msg_login );
		}
		if ( senha == null || senha.trim().equals( "" ) ) {
			return context.getString( br.com.abaco.chronos.menubar.R.string.msg_senha );
		}
		return null;
	}
}
