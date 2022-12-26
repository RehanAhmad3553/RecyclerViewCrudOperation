package com.example.recyclercrudoperation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ExtendedFloatingActionButton floatingActionButton;
    ArrayList<MyMovieData>  List = new ArrayList<>();
    MyMovieAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton=findViewById(R.id.extended_fab);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);





        List.add( new MyMovieData("Venom","2018 film",R.drawable.venom));
        List.add(new MyMovieData("Batman Begins","2005 film",R.drawable.batman));
        List.add( new MyMovieData("Jumanji","2019 film",R.drawable.jumanji));
        List.add( new MyMovieData("Good Deeds","2012 film",R.drawable.good_deeds));
        List.add(new MyMovieData("Hulk","2003 film",R.drawable.hulk));
        List.add(new MyMovieData("Avatar","2009 film",R.drawable.avatar));



        LinearLayoutManager obj =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(obj);
        adapter = new MyMovieAdapter(List,MainActivity.this);
        recyclerView.setAdapter(adapter);






        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_layout);


                EditText editText = dialog.findViewById(R.id.et_first);
                EditText secondText = dialog.findViewById(R.id.et_Second);
                Button buttonAdd = dialog.findViewById(R.id.btn_add);


                buttonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String name = "";
                        String number = "";

                        if(editText.getText().toString().equals(""))
                        {
                            Toast.makeText(MainActivity.this, "Fill Edit Text", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            name = editText.getText().toString();
                        }
                        if(secondText.getText().toString().equals(""))
                        {

                            Toast.makeText(MainActivity.this, "Fill Movie Date", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            number  = secondText.getText().toString();
                        }

                        List.add(new MyMovieData(name,number,R.drawable.avatar));

                        adapter.notifyItemInserted(List.size()-1);
                        recyclerView.scrollToPosition(List.size()-1);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }
}