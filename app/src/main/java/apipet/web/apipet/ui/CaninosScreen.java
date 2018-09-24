package apipet.web.apipet.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import apipet.web.apipet.R;

public class CaninosScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caninos_screen);
        hideNavigationBar();

        Button back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();

            }
        });

        Button btn_canino_criollo = findViewById(R.id.btn_canino_criollo);
        btn_canino_criollo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent i1 = new Intent(getApplicationContext(),Informacion_caninos_criollo.class);
                startActivity(i1);


            }
        });
        Button btn_golden_retriever = findViewById(R.id.btn_golden);
        btn_golden_retriever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent i1 = new Intent(getApplicationContext(),Informacion_caninos_golden_retriever.class);
                startActivity(i1);


            }
        });
        Button btn_pitbull = findViewById(R.id.btn_pitbull);
        btn_pitbull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent i1 = new Intent(getApplicationContext(),Informacion_caninos_pitbull.class);
                startActivity(i1);


            }
        });
        Button btn_bulldog = findViewById(R.id.btn_bulldog);
        btn_bulldog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent i1 = new Intent(getApplicationContext(),Informacion_caninos_bulldog.class);
                startActivity(i1);


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
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        hideNavigationBar();
    }
}
