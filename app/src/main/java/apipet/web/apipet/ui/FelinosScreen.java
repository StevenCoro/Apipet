package apipet.web.apipet.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import apipet.web.apipet.R;

public class FelinosScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_felinos_screen);
        hideNavigationBar();

        Button back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                onBackPressed();

            }
        });
        Button btn_felinos_criollo = findViewById(R.id.btn_felino_criollo);
        btn_felinos_criollo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i1 = new Intent(getApplicationContext(),Informacion_felinos_criollo.class);
                startActivity(i1);
            }
        });

        Button btn_felinos_angora = findViewById(R.id.btn_felino_angora);
        btn_felinos_angora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i1 = new Intent(getApplicationContext(),Informacion_felinos_angora.class);
                startActivity(i1);
            }
        });

        Button btn_felinos_persa = findViewById(R.id.btn_felino_persa);
        btn_felinos_persa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i1 = new Intent(getApplicationContext(),Informacion_felinos_persa.class);
                startActivity(i1);
            }
        });

        Button btn_felinos_ragdoll = findViewById(R.id.btn_felino_ragdoll);
        btn_felinos_ragdoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i1 = new Intent(getApplicationContext(),Informacion_felinos_ragdoll.class);
                startActivity(i1);
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
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        hideNavigationBar();
    }

    private Handler mHandler = new Handler();
    private Runnable decor_view_settings = new Runnable()
    {
        public void run()
        {
            getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    Rect r = new Rect();
                    getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                    int screenHeight = getWindow().getDecorView().getRootView().getHeight();

                    int keypadHeight = screenHeight - r.bottom;

                    //Log.d(TAG, "keypadHeight = " + keypadHeight);

                    if (keypadHeight > screenHeight * 0.15) {
                        hideNavigationBar();
                    }
                    else {
                        hideNavigationBar();
                    }
                }
            });



        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus)
        {
            mHandler.post(decor_view_settings);
            hideNavigationBar();
        }
        else {
            mHandler.post(decor_view_settings);
            hideNavigationBar();
        }
    }
}
