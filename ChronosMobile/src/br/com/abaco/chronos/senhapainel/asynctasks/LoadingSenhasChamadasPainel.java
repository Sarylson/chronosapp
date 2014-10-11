package br.com.abaco.chronos.senhapainel.asynctasks;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.chronos.senhapainel.customs.AdapterSenhaChamadaPainel;
import br.com.abaco.chronos.senhapainel.customs.SenhaChamadaPainelItem;
import br.com.abaco.chronos.util.MensagemUtil;
import br.com.abaco.chronos.util.RestUtil;
import br.com.abaco.modelo.enums.EnumSituacaoChamadaPainel;
import br.com.abaco.modelo.rest.usuario.UsuarioVO;
import br.com.abaco.modelo.rest.usuario.usuarioexterno.UsuarioExternoVO;
import br.com.abaco.modelo.senhachamadapainel.SenhaChamadaPainelVO;
import br.com.abaco.modelo.senhapainel.SenhaPainelVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Meus Agendamentos Agendados
 * 
 * @author Jackson Silva
 *
 */
public class LoadingSenhasChamadasPainel extends AsyncTask<Void, Void, String> {

	private ProgressDialog progressDialog;

	private Gson jsonUtil = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

	private Activity activity;

	private View viewRoot;
	
	private Menu menu;

	public LoadingSenhasChamadasPainel( final Activity activity, final View view , final Menu menu ) {
		super();
		
		this.menu = menu;
		this.activity = activity;
		this.viewRoot = view;
		progressDialog = new ProgressDialog( activity );
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage( "Carregando..." );
		progressDialog.show();
	}

	@Override
	protected String doInBackground( Void... params ) {

		final String usuarioLogado = this.activity.getSharedPreferences( "MyprefChronos", 0 ).getString( "usuario", " " );

		final UsuarioVO usuario = jsonUtil.fromJson( usuarioLogado, new TypeToken<UsuarioVO>() {}.getType() );

		final UsuarioExternoVO usuarioExterno = usuario.getUsuarioExterno();

		final String json = jsonUtil.toJson( usuarioExterno, new TypeToken<UsuarioExternoVO>() {}.getType() );

		final String retorno = RestUtil.post( activity.getString( R.string.url_rest_painel_senhas ), json );
		return retorno;
	}

	@Override
	protected void onPostExecute( String result ) {
		progressDialog.dismiss();

		MenuItem itemBack = menu.findItem( R.id.idBackMenu );
		if ( itemBack != null ) {
			itemBack.setVisible( Boolean.FALSE );
		}
		MenuItem item = menu.findItem( R.id.action_home );
		if ( item != null ) {
			item.setVisible( Boolean.TRUE );
		}
		
		//action_example_logout
		
	   if ( result != null && result.equalsIgnoreCase( "FALHA" ) ) {
	   	MensagemUtil.addMsg( activity, "Ocorreu um problema na comunicação com o servidor!\n tente novamente mais tarde." );
	   }else
		if ( result != null && !result.isEmpty() ) {

			if( result.contains( "Error:" )){
			    MensagemUtil.addMsgOk( this.activity, "Informação", result, R.drawable.info_dark );
			}else{
			
				final SenhaPainelVO senhaPainel = jsonUtil.fromJson( result, new TypeToken<SenhaPainelVO>() {}.getType() );
	
				final List<SenhaChamadaPainelItem> listaItems = new ArrayList<SenhaChamadaPainelItem>();
				for ( final SenhaChamadaPainelVO senhaChamadaPainelFor : senhaPainel.getSenhaChamadaPainel() ) {
	
					SenhaChamadaPainelItem senhaChamadaPainelItem = new SenhaChamadaPainelItem();
					senhaChamadaPainelItem.setIdRegistro( senhaChamadaPainelFor.getId() );
					senhaChamadaPainelItem.setDataHora( senhaChamadaPainelFor.getDataHoraChamadaFormatada() );
					senhaChamadaPainelItem.setSenha(senhaChamadaPainelFor.getNumeroSenha() );
					senhaChamadaPainelItem.setSituacao(  LoadingSenhasChamadasPainel.getSituacaoSenhaPainel( senhaChamadaPainelFor.getSenhaPainel().intValue() ) );
	
					listaItems.add( senhaChamadaPainelItem );
				}
	
				final AdapterSenhaChamadaPainel adapter = new AdapterSenhaChamadaPainel( this.activity, R.layout.listview_custom_senhas_chamadas_painel, listaItems );
	
				final ListView listViewSenhaChamadaPainel = (ListView) viewRoot.findViewById( br.com.abaco.chronos.menubar.R.id.listViewSenhasChamadasPainel );
	
				listViewSenhaChamadaPainel.setAdapter( adapter );
				
				TextView textoSenha  = (TextView) viewRoot.findViewById( br.com.abaco.chronos.menubar.R.id.idSenhaPainel );
				textoSenha.setText( senhaPainel.getSenha().getNumeroSenha() );
				
				TextView textoNotificacaoTempoAtendimento  = (TextView) viewRoot.findViewById( br.com.abaco.chronos.menubar.R.id.idNotificacaoTempoAtendimento );
				textoNotificacaoTempoAtendimento.setText( senhaPainel.getTempoParaAtendimento());
			}

		}
	}
	
	private static String getSituacaoSenhaPainel( final int situacao ){
		
		final EnumSituacaoChamadaPainel enumChamadaPainel = EnumSituacaoChamadaPainel.valueOf( Long.valueOf( situacao ) );
		
		String situacaoString = "";
		switch( enumChamadaPainel ){
			
			case ATENDENDO:
			{
				situacaoString = EnumSituacaoChamadaPainel.ATENDENDO.getTexto();
				break;
			}
			case ATENDIDA:
			{
				situacaoString = EnumSituacaoChamadaPainel.ATENDIDA.getTexto();
				break;
			}
			case EM_CANCELAMENTO:
			{
				situacaoString = EnumSituacaoChamadaPainel.EM_CANCELAMENTO.getTexto();
				break;
			}
			case AUSENTE:
			{
				situacaoString = EnumSituacaoChamadaPainel.AUSENTE.getTexto();
				break;
			}
			case CANCELADA:
			{
				situacaoString = EnumSituacaoChamadaPainel.CANCELADA.getTexto();
				break;
			}
		}
		
		return situacaoString;
	}
}