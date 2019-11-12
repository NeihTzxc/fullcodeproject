package com.example.thuchicanhan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView lv_thu;
    Button btn_add_thu;
    Button btn_edit_thu;
    String title, price,danhmuc;
    String date;
    ArrayList<lv_item_thu> lv_item_thuArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();

        final lv_item_thu lv_item_thu=new lv_item_thu("Tiền lương","1/1/2019","15.000.000 VND","Thu Nhập chính");
        setLv_thu(lv_item_thu);

        lv_thu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 btn_edit_thu_click(view,i);
            }
        });
        lv_thu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog_Xoa_thunhap(i);
                return true;
            }
        });
        btn_add_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_Add_thunhap();
            }
        });

    }
    public void btn_edit_thu_click(View view, final int i){
        btn_edit_thu =(Button) view.findViewById(R.id.btn_edit_thu);
        btn_edit_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_Edit_thunhap(
                        lv_item_thuArrayList.get(i).getDanhmuc(),
                        lv_item_thuArrayList.get(i).getTitle(),
                        lv_item_thuArrayList.get(i).getTien(),
                        lv_item_thuArrayList.get(i).getNgay(),
                        i
                );

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void anhxa(){
        lv_thu=(ListView) findViewById(R.id.lv_thu);
        btn_add_thu=(Button) findViewById(R.id.add_thu);
    }
    public void setLv_thu(lv_item_thu lv_item_thu){
        lv_item_thuArrayList.add(lv_item_thu);
        customAdap_thu customAdap_thu=new customAdap_thu(this,R.layout.detail_thu,lv_item_thuArrayList);
        lv_thu.setAdapter(customAdap_thu);

    }
    public void editlv_thu(ArrayList<lv_item_thu> arrayList){
        customAdap_thu customAdap_thu=new customAdap_thu(this,R.layout.detail_thu,arrayList);
        lv_thu.setAdapter(customAdap_thu);
    }
    public void Dialog_Xoa_thunhap(final int i){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_xoa_thu);
        Button btn_no=(Button) dialog.findViewById(R.id.no);
        Button btn_ok=(Button) dialog.findViewById(R.id.ok);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv_item_thuArrayList.remove(i);
                editlv_thu(lv_item_thuArrayList);
                dialog.cancel();
            }
        });
        dialog.show();
    }
    public void Dialog_Edit_thunhap(String edit_danhmuc, String edit_mota, String edit_price, String edit_date,final int ii){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit_thu);
        Button btn_huy=(Button) dialog.findViewById(R.id.btn_huy);
        Button btn_ok=(Button) dialog.findViewById(R.id.btn_ok);
        ImageView imageView_cal=(ImageView) dialog.findViewById(R.id.calendar);
        final EditText add_title=(EditText) dialog.findViewById(R.id.add_title);
        final EditText add_date=(EditText) dialog.findViewById(R.id.add_date);
        final EditText add_price=(EditText) dialog.findViewById(R.id.add_price);
        final TextView select_danhmuc=(TextView) dialog.findViewById(R.id.select_danhmuc);

        select_danhmuc.setText(edit_danhmuc);
        add_title.setText(edit_mota);
        add_date.setText(edit_date);
        add_price.setText(edit_price);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=add_title.getText().toString();//lấy dữ liệu title
                price=add_price.getText().toString();//lấy dữ liệu price
                lv_item_thuArrayList.get(ii).setDanhmuc(select_danhmuc.getText().toString());
                lv_item_thuArrayList.get(ii).setNgay(add_date.getText().toString());
                lv_item_thuArrayList.get(ii).setTien(price);
                lv_item_thuArrayList.get(ii).setTitle(title);
                editlv_thu(lv_item_thuArrayList);
                dialog.cancel();

            }
        });
        imageView_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                        add_date.setText(dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
                        date=add_date.getText().toString();
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });
        select_danhmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup_thu_show(select_danhmuc);
            }
        });
        dialog.show();

    }
    public void Dialog_Add_thunhap(){

        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_thu_click);
        Button btn_huy=(Button) dialog.findViewById(R.id.btn_huy);
        Button btn_ok=(Button) dialog.findViewById(R.id.btn_ok);
        ImageView imageView_cal=(ImageView) dialog.findViewById(R.id.calendar);
        final EditText add_title=(EditText) dialog.findViewById(R.id.add_title);
        final EditText add_date=(EditText) dialog.findViewById(R.id.add_date);
        final EditText add_price=(EditText) dialog.findViewById(R.id.add_price);


        final TextView select_danhmuc=(TextView) dialog.findViewById(R.id.select_danhmuc);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=add_title.getText().toString();//lấy dữ liệu title
                price=add_price.getText().toString();//lấy dữ liệu price
                lv_item_thu lv_item_thu=new lv_item_thu(title,date,price,danhmuc);
                setLv_thu(lv_item_thu);
                dialog.cancel();

            }
        });
        imageView_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                        add_date.setText(dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
                        date=add_date.getText().toString();
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });
        select_danhmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup_thu_show(select_danhmuc);
            }
        });
        dialog.show();

    }
    public void popup_thu_show(final TextView textView) {
        PopupMenu popupMenu = new PopupMenu(this, textView);
        popupMenu.getMenuInflater().inflate(R.menu.popup_thu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                danhmuc=menuItem.toString();
                textView.setText(danhmuc);
                return true;
            }
        });
        popupMenu.show();
    }


}
