package com.example.test8;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Library_MainActivity extends AppCompatActivity {

    private MyLibrary dbHelper;
    private List<Book> bookList = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);
        dbHelper = new MyLibrary(this, "Library.db", null, 5);
        Button createDataBase = (Button) findViewById(R.id.create_library);
        createDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
            }
        });
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // 开始组装第一条数据
                values.put("category_name", "经济类");
                values.put("category_code", 1);
                db.insert("Category", null, values);//插入第一条数据
                values.clear();
                //开始组装第二条数据
                values.put("category_name", "自然科学类");
                values.put("category_code", 2);
                db.insert("Category", null, values);//插入第二条数据
            }
        });

        Button addbook = (Button) findViewById(R.id.add_book);
        addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Library_MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });

        listBook();
        BookAdapter bookAdapter = new BookAdapter(Library_MainActivity.this,R.layout.list_item,bookList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(bookAdapter);

    }

    private void listBook(){
        Book book;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select b.name,c.category_name,b.price from Book b,Category c where b.category_id=c.id",null);
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(0);
                String category = cursor.getString(1);
                Double price = cursor.getDouble(2);
                book = new Book(name,category,price);
                bookList.add(book);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}