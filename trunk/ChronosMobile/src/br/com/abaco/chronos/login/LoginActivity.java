package br.com.abaco.chronos.login;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import br.com.abaco.chronos.bo.login.LoginBO;
import br.com.abaco.chronos.menubar.HomeActivity;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.chronos.util.JsonUtil;
import br.com.abaco.chronos.util.MensagemUtil;
import br.com.abaco.chronos.util.RestUtil;
import br.com.abaco.modelo.rest.usuario.UsuarioVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Classe de Login
 * 
 * @author jackson
 */
public class LoginActivity extends Activity {

	private EditText edtLogin;

	private EditText edtSenha;

	private LoginBO loginBO;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		setContentView( R.layout.activity_login );

		LinearLayout linear = (LinearLayout) findViewById( R.id.LinearCampos );

		final AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator( this, R.anim.property_animator );
		set.setTarget( linear );
		set.start();

		edtLogin = (EditText) findViewById( R.id.edt_login );
		edtSenha = (EditText) findViewById( R.id.edt_senha );

		final String logout = getIntent().getStringExtra( "logout" );
		if ( logout != null && !logout.equals( "" ) ) {
			removerUsuarioSessao();
			getSharedPreferences( "MyprefChronos", 0 ).edit().clear();
			getSharedPreferences( "MyprefChronos", 0 ).edit().commit();

			getSharedPreferences( "MyprefChronos", 0 ).edit().putString( "usuario", " " ).commit();

			// Removendo o Alarm
			final Intent intent = new Intent( "ALARME_DISPARADO" );
			final PendingIntent pendingIntent = PendingIntent.getBroadcast( this, 0, intent, 0 );

			AlarmManager alarmManager = (AlarmManager) getSystemService( Context.ALARM_SERVICE );
			alarmManager.cancel( pendingIntent );
		}

		final String isUsuarioLogado = getSharedPreferences( "MyprefChronos", 0 ).getString( "usuario", " " );
		// Sessão Ativa
		if ( isUsuarioLogado != null && !isUsuarioLogado.equalsIgnoreCase( " " ) ) {
			final Intent i = new Intent( LoginActivity.this, HomeActivity.class );
			startActivity( i );
			finish();
		}

	}

	public void logar( final View view ) {

		final String campoLogin = edtLogin.getText().toString();
		final String campoSenha = edtSenha.getText().toString();

		loginBO = new LoginBO( LoginActivity.this );
		final String msg = loginBO.validarLogin( campoLogin, campoSenha );

		if ( msg != null ) {
			MensagemUtil.addMsg( this, msg );
			return;
		}
		new LoadingAsync().execute();
	}

	public class LoadingAsync extends AsyncTask<Void, Void, String> {

		private ProgressDialog progressDialog = new ProgressDialog( LoginActivity.this );

		final JsonUtil<UsuarioVO> jsonUtil = new JsonUtil<UsuarioVO>();

		@Override
		protected void onPreExecute() {
			progressDialog.setMessage( "Carregando..." );
			progressDialog.show();
		}

		@Override
		protected String doInBackground( Void... params ) {
			final String campoLogin = edtLogin.getText().toString();
			final String campoSenha = edtSenha.getText().toString();

			UsuarioVO usuario = new UsuarioVO();
			usuario.setLogin( campoLogin );
			usuario.setSenha( campoSenha );

			Gson gson = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

			final String json = gson.toJson( usuario, new TypeToken<UsuarioVO>() {
			}.getType() );

			final String retorno = RestUtil.post( getString( R.string.url_rest_autenticacao ), json );
			return retorno;
		}

		@Override
		protected void onPostExecute( String result ) {
			progressDialog.dismiss();

			if ( result == null || result.isEmpty() ) {
				MensagemUtil.addMsg( LoginActivity.this, "Login ou Senha Inválidos!" );
			} else if ( result != null && result.equalsIgnoreCase( "FALHA" ) ) {
				MensagemUtil.addMsg( LoginActivity.this, "Ocorreu um problema na comunicação com o servidor!\n tente novamente mais tarde." );
			} else {
				Log.i( "ERRORS", result );

				final Gson gson = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();
				final UsuarioVO usuarioRetorno = gson.fromJson( result, new TypeToken<UsuarioVO>() {}.getType() );

				colocarUsuarioSessao( usuarioRetorno );
				final Intent i = new Intent( LoginActivity.this, HomeActivity.class );
				startActivity( i );
				finish();
			}
		}
	}

	public void cadastrarNovoUsuario( final View view ) {

		MensagemUtil.addMsg( this, "Cadastrar" );
	}

	public void recuperarSenha( final View view ) {
		MensagemUtil.addMsg( this, "Recuperar Senha" );
	}

	public void colocarUsuarioSessao( final UsuarioVO usuario ) {

		final Gson gson = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

		SharedPreferences preferences = getSharedPreferences( "MyprefChronos", 0 );
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString( "usuario", gson.toJson( usuario, new TypeToken<UsuarioVO>() {
		}.getType() ) );
		editor.commit();
		preferences = null;
	}

	public void removerUsuarioSessao() {

		SharedPreferences prefs = getSharedPreferences( "MyprefChronos", 0 );
		prefs.edit().clear();
		prefs.edit().commit();

		prefs = null;
	}

}
