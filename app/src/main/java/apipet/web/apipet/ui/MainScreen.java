package apipet.web.apipet.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import apipet.web.apipet.R;

public class MainScreen extends AppCompatActivity  implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_screen);
        hideNavigationBar();

        Button btn_mapa = (Button)findViewById(R.id.btn_mapa);
        btn_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i2 = new Intent(getApplicationContext(),MapScreen.class);
                startActivity(i2);
                finish();

            }
        });
        Button btn_mascotas = (Button)findViewById(R.id.btn_mascotas);
        btn_mascotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                Intent i3 = new Intent(getApplicationContext(),MascotasScreen.class);
                startActivity(i3);
                finish();

            }
        });
        Button btn_consideraciones = (Button)findViewById(R.id.btn_consideraciones);
        btn_consideraciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v4) {
                Intent i4 = new Intent(getApplicationContext(),ConsidScreen.class);
                startActivity(i4);
                finish();

            }
        });
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
