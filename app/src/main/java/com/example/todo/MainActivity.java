package com.example.todo;

//import static com.example.todo.MainActivity2.SHARED_PREFS;
//import static com.example.todo.MainActivity2.TEXT;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MyAdapter adapter;
    String activities[];
    Button button;

    SharedPreferences Sprefs;
    private String prefName = "report";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sprefs = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = Sprefs.edit();
        editor.putInt("counter",0);

        activities = getResources().getStringArray(R.array.activities);
        ArrayList<String> animalNames = new ArrayList<>();
        ArrayList<String> timeList = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<String> monthList = new ArrayList<>();
        ArrayList<String> yearList = new ArrayList<>();
        ArrayList<String> hourList = new ArrayList<>();
        ArrayList<String> minList = new ArrayList<>();
        ArrayList<String> finalList = new ArrayList<>();

//        for (int i=0;i<activities.length;i++){
//            animalNames.add(activities[i]);
//        }

        // data to populate the RecyclerView with

        // set up the RecyclerView

//        for(int i=0;i<)

        SharedPreferences sp=getSharedPreferences(prefName,MODE_PRIVATE);
        Integer counter=sp.getInt("counter",0);
        for(int i=0;i<counter;i++){
            String s=sp.getString("activity"+i,"");
            String time=sp.getString("time"+i,"");
            animalNames.add(i,s);
            timeList.add(i,time);
            dateList.add(i,sp.getString("day"+i,""));
            monthList.add(i,sp.getString("month"+i,""));
            yearList.add(i,sp.getString("year"+i,""));
            hourList.add(i,sp.getString("hour"+i,""));
            minList.add(i,sp.getString("min"+i,""));
        }

        for(int i=0;i<counter;i++){
            //finalList.add(dateList.get(i)+'/'+monthList.get(i)+' '+yearList.get(i)+hourList.get(i)+minList.get(i));
            finalList.add(dateList.get(i)+'/'+monthList.get(i)+' '+hourList.get(i)+':'+minList.get(i));
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, animalNames,timeList,finalList);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences(prefName,MODE_PRIVATE);
                Integer counter=sp.getInt("counter",0);
                for(int i=0;i<counter;i++){
                    String s=sp.getString("activity"+i,"");
                    animalNames.add(i,s);
                }

                Toast.makeText(MainActivity.this, "counter = "+counter, Toast.LENGTH_SHORT).show();
                for(int i=0;i<animalNames.size();i++){
                    String ss=animalNames.get(i);
                    Toast.makeText(MainActivity.this, ""+ss, Toast.LENGTH_SHORT).show();
                }

//                SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
//                String text=sharedPreferences.getString(TEXT,"vgh");
//                ArrayList<String> animalNames2 = new ArrayList<>();
//                animalNames2.add(text);
//

//                RecyclerView recyclerView = findViewById(R.id.recyclerView);
//                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                adapter = new MyAdapter(MainActivity.this, animalNames);

                //adapter.notifyItemInserted(counter-1);
                //adapter.notifyItemRangeRemoved(0,2);
//                adapter.notifyItemRemoved(0);
//                adapter.notifyItemRemoved(0);
               // adapter.notifyItemInserted(counter-1);

                recyclerView.setAdapter(adapter);
            }
        });
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // do something here
            //Toast.makeText(MainActivity.this, "bitchhhhhhh yessssssss", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,MainActivity2.class);
            startActivity(intent);
            //Toast.makeText(MainActivity.this, "we're back here", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onItemClick(View view, int position) {
//        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
//    }
}