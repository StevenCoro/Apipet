package apipet.web.apipet.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import apipet.web.apipet.R;
import apipet.web.apipet.io.Usuario;

public class InicioSesion extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    EditText etEmailInicio, etPasswordInicio;
    Button btnIngresar;
    int cont =0;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    //private ProgressDialog progressDialog;

    String validarcorreo, validarclave;

    //RequestQueue rq;
    //JsonObjectRequest jrq;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        etEmailInicio =  findViewById(R.id.etUserInicio);
        etPasswordInicio = findViewById(R.id.etPasswordInicio);
        btnIngresar =  findViewById(R.id.btn_ingresar);

        firebaseAuth = FirebaseAuth.getInstance();

        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 750);
        toast.setDuration(Toast.LENGTH_LONG);

        final TextView tv = new TextView(InicioSesion.this);
        tv.setBackgroundColor(Color.argb(100, 0, 0, 0));
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(15);
        tv.setPadding(10, 10, 10, 10);


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    if (!user.isEmailVerified()){
                        tv.setText("Por favor verifica el correo electrónico");
                        toast.setView(tv);
                        toast.show();

                    }else{
                        Intent i3 = new Intent(getApplicationContext(), MainScreen.class);
                        startActivity(i3);
                    }
                }

            }
        };

        //progressDialog = new ProgressDialog(this);


        //rq = Volley.newRequestQueue(getApplicationContext());

        Button btn_registro = findViewById(R.id.btn_register);
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
                Ingresar();



                //cargarWebService();



            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null)
            firebaseAuth.removeAuthStateListener(authStateListener);

    }

    private void Ingresar() {

        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 750);
        toast.setDuration(Toast.LENGTH_SHORT);

        final TextView tv = new TextView(InicioSesion.this);
        tv.setBackgroundColor(Color.argb(100, 0, 0, 0));
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(15);
        tv.setPadding(10, 10, 10, 10);

        //Obtenemos el email y la contraseña desde las cajas de texto
        String email = etEmailInicio.getText().toString().trim();
        String password  = etPasswordInicio.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(email)){
            tv.setText("Debes ingresar un correo");
            toast.setView(tv);
            toast.show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            tv.setText("Debes ingresar una contraseña");
            toast.setView(tv);
            toast.show();
            return;
        }


        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    tv.setText("Corrreo y/o contraseña incorrectos");
                    toast.setView(tv);
                    toast.show();
                }

                authStateListener.onAuthStateChanged(firebaseAuth);
            }
        });

    }





    @Override
    public void onErrorResponse(VolleyError error) {

        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 750);
        toast.setDuration(Toast.LENGTH_SHORT);

        final TextView tv = new TextView(InicioSesion.this);
        tv.setBackgroundColor(Color.argb(100, 0, 0, 0));
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(15);
        tv.setPadding(10, 10, 10, 10);

        tv.setText("Ha ocurrido un error de conexión");
        toast.setView(tv);
        toast.show();

    }

    @Override
    public void onResponse (JSONObject response) {


        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 750);
        toast.setDuration(Toast.LENGTH_SHORT);

        final TextView tv = new TextView(InicioSesion.this);
        tv.setBackgroundColor(Color.argb(100, 0, 0, 0));
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(15);
        tv.setPadding(10, 10, 10, 10);

        Usuario miUsuario = new Usuario();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            miUsuario.setUser(jsonObject.optString("correo"));
            miUsuario.setPwd(jsonObject.optString("contrasena"));



        } catch (JSONException e) {
            e.printStackTrace();
        }

        validarcorreo= (jsonObject.optString("correo"));
        validarclave= (jsonObject.optString("contrasena"));

        if (validarcorreo.equals(etEmailInicio.getText().toString())){
            if (validarclave.equals(etPasswordInicio.getText().toString())){
                Intent noriel = new Intent(getApplicationContext(), MainScreen.class);
                startActivity(noriel);
            }
            else if (!validarclave.equals(etPasswordInicio.getText().toString())){
                tv.setText("Usuario y/o Contraseña Incorrectos.");
                toast.setView(tv);
                toast.show();
            }
        }

        else if(!validarcorreo.equals(etEmailInicio.getText().toString())){
            tv.setText("Usuario y/o Contraseña Incorrectos.");
            toast.setView(tv);
            toast.show();
        }


    }

    /*private void cargarWebService() {


        String url = "http://192.168.0.14/apipet/JSONInicio.php?correo="+etEmailInicio.getText().toString()+"&contrasena="+etPasswordInicio.getText().toString();
        url = url.replace(" ", "%20");


        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);

    }*/






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
