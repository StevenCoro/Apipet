package apipet.web.apipet;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class PautasMascotas extends AppCompatActivity {
    private Button btnVideo;
    private VideoView video;
    private MediaController controlVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pautas_mascotas);
        hideNavigationBar();



        btnVideo = (Button)findViewById(R.id.btnVideo);
        getWindow().setFormat(PixelFormat.UNKNOWN);
        video = (VideoView)findViewById(R.id.videoView);
        controlVideo = new MediaController(this);

                String uriPath2 = "android.resource://apipet.web.apipet/"+R.raw.ejemplo;
        Uri uri2 = Uri.parse(uriPath2);
        video.setVideoURI(uri2);
        video.setMediaController(controlVideo);
        controlVideo.setAnchorView(video);
        video.requestFocus();
        video.start();
        hideNavigationBar();


        btnVideo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                video = (VideoView)findViewById(R.id.videoView);
                String uriPath = "android.resource://apipet.web.apipet/"+R.raw.ejemplo;
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
    public void Back(View view){
        Intent i = new Intent(this,ConsidScreen.class);
        startActivity(i);
        finish();
    }
    public void VerMas(View view){
        Intent i = new Intent(this,WebPautasMascota.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        hideNavigationBar();
    }
}
