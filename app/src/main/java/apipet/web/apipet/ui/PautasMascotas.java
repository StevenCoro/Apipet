package apipet.web.apipet.ui;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import apipet.web.apipet.R;
import apipet.web.apipet.model.FullScreenMediaController;

public class PautasMascotas extends AppCompatActivity {
    private Button btnVideo;
    private VideoView video;
    private MediaController controlVideo;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pautas_mascotas);
        hideNavigationBar();

        Button btn_atras = (Button)findViewById(R.id.back_btn);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
             onBackPressed();

            }
        });

        Button btn_ver_mas = (Button)findViewById(R.id.btn_ver_mas);
        btn_ver_mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i2 = new Intent(getApplicationContext(),WebPautasMascota.class);
                startActivity(i2);

            }
        });




        btnVideo = (Button)findViewById(R.id.btnVideo);
        getWindow().setFormat(PixelFormat.UNKNOWN);
        video = (VideoView)findViewById(R.id.videoView);
        controlVideo = new MediaController(this);

        String fullScreen =  getIntent().getStringExtra("fullScreenInd");
        if("y".equals(fullScreen)){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }


        String uriPath2 = "android.resource://apipet.web.apipet/"+R.raw.reglas_mascotas;
        Uri uri2 = Uri.parse(uriPath2);
        video.setVideoURI(uri2);
        video.setMediaController(controlVideo);
        controlVideo = new FullScreenMediaController(this);
        controlVideo.setAnchorView(video);
        video.requestFocus();
        video.start();
        hideNavigationBar();


        btnVideo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                video = (VideoView)findViewById(R.id.videoView);
                String uriPath = "android.resource://apipet.web.apipet/"+R.raw.reglas_mascotas;
                Uri uri2 = Uri.parse(uriPath);video.requestFocus(); video.start();
                hideNavigationBar();


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
