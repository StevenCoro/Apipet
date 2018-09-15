package apipet.web.apipet.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import apipet.web.apipet.R;

public class WebPautasMascota extends AppCompatActivity {
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_pautas_mascota);
        web = (WebView)findViewById(R.id.webView);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("https://www.google.com/");
        WebSettings webSettigs = web.getSettings();
        webSettigs.setJavaScriptEnabled(true);
        hideNavigationBar();

        Button back_btn= (Button)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
              onBackPressed();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (web.canGoBack()){
            web.goBack();
        }else{
            super.onBackPressed();

        }
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
    }

}
