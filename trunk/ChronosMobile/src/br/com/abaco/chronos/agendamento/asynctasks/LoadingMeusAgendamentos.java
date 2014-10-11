package br.com.abaco.chronos.agendamento.asynctasks;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import br.com.abaco.chronos.agendamento.customs.AdapterMeusAgendamentos;
import br.com.abaco.chronos.agendamento.customs.MeusAgendamentosItem;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.chronos.util.MensagemUtil;
import br.com.abaco.chronos.util.RestUtil;
import br.com.abaco.modelo.rest.usuario.UsuarioVO;
import br.com.abaco.modelo.rest.usuario.usuarioexterno.UsuarioExternoVO;
import br.com.abaco.modelo.senha.SenhaVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Meus Agendamentos Agendados
 * 
 * @author Jackson Silva
 *
 */
public class LoadingMeusAgendamentos extends AsyncTask<Void, Void, String> {

	private ProgressDialog progressDialog;

	private Gson jsonUtil = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

	private Activity activity;

	private View viewRoot;
	
	private Menu menu;

	public LoadingMeusAgendamentos( final Activity activity, final View view , final Menu menu ) {
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

		final UsuarioVO usuario = jsonUtil.fromJson( usuarioLogado, new TypeToken<UsuarioVO>() {
		}.getType() );

		final UsuarioExternoVO usuarioExterno = usuario.getUsuarioExterno();

		final String json = jsonUtil.toJson( usuarioExterno, new TypeToken<UsuarioExternoVO>() {
		}.getType() );

		final String retorno = RestUtil.post( activity.getString( R.string.url_rest_meus_agendamentos ), json );
		return retorno;
	}

	@Override
	protected void onPostExecute( String result ) {
		progressDialog.dismiss();

		MenuItem item = menu.findItem( R.id.action_home );
		item.setVisible(Boolean.FALSE );
		
		MenuItem itemBack = menu.findItem( R.id.idBackMenu );
		itemBack.setVisible(Boolean.TRUE );
		
		//action_example_logout
		
		if( result != null && result.equals( "[]" )){
			
			final LinearLayout linearLayout = (LinearLayout) viewRoot.findViewById( R.id.idLinearLayoutMinhasSenhasSemRegistros );
			linearLayout.setVisibility( View.VISIBLE );
			
			final LinearLayout linearLayoutComRegistros = (LinearLayout) viewRoot.findViewById( R.id.idLinearLayoutMinhasSenhasComRegistros );
			linearLayoutComRegistros.setVisibility( View.INVISIBLE );
			
		}else 
		
		   if ( result != null && result.equalsIgnoreCase( "FALHA" ) ) {
		   	MensagemUtil.addMsg( activity, "Ocorreu um problema na comunicação com o servidor!\n tente novamente mais tarde." );
		   }else if ( result != null && !result.isEmpty() ) {

			final List<SenhaVO> senhas = jsonUtil.fromJson( result, new TypeToken<List<SenhaVO>>() {
			}.getType() );

			final List<MeusAgendamentosItem> listaItems = new ArrayList<MeusAgendamentosItem>();
			for ( final SenhaVO senhaFor : senhas ) {

				MeusAgendamentosItem meuAgendamentoItem = new MeusAgendamentosItem();
				meuAgendamentoItem.setIdRegistro( senhaFor.getAgendamento().getCodgAgendamentoSeqc() );
				meuAgendamentoItem.setData( senhaFor.getAgendamento().getDataAgendamentoFormatada()+" "+ senhaFor.getAgendamento().getHoraAgendamentoFormatada());
				meuAgendamentoItem.setSenha( senhaFor.getNumeroSenha() );
				meuAgendamentoItem.setUnidadeAtendimento( senhaFor.getAgendamento().getUnidadeNegocio().getNome() );

				listaItems.add( meuAgendamentoItem );
			}

			final AdapterMeusAgendamentos adapter = new AdapterMeusAgendamentos( this.activity, R.layout.listview_custom_meus_agendamentos, listaItems );

			final ListView listViewAgendamentoCancelamento = (ListView) viewRoot.findViewById( br.com.abaco.chronos.menubar.R.id.listViewMeusAgendamentos );

			listViewAgendamentoCancelamento.setAdapter( adapter );

		}
	}
}