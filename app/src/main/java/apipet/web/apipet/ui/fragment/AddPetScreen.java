package apipet.web.apipet.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import apipet.web.apipet.R;
import apipet.web.apipet.ui.MisMascotasScreen;

import static apipet.web.apipet.ui.MisMascotasScreen.tvNombreMascota1;
import static apipet.web.apipet.ui.MisMascotasScreen.tvNombreMascota2;
import static apipet.web.apipet.ui.MisMascotasScreen.tvNombreMascota3;
import static apipet.web.apipet.ui.MisMascotasScreen.tvNombreMascota4;


public class AddPetScreen extends AppCompatActivity {

    private Spinner spinnerTipo, spinnerRaza, spinnerGenero;
    public static final String nombreDelArchivo1 = "datosNombre1.txt";
    public static final String nombreDelArchivo2 = "datosNombre2.txt";
    public static final String nombreDelArchivo3 = "datosNombre3.txt";
    public static final String nombreDelArchivo4 = "datosNombre4.txt";
    public static final String nombreDelArchivo5 = "datosNombre5.txt";



    EditText etNombre;
    ImageView imagenMascota;
    ImageView imagen_mascota, imagen_mascota2, imagen_mascota3, imagen_mascota4;

    public static String path;

    public static final String carpetaRaiz="ImagenesDePeluditos/";
    public static final String rutaImagen =carpetaRaiz+"Peluditos";

    final int codigoSeleccionSubirImagen =10;
    final int codigoSeleccionTomarFoto =20;


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

        imagenMascota =(findViewById(R.id.imagen_add_mascota));
        imagen_mascota =(findViewById(R.id.imagen_mascota));
        imagen_mascota2 =(findViewById(R.id.imagen_mascota2));
        imagen_mascota3 =(findViewById(R.id.imagen_mascota3));
        imagen_mascota4 =(findViewById(R.id.imagen_mascota4));

    }

    public void AddImagenMascota(View view){
        cargarImagen();
    }

    private void cargarImagen() {

        final CharSequence[] opciones= {"Tomar Foto", "Subir Imagen"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(AddPetScreen.this);
        alertOpciones.setTitle("Seleccione una opción");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (opciones[i].equals("Tomar Foto")){
                            TomarFoto();
                        }
                        else{
                            if (opciones[i].equals("Subir Imagen")){

                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/");
                                startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), codigoSeleccionSubirImagen);

                            }
                        }
                    }
                });
                alertOpciones.show();


    }

    private void TomarFoto() {
        File fileImagen = new File(Environment.getExternalStorageDirectory(), rutaImagen);
        boolean isCreada = fileImagen.exists();
        String nombreImagen = "";

        if (!isCreada){
            isCreada=fileImagen.mkdirs();
        }
        if (isCreada){
            nombreImagen= (System.currentTimeMillis()/1000)+".jpg";
        }

        path=Environment.getExternalStorageDirectory()+File.separator+rutaImagen+File.separator+nombreImagen;

        File imagen = new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        startActivityForResult(intent,codigoSeleccionTomarFoto);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){

            switch (requestCode){
                case codigoSeleccionSubirImagen:
                     Uri path2 = data.getData();
                    imagenMascota.setImageURI(path2);


                    break;

                case codigoSeleccionTomarFoto:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("Ruta de almacenamiento", "path: "+path);
                                }
                            });

                    Bitmap bitmap2 = BitmapFactory.decodeFile(path);
                    imagenMascota.setImageBitmap(bitmap2);
                    break;
            }


        }

    }

    public void GuardarDatos(View view){
        int cont =1;


        etNombre = findViewById(R.id.etNombre);
        String nombreMascota = etNombre.getText().toString();
        String seleccionTipo = spinnerTipo.getSelectedItem().toString();
        String seleccionRaza = spinnerRaza.getSelectedItem().toString();
        String seleccionGenero = spinnerGenero.getSelectedItem().toString();



        FileOutputStream fos = null;

        if (nombreMascota.isEmpty()){
            Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_LONG).show();
        }
        else{
            try {

                if (cont==1){
                    fos = openFileOutput(nombreDelArchivo1, MODE_PRIVATE);
                    fos.write(nombreMascota.getBytes());
                    Toast.makeText(this, "Guardado con éxito en: " + getFilesDir() + "/" + nombreDelArchivo1, Toast.LENGTH_LONG).show();


                }
                else {
                    if (cont==2){
                        fos = openFileOutput(nombreDelArchivo2, MODE_PRIVATE);
                        fos.write(nombreMascota.getBytes());
                        Toast.makeText(this, "Guardado con éxito en: " + getFilesDir() + "/" + nombreDelArchivo2, Toast.LENGTH_LONG).show();

                    }
                }
                if (cont==3) {
                    fos = openFileOutput(nombreDelArchivo3, MODE_PRIVATE);
                    fos.write(nombreMascota.getBytes());
                    Toast.makeText(this, "Guardado con éxito en: " + getFilesDir() + "/" + nombreDelArchivo3, Toast.LENGTH_LONG).show();

                }
                else {
                    if (cont==4) {
                        fos = openFileOutput(nombreDelArchivo4, MODE_PRIVATE);
                        fos.write(nombreMascota.getBytes());
                        Toast.makeText(this, "Guardado con éxito en: " + getFilesDir() + "/" + nombreDelArchivo4, Toast.LENGTH_LONG).show();

                    }
                }
                if (cont==5){
                   fos = openFileOutput(nombreDelArchivo5, MODE_PRIVATE);
                   fos.write(nombreMascota.getBytes());
                   Toast.makeText(this, "Guardado con éxito en: " + getFilesDir() + "/" + nombreDelArchivo5, Toast.LENGTH_LONG).show();
                }

                Intent i2 = new Intent(getApplicationContext(),MisMascotasScreen.class);
                startActivity(i2);
                etNombre.getText().clear();
            }
            catch (FileNotFoundException e) {
                   e.printStackTrace();
            }
            catch (IOException e) {
                  e.printStackTrace();
            }
            finally {
                if(fos!=null){
                    try {
                        fos.close();
                    }
                    catch (IOException e) {
                          e.printStackTrace();
                    }
                }
            }
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
        hideNavigationBar();
    }
}