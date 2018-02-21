package com.ideabinbd.carouselbmicalculator;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ideabinbd.carouselbmicalculator.adapter.BMIDataRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    Dialog customDialog;
    Realm realm;
    RecyclerView allDataRecycler;
    BMIDataRecyclerAdapter bmiDataRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm= Realm.getDefaultInstance();


        allDataRecycler= findViewById(R.id.recycler_all_data);
        OrderedRealmCollection<BmiData> dataToSend=realm.where(BmiData.class).findAll();
        bmiDataRecyclerAdapter= new BMIDataRecyclerAdapter(dataToSend,true);

        allDataRecycler.setLayoutManager(new LinearLayoutManager(this));
        allDataRecycler.setAdapter(bmiDataRecyclerAdapter);
        allDataRecycler.setHasFixedSize(true);

        fab= findViewById(R.id.add_data);
        customDialog= new Dialog(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            customDialog.setContentView(R.layout.add_dialog);
            customDialog.setTitle("Input your data");
                Button enterData, clearData;
                final EditText edtWeight, edtHeight;

                edtWeight= customDialog.findViewById(R.id.txt_weight);
                edtHeight= customDialog.findViewById(R.id.txt_height);
                enterData= customDialog.findViewById(R.id.btn_enterData);
                clearData= customDialog.findViewById(R.id.btn_clear);

                enterData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final double inpWeight, inpHeight;
                        inpWeight= Double.parseDouble(edtWeight.getText().toString());
                        inpHeight= Double.parseDouble(edtHeight.getText().toString()) * 0.3048;

                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {

                                Number currentIdNum = realm.where(BmiData.class).max("id");

                                int nextId;
                                if(currentIdNum == null) {
                                    nextId = 1;
                                } else {
                                    nextId = currentIdNum.intValue() + 1;
                                }
                                double bmi = ((inpWeight)/(inpHeight* inpHeight));
                                BmiData newData= realm.createObject(BmiData.class,nextId);

                                newData.setHeight(inpHeight);
                                newData.setWeight(inpWeight);
                                newData.setBmi(bmi);
                                newData.setTimeDate(TDFormatter.getTimeDate(System.currentTimeMillis()));

                            }
                        });

                        bmiDataRecyclerAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, inpWeight+" nd " + inpHeight, Toast.LENGTH_SHORT).show();
                        customDialog.dismiss();
                    }
                });

                clearData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                        customDialog.dismiss();
                    }
                });
            customDialog.show();

            }
        });
    }


    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
