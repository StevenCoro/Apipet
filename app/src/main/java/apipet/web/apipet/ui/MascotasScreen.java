package apipet.web.apipet.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import apipet.web.apipet.R;

public class MascotasScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mascotas_screen);
        hideNavigationBar();

        Button btn_razas = (Button)findViewById(R.id.btn_razas);
        btn_razas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i2 = new Intent(getApplicationContext(),RazasScreen.class);
                startActivity(i2);
                finish();

            }
        });

        Button btn_mis_mascotas = (Button)findViewById(R.id.btn_mis_mascotas);
        btn_mis_mascotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i2 = new Intent(getApplicationContext(),MisMascotasScreen.class);
                startActivity(i2);
                finish();

            }
        });

        Button btn_atras = (Button)findViewById(R.id.btn_back);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i2 = new Intent(getApplicationContext(),MainScreen.class);
                startActivity(i2);
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

}
