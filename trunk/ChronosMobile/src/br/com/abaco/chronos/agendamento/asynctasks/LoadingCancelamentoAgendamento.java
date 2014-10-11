package br.com.abaco.chronos.agendamento.asynctasks;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import br.com.abaco.chronos.agendamento.customs.AdapterAgendamentoCancelamento;
import br.com.abaco.chronos.agendamento.customs.AgendamentoCancelamentoItem;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.chronos.util.MensagemUtil;
import br.com.abaco.chronos.util.RestUtil;
import br.com.abaco.modelo.agendamento.AgendamentoVO;
import br.com.abaco.modelo.rest.usuario.UsuarioVO;
import br.com.abaco.modelo.rest.usuario.usuarioexterno.UsuarioExternoVO;
import br.com.abaco.modelo.senha.SenhaVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class LoadingCancelamentoAgendamento extends AsyncTask<Void, Void, String> {

	private ProgressDialog progressDialog;

	private Gson jsonUtil = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

	private Activity activity;

	private View viewRoot;

	private Menu menu;

	public LoadingCancelamentoAgendamento( final Activity activity, final View view, final Menu menu ) {
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
		// getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_unidade_negocio_selecionada_agendamento ) );
		// getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_servico_selecionado_agendamento ) );

		final String retorno = RestUtil.post( activity.getString( R.string.url_rest_listar_agendamento_para_cancelamento ), json );
		return retorno;
	}

	@Override
	protected void onPostExecute( String result ) {
		progressDialog.dismiss();

		MenuItem item = menu.findItem( R.id.action_home );
		item.setVisible( Boolean.FALSE );

		MenuItem itemBack = menu.findItem( R.id.idBackMenu );
		itemBack.setVisible( Boolean.TRUE );

		// action_example_logout
		
		if( result != null && result.equals( "[]" )){
			
			final LinearLayout linearLayout = (LinearLayout) viewRoot.findViewById( R.id.idLinearLayoutAgendamentoCancelamento );
			linearLayout.setVisibility( View.VISIBLE );
			
			final LinearLayout linearLayoutComRegistros = (LinearLayout) viewRoot.findViewById( R.id.idLinearLayoutAgendamentoCancelamentoComRegistros );
			linearLayoutComRegistros.setVisibility( View.INVISIBLE );
			
		}else 
		
		if ( result != null && result.equalsIgnoreCase( "FALHA" ) ) {
			MensagemUtil.addMsg( activity, "Ocorreu um problema na comunicação com o servidor!\n tente novamente mais tarde." );
		} else if ( result != null && !result.isEmpty() ) {
			final List<SenhaVO> senhas = jsonUtil.fromJson( result, new TypeToken<List<SenhaVO>>() {
			}.getType() );

			final List<AgendamentoCancelamentoItem> listaItems = new ArrayList<AgendamentoCancelamentoItem>();
			for ( final SenhaVO senhaFor : senhas ) {

				AgendamentoCancelamentoItem agendamentoCancelamentoItem = new AgendamentoCancelamentoItem();
				agendamentoCancelamentoItem.setIdRegistro( senhaFor.getAgendamento().getCodgAgendamentoSeqc() );
				agendamentoCancelamentoItem.setData( senhaFor.getAgendamento().getDataAgendamentoFormatada() + " " + senhaFor.getAgendamento().getHoraAgendamentoFormatada() );
				agendamentoCancelamentoItem.setSenha( senhaFor.getNumeroSenha() );
				agendamentoCancelamentoItem.setUnidadeAtendimento( senhaFor.getAgendamento().getUnidadeNegocio().getNome() );

				listaItems.add( agendamentoCancelamentoItem );
			}

			final AdapterAgendamentoCancelamento adapter = new AdapterAgendamentoCancelamento( this.activity, R.layout.listview_custom_cancelamento_agendamento, listaItems );

			final ListView listViewAgendamentoCancelamento = (ListView) viewRoot.findViewById( br.com.abaco.chronos.menubar.R.id.listViewCancelamentoAgendamento );

			listViewAgendamentoCancelamento.setAdapter( adapter );

			listViewAgendamentoCancelamento.setOnItemClickListener( new OnItemClickListener() {

				@Override
				public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
					final AgendamentoCancelamentoItem itemSelecionado = (AgendamentoCancelamentoItem) parent.getItemAtPosition( position );
					if ( itemSelecionado != null ) {

						MensagemUtil.addMsgConfirm( activity, "Confirmação", "Confirmar Cancelamento?", R.drawable.info_dark, new OnClickListener() {

							@Override
							public void onClick( DialogInterface dialog, int which ) {

								final AgendamentoVO agendamento = new AgendamentoVO();
								agendamento.setCodgAgendamentoSeqc( itemSelecionado.getIdRegistro() );

								final String jsonAgendamento = jsonUtil.toJson( agendamento, new TypeToken<AgendamentoVO>() {
								}.getType() );

								activity.getIntent().putExtra( activity.getString( R.string.lb_requisicao_cancelar_agendamento ), jsonAgendamento );

								new LoadingRealizarCancelamentoAgendamentoAsync( activity, viewRoot , menu ).execute();
							}
						} );

					}
				}
			} );

		}
	}

}