package apipet.web.apipet.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import apipet.web.apipet.R;
import apipet.web.apipet.io.Usuario;

public class InicioSesion extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    EditText etEmail, etPassword;
    Button btnIngresar;
    int cont =0;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        etEmail =  findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        btnIngresar =  findViewById(R.id.btn_ingresar);

        request = Volley.newRequestQueue(getApplicationContext());

        Button btn_registro = (Button)findViewById(R.id.btn_register);
        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i2 = new Intent(getApplicationContext(),RegistroScreen.class);
                startActivity(i2);

            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                /*Intent i2 = new Intent(getApplicationContext(),MainScreen.class);
                startActivity(i2);
                finish();*/
                cargarWebService();

            }
        });

    }

    private void cargarWebService() {
        String url = "http://192.168.0.14/apipet/JSONInicio.php?correo="+etEmail.getText().toString();

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse (JSONObject response) {

        Toast.makeText(getApplicationContext(), "Mensaje: "+response, Toast.LENGTH_SHORT).show();



        Usuario miUsuario = new Usuario();

        JSONArray json = response.optJSONArray("usuarios");
        JSONObject jsonObject = null;


        try {
            jsonObject = json.getJSONObject(0);
            miUsuario.setClave(jsonObject.optString("contrasena"));
            miUsuario.setCorreo(jsonObject.optString("correo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        etPassword.setText(miUsuario.getClave());


    }






    @Override
    public void onBackPressed() {
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0,750);
        toast.setDuration(Toast.LENGTH_SHORT);

        TextView tv = new TextView(InicioSesion.this);
        tv.setBackgroundColor(Color.argb(99, 0, 0, 0));
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(15);

        tv.setPadding(10, 10,10,10);
        tv.setText("Presiona de nuevo para salir");
        toast.setView(tv);




        if (cont==0){
            toast.show();
            cont++;
        }
        else {
            super.onBackPressed();
            finishAffinity();
            System.exit(0);
        }
        new CountDownTimer(3000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                cont =0;
            }
        }.start();
    }



    @Override
    protected void onPostResume() {
        super.onPostResume();
        hideNavigationBar();
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
