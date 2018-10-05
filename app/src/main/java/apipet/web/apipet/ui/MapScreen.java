package apipet.web.apipet.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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

public class MapScreen extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener, View.OnClickListener {

    private GoogleMap mMap;
    private Marker markerExito, markerDoctorPet, markerLaGranjita, markerVeterinariaSanBernardo, markerVeterinariaCaninos
            , markerVeterinariaAgroQuirama, markerVeterinariaCVPets, markerAnimalLoversStore
            , markerCentroVeterinarioSanAntonio, markerVeterinariaDelValle, markerKanu
            , markerPetLand, markerPetigree, markerOrejasYColas;
    Dialog MyDialog;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Button btn_atras = (Button)findViewById(R.id.back_btn);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                onBackPressed();
            }
        });



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

        // Add a marker in Sydney and move the camera
        LatLng rionegro = new LatLng(6.144809634626859, -75.37547391822147);
        LatLng doctorPet = new LatLng(6.149191003444293, -75.37856574565893);
        LatLng exito = new LatLng(6.1485018460937395, -75.37822723388672);
        LatLng veterinariaSanAntonio = new LatLng(6.1279032, -75.38182210000002);
        LatLng veterinariaLaGranjita = new LatLng(6.145088105435121, -75.37932234963792);
        LatLng veterinariaCaninos = new LatLng(6.1390017, -75.3848885);
        LatLng veterinariaSanBernardo = new LatLng(6.154878399999999, -75.37314429999998);
        LatLng veterinariaAgroQuirama = new LatLng(6.1565503, -75.37095369999997);
        LatLng animalLoversStore = new LatLng(6.149365882100768, -75.36773443222046);
        LatLng veterinariaCVPets = new LatLng(6.149088537362608, -75.37930279970169);
        LatLng clinicaVeterinariaDelValle = new LatLng(6.132544857643609, -75.4019847495116);
        LatLng veterinariaKanu = new LatLng(6.125982303327703, -75.41916664685185);
        LatLng veterinariaPetLand = new LatLng(6.125877272250506, -75.41963151377627);
        LatLng veterinariaPetigree = new LatLng(6.145649, -75.379218);
        LatLng veterinariaOrejasYColas = new LatLng(6.143998, -75.379844);


        markerExito = googleMap.addMarker(new MarkerOptions()
                .position(exito)
                .title("Éxito Rionegro").snippet("Se permite el ingreso de animales pero con condiciones")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_lugares)));


        markerDoctorPet = googleMap.addMarker(new MarkerOptions()
                .position(doctorPet)
                .title("Veterinaria Doctor Pet")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));

        markerCentroVeterinarioSanAntonio = googleMap.addMarker(new MarkerOptions()
                .position(veterinariaSanAntonio)
                .title("Veterinaria San Antonio")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));

        markerLaGranjita = googleMap.addMarker(new MarkerOptions()
                .position(veterinariaLaGranjita)
                .title("Veterinaria La Granjita")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));

        markerVeterinariaCaninos = googleMap.addMarker(new MarkerOptions()
                .position(veterinariaCaninos)
                .title("Veterinaria Caninos")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));

        markerVeterinariaSanBernardo = googleMap.addMarker(new MarkerOptions()
                .position(veterinariaSanBernardo)
                .title("Veterinaria San Bernardo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));



        markerVeterinariaAgroQuirama = googleMap.addMarker(new MarkerOptions()
                .position(veterinariaAgroQuirama)
                .title("Veterinaria AgroQuirama")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));

        markerAnimalLoversStore = googleMap.addMarker(new MarkerOptions()
                .position(animalLoversStore)
                .title("Animal Lovers Store")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));



        markerVeterinariaCVPets = googleMap.addMarker(new MarkerOptions()
                .position(veterinariaCVPets)
                .title("Veterinaria CVPets")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));


        markerVeterinariaDelValle = googleMap.addMarker(new MarkerOptions()
                .position(clinicaVeterinariaDelValle)
                .title("Clinica Veterinaria Del Valle")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));

        markerKanu = googleMap.addMarker(new MarkerOptions()
                .position(veterinariaKanu)
                .title("Centro de mascotas Kanú")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));

        markerPetLand = googleMap.addMarker(new MarkerOptions()
                .position(veterinariaPetLand)
                .title("Veterinaria PetLand")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));

        markerPetigree = googleMap.addMarker(new MarkerOptions()
                .position(veterinariaPetigree)
                .title("Centro de mascotas Petigree")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));

        markerOrejasYColas = googleMap.addMarker(new MarkerOptions()
                .position(veterinariaOrejasYColas)
                .title("Orejas y colas")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_mapa_veterinarias)));




        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rionegro, 15));
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
    if (marker.equals(markerDoctorPet)){
            MyDialog = new Dialog(MapScreen.this);
            MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            MyDialog.setContentView(R.layout.fragment_doctor_pet);
            MyDialog.setTitle("My Custom Dialog");
            MyDialog.show();
    }
     if (marker.equals(markerLaGranjita)){
            MyDialog = new Dialog(MapScreen.this);
            MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            MyDialog.setContentView(R.layout.fragment_veterinaria_la_granjita);
            MyDialog.setTitle("My Custom Dialog");
            MyDialog.show();
    }
        if (marker.equals(markerCentroVeterinarioSanAntonio)){
            MyDialog = new Dialog(MapScreen.this);
            MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            MyDialog.setContentView(R.layout.fragment_centro_veterinario_san_antonio);
            MyDialog.setTitle("My Custom Dialog");
            MyDialog.show();
    }
        if (marker.equals(markerVeterinariaCaninos)){
            MyDialog = new Dialog(MapScreen.this);
            MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            MyDialog.setContentView(R.layout.fragment_veterinaria_caninos);
            MyDialog.setTitle("My Custom Dialog");
            MyDialog.show();
    }

        if (marker.equals(markerVeterinariaSanBernardo)){
            MyDialog = new Dialog(MapScreen.this);
            MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            MyDialog.setContentView(R.layout.fragment_veterinaria_san_bernardo);
            MyDialog.setTitle("My Custom Dialog");
            MyDialog.show();
    }

        if (marker.equals(markerVeterinariaAgroQuirama)){
            MyDialog = new Dialog(MapScreen.this);
            MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            MyDialog.setContentView(R.layout.fragment_veterinaria_agro_quirama);
            MyDialog.setTitle("My Custom Dialog");
            MyDialog.show();
    }
        if (marker.equals(markerAnimalLoversStore)){
            MyDialog = new Dialog(MapScreen.this);
            MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            MyDialog.setContentView(R.layout.fragment_animal_lovers_store);
            MyDialog.setTitle("My Custom Dialog");
            MyDialog.show();
    }

         if (marker.equals(markerVeterinariaCVPets)){
            MyDialog = new Dialog(MapScreen.this);
            MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            MyDialog.setContentView(R.layout.fragment_veterinaria_cvpets);
            MyDialog.setTitle("My Custom Dialog");
            MyDialog.show();
    }

        if (marker.equals(markerVeterinariaDelValle)){
            MyDialog = new Dialog(MapScreen.this);
            MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            MyDialog.setContentView(R.layout.fragment_veterinaria_del_valle);
            MyDialog.setTitle("My Custom Dialog");
            MyDialog.show();
    }

        if (marker.equals(markerKanu)){
            MyDialog = new Dialog(MapScreen.this);
            MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            MyDialog.setContentView(R.layout.fragment_kanu);
            MyDialog.setTitle("My Custom Dialog");
            MyDialog.show();
        }

        if (marker.equals(markerPetLand)){
            MyDialog = new Dialog(MapScreen.this);
            MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            MyDialog.setContentView(R.layout.fragment_petland);
            MyDialog.setTitle("My Custom Dialog");
            MyDialog.show();
        }

        if (marker.equals(markerPetigree)){
            MyDialog = new Dialog(MapScreen.this);
            MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            MyDialog.setContentView(R.layout.fragment_petigree);
            MyDialog.setTitle("My Custom Dialog");
            MyDialog.show();
        }

        if (marker.equals(markerOrejasYColas)){
            MyDialog = new Dialog(MapScreen.this);
            MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            MyDialog.setContentView(R.layout.fragment_orejas_colas);
            MyDialog.setTitle("My Custom Dialog");
            MyDialog.show();
        }









    }
    @Override
    public void onClick(View v) {

    }
}
