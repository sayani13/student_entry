package com.example.sayani.student_entry;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main3Activity extends Activity {
    public SQLiteDatabase sqLiteDatabase;
    int id;
    TextView textView4;
    TextView textView6;
    TextView textView8;
    TextView textView10;
    TextView textView12;
    TextView textView14;
    EditText nameText;
    EditText semText;
    EditText branchText;
    EditText faText;
    EditText phoneText;
    EditText emailText;
    Button submitButton;
    Button editButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView textView2 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView8 = findViewById(R.id.textView8);
        textView10 = findViewById(R.id.textView10);
        textView12 = findViewById(R.id.textView12);
        textView14 = findViewById(R.id.textView14);
        nameText = findViewById(R.id.editText8);
        semText = findViewById(R.id.editText9);
        branchText = findViewById(R.id.editText10);
        faText = findViewById(R.id.editText11);
        phoneText = findViewById(R.id.editText12);
        emailText = findViewById(R.id.editText13);
        submitButton = findViewById(R.id.button2);
        editButton = findViewById(R.id.button);
        nameText.setVisibility(View.INVISIBLE);
        semText.setVisibility(View.INVISIBLE);
        branchText.setVisibility(View.INVISIBLE);
        faText.setVisibility(View.INVISIBLE);
        phoneText.setVisibility(View.INVISIBLE);
        id=Main2Activity.id;
        emailText.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);
        sqLiteDatabase = openOrCreateDatabase("student",MODE_PRIVATE,null);
        Cursor c = sqLiteDatabase.rawQuery("select * from Student where id="+id,null);
        if(c.moveToNext())
        {
            textView2.setText(id+"");
            textView4.setText(c.getString(1));
            textView6.setText(c.getInt(2)+"");
            textView8.setText(c.getString(3));
            textView10.setText(c.getString(4));
            textView12.setText(c.getInt(5)+"");
            textView14.setText(c.getString(6));
        }
    }
    public void editClick(View view)
    {
        textView4.setVisibility(View.INVISIBLE);
        textView6.setVisibility(View.INVISIBLE);
        textView8.setVisibility(View.INVISIBLE);
        textView10.setVisibility(View.INVISIBLE);
        textView12.setVisibility(View.INVISIBLE);
        textView14.setVisibility(View.INVISIBLE);
        nameText.setVisibility(View.VISIBLE);
        semText.setVisibility(View.VISIBLE);
        branchText.setVisibility(View.VISIBLE);
        faText.setVisibility(View.VISIBLE);
        phoneText.setVisibility(View.VISIBLE);
        emailText.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
    }
    public void submitClick(View view)
    {
        String s1,s2,s3,s4,s5,s6;
        s1=nameText.getText().toString();
        s2=semText.getText().toString();
        s3=branchText.getText().toString();
        s4=faText.getText().toString();
        s5=phoneText.getText().toString();
        s6=emailText.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put("name",s1);
        cv.put("sem",s2);
        cv.put("branch",s3);
        cv.put("faculty",s4);
        cv.put("phone",s5);
        cv.put("email",s6);
        sqLiteDatabase.update("Student",cv,"id="+id,null);
        Intent intent = new Intent(Main3Activity.this,Main2Activity.class);
        startActivity(intent);
        finish();
    }
}