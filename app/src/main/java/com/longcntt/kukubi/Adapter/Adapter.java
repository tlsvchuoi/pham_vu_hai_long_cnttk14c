package com.longcntt.kukubi.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.longcntt.kukubi.R;
import com.longcntt.kukubi.Setting.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> arr;

    public Adapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arr = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.item, null);
        if (arr != null && arr.size() > 0) {
            TextView item = row.findViewById(R.id.tvItem);
            item.setBackgroundColor(Color.parseColor(arr.get(position)));
        }
        return row;
    }
}
