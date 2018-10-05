package apipet.web.apipet.ui;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;
import apipet.web.apipet.R;

public class RegistroScreen extends AppCompatActivity implements com.android.volley.Response.Listener<JSONObject>, com.android.volley.Response.ErrorListener{

   EditText correo_usuario, contrasena_usuario, contrasena_usuario2;
   Button btn_registrarse;
   RequestQueue request;
   JsonObjectRequest jsonObjectRequest;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_screen);
        hideNavigationBar();

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        correo_usuario = findViewById(R.id.etUsuario);
        contrasena_usuario = findViewById(R.id.etclave);
        contrasena_usuario2 = findViewById(R.id.etclave2);
        btn_registrarse = findViewById(R.id.btn_register);

        request = Volley.newRequestQueue(getApplicationContext());


        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cargarWebService();
                registrarUsuario();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
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

    private void registrarUsuario(){

        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 750);
        toast.setDuration(Toast.LENGTH_SHORT);

        final TextView tv = new TextView(RegistroScreen.this);
        tv.setBackgroundColor(Color.argb(100, 0, 0, 0));
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(15);
        tv.setPadding(10, 10, 10, 10);

        //Obtenemos el email y la contraseña desde las cajas de texto
        String email = correo_usuario.getText().toString().trim();
        String password  = contrasena_usuario.getText().toString().trim();

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

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();
        if (contrasena_usuario.getText().toString().equals(contrasena_usuario2.getText().toString())){
        //creando nuevo usuario
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //verificando validación
                        if(task.isSuccessful()){

                            tv.setText("Se ha registrado con éxito");
                            toast.setView(tv);
                            toast.show();
                            Intent i3 = new Intent(getApplicationContext(),MainScreen.class);
                            startActivity(i3);
                        }else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){ //Si el usuario ya existe
                                tv.setText("Este correo ya existe");
                                toast.setView(tv);
                                toast.show();
                            }
                            else{ tv.setText("No se ha podido registrar");
                                toast.setView(tv);
                                toast.show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
        }
        else {
                tv.setText("Las contraseñas deben coincidir");
                toast.setView(tv);
                toast.show();
                progressDialog.dismiss();

            }

    }



    }



