package com.example.isil_friends_add;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class DetailyActivity extends AppCompatActivity {


    Bitmap selectedImage;
    ImageView imageView;
    EditText etName,etPhoneNumber;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaily);

        imageView=findViewById(R.id.imageView);
        etName=findViewById(R.id.etName);
        etPhoneNumber=findViewById(R.id.etPhoneNumber);
        save=findViewById(R.id.btnSave);
    }

    public void selectImage(View view){
        //Dosyaları erişim iznini sormamız gerekiyor.
        //Sadece 1 kez soracağız. Alınmışsa bir daha almayacağız.
        //ContextCompat -> API 23'den önce bu izinler sorulmuyordu.Ve bu geliştirmeyle karışıklık olmaması için böyle bir yapı geliştirilmiş.
        //checkSelfPermission -> İzin alınıp alınmadığının kontrolü yapılıyor.
        //requestCode -> Hangi izin için olduğunu belirtmek için integer bir değer yazıyoruz.

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            //izin verilmemişse,izin istiyoruz.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        }else{
            //İzin verilmişse galeriye gitmemiz, galeriyi açmamız gerekiyor.
            Intent intentToGalery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentToGalery,2);
        }




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intentToGalery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGalery,2);
            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //startForActivity ile galeriye gittik ve orda resim seçildiğinde bu resmi imagevievde göstermek için bu metodu kullanmak zorundayım.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {

        //ContentResolver -> Bitmapi Uri'ye dönüştürmek için bunu yapabiliyorum.

        if(requestCode==2 && resultCode==RESULT_OK && data !=null){
            Uri imageData=data.getData();

            try {

                if(Build.VERSION.SDK_INT >= 28){
                    ImageDecoder.Source source=ImageDecoder.createSource(this.getContentResolver(),imageData);
                    selectedImage=ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(selectedImage);
                }else{

                    selectedImage=MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
                    imageView.setImageBitmap(selectedImage);

                }


            }catch (IOException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}