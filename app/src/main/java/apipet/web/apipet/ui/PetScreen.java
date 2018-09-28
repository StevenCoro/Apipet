package apipet.web.apipet.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import apipet.web.apipet.R;

public class PetScreen extends AppCompatActivity {

    CardView cardView_opciones;
    boolean visible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_screen);

        hideNavigationBar();

        cardView_opciones = findViewById(R.id.cardViewOpciones);

        final Button btn_opciones = findViewById(R.id.option_btn);
        btn_opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                cardView_opciones.setVisibility(View.VISIBLE);
                btn_opciones.setVisibility(View.GONE);
                visible = true;


            }

        });
        final Button btn_close_option = findViewById(R.id.btn_close);
        btn_close_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                cardView_opciones.setVisibility(View.GONE);
                btn_opciones.setVisibility(View.VISIBLE);
                visible = false;

            }

        });

         Button btn_atras = findViewById(R.id.back_btn);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                onBackPressed();

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
        if(visible) {
            Button btn_opciones = findViewById(R.id.option_btn);
            cardView_opciones.setVisibility(View.GONE);
            btn_opciones.setVisibility(View.VISIBLE);
            visible = false;
        }
        else if(!visible){
            super.onBackPressed();
        }
    }
}
