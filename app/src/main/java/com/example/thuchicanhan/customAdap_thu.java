package com.example.thuchicanhan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class customAdap_thu extends ArrayAdapter<lv_item_thu> {
    private Context context;
    private int resource;
    private List<lv_item_thu> arrlv_item_thu;
    public customAdap_thu(Context context, int resource, ArrayList<lv_item_thu> arrlv_item_thu) {
        super(context, resource, arrlv_item_thu);
        this.context = context;
        this.resource = resource;
        this.arrlv_item_thu = arrlv_item_thu;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.detail_thu, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title_thu);
            viewHolder.ngay = (TextView) convertView.findViewById(R.id.ngay);
            viewHolder.tien = (TextView) convertView.findViewById(R.id.sotien);
            viewHolder.danhmuc = (TextView) convertView.findViewById(R.id.danhmuc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        lv_item_thu lv_item_thu = arrlv_item_thu.get(position);
        viewHolder.title.setText(lv_item_thu.getTitle());
        viewHolder.ngay.setText(lv_item_thu.getNgay());
        viewHolder.tien.setText(lv_item_thu.getTien());
        viewHolder.danhmuc.setText(lv_item_thu.getDanhmuc());
        return convertView;
    }
    public class ViewHolder {
        TextView title,ngay, tien,danhmuc;

    }
}
