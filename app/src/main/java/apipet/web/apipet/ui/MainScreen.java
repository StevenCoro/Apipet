package apipet.web.apipet.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import apipet.web.apipet.R;

public class MainScreen extends AppCompatActivity  implements View.OnClickListener{



    CardView cardView_opciones;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_screen);
        hideNavigationBar();
        cardView_opciones = findViewById(R.id.card_view_opciones);

        Button btn_mapa = findViewById(R.id.btn_mapa);
        btn_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i2 = new Intent(getApplicationContext(),MapScreen.class);
                startActivity(i2);

            }
        });
        Button btn_mascotas = (Button)findViewById(R.id.btn_mascotas);
        btn_mascotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                Intent i3 = new Intent(getApplicationContext(),MascotasScreen.class);
                startActivity(i3);

            }
        });
        Button btn_consideraciones = (Button)findViewById(R.id.btn_consideraciones);
        btn_consideraciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v4) {
                Intent i4 = new Intent(getApplicationContext(),ConsidScreen.class);
                startActivity(i4);

            }
        });
        final Button btn_opciones = (Button)findViewById(R.id.option_btn);
        btn_opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                cardView_opciones.setVisibility(View.VISIBLE);
                btn_opciones.setVisibility(View.GONE);

            }

        });
        final Button btn_close_option = (Button)findViewById(R.id.btn_close);
        btn_close_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                cardView_opciones.setVisibility(View.GONE);
                btn_opciones.setVisibility(View.VISIBLE);

            }

        });
         Button btn_salir = (Button)findViewById(R.id.btn_salir);
        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                finishAffinity();
                System.exit(0);

            }

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        System.exit(0);
        Toast.makeText(this, "Cerrar", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        hideNavigationBar();
    }

    public void hideNavigationBar(){
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
    @Override
    public void onClick(View v) {

    }

}
