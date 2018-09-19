package apipet.web.apipet.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import apipet.web.apipet.R;

public class InicioSesion extends AppCompatActivity {

    TextView tvEmail;
    TextView tvPassword;
    Button btnIngresar;
    FirebaseAuth mAuth;
    int cont =0;

    @Override
    public void onBackPressed() {
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0,750);
        toast.setDuration(Toast.LENGTH_SHORT);

        TextView tv = new TextView(InicioSesion.this);
        tv.setBackgroundColor(Color.argb(99, 0, 0, 0));
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(15);

        tv.setPadding(10, 10,10,10);
        tv.setText("Presiona de nuevo para salir");
        toast.setView(tv);




        if (cont==0){
            toast.show();
            cont++;
        }
        else {
            super.onBackPressed();
            finishAffinity();
            System.exit(0);
        }
        new CountDownTimer(3000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                cont =0;
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        hideNavigationBar();

        tvEmail = (TextView) findViewById(R.id.etUser);
        tvPassword = (TextView) findViewById(R.id.etPassword);
        btnIngresar = (Button) findViewById(R.id.btn_ingresar);

        Button btn_registro = (Button)findViewById(R.id.btn_registrarse);
        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i2 = new Intent(getApplicationContext(),RegistroScreen.class);
                startActivity(i2);

            }
        });
        Button btn_ingresar = (Button)findViewById(R.id.btn_ingresar);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
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
