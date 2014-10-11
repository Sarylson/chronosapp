package br.com.abaco.chronos.avaliacaoatendimento.tipoavaliacao.asynctasks;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.abaco.chronos.avaliacaoatendimento.AvaliarAtendimentoRealizarAvaliacaoFragment;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.customs.AdpterListViewCustom;
import br.com.abaco.customs.ItemListCustom;
import br.com.abaco.modelo.enums.EnumTipoItemAvaliacao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Loading seleção do tipos de itens para avaliação
 * 
 * @author Jackson Silva
 */
public class LoadingSelecionarTipoAvaliacao extends AsyncTask<Void, Void, String> {

	private ProgressDialog progressDialog;

	private Gson jsonUtil = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

	private Activity activity;

	private View viewRoot;

	private Menu menu;

	private FragmentManager fragmentManager;

	public LoadingSelecionarTipoAvaliacao( final Activity activity, final View view, final Menu menu, final FragmentManager fragmentManager ) {
		super();

		this.menu = menu;
		this.activity = activity;
		this.viewRoot = view;
		this.fragmentManager = fragmentManager;
		progressDialog = new ProgressDialog( activity );
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage( "Carregando..." );
		progressDialog.show();
	}

	@Override
	protected String doInBackground( Void... params ) {
		activity.getIntent().removeExtra( activity.getString( R.string.lb_requisicao_avaliacao_atendimento_item_avaliacao ) );
		return null;
	}

	@Override
	protected void onPostExecute( String result ) {
		progressDialog.dismiss();

		final List<ItemListCustom> listaItems = new ArrayList<ItemListCustom>();

		for ( final EnumTipoItemAvaliacao enumTipoItemAvaliacaoFor : EnumTipoItemAvaliacao.values() ) {

			ItemListCustom itemListCustom = new ItemListCustom( enumTipoItemAvaliacaoFor.getId(), R.drawable.expandir_back, enumTipoItemAvaliacaoFor.getTexto() );

			listaItems.add( itemListCustom );
		}
		final AdpterListViewCustom adapter = new AdpterListViewCustom( this.activity, R.layout.listview_custom, listaItems );
		final ListView listViewTipoItemAvaliacaoSelecionado = (ListView) viewRoot.findViewById( br.com.abaco.chronos.menubar.R.id.listViewSelecionarTipoAvaliacaoAtendimento );
		listViewTipoItemAvaliacaoSelecionado.setAdapter( adapter );
		listViewTipoItemAvaliacaoSelecionado.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
				final ItemListCustom itemCustomList = (ItemListCustom) parent.getItemAtPosition( position );

				activity.getIntent().putExtra(  activity.getString( R.string.lb_requisicao_avaliacao_atendimento_tipo_avaliacao ), String.valueOf( itemCustomList.getIdRegistro() ) );
				
				final AvaliarAtendimentoRealizarAvaliacaoFragment avaliarAtendimentoRealizarAvaliacao = new AvaliarAtendimentoRealizarAvaliacaoFragment(menu,fragmentManager);
				fragmentManager.beginTransaction().replace( R.id.container, avaliarAtendimentoRealizarAvaliacao ).addToBackStack( avaliarAtendimentoRealizarAvaliacao.getClass().getName() ).commit();
			}
		} );
	}
}

