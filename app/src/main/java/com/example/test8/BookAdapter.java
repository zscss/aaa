package com.example.test8;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private int resourceId;
    public BookAdapter(Context context, int textViewResourceId, List<Book> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position); // 获取当前项的实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView bookname = (TextView) view.findViewById(R.id.name);
        TextView categoryname = (TextView) view.findViewById(R.id.category);
        TextView prices = (TextView) view.findViewById(R.id.price);
        bookname.setText(book.getBookname());
        categoryname.setText(book.getCategoryname());
        prices.setText(String.valueOf(book.getPrices()));
        return view;
    }
}
