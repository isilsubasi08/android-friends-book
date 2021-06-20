package com.example.isil_friends_add;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //MainActivitye menu ekleyeceğiz. Bu menü sayesinde istediğimiz aktiviteye gidebileceğiz.
    //res->new directory->menu içerisinde itemler ekleyerek menu oluşturmuş oluyoruz.bunu burda tanıtmamız lazım.
    //2 metodu override etmem lazım. 1- onCreateOptionsMenu 2- onOptionsItemSelected

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Hangi menuyu göstereceğiz onu belirliyoruz.
        //xml dosyasını inflater etmem lazım. Tanıtmam lazım.
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_friends,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.add_friends_item){
            Intent intent=new Intent(MainActivity.this,DetailyActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}