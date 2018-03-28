package com.example.sayani.student_entry;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main2Activity extends Activity {
    public SQLiteDatabase sqLiteDatabase;
    public ArrayList<CharSequence> idList;
    public static int id;
    public ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sqLiteDatabase = openOrCreateDatabase("student",MODE_PRIVATE,null);
        listView = findViewById(R.id.listView);
        registerForContextMenu(listView);
        idList = new ArrayList<>();
        Cursor resultSet = sqLiteDatabase.rawQuery("select * from Student",null);
        resultSet.moveToFirst();
        while(resultSet.moveToNext())
        {
            int id=resultSet.getInt(0);
            idList.add(id+"");
        }
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_list_item_1,android.R.id.text1,idList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int listPosition = info.position;
        id = Integer.parseInt(idList.get(listPosition).toString());
        if(item.getTitle().equals("View"))//View
        {
            Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
            startActivity(intent);
        }
        else if(item.getTitle().equals("Delete"))//Delete
        {
            sqLiteDatabase.delete("Student","id"+"="+id,null);
            Cursor cursor = sqLiteDatabase.rawQuery("select * from Student",null);
            idList=new ArrayList<>();
            while(cursor.moveToNext())
            {
                idList.add(cursor.getInt(0)+"");
            }
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_list_item_1,android.R.id.text1,idList);
            listView.setAdapter(adapter);
        }
        return true;
    }
}