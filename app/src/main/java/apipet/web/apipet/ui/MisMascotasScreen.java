package apipet.web.apipet.ui;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import apipet.web.apipet.R;
import apipet.web.apipet.ui.fragment.AddPetScreen;
import static apipet.web.apipet.ui.fragment.AddPetScreen.nombreDelArchivo1;
import static apipet.web.apipet.ui.fragment.AddPetScreen.nombreDelArchivo2;
import static apipet.web.apipet.ui.fragment.AddPetScreen.nombreDelArchivo3;
import static apipet.web.apipet.ui.fragment.AddPetScreen.nombreDelArchivo4;
public class MisMascotasScreen extends AppCompatActivity {

    ImageView imagenMascota1;
    public static TextView tvNombreMascota1;
    public static TextView tvNombreMascota2;
    public static TextView tvNombreMascota3;
    public static TextView tvNombreMascota4;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri path = data.getData();
            imagenMascota1.setImageURI(path);
        }

    }

    @Override
    public void onBackPressed() {
        Intent i2 = new Intent(getApplicationContext(),MascotasScreen.class);
        startActivity(i2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_mascotas_screen);
        hideNavigationBar();

         imagenMascota1 = (ImageView)(findViewById(R.id.imagen_mascota));

         tvNombreMascota1 =findViewById(R.id.tvNombreMascota1);
         tvNombreMascota2 =findViewById(R.id.tvNombreMascota2);
         tvNombreMascota3 =findViewById(R.id.tvNombreMascota3);
         tvNombreMascota4 =findViewById(R.id.tvNombreMascota4);

         CardView cardViewMascota1 = (CardView)findViewById(R.id.cardViewMascota1);
         CardView cardViewMascota2 = (CardView)findViewById(R.id.cardViewMascota2);
         CardView cardViewMascota3 = (CardView)findViewById(R.id.cardViewMascota3);
         CardView cardViewMascota4 = (CardView)findViewById(R.id.cardViewMascota4);


        Button btn_atras = (Button)findViewById(R.id.back_btn);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
              onBackPressed();

            }
        });

        Button btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i2 = new Intent(getApplicationContext(),AddPetScreen.class);
                startActivity(i2);

            }
        });



        FileInputStream fis = null;
        try {
            fis = openFileInput(nombreDelArchivo1);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String nombreMascota1;
            while ((nombreMascota1 = br.readLine()) !=null){
                sb.append(nombreMascota1).append("\n");
            }
            tvNombreMascota1.setText(sb.toString());
            if (!tvNombreMascota1.toString().isEmpty()){
                cardViewMascota1.setVisibility(View.VISIBLE);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fis = openFileInput(nombreDelArchivo2);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String nombreMascota2;
            while ((nombreMascota2 = br.readLine()) !=null){
                sb.append(nombreMascota2).append("\n");
            }
            tvNombreMascota2.setText(sb.toString());
            if (!tvNombreMascota2.toString().isEmpty()){
                cardViewMascota2.setVisibility(View.VISIBLE);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            fis = openFileInput(nombreDelArchivo3);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String nombreMascota3;
            while ((nombreMascota3 = br.readLine()) !=null){
                sb.append(nombreMascota3).append("\n");
            }
            tvNombreMascota3.setText(sb.toString());
            if (!tvNombreMascota3.toString().isEmpty()){
                cardViewMascota3.setVisibility(View.VISIBLE);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            fis = openFileInput(nombreDelArchivo4);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String nombreMascota4;
            while ((nombreMascota4 = br.readLine()) !=null){
                sb.append(nombreMascota4).append("\n");
            }
            tvNombreMascota4.setText(sb.toString());

            if (!tvNombreMascota4.toString().isEmpty()){
                cardViewMascota4.setVisibility(View.VISIBLE);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    public void AddImagenMascota(View view){
        cargarImagen();
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);

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

