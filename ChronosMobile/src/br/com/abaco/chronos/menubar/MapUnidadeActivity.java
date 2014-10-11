package br.com.abaco.chronos.menubar;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * @author jackson
 */
public class MapUnidadeActivity extends FragmentActivity {

	private AtualizadorDePosicao atualizador;

	@Override
	protected void onCreate( Bundle arg0 ) {
		super.onCreate( arg0 );
		setContentView( br.com.abaco.chronos.menubar.R.layout.activity_localizacao_unidade );

		final FragmentManager manager = getSupportFragmentManager();

		final FragmentTransaction fragmentTransaction = manager.beginTransaction();
		final LocalizacaoUnidadeFragment localizacaoUnidadeFragment = new LocalizacaoUnidadeFragment( null, null );
		fragmentTransaction.replace( br.com.abaco.chronos.menubar.R.id.mapa, localizacaoUnidadeFragment );
		fragmentTransaction.commit();

		atualizador = new AtualizadorDePosicao( this , localizacaoUnidadeFragment);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		atualizador.cancelar();
	}
}
