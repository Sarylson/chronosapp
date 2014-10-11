package br.com.abaco.chronos.avaliacaoatendimento.itemavaliacao.asynctasks;

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
import br.com.abaco.chronos.util.RestUtil;
import br.com.abaco.customs.AdpterListViewCustom;
import br.com.abaco.customs.ItemListCustom;
import br.com.abaco.modelo.itemavaliacao.ItemAvaliacaoVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Loading seleção do Item de avaliação
 * 
 * @author Jackson Silva
 */
public class LoadingSelecionarItemAvaliacao extends AsyncTask<Void, Void, String> {

	private ProgressDialog progressDialog;

	private Gson jsonUtil = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

	private Activity activity;

	private View viewRoot;

	private Menu menu;

	private FragmentManager fragmentManager;

	public LoadingSelecionarItemAvaliacao( final Activity activity, final View view, final Menu menu, final FragmentManager fragmentManager ) {
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

		final String jsonItemAvaliacao = activity.getIntent().getStringExtra( this.activity.getString( R.string.lb_requisicao_avaliacao_atendimento_tipo_avaliacao ) );

		final String retorno = RestUtil.post( activity.getString( R.string.url_rest_listar_itens_avaliacao ), jsonItemAvaliacao );
		return retorno;
	}

	@Override
	protected void onPostExecute( String result ) {
		progressDialog.dismiss();

		if ( result != null && !result.isEmpty() ) {
			
			final List<ItemAvaliacaoVO> itensAvaliacao = jsonUtil.fromJson( result, new TypeToken<List<ItemAvaliacaoVO>>() {}.getType() );
			
			final List<ItemListCustom> listaItems = new ArrayList<ItemListCustom>();

			// Setar os valores do itens aqui.
			for ( final ItemAvaliacaoVO itemAvaliacaoFor : itensAvaliacao ) {
				ItemListCustom custom = new ItemListCustom( itemAvaliacaoFor.getId(), R.drawable.expandir_back, itemAvaliacaoFor.getNomeItemAvaliacao() );
				listaItems.add( custom );
			}
			final AdpterListViewCustom adapter = new AdpterListViewCustom( this.activity, R.layout.listview_custom, listaItems );
			final ListView listViewTipoItemAvaliacaoSelecionado = (ListView) viewRoot.findViewById( br.com.abaco.chronos.menubar.R.id.listViewSelecionarItemAvaliacaoAtendimento );
			listViewTipoItemAvaliacaoSelecionado.setAdapter( adapter );
			listViewTipoItemAvaliacaoSelecionado.setOnItemClickListener( new OnItemClickListener() {

				@Override
				public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
					final ItemListCustom itemCustomList = (ItemListCustom) parent.getItemAtPosition( position );

					ItemAvaliacaoVO itemAvaliacao = new ItemAvaliacaoVO();
					itemAvaliacao.setId( itemCustomList.getIdRegistro() );
					itemAvaliacao.setNomeItemAvaliacao( itemCustomList.getTitulo() );
					
					final String jsonItemAvalicao = jsonUtil.toJson( itemAvaliacao,new TypeToken<ItemAvaliacaoVO>() {}.getType() );
					activity.getIntent().putExtra( activity.getString( R.string.lb_requisicao_avaliacao_atendimento_item_avaliacao ), jsonItemAvalicao );
					
					final AvaliarAtendimentoRealizarAvaliacaoFragment avaliarAtendimentoRealizarAvaliacao = new AvaliarAtendimentoRealizarAvaliacaoFragment(menu,fragmentManager);
					fragmentManager.beginTransaction().replace( R.id.container, avaliarAtendimentoRealizarAvaliacao ).addToBackStack( avaliarAtendimentoRealizarAvaliacao.getClass().getName() ).commit();
					
				}
			} );
		}
	}
}
