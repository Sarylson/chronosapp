package br.com.abaco.chronos.menubar;

import java.util.Calendar;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import br.com.abaco.asynctasks.agendamento.LoadingSelecionarHorarioDisponivelAsync;
import br.com.abaco.chronos.agendamento.AgendamentoFragment;
import br.com.abaco.chronos.agendamento.CancelamentoAgendamentoListFragment;
import br.com.abaco.chronos.agendamento.MeusAgendamentosListFragment;
import br.com.abaco.chronos.agendamento.SobreFragment;
import br.com.abaco.chronos.avaliacaoatendimento.AvaliarAtendimentoSelecaoSenhaFragment;
import br.com.abaco.chronos.avaliacaoatendimento.asynctasks.LoadingAvaliarAtendimento;
import br.com.abaco.chronos.configuracao.Configuracao;
import br.com.abaco.chronos.configuracao.ConfiguracaoFragment;
import br.com.abaco.chronos.dashboard.DashBoardFragment;
import br.com.abaco.chronos.login.LoginActivity;
import br.com.abaco.chronos.senhapainel.SenhaPainelFragment;
import br.com.abaco.chronos.usuario.AlterarMeusDadosFragment;
import br.com.abaco.chronos.util.MensagemUtil;
import br.com.abaco.modelo.rest.usuario.UsuarioVO;
import br.com.abaco.notification.NotificacaoFragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class HomeActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
	 */

	ConfiguracaoFragment fragment = new ConfiguracaoFragment();

	/**
	 * Used to store the last screen title. For use in {@link #restoreActionBar()}.
	 */
	private CharSequence tituloCentral;


	private Menu menu;

	private AgendamentoFragment agendamentFragment;

	private Fragment fragmentGenerico;

	private AtualizadorDePosicao atualizador;

	private CheckBox checkboxHabilitarNotificao;

	private CheckBox checkboxNotificacaoComSom;

	private CheckBox checkboxNotificacaoComVibracao;

	private CheckBox checkboxNotificacaoACadaXTempo;
	
	final Gson gson = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_home );

		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled( true );
		actionBar.setDisplayShowTitleEnabled( true );

		// Configura as notificações
		this.configurarAlarmeNotificacao();

		checkboxHabilitarNotificao = (CheckBox) findViewById( R.id.idCheckboxHabilitarNotificacao );

		checkboxNotificacaoComSom = (CheckBox) findViewById( R.id.idCheckboxNotificacaoComSom );
		checkboxNotificacaoComVibracao = (CheckBox) findViewById( R.id.idCheckboxNotificacaoComVibracao );
		checkboxNotificacaoACadaXTempo = (CheckBox) findViewById( R.id.idCheckboxNotificacaoAcadaXTempo );

		FrameLayout f = (FrameLayout) findViewById( R.id.container );
		// fadeOut.setDuration( 2000 );
