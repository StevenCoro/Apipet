package apipet.web.apipet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RazasScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razas_screen);
        hideNavigationBar();
    }
    public void Back(View view){
        Intent i = new Intent(this, MascotasScreen.class);
        startActivity(i);
        finish();
    }
    public void Felinos(View view){
        Intent i = new Intent(this, FelinosScreen.class);
        startActivity(i);
        finish();
    }
    public void Caninos(View view){
        Intent i = new Intent(this, CaninosScreen.class);
        startActivity(i);
        finish();
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
        super.onPostResume(); hideNavigationBar();
    }
}
