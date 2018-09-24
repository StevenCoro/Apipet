package apipet.web.apipet.ui;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import apipet.web.apipet.R;

public class RegistroScreen extends AppCompatActivity implements com.android.volley.Response.Listener<JSONObject>, com.android.volley.Response.ErrorListener{

   EditText correo_usuario, contrasena_usuario;
   Button btn_registrarse;
   RequestQueue request;
   JsonObjectRequest jsonObjectRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_screen);
        hideNavigationBar();

        correo_usuario = findViewById(R.id.etUsuario);
        contrasena_usuario = findViewById(R.id.etclave);
        btn_registrarse = findViewById(R.id.btn_register);

        request = Volley.newRequestQueue(getApplicationContext());


        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();

            }
        });
    }

    private void cargarWebService() {


        String url = "http://192.168.0.14/apipet/JSONRegistro.php?correo="+correo_usuario.getText().toString()+"&contrasena="+contrasena_usuario.getText().toString();

        url = url.replace(" ", "%20");

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }
    @Override
    public void onResponse(JSONObject response) {
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 750);
        toast.setDuration(Toast.LENGTH_SHORT);

        TextView tv = new TextView(RegistroScreen.this);
        tv.setBackgroundColor(Color.argb(100, 0, 0, 0));
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(15);

        tv.setPadding(10, 10, 10, 10);
        tv.setText("Se ha registrado con éxito");
        toast.setView(tv);
        toast.show();
        correo_usuario.setText("");
        contrasena_usuario.setText("");
        Intent i = new Intent(getApplicationContext(), MainScreen.class);
        startActivity(i);

    }



    @Override
    public void onErrorResponse(VolleyError error) {


        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 750);
        toast.setDuration(Toast.LENGTH_SHORT);

        TextView tv = new TextView(RegistroScreen.this);
        tv.setBackgroundColor(Color.argb(100, 0, 0, 0));
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(15);

        tv.setPadding(10, 10, 10, 10);
        tv.setText("No se ha podido registrar:"+ error.toString());
        toast.setView(tv);
        toast.show();
        Log.i("ERROR", error.toString());

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



