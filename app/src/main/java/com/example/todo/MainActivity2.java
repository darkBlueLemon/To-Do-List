package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    EditText editText;
    Button button;
    int count=0;

    NumberPicker numberPicker;
    NumberPicker numberPicker2;
    NumberPicker numberPicker3;
    NumberPicker numberPicker4;
    NumberPicker numberPicker5;

//    public static final String SHARED_PREFS="sharedPrefs";
//    public static final String TEXT="text";

    SharedPreferences Sprefs;
    private String prefName = "report";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView=findViewById(R.id.imageView2);
        textView=findViewById(R.id.textView3);
        button=findViewById(R.id.button);

        numberPicker=findViewById(R.id.monthPicker);
        numberPicker2=findViewById(R.id.datePicker);
        numberPicker3=findViewById(R.id.yearPicker);
        numberPicker4=findViewById(R.id.hourPicker);
        numberPicker5=findViewById(R.id.minPicker);

        Calendar cal=Calendar.getInstance();
        int date=cal.get(Calendar.DAY_OF_MONTH);
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);
        int hour=cal.get(Calendar.HOUR_OF_DAY);
        int min=cal.get(Calendar.MINUTE);

        numberPicker2.setMaxValue(31);
        numberPicker2.setMinValue(1);
        numberPicker2.setValue(date);

        numberPicker.setMaxValue(12);
        numberPicker.setMinValue(1);
        numberPicker.setValue(month+1);

        numberPicker3.setMaxValue(2030);
        numberPicker3.setMinValue(2022);
        numberPicker3.setValue(year);

        numberPicker4.setMaxValue(23);
        numberPicker4.setMinValue(0);
        numberPicker4.setValue(hour);

        numberPicker5.setMaxValue(59);
        numberPicker5.setMinValue(0);
        numberPicker5.setValue(min);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity2.this, "hiiiiiiiiiiiiiii", Toast.LENGTH_SHORT).show();
                if(count==0){
                    imageView.setImageResource(R.drawable.yellow);
                    textView.setText("medium");
                    count++;
                } else if(count==1){
                    imageView.setImageResource(R.drawable.orange);
                    textView.setText("high");
                    count++;
                } else if(count==2){
                    imageView.setImageResource(R.drawable.dot);
                    textView.setText("urgent");
                    count++;
                }
                else{
                    imageView.setImageResource(R.drawable.grey);
                    textView.setText("normal");
                    count=0;
                }
            }
        });

        editText=findViewById(R.id.editTextActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=editText.getText().toString();

                if(s.equals("")){
                    Toast.makeText(MainActivity2.this, "type something bitch", Toast.LENGTH_SHORT).show();
                } else {
                    String daySave, monthSave, yearSave, hourSave, minSave;
                    daySave = String.valueOf(numberPicker2.getValue());
                    monthSave = String.valueOf(numberPicker.getValue());
                    yearSave = String.valueOf(numberPicker3.getValue());
                    hourSave = String.valueOf(numberPicker4.getValue());
                    minSave = String.valueOf(numberPicker5.getValue());

                    if (Integer.valueOf(daySave) / 10 == 0) {
                        daySave = "0" + daySave;
                    }
                    if (Integer.valueOf(monthSave) / 10 == 0) {
                        monthSave = "0" + monthSave;
                    }
                    if (Integer.valueOf(yearSave) / 10 == 0) {
                        yearSave = "0" + yearSave;
                    }
                    if (Integer.valueOf(hourSave) / 10 == 0) {
                        hourSave = "0" + hourSave;
                    }
                    if (Integer.valueOf(minSave) / 10 == 0) {
                        minSave = "0" + minSave;
                    }

                    SharedPreferences sp = getSharedPreferences(prefName, MODE_PRIVATE);
                    Integer counter = sp.getInt("counter", 0);

                    Sprefs = getSharedPreferences(prefName, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = Sprefs.edit();

                    editor.putString("activity" + counter, s);

                    if (count == 0) {
                        editor.putString("time" + counter, "grey");
                    } else if (count == 1) {
                        editor.putString("time" + counter, "yellow");
                    } else if (count == 2) {
                        editor.putString("time" + counter, "orange");
                    } else {
                        editor.putString("time" + counter, "dot");
                    }

                    editor.putString("day" + counter, daySave);
                    editor.putString("month" + counter, monthSave);
                    editor.putString("year" + counter, yearSave);
                    editor.putString("hour" + counter, hourSave);
                    editor.putString("min" + counter, minSave);

                    editor.putInt("counter", ++counter);

                    editor.commit();//---saves the values---
                    //Toast.makeText(getBaseContext(), "saved",Toast.LENGTH_SHORT).show();

                    //saveData(s);
                    finish();
                }
            }
        });
    }

//    public void saveData(String s){
//        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putString(TEXT,s);
//        editor.apply();
//        //Toast.makeText(MainActivity2.this, "got this far", Toast.LENGTH_SHORT).show();
//    }
}