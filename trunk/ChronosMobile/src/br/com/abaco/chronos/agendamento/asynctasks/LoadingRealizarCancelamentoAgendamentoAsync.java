package br.com.abaco.chronos.agendamento.asynctasks;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import br.com.abaco.chronos.agendamento.customs.AdapterAgendamentoCancelamento;
import br.com.abaco.chronos.agendamento.customs.AgendamentoCancelamentoItem;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.chronos.util.MensagemUtil;
import br.com.abaco.chronos.util.RestUtil;
import br.com.abaco.modelo.senha.SenhaVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author jackson
 */
public class LoadingRealizarCancelamentoAgendamentoAsync extends AsyncTask<Void, Void, String> {

	private ProgressDialog progressDialog;

	private Gson jsonUtil = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

	private Activity activity;

	private View viewRoot;
	
	private Menu menu;

	public LoadingRealizarCancelamentoAgendamentoAsync( final Activity activity, final View view , final Menu menu) {
		super();
		this.activity = activity;
		this.viewRoot = view;
		this.menu = menu;
		progressDialog = new ProgressDialog( activity );
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage( "Carregando..." );
		progressDialog.show();
	}

	@Override
	protected String doInBackground( Void... params ) {

		final String jsonAgendamento = this.activity.getIntent().getStringExtra( activity.getString( R.string.lb_requisicao_cancelar_agendamento ) );

		final String retorno = RestUtil.post( activity.getString( R.string.url_rest_cancelar_agendamento ), jsonAgendamento );
		return retorno;
	}

	@Override
	protected void onPostExecute( String result ) {
		progressDialog.dismiss();
		// final Button confirmar = (Button) rootView.findViewById( R.id.btn_confirmar_agendamento );
		// confirmar.setVisibility( View.INVISIBLE );
	   if ( result != null && result.equalsIgnoreCase( "FALHA" ) ) {
	   	MensagemUtil.addMsg( activity, "Ocorreu um problema na comunicação com o servidor!\n tente novamente mais tarde." );
	   }else if( result != null && !result.isEmpty() ){
			
			if( result.contains( "Error:" )){
			    MensagemUtil.addMsgOk( this.activity, "Informação", result, R.drawable.info_dark );
			}else{
				
				final List<SenhaVO> senhas = jsonUtil.fromJson( result, new TypeToken<List<SenhaVO>>() {}.getType() );

				final List<AgendamentoCancelamentoItem> listaItems = new ArrayList<AgendamentoCancelamentoItem>();
				for ( final SenhaVO senhaFor : senhas ) {

					AgendamentoCancelamentoItem agendamentoCancelamentoItem = new AgendamentoCancelamentoItem();
					agendamentoCancelamentoItem.setIdRegistro( senhaFor.getAgendamento().getCodgAgendamentoSeqc() );
					agendamentoCancelamentoItem.setData( senhaFor.getAgendamento().getDataAgendamentoFormatada() );
					agendamentoCancelamentoItem.setSenha( senhaFor.getNumeroSenha() );
					agendamentoCancelamentoItem.setUnidadeAtendimento( senhaFor.getAgendamento().getUnidadeNegocio().getNome() );

					listaItems.add( agendamentoCancelamentoItem );
				}
				
				final AdapterAgendamentoCancelamento adapter = new AdapterAgendamentoCancelamento( this.activity, R.layout.listview_custom_cancelamento_agendamento, listaItems );

				final ListView listViewAgendamentoCancelamento = (ListView) viewRoot.findViewById( br.com.abaco.chronos.menubar.R.id.listViewCancelamentoAgendamento );

				listViewAgendamentoCancelamento.setAdapter( adapter );

				 MensagemUtil.addMsg(this.activity, "Cancelamento realizado com sucesso!");
				 
				 new LoadingCancelamentoAgendamento( activity, viewRoot, menu ).execute();
//				listViewAgendamentoCancelamento.setOnItemClickListener( new OnItemClickListener() {
//
//					@Override
//					public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
//						final AgendamentoCancelamentoItem itemSelecionado = (AgendamentoCancelamentoItem) parent.getItemAtPosition( position );
//						if ( itemSelecionado != null ) {
//
//							MensagemUtil.addMsgConfirm( activity, "Confirmação", "Confirmar Cancelamento?", R.drawable.info_dark,new OnClickListener() {
//								
//								@Override
//								public void onClick( DialogInterface dialog, int which ) {
//									
//									final AgendamentoVO agendamento = new AgendamentoVO();
//									agendamento.setCodgAgendamentoSeqc( itemSelecionado.getIdRegistro() );
//									
//									final String jsonAgendamento = jsonUtil.toJson( agendamento,new TypeToken<AgendamentoVO>() {}.getType() );
//									
//									activity.getIntent().putExtra( activity.getString( R.string.lb_requisicao_cancelar_agendamento ), jsonAgendamento );
//									
//									new LoadingRealizarCancelamentoAgendamentoAsync(activity, viewRoot).execute();
//								}
//							} );
//						
//						}
//					}
//				} );
			}
		}
	}
}
