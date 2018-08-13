package apipet.web.apipet;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class PautasMascotas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pautas_mascotas);
        hideNavigationBar();



        Button btnVideo = (Button)findViewById(R.id.btnVideo);
        getWindow().setFormat(PixelFormat.UNKNOWN);

        final VideoView video = (VideoView)findViewById(R.id.videoView);

        String uriPath2 = "android.resource://com.apipet.web.apipet/"+R.raw.ejemplo;
        Uri uri2 = Uri.parse(uriPath2);
        video.setVideoURI(uri2);
        video.requestFocus();
        video.start();

        btnVideo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoView video = (VideoView)findViewById(R.id.videoView);
                String uriPath = "android.resource://com.apipet.web.apipet/"+R.raw.ejemplo;
                Uri uri2 = Uri.parse(uriPath);video.requestFocus(); video.start();


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
}
