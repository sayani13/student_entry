package com.example.sayani.student_entry;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    public SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteDatabase = openOrCreateDatabase("student",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Student(id INTEGER primary key,name VARCHAR,sem INTEGER,branch VARCHAR,faculty VARCHAR,phone INTEGER,email VARCHAR);");
    }
    public void SubmitClick(View view) {
        EditText idText = findViewById(R.id.editText);
        int id = Integer.parseInt(idText.getText().toString());
        EditText nameText = findViewById(R.id.editText2);
        String name = nameText.getText().toString();
        EditText semText = findViewById(R.id.editText3);
        int sem = Integer.parseInt(semText.getText().toString());
        EditText branchText = findViewById(R.id.editText4);
        String branch = branchText.getText().toString();
        EditText faText = findViewById(R.id.editText5);
        String FA = faText.getText().toString();
        EditText phoneText = findViewById(R.id.editText6);
        int phone = Integer.parseInt(phoneText.getText().toString());
        EditText emailText = findViewById(R.id.editText7);
        String email = emailText.getText().toString();
        try {
            sqLiteDatabase.execSQL("INSERT INTO Student VALUES(" + id + ",'" + name + "'," + sem + ",'" + branch + "','" + FA + "'," + phone + ",'" + email + "');");
        }
        catch(Exception e)
        {
            Toast.makeText(this,"duplicate entry",Toast.LENGTH_LONG).show();
        }
        Toast.makeText(this,"Submitted",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }
}