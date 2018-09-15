package apipet.web.apipet.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import apipet.web.apipet.R;

public class ConsidScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consid_screen);
        hideNavigationBar();

        Button back_btn = (Button)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
               onBackPressed();


            }
        });

        Button btn_pautas = (Button)findViewById(R.id.btn_pautas);
        btn_pautas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i2 = new Intent(getApplicationContext(),PautasMascotas.class);
                startActivity(i2);

            }
        });


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
    protected void onPostResume() {
        super.onPostResume();
        hideNavigationBar();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainScreen.class);
        startActivity(i);
    }


}
