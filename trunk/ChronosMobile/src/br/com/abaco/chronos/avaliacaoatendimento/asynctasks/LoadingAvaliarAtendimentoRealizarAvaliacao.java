package br.com.abaco.chronos.avaliacaoatendimento.asynctasks;

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
import br.com.abaco.chronos.avaliacaoatendimento.itemavaliacao.SelecionarItemAvaliacaoFragment;
import br.com.abaco.chronos.avaliacaoatendimento.tipoavaliacao.SelecionarTipoAvaliacaoFragment;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.customs.AdpterListViewCustom;
import br.com.abaco.customs.ItemListCustom;
import br.com.abaco.modelo.enums.EnumAvaliacao;
import br.com.abaco.modelo.enums.EnumTipoItemAvaliacao;
import br.com.abaco.modelo.itemavaliacao.ItemAvaliacaoVO;
import br.com.abaco.modelo.unidadeorganizacional.UnidadeOrganizacionalVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Loading solicita avaliação de atendimentos
 * 
 * @author Jackson Silva
 */
public class LoadingAvaliarAtendimentoRealizarAvaliacao extends AsyncTask<Void, Void, String> {

	private ProgressDialog progressDialog;

	private Gson jsonUtil = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

	private Activity activity;

	private View viewRoot;

	private Menu menu;

	private FragmentManager fragmentManager;

	public LoadingAvaliarAtendimentoRealizarAvaliacao( final Activity activity, final View view, final Menu menu, final FragmentManager fragmentManager ) {
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

		return null;
	}

	@Override
	protected void onPostExecute( String result ) {
		progressDialog.dismiss();

		final String jsonItemAvalicao = (String) activity.getIntent().getStringExtra( activity.getString( R.string.lb_requisicao_avaliacao_atendimento_item_avaliacao ) );
		final ItemAvaliacaoVO itemAvalicao = jsonUtil.fromJson( jsonItemAvalicao, new TypeToken<ItemAvaliacaoVO>() {
		}.getType() );

		final String tipoAvaliacaoSelecionado = this.activity.getIntent().getStringExtra( this.activity.getString( R.string.lb_requisicao_avaliacao_atendimento_tipo_avaliacao ) );
		
		final List<ItemListCustom> listaItems = new ArrayList<ItemListCustom>();
		for ( final EnumAvaliacao enumAvaliacaoFor : EnumAvaliacao.values() ) {

			ItemListCustom itemListCustom = null;
			if ( enumAvaliacaoFor.getId() == EnumAvaliacao.TIPO_AVALIACAO.getId() ) {

				if ( tipoAvaliacaoSelecionado != null && !tipoAvaliacaoSelecionado.isEmpty() ) {
					String textoTipoAvaliacao = EnumTipoItemAvaliacao.valueOf( Long.valueOf( tipoAvaliacaoSelecionado ) ).getTexto();
					itemListCustom = new ItemListCustom( enumAvaliacaoFor.getId(), R.drawable.expandir_selecionado, textoTipoAvaliacao );
					listaItems.add( itemListCustom );
					continue;
				}
			}
			if ( enumAvaliacaoFor.getId() == EnumAvaliacao.ITEM_AVALIACAO.getId() ) {

				if ( itemAvalicao != null && itemAvalicao.getId() != null ) {
					String textoItemAvaliacao = itemAvalicao.getNomeItemAvaliacao();
					itemListCustom = new ItemListCustom( enumAvaliacaoFor.getId(), R.drawable.expandir_selecionado, textoItemAvaliacao );
					listaItems.add( itemListCustom );
					continue;
				}

			}
			itemListCustom = new ItemListCustom( enumAvaliacaoFor.getId(), R.drawable.expandir_back, enumAvaliacaoFor.getTexto() );
			listaItems.add( itemListCustom );
		}
		final AdpterListViewCustom adapter = new AdpterListViewCustom( this.activity, R.layout.listview_custom, listaItems );
		final ListView listViewTipoItemAvaliacaoSelecionado = (ListView) viewRoot.findViewById( br.com.abaco.chronos.menubar.R.id.listViewTipoAvaliacao );
		listViewTipoItemAvaliacaoSelecionado.setAdapter( adapter );
		listViewTipoItemAvaliacaoSelecionado.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
				final ItemListCustom itemCustomList = (ItemListCustom) parent.getItemAtPosition( position );

				EnumAvaliacao enumAvaliacao = EnumAvaliacao.valueOf( itemCustomList.getIdRegistro() );

				switch ( enumAvaliacao ) {

					case TIPO_AVALIACAO:
						final SelecionarTipoAvaliacaoFragment selecionarTipoAvaliacaoFragment = new SelecionarTipoAvaliacaoFragment( menu, fragmentManager );
						fragmentManager.beginTransaction().replace( R.id.container, selecionarTipoAvaliacaoFragment ).addToBackStack( selecionarTipoAvaliacaoFragment.getClass().getName() ).commit();
						break;

					case ITEM_AVALIACAO:
						
						final SelecionarItemAvaliacaoFragment selecionarItemAvaliacao = new SelecionarItemAvaliacaoFragment( menu, fragmentManager );
						fragmentManager.beginTransaction().replace( R.id.container, selecionarItemAvaliacao ).addToBackStack( selecionarItemAvaliacao.getClass().getName() ).commit();
						break;

				}

			}
		} );
	}
}
