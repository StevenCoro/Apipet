package apipet.web.apipet.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import apipet.web.apipet.R;

public class RegistroScreen extends AppCompatActivity {

    /*TextView tvText;
    TextView tvPassword;
    Button btnRegistrar;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_screen);
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

    @Override
    protected void onPostResume() {
        super.onPostResume();
        hideNavigationBar();
    }

    public class activity_register extends AppCompatActivity implements View.OnClickListener {

        //defining view objects
        private EditText TextEmail;
        private EditText TextPassword;
        private Button btnRegistrar;
        private ProgressDialog progressDialog;


        //Declaramos un objeto firebaseAuth
        private FirebaseAuth firebaseAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registro_screen);

            //inicializamos el objeto firebaseAuth
            firebaseAuth = FirebaseAuth.getInstance();

            //Referenciamos los views
            TextEmail = (EditText) findViewById(R.id.edEmail);
            TextPassword = (EditText) findViewById(R.id.editText2);

            btnRegistrar = (Button) findViewById(R.id.btn_registrarse);

            progressDialog = new ProgressDialog(this);

            //attaching listener to button
            btnRegistrar.setOnClickListener(this);
        }

        private void registrarUsuario(){

            //Obtenemos el email y la contraseña desde las cajas de texto
            String email = TextEmail.getText().toString().trim();
            String password  = TextPassword.getText().toString().trim();

            //Verificamos que las cajas de texto no esten vacías
            if(TextUtils.isEmpty(email)){
                Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
                return;
            }

            if(TextUtils.isEmpty(password)){
                Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
                return;
            }


            progressDialog.setMessage("Realizando registro en linea...");
            progressDialog.show();

            //creating a new user
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if(task.isSuccessful()){

                                Toast.makeText(activity_register.this,"Se ha registrado el usuario con el email: "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                            }else{

                                Toast.makeText(activity_register.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

        }

        @Override
        public void onClick(View view) {
            //Invocamos al método:
            registrarUsuario();
        }
    }



}