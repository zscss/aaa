package com.example.test8;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddBookActivity extends Activity {
    EditText name;
    EditText price;
    EditText author;
    EditText pages;
    EditText category_id;
    private MyLibrary dbHelper;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook);

        dbHelper =new MyLibrary(this, "Library.db", null, 5);

        name = findViewById(R.id.book_name);
        price = findViewById(R.id.book_price);
        author = findViewById(R.id.book_author);
        pages = findViewById(R.id.book_pages);
        category_id = findViewById(R.id.book_category_id);

    }

    public void Addbook(View v) {
        String book_name = name.getText().toString().trim();
        String book_author = author.getText().toString().trim();
        String book_pages1 = pages.getText().toString().trim();
        Integer book_pages = Integer.parseInt(book_pages1);
        String book_price1 = price.getText().toString().trim();
        Double book_price = Double.parseDouble(book_price1);
        String book_category_id1 = category_id.getText().toString().trim();
        Integer book_category_id = Integer.parseInt(book_category_id1);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",book_name);
        values.put("author", book_author);
        values.put("pages", book_pages);
        values.put("price", book_price);
        values.put("category_id", book_category_id);
        db.insert("Book", null, values); // 插入数据
        values.clear();
    }

}
