package br.com.abaco.chronos.menubar;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

/**
 * @author jackson
 */
public class Localizador {

	private Context context;

	public Localizador( final Context context ) {
		this.context = context;
	}

	public LatLng getCoordenada( String endereco ) {
		final Geocoder geocoder = new Geocoder( context );

		try {
			final List<Address> enderecos = geocoder.getFromLocationName( endereco, 1 );
			if ( !enderecos.isEmpty() ) {
				Address enderecoLocalizado = enderecos.get( 0 );

				double latitude = enderecoLocalizado.getLatitude();
				double longitude = enderecoLocalizado.getLongitude();

				return new LatLng( latitude, longitude );
			} else {
				return null;
			}
		} catch ( IOException e ) {
			e.printStackTrace();
			return null;
		}
	}
}
