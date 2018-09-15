package apipet.web.apipet.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import apipet.web.apipet.R;

public class RazasScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razas_screen);
        hideNavigationBar();

        Button btn_atras = (Button)findViewById(R.id.back_btn);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                onBackPressed();

            }
        });

        Button btn_felinos = (Button)findViewById(R.id.btn_felinos);
        btn_felinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i2 = new Intent(getApplicationContext(),FelinosScreen.class);
                startActivity(i2);

            }
        });
        Button btn_caninos = (Button)findViewById(R.id.btn_caninos);
        btn_caninos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i2 = new Intent(getApplicationContext(),CaninosScreen.class);
                startActivity(i2);

            }
        });
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
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
}
