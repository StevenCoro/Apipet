package apipet.web.apipet.ui;

import android.content.Intent;
import android.net.Uri;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import apipet.web.apipet.R;
import apipet.web.apipet.io.MascotasUsuario;
import apipet.web.apipet.ui.fragment.AddPetScreen;
import static apipet.web.apipet.ui.fragment.AddPetScreen.nombreDelArchivo1;
import static apipet.web.apipet.ui.fragment.AddPetScreen.nombreDelArchivo2;
import static apipet.web.apipet.ui.fragment.AddPetScreen.nombreDelArchivo3;
import static apipet.web.apipet.ui.fragment.AddPetScreen.nombreDelArchivo4;
public class MisMascotasScreen extends AppCompatActivity {

    public static ImageView imagenMascota1;
    public static TextView tvMascota1;
    public static TextView tvMascota2;
    public static TextView tvMascota3;
    public static TextView tvMascota4;

    public static String nombreMascota1;
    public static String nombreMascota2;
    public static String nombreMascota3;
    public static String nombreMascota4;

    public static CardView cardViewMascota1;
    boolean visible = false;
    Button btn_atras;
    CardView cardViewOpciones;
    private StorageReference mStorage;

    DatabaseReference mRootReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_mascotas_screen);
        hideNavigationBar();

        cardViewMascota1 = findViewById(R.id.cardViewMascota1);
        CardView cardViewMascota2 = findViewById(R.id.cardViewMascota2);
        CardView cardViewMascota3 = findViewById(R.id.cardViewMascota3);
        CardView cardViewMascota4 = findViewById(R.id.cardViewMascota4);


        tvMascota1 =findViewById(R.id.tvNombreMascota1);
        tvMascota2 =findViewById(R.id.tvNombreMascota2);
        tvMascota3 =findViewById(R.id.tvNombreMascota3);
        tvMascota4 =findViewById(R.id.tvNombreMascota4);


        tvMascota1.setText(getIntent().getStringExtra("primerNombre"));
        tvMascota2.setText(getIntent().getStringExtra("primerNombre"));
        tvMascota3.setText(getIntent().getStringExtra("primerNombre"));
        tvMascota4.setText(getIntent().getStringExtra("primerNombre"));


        if (!tvMascota1.getText().toString().equals("")){
            cardViewMascota1.setVisibility(View.VISIBLE);

        }


        if (!tvMascota2.getText().toString().equals("")){
            cardViewMascota1.setVisibility(View.VISIBLE);

        }

        if (!tvMascota3.getText().toString().equals("")){
            cardViewMascota1.setVisibility(View.VISIBLE);

        }

        if (!tvMascota4.getText().toString().equals("")){
            cardViewMascota1.setVisibility(View.VISIBLE);

        }





        mRootReference = FirebaseDatabase.getInstance().getReference();
        mStorage = FirebaseStorage.getInstance().getReference();


        mRootReference.child("Mascotas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for( final DataSnapshot snapshot : dataSnapshot.getChildren()){

                    mRootReference.child("Mascotas").child( snapshot.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            MascotasUsuario mascota = snapshot.getValue(MascotasUsuario.class);

                            String nombre_mascota = mascota.getNombreMascota();
                            String tipo_mascota = mascota.getTipoMascota();
                            String raza_mascota = mascota.getRazaMascota();
                            String genero_mascota = mascota.getGeneroMascota();

                            String [] nombres_mascotas = {mascota.getNombreMascota(), mascota.getNombreMascota()};






                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        cardViewOpciones = findViewById(R.id.cardViewOpciones);

         imagenMascota1 = (findViewById(R.id.imagen_mascota));







        final Button btn_atras = findViewById(R.id.back_btn);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
              onBackPressed();

            }
        });


        Button btn_close_option = findViewById(R.id.btn_close);
        btn_close_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                cardViewOpciones.setVisibility(View.GONE);
                visible = false;

            }

        });

        Button btn_uno = findViewById(R.id.btn_uno);
        btn_uno.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v2) {
               cardViewOpciones.setVisibility(View.VISIBLE);
                visible = true;

                return false;
            }

        });


        btn_uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                if (visible){

                }
                else{
                    Intent i = new Intent(getApplicationContext(),PetScreen.class);
                    startActivity(i);

                }


            }
        });




        Button btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i = new Intent(getApplicationContext(),AddPetScreen.class);
                startActivity(i);

            }
        });



        /*FileInputStream fis = null;
        try {
            fis = openFileInput(nombreDelArchivo1);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while ((nombreMascota1 = br.readLine()) !=null){
                sb.append(nombreMascota1).append("\n");
            }
            tvMascota1.setText(sb.toString());
            if (!nombreMascota1.toString().isEmpty()){
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
            while ((nombreMascota2 = br.readLine()) !=null){
                sb.append(nombreMascota2).append("\n");
            }
            tvMascota2.setText(sb.toString());
            if (!tvMascota2.toString().isEmpty()){
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
            while ((nombreMascota3 = br.readLine()) !=null){
                sb.append(nombreMascota3).append("\n");
            }
            tvMascota3.setText(sb.toString());
            if (!tvMascota3.toString().isEmpty()){
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
            while ((nombreMascota4 = br.readLine()) !=null){
                sb.append(nombreMascota4).append("\n");
            }
            tvMascota4.setText(sb.toString());

            if (!tvMascota4.toString().isEmpty()){
                cardViewMascota4.setVisibility(View.VISIBLE);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/




    }

    @Override
    public void onBackPressed() {

        if(visible) {

            cardViewOpciones.setVisibility(View.GONE);
            visible = false;
        }
        else if(!visible){
            super.onBackPressed();
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

