package apipet.web.apipet.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import apipet.web.apipet.ui.fragment.ExitoInformacion;
import apipet.web.apipet.R;

public class MapScreen extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private Marker markerExito, markerDoctorPet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
        hideNavigationBar();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void hideNavigationBar() {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        hideNavigationBar();
        // Add a marker in Sydney and move the camera
        LatLng rionegro = new LatLng(6.144809634626859, -75.37547391822147);
        LatLng doctorPet = new LatLng(6.149245088205296, -75.37840385773495);
        LatLng exito = new LatLng(6.1485018460937395, -75.37822723388672);

        markerExito = googleMap.addMarker(new MarkerOptions()
                .position(exito)
                .title("Éxito Rionegro").snippet("Se permite el ingreso de animales pero con condiciones")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_lugares)));

        markerDoctorPet = googleMap.addMarker(new MarkerOptions()
                .position(doctorPet)
                .title("Veterinaria Doctor Pet")
                .snippet("Ubicada en la cra 55b al frente del éxito, tienen atención de lunes a viernes de 6:00am a 8:00pm")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rionegro, 13));
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnInfoWindowClickListener(this);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
    if (marker.equals(markerExito)){
        ExitoInformacion.newInstance(marker.getTitle(),getString(R.string.InformacionExito)).show(getSupportFragmentManager(), null);

    }


    }
    public void Back(View view){
        Intent i = new Intent(this, MainScreen.class);
        startActivity(i);
        finish();
    }
}
