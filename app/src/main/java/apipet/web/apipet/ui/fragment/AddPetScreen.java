package apipet.web.apipet.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import apipet.web.apipet.R;
import apipet.web.apipet.io.GuardarImagenes;
import apipet.web.apipet.ui.MisMascotasScreen;
import apipet.web.apipet.ui.SplashScreen_IA;

import static apipet.web.apipet.ui.MisMascotasScreen.cardViewMascota1;
import static apipet.web.apipet.ui.MisMascotasScreen.imagenMascota1;
import static apipet.web.apipet.ui.MisMascotasScreen.imagenMascota2;
import static apipet.web.apipet.ui.MisMascotasScreen.imagenMascota3;
import static apipet.web.apipet.ui.MisMascotasScreen.imagenMascota4;
import static apipet.web.apipet.ui.MisMascotasScreen.tvMascota1;
import static apipet.web.apipet.ui.MisMascotasScreen.tvMascota2;
import static apipet.web.apipet.ui.MisMascotasScreen.tvMascota3;
import static apipet.web.apipet.ui.MisMascotasScreen.tvMascota4;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;


public class AddPetScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Spinner spinnerTipo, spinnerRaza, spinnerGenero;
    public static final String nombreDelArchivo1 = "datosNombre1.txt";
    public static final String nombreDelArchivo2 = "datosNombre2.txt";
    public static final String nombreDelArchivo3 = "datosNombre3.txt";
    public static final String nombreDelArchivo4 = "datosNombre4.txt";
    public static final String nombreDelArchivo5 = "datosNombre5.txt";

    public static  String nombreMascotaUno = "";
    public static  String nombreMascotaDos = "";
    public static  String nombreMascotaTres = "";
    public static  String nombreMascotaCuatro = "";
    public static  String nombreMascotaCinco = "";

    int cont =1;

    EditText etNombre;
    ImageView imagenMascota;
    ImageView imagen_mascota, imagen_mascota2, imagen_mascota3, imagen_mascota4;

    private StorageReference mStorage;

    public static String path;
    public static Bitmap bmap;
    public static Bitmap bmap2;
    public static Bitmap bmap3;
    public static Bitmap bmap4;

    public static final String carpetaRaiz="ImagenesDePeluditos/";
    public static final String rutaImagen =carpetaRaiz+"Peluditos";

    final int codigoSeleccionSubirImagen =10;
    final int codigoSeleccionTomarFoto =20;
    String [] tipo = {"Canino", "Felino"};
    String [] razas_caninos = {"Criollo", "Golden retriever", "Pitbull", "Bulldog", "Persa", "Angora", "Ragdoll"};
    String [] razas_felinos = {"Criollo", "Persa", "Angora", "Ragdoll"};
    String [] genero = {"Macho", "Hembra"};

    ArrayAdapter<String> adapter_caninos, adapter_felinos, adapter_genero;

    DatabaseReference mRootReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet_screen);

        mStorage = FirebaseStorage.getInstance().getReference();

        mRootReference = FirebaseDatabase.getInstance().getReference();




        spinnerTipo = findViewById(R.id.spnTipo);
        ArrayAdapter <String> adapter= new ArrayAdapter<>(this, R.layout.spinner_item_add_pet, tipo);
        spinnerTipo.setAdapter(adapter);
        spinnerTipo.setOnItemSelectedListener(this);


        spinnerRaza = findViewById(R.id.spnRaza);
        adapter_caninos = new  ArrayAdapter<>(this, R.layout.spinner_item_add_pet, razas_caninos);
        adapter_felinos = new ArrayAdapter<>(this, R.layout.spinner_item_add_pet, razas_felinos);
        spinnerRaza.setOnItemSelectedListener(this);


        spinnerGenero = findViewById(R.id.spnGenero);
        adapter_genero = new ArrayAdapter<>(this, R.layout.spinner_item_add_pet, genero);
        spinnerGenero.setAdapter(adapter_genero);
        spinnerGenero.setOnItemSelectedListener(this);

        hideNavigationBar();

        Button btn_back = findViewById(R.id.back_btn);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                onBackPressed();

            }
        });

        imagenMascota =(findViewById(R.id.imagen_add_mascota));
        imagen_mascota =(findViewById(R.id.imagen_mascota));
        imagen_mascota2 =(findViewById(R.id.imagen_mascota2));
        imagen_mascota3 =(findViewById(R.id.imagen_mascota3));
        imagen_mascota4 =(findViewById(R.id.imagen_mascota4));

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
            tvMascota1.setText(sb.toString());
            if (!tvMascota1.toString().isEmpty()){
                cont=2;
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
            tvMascota2.setText(sb.toString());
            if (!tvMascota2.toString().isEmpty()){
                cont=3;
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
            tvMascota3.setText(sb.toString());
            if (!tvMascota3.toString().isEmpty()){
                cont=4;
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
            tvMascota4.setText(sb.toString());

            if (!tvMascota4.toString().isEmpty()){
                cont=5;
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

    public void cargarImagen() {

        final CharSequence[] opciones= {"Tomar Foto", "Subir Imagen"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(AddPetScreen.this);
        alertOpciones.setTitle("Seleccione una opción");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")){
                    // TomarFoto();
                    Intent intent = new Intent(getApplicationContext(), SplashScreen_IA.class);
                    startActivity(intent);
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

                    StorageReference destino = mStorage.child("Peluditos").child(path2.getLastPathSegment());

                    destino.putFile(path2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        }
                    });
                    imagenMascota.setImageURI(path2);

                    imagenMascota.buildDrawingCache();
                    if (cont==1){
                        bmap = imagenMascota.getDrawingCache();
                        GuardarImagenes savefile = new GuardarImagenes();
                        savefile.SaveImage(getApplicationContext(), bmap);

                        imagenMascota1.setImageBitmap(bmap);
                    }
                    if (cont==2){
                        bmap2 = imagenMascota.getDrawingCache();
                        GuardarImagenes savefile = new GuardarImagenes();
                        savefile.SaveImage(getApplicationContext(), bmap2);

                        imagenMascota2.setImageBitmap(bmap2);
                    }
                    if (cont==3){
                        bmap3 = imagenMascota.getDrawingCache();
                        GuardarImagenes savefile = new GuardarImagenes();
                        savefile.SaveImage(getApplicationContext(), bmap3);

                        imagenMascota3.setImageBitmap(bmap3);
                    }
                    if (cont==4){
                        bmap4 = imagenMascota.getDrawingCache();
                        GuardarImagenes savefile = new GuardarImagenes();
                        savefile.SaveImage(getApplicationContext(), bmap4);

                        imagenMascota4.setImageBitmap(bmap4);
                    }



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

    @Override
    public void onBackPressed() {
       Intent intent = new Intent(getApplicationContext(), MisMascotasScreen.class);
       startActivity(intent);

    }

    public void GuardarDatos(View view){

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0,750);
        toast.setDuration(Toast.LENGTH_SHORT);

        TextView tv = new TextView(AddPetScreen.this);
        tv.setBackgroundColor(Color.argb(100, 0, 0,0));
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(15);

        tv.setPadding(10, 10,10,10);


        etNombre = findViewById(R.id.etNombre);
        String nombreMascota = etNombre.getText().toString();
        String seleccionTipo = spinnerTipo.getSelectedItem().toString();
        String seleccionRaza = spinnerRaza.getSelectedItem().toString();
        String seleccionGenero = spinnerGenero.getSelectedItem().toString();

        FileOutputStream fos = null;

        if (nombreMascota.isEmpty()){
            tv.setText("Debes ingresar un nombre");
            toast.setView(tv);
            toast.show();
        }
        else{
            try {

                if (cont==1){
                    fos = openFileOutput(nombreDelArchivo1, MODE_PRIVATE);
                    fos.write(nombreMascota.getBytes());
                    tv.setText("Mascota Registrada");
                    toast.setView(tv);
                    toast.show();






                }
                if (cont==2){
                    fos = openFileOutput(nombreDelArchivo2, MODE_PRIVATE);
                    fos.write(nombreMascota.getBytes());
                    tv.setText("Mascota Registrada");
                    toast.setView(tv);
                    toast.show();



                }
                if (cont==3) {
                    fos = openFileOutput(nombreDelArchivo3, MODE_PRIVATE);
                    fos.write(nombreMascota.getBytes());
                    tv.setText("Mascota Registrada");
                    toast.setView(tv);
                    toast.show();


                }
                if(cont==4) {
                    fos = openFileOutput(nombreDelArchivo4, MODE_PRIVATE);
                    fos.write(nombreMascota.getBytes());
                    tv.setText("Mascota Registrada");
                    toast.setView(tv);
                    toast.show();



                }
                if (cont==5){
                    fos = openFileOutput(nombreDelArchivo5, MODE_PRIVATE);
                    fos.write(nombreMascota.getBytes());
                    tv.setText("Mascota Registrada");
                    toast.setView(tv);
                    toast.show();
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

    private void SubirFirebase(String nombreMascota, String seleccionTipo, String seleccionRaza, String seleccionGenero) {
        Map<String, Object> datosMascota = new HashMap<>();
        datosMascota.put("NombreMascota", nombreMascota);
        datosMascota.put("TipoMascota", seleccionTipo);
        datosMascota.put("RazaMascota", seleccionRaza);
        datosMascota.put("GeneroMascota", seleccionGenero);

        cardViewMascota1.setVisibility(View.VISIBLE);

        mRootReference.child("Mascotas").push().setValue(datosMascota);
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

    @Override
    public void onItemSelected(AdapterView<?> a, View v, int position, long arg3) {

        switch (position){
            case 0:
                spinnerRaza.setAdapter(adapter_caninos);
                break;
            case 1:
                spinnerRaza.setAdapter(adapter_felinos);
                break;


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
