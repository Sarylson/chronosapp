package br.com.abaco.chronos.menubar;

import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * @author jackson
 */
public class AtualizadorDePosicao implements LocationListener {

	private LocationManager locationManager;
	private LocalizacaoUnidadeFragment localizacaoUnidadeFragment;

	public AtualizadorDePosicao( final Activity activity ,final LocalizacaoUnidadeFragment localizacaoUnidadeFragment) {

		this.localizacaoUnidadeFragment = localizacaoUnidadeFragment;
		locationManager = (LocationManager) activity.getSystemService( Context.LOCATION_SERVICE );
		String provider = LocationManager.GPS_PROVIDER;
		long tempoMinimo = 2000; // ms
		float distanciaMinima = 20;// m
		// provider, minTime, minDistance, listener
		locationManager.requestLocationUpdates( provider, tempoMinimo, distanciaMinima, this );
		
	}

	public void cancelar() {
		locationManager.removeUpdates( this );
	}

	@Override
	public void onLocationChanged( Location novaLocalizacao ) {
		
		final double latitude = novaLocalizacao.getLatitude();
		final double longitude = novaLocalizacao.getLongitude();
		
		final LatLng local = new LatLng( latitude, longitude );
		
		localizacaoUnidadeFragment.centralizaNo( local );

	}

	@Override
	public void onStatusChanged( String provider, int status, Bundle extras ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled( String provider ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled( String provider ) {
		// TODO Auto-generated method stub

	}

}
