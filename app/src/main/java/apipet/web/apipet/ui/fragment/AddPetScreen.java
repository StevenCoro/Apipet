package apipet.web.apipet.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import apipet.web.apipet.R;
import apipet.web.apipet.ui.MisMascotasScreen;

import static apipet.web.apipet.ui.MisMascotasScreen.tvNombreMascota1;
import static apipet.web.apipet.ui.MisMascotasScreen.tvNombreMascota2;
import static apipet.web.apipet.ui.MisMascotasScreen.tvNombreMascota3;
import static apipet.web.apipet.ui.MisMascotasScreen.tvNombreMascota4;


public class AddPetScreen extends AppCompatActivity {

    private Spinner spinnerTipo, spinnerRaza, spinnerGenero;
    public static final String nombreDelArchivo = "datosNombre1.txt";
    public static final String nombreDelArchivo2 = "datosNombre2.txt";
    public static final String nombreDelArchivo3 = "datosNombre3.txt";
    public static final String nombreDelArchivo4 = "datosNombre4.txt";
    public static final String nombreDelArchivo5 = "datosNombre5.txt";
    public int cont=0;
    EditText etNombre;
    ImageView imagenMascota;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet_screen);

        spinnerTipo = (Spinner)findViewById(R.id.spnTipo);
        String [] tipo = {"Canino", "Felino"};
        ArrayAdapter <String> adapter= new ArrayAdapter<String>(this, R.layout.spinner_item_add_pet, tipo);
        spinnerTipo.setAdapter(adapter);

        String selection = spinnerTipo.getSelectedItem().toString();




        if(selection.equals("Canino")){
            spinnerRaza = (Spinner)findViewById(R.id.spnRaza);
            String [] raza = {"Criollo", "Golden retriever", "Pitbull", "Bulldog"};
            ArrayAdapter <String> adapter2= new ArrayAdapter<String>(this, R.layout.spinner_item_add_pet, raza);
            spinnerRaza.setAdapter(adapter2);

        }
        if(selection.equals("Felino")){
            spinnerRaza = (Spinner)findViewById(R.id.spnRaza);
            String[]raza = {"Criollo", "Persa", "Angora", "Ragdoll"};
            ArrayAdapter <String> adapter3= new ArrayAdapter<String>(this, R.layout.spinner_item_add_pet, raza);
            spinnerRaza.setAdapter(adapter3);}

        spinnerGenero = (Spinner)findViewById(R.id.spnGenero);
        String [] genero = {"Macho", "Hembra"};
        ArrayAdapter <String> adapter4= new ArrayAdapter<String>(this, R.layout.spinner_item_add_pet, genero);
        spinnerGenero.setAdapter(adapter4);

        hideNavigationBar();

        Button btn_atras = (Button)findViewById(R.id.btn_back);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i2 = new Intent(getApplicationContext(),MisMascotasScreen.class);
                startActivity(i2);
                finish();

            }
        });

        imagenMascota = (ImageView)(findViewById(R.id.imagen_add_mascota));


    }

    public void AddImagenMascota(View view){
        cargarImagen();
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri path = data.getData();
            imagenMascota.setImageURI(path);
        }

    }

    public void GuardarDatos(View view){
        etNombre = findViewById(R.id.etNombre);
        String nombreMascota = etNombre.getText().toString();
        String seleccionTipo = spinnerTipo.getSelectedItem().toString();
        String seleccionRaza = spinnerRaza.getSelectedItem().toString();
        String seleccionGenero = spinnerGenero.getSelectedItem().toString();


        FileOutputStream fos = null;
        cont=cont+1;
        if (nombreMascota.isEmpty()){
            Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_LONG).show();
        }
        else{
        try {

            switch (cont) {
                case 1:

                    fos = openFileOutput(nombreDelArchivo, MODE_PRIVATE);
                    fos.write(nombreMascota.getBytes());
                    Toast.makeText(this, "Guardado con éxito en: " + getFilesDir() + "/" + nombreDelArchivo, Toast.LENGTH_LONG).show();
                    cont=cont+1;
                    break;

                case 2:

                    fos = openFileOutput(nombreDelArchivo2, MODE_PRIVATE);
                    fos.write(nombreMascota.getBytes());
                    Toast.makeText(this, "Guardado con éxito en: " + getFilesDir() + "/" + nombreDelArchivo2, Toast.LENGTH_LONG).show();

                    break;

                case 3:

                    fos = openFileOutput(nombreDelArchivo3, MODE_PRIVATE);
                    fos.write(nombreMascota.getBytes());
                    Toast.makeText(this, "Guardado con éxito en: " + getFilesDir() + "/" + nombreDelArchivo3, Toast.LENGTH_LONG).show();

                    break;

                case 4:

                    fos = openFileOutput(nombreDelArchivo4, MODE_PRIVATE);
                    fos.write(nombreMascota.getBytes());
                    Toast.makeText(this, "Guardado con éxito en: " + getFilesDir() + "/" + nombreDelArchivo4, Toast.LENGTH_LONG).show();

                    break;

                case 5:
                    fos = openFileOutput(nombreDelArchivo5, MODE_PRIVATE);
                    fos.write(nombreMascota.getBytes());
                    Toast.makeText(this, "Guardado con éxito en: " + getFilesDir() + "/" + nombreDelArchivo5, Toast.LENGTH_LONG).show();

                    break;
                    default: break;

            }

            Intent i2 = new Intent(getApplicationContext(),MisMascotasScreen.class);
            startActivity(i2);
            etNombre.getText().clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }}



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
