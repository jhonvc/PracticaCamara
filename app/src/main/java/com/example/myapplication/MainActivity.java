package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageView img_camara;
    Button btn_start;
    final int PHOTO_CONSTANT = 1;
    String mAbsolutePâth = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_camara = findViewById(R.id.img_camara);
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TomarFotos();
            }
        });


    }

    private void TomarFotos() {
        Intent TomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (TomarFoto.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createfotoFile();

            } catch (Exception e) {
                e.printStackTrace();

            }
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(MainActivity.this, "com.codigo.recplants", photoFile);
                TomarFoto.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(TomarFoto, PHOTO_CONSTANT);

            }

        }

    }

    private File createfotoFile() throws IOException {
        String timestap = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());
        String imageFileName = "imagen" + timestap;

        File storageFile = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photoFile = File.createTempFile(imageFileName, "jpj", storageFile);
        mAbsolutePâth = photoFile.getAbsolutePath();
        return photoFile;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_CONSTANT && resultCode == RESULT_OK) ;
        Uri uri = Uri.parse(mAbsolutePâth);
        img_camara.setImageURI(uri);


    }
}