//		ObjectAnimator fadeIn = ObjectAnimator.ofFloat( f,  "alpha", 0.05f, 1, 2);
//		fadeIn.setDuration( 1000 );
//
//		// animatorSet.play(mover).with(fadeIn).after(fadeOut);
//		// animatorSet.start();
//
//		set = new AnimatorSet();
//		set.play( fadeIn );
//		set.start();
		AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator( this, R.anim.property_animator );
		set.setTarget( f );
		set.start();
		//mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById( R.id.navigation_drawer );
		tituloCentral = getTitle();

		// Set up the drawer.
		//mNavigationDrawerFragment.setUp( R.id.navigation_drawer, (DrawerLayout) findViewById( R.id.drawer_layout ) );
		final String isUsuarioLogado = getSharedPreferences( "MyprefChronos", 0 ).getString( "usuario", " " );

		if ( isUsuarioLogado != null ) {
			
			final UsuarioVO usuario = gson.fromJson( isUsuarioLogado, new TypeToken<UsuarioVO>() {}.getType() );
			
			if( usuario.getUsuarioExterno() != null && usuario.getUsuarioExterno().getNome() != null ){
			  MensagemUtil.addMsg( this, "Bem vindo " + usuario.getUsuarioExterno().getNome() + "!" );
			}
		}

		
		//Inicio
		tituloCentral = getString( R.string.lb_home );
		DashBoardFragment fragment = new DashBoardFragment();
		getSupportFragmentManager().beginTransaction().replace( R.id.container, fragment ).commit();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Redirecionar para o senha painel
		final String jhackson = getIntent().getStringExtra( "jacksonsilva" );

		if ( jhackson != null ) {
			tituloCentral = getString( R.string.lb_painel_senhas );
			fragmentGenerico = new SenhaPainelFragment( this.menu );

			final FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().replace( R.id.container, fragmentGenerico ).addToBackStack( fragment.getClass().getName() ).commit();
		}
	}

	@Override
	public void onNavigationDrawerItemSelected( int position ) {
		// update the main content by replacing fragments
		final FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = null;

		switch ( position ) {

			case 0:
				tituloCentral = getString( R.string.lb_home );
				fragment = new DashBoardFragment();
				fragmentManager.beginTransaction().replace( R.id.container, fragment ).commit();
				break;
			case 1:
				tituloCentral = getString( R.string.configuracao );
				fragment = new ConfiguracaoFragment();
				fragmentManager.beginTransaction().replace( R.id.container, fragment ).commit();
				break;
			case 2:
				tituloCentral = getString( R.string.alterar_meus_dados );
				fragment = new AlterarMeusDadosFragment();
				fragmentManager.beginTransaction().replace( R.id.container, fragment ).commit();
				break;
			case 3:
				tituloCentral = getString( R.string.lb_relatar_problema );
				fragment = new AlterarMeusDadosFragment();
				fragmentManager.beginTransaction().replace( R.id.container, fragment ).commit();
				break;
			case 4:
				tituloCentral = getString( R.string.lb_notificacao );
				fragment = new NotificacaoFragment();
				fragmentManager.beginTransaction().replace( R.id.container, fragment ).commit();
				break;
			case 5:
				tituloCentral = getString( R.string.lb_sobre );
				fragment = new SobreFragment();
				fragmentManager.beginTransaction().replace( R.id.container, fragment ).commit();
				break;
			case 6:
				tituloCentral = getString( R.string.sair );
				MensagemUtil.addMsgConfirm( this, this.getString( R.string.lb_sair ), this.getString( R.string.msg_sair ), R.drawable.logout, new OnClickListener() {

					@Override
					public void onClick( DialogInterface dialog, int which ) {
						final Intent i = new Intent( HomeActivity.this, LoginActivity.class );
						i.putExtra( "logout", "OK" );
						startActivity( i );
						finish();
						// removerUsuarioSessao();
					}
				} );
				break;
			default:
				fragment = new DashBoardFragment();
				fragmentManager.beginTransaction().replace( R.id.container, fragment ).commit();
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode( ActionBar.NAVIGATION_MODE_STANDARD );
		actionBar.setDisplayShowTitleEnabled( true );
		actionBar.setTitle( tituloCentral );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {

		this.menu = menu;
		//if ( !mNavigationDrawerFragment.isDrawerOpen() ) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate( R.menu.home, menu );
			restoreActionBar();
			return true;
		//}
		//return super.onCreateOptionsMenu( menu );
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		int id = item.getItemId();
//		if ( id == R.id.action_settings ) {
//			tituloCentral = getString( R.string.configuracao );
//			fragment = new ConfiguracaoFragment();
//			final FragmentManager fragmentManager = getSupportFragmentManager();
//			fragmentManager.beginTransaction().replace( R.id.container, fragment ).commit();
//			return true;
//		}
		if ( id == R.id.action_example_logout ) {

			// Dialog dialog = new Dialog( this);
			// dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
			// dialog.setContentView( br.com.abaco.chronos.menubar.R.layout.dialog_custom_mensagens );
			//
			// Button buttonCancelar = (Button) dialog.findViewById( R.id.btn_dialog_custom_cancelar );
			// buttonCancelar.setOnClickListener( new View.OnClickListener() {
			//
			// @Override
			// public void onClick( View v ) {
			//
			// MensagemUtil.addMsg( HomeActivity.this, "teste" );
			// }
			// } );
			//
			// dialog.show();
			MensagemUtil.addMsgConfirm( this, this.getString( R.string.lb_sair ), this.getString( R.string.msg_sair ), R.drawable.logout, new OnClickListener() {

				@Override
				public void onClick( DialogInterface dialog, int which ) {
					final Intent i = new Intent( HomeActivity.this, LoginActivity.class );
					i.putExtra( "logout", "OK" );
					startActivity( i );
					finish();
				}
			} );
		}
		if ( id == R.id.idBackMenu ) {

			tituloCentral = getString( R.string.lb_home );
			
			DashBoardFragment fragmentDash = new DashBoardFragment();
			restoreActionBar();
			this.getSupportFragmentManager().beginTransaction().replace( R.id.container, fragmentDash ).commit();

		}
		if( id == R.id.action_home ){
			tituloCentral = getString( R.string.lb_home );
			DashBoardFragment fragment = new DashBoardFragment();
			this.getSupportFragmentManager().beginTransaction().replace( R.id.container, fragment ).commit();
		}
		if( id == R.id.action_configuracao ){
			tituloCentral = getString( R.string.configuracao );
			ConfiguracaoFragment fragment = new ConfiguracaoFragment();
			this.getSupportFragmentManager().beginTransaction().replace( R.id.container, fragment ).commit();
		}
		if( id == R.id.action_alterar_meus_dados ){
			tituloCentral = getString( R.string.alterar_meus_dados );
			AlterarMeusDadosFragment fragment = new AlterarMeusDadosFragment();
			this.getSupportFragmentManager().beginTransaction().replace( R.id.container, fragment ).commit();
		}

		if( id == R.id.action_sobre ){
			tituloCentral = getString( R.string.lb_sobre );
			SobreFragment	fragment = new SobreFragment();
			this.getSupportFragmentManager().beginTransaction().replace( R.id.container, fragment ).commit();
		}
		
		return super.onOptionsItemSelected( item );
	}

	public void alterarDados( final View view ) {

		final EditText edtTexto = (EditText) findViewById( R.id.edtNome );
		MensagemUtil.addMsg( this, " Valor digitado --> " + edtTexto.getText().toString() );
	}

	public void logar( View view ) {

		MensagemUtil.addMsg( this, "logou com sucesso!" );

	}

	// ===============================================================

	public void realizarAgendamento( final View view ) {

		agendamentFragment = new AgendamentoFragment( this.menu );
		tituloCentral = getString( R.string.lb_agendamento );
		restoreActionBar();
		final FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace( R.id.container, agendamentFragment ).commit();

	}

	public void confirmarAgendamento( final View view ) {

		// final String unidadeOrganizacional = getIntent().getStringExtra( getString( R.string.lb_requisicao_unidade_organizacional_selecionada_agendamento ) );

		MensagemUtil.addMsgConfirm( this, "Confirmação", "Confirmar agendamento?", R.drawable.selecione_black, new OnClickListener() {

			@Override
			public void onClick( DialogInterface dialog, int which ) {

				new LoadingSelecionarHorarioDisponivelAsync( HomeActivity.this, getSupportFragmentManager() ).execute();
			}
		} );

	}

	public void realizarCancelamento( final View view ) {
		tituloCentral = getString( R.string.lb_cancelamento );
		restoreActionBar();
		fragmentGenerico = new CancelamentoAgendamentoListFragment( this.menu );

		final FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace( R.id.container, fragmentGenerico ).addToBackStack( fragment.getClass().getName() ).commit();
	}

	public void meusAgendamentos( final View view ) {

		tituloCentral = getString( R.string.lb_meus_agendamentos );
		fragmentGenerico = new MeusAgendamentosListFragment( this.menu );
		restoreActionBar();
		final FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace( R.id.container, fragmentGenerico ).addToBackStack( fragment.getClass().getName() ).commit();
	}

	public void telaInicial( final View view ) {
		tituloCentral = getString( R.string.lb_home );
		restoreActionBar();
		final FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = new DashBoardFragment();
		fragmentManager.beginTransaction().replace( R.id.container, fragment ).commit();
	}

	public void verificarSenhaPainel( final View view ) {
		tituloCentral = getString( R.string.lb_painel_senhas );
		fragmentGenerico = new SenhaPainelFragment( this.menu );
		restoreActionBar();
		final FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace( R.id.container, fragmentGenerico ).addToBackStack( fragment.getClass().getName() ).commit();
	}

	public void avaliarAtendimentos( final View view ) {

		 tituloCentral = getString( R.string.lb_avaliar_atendimento );
		 final FragmentManager fragmentManager = getSupportFragmentManager();
		
		 fragmentGenerico = new AvaliarAtendimentoSelecaoSenhaFragment( this.menu, fragmentManager );
		
		 fragmentManager.beginTransaction().replace( R.id.container, fragmentGenerico ).addToBackStack( fragment.getClass().getName() ).commit();
	}

	public void localizarUnidades( final View view ) {

		final FragmentManager manager = getSupportFragmentManager();

		final FragmentTransaction fragmentTransaction = manager.beginTransaction();
		final LocalizacaoUnidadeFragment localizacaoUnidadeFragment = new LocalizacaoUnidadeFragment( null, null );
		fragmentTransaction.replace( br.com.abaco.chronos.menubar.R.id.container, localizacaoUnidadeFragment );
		fragmentTransaction.addToBackStack( localizacaoUnidadeFragment.getClass().getName() );
		fragmentTransaction.commit();

		atualizador = new AtualizadorDePosicao( this, localizacaoUnidadeFragment );
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if ( atualizador != null ) {
			atualizador.cancelar();
		}
	}
	
	public void habilitarOutrasOpcoes( View view ) {

		final CheckBox checkboxHabilitarNotificao = (CheckBox) findViewById( R.id.idCheckboxHabilitarNotificacao );

		if ( checkboxHabilitarNotificao == null ) {
			return;
		}
		final TextView txtNotificacaoComSom = (TextView) findViewById( R.id.txtCheckboxNotificacaoComSom );
		final CheckBox checkboxNotificacaoComSom = (CheckBox) findViewById( R.id.idCheckboxNotificacaoComSom );

		final TextView txtNotificacaoComVibracao = (TextView) findViewById( R.id.txtCheckboxNotificacaoComVibracao );
		final CheckBox checkboxNotificacaoComVibracao = (CheckBox) findViewById( R.id.idCheckboxNotificacaoComVibracao );

		final TextView txtNotificacaoACadaXTempo = (TextView) findViewById( R.id.txtCheckboxNotificacaoAcadaXTempo );
		final CheckBox checkboxNotificacaoACadaXTempo = (CheckBox) findViewById( R.id.idCheckboxNotificacaoAcadaXTempo );

		if ( checkboxHabilitarNotificao.isChecked() ) {

			txtNotificacaoComSom.setEnabled( Boolean.TRUE );
			checkboxNotificacaoComSom.setEnabled( Boolean.TRUE );
			txtNotificacaoComVibracao.setEnabled( Boolean.TRUE );
			checkboxNotificacaoComVibracao.setEnabled( Boolean.TRUE );
			txtNotificacaoACadaXTempo.setEnabled( Boolean.TRUE );
			checkboxNotificacaoACadaXTempo.setEnabled( Boolean.TRUE );
		} else {
			txtNotificacaoComSom.setEnabled( Boolean.FALSE );
			checkboxNotificacaoComSom.setEnabled( Boolean.FALSE );
			txtNotificacaoComVibracao.setEnabled( Boolean.FALSE );
			checkboxNotificacaoComVibracao.setEnabled( Boolean.FALSE );
			txtNotificacaoACadaXTempo.setEnabled( Boolean.FALSE );
			checkboxNotificacaoACadaXTempo.setEnabled( Boolean.FALSE );
		}
	}

	public void salvarConfiguracao( final View view ) {

		// Verificando se já existe um registro no SharedPreferences

		// final String isExisteConfiguracao = getSharedPreferences( "MyprefChronos", 0 ).getString( "configuracao", " " );
		//
		// if ( isExisteConfiguracao != null ) {
		//
		// }

		checkboxHabilitarNotificao = (CheckBox) findViewById( R.id.idCheckboxHabilitarNotificacao );

		checkboxNotificacaoComSom = (CheckBox) findViewById( R.id.idCheckboxNotificacaoComSom );
		checkboxNotificacaoComVibracao = (CheckBox) findViewById( R.id.idCheckboxNotificacaoComVibracao );
		checkboxNotificacaoACadaXTempo = (CheckBox) findViewById( R.id.idCheckboxNotificacaoAcadaXTempo );

		final Configuracao configuracao = new Configuracao();
		configuracao.setHabilitarNotificacao( checkboxHabilitarNotificao.isChecked() );
		configuracao.setNotificacaoComSom( checkboxNotificacaoComSom.isChecked() );
		configuracao.setNotificacaoComVibracao( checkboxNotificacaoComVibracao.isChecked() );
		configuracao.setNotificacaoACadaXTempo( checkboxNotificacaoACadaXTempo.isChecked() );

		// Gravando as configurações
		final Gson gson = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

		SharedPreferences preferences = getSharedPreferences( "MyprefChronos", 0 );
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString( "configuracao", gson.toJson( configuracao, new TypeToken<Configuracao>() {
		}.getType() ) );
		editor.commit();

		MensagemUtil.addMsg( this, "Configuração realizada com sucesso!" );

		this.configurarAlarmeNotificacao();

		// Manda para pagina de inicio
		tituloCentral = getString( R.string.lb_home );
		restoreActionBar();
		final FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = new DashBoardFragment();
		fragmentManager.beginTransaction().replace( R.id.container, fragment ).commit();
	}

	private void configurarAlarmeNotificacao() {

		final String configuracaoSharedPreferences = getSharedPreferences( "MyprefChronos", 0 ).getString( "configuracao", " " );

		if ( configuracaoSharedPreferences != null ) {
			final Gson gson = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();
			final Configuracao configuracao = gson.fromJson( configuracaoSharedPreferences, new TypeToken<Configuracao>() {
			}.getType() );

			if ( configuracao == null ) {

				// Cancela todas as notificações
				final Intent intent = new Intent( "ALARME_DISPARADO" );
				final PendingIntent pendingIntent = PendingIntent.getBroadcast( this, 0, intent, 0 );

				AlarmManager alarmManager = (AlarmManager) getSystemService( Context.ALARM_SERVICE );
				alarmManager.cancel( pendingIntent );
				return;
			}

			if ( configuracao.getNotificacaoACadaXTempo() ) {
				final boolean alarmeDisparado = PendingIntent.getBroadcast( this, 0, new Intent( "ALARME_DISPARADO" ), PendingIntent.FLAG_NO_CREATE ) == null;

				if ( alarmeDisparado ) {

					final Intent intent = new Intent( "ALARME_DISPARADO" );
					final PendingIntent pendingIntent = PendingIntent.getBroadcast( this, 0, intent, 0 );

					final Calendar c = Calendar.getInstance();
					c.setTimeInMillis( System.currentTimeMillis() );

					c.add( Calendar.MINUTE, 5 );

					final AlarmManager alarmManager = (AlarmManager) getSystemService( Context.ALARM_SERVICE );
					alarmManager.setRepeating( AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 500000, pendingIntent );

					return;
				}
			}
			if ( !configuracao.getNotificacaoACadaXTempo() ) {
				final Intent intent = new Intent( "ALARME_DISPARADO" );
				final PendingIntent pendingIntent = PendingIntent.getBroadcast( this, 0, intent, 0 );

				AlarmManager alarmManager = (AlarmManager) getSystemService( Context.ALARM_SERVICE );
				alarmManager.cancel( pendingIntent );
			}
		}
	}
	
	public void confirmarAvaliacaoAtendimento(final View view){
		
      new LoadingAvaliarAtendimento(HomeActivity.this, menu, this.getSupportFragmentManager(),view).execute();
		
	}
}
