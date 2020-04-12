package com.example.android_wei28;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView birthday,time;
    private View rootview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        birthday=findViewById(R.id.birthday);
        time=findViewById(R.id.time);
        rootview=findViewById(R.id.root_view);
        rootview.setOnClickListener(rootListen);
    }

    ///////////button///////////
    public void test1(View view) {
        if (Build.VERSION.SDK_INT>=24){
            new_version_datepick();
            mytoast("版本24以上",true);
        }else{
            old_version_datepick();
            mytoast("版本24以下",false);
        }
    }
    public void test2(View view){
        TimePickerDialog tpd=new TimePickerDialog(this,TimePickerDialog.THEME_DEVICE_DEFAULT_DARK,TimeListener,12,30,true);
        tpd.show();
    }
    public void test3(View view){
        mytoast("hello",true);
    }

    Snackbar sn;
    public void test4(View view) {
        sn=Snackbar.make(rootview,"hehehe",
                Snackbar.LENGTH_INDEFINITE);
        sn.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mytoast("OK",true);
            }
        });
        sn.setActionTextColor(Color.YELLOW);
        sn.show();
    }

    public void test5(View view) {
        if (sn!=null){
            sn.dismiss();
        }

    }

    ///////////function/////////////
    private void old_version_datepick(){
        DatePickerDialog dpd_old=new DatePickerDialog(this,DatePickerDialog.THEME_DEVICE_DEFAULT_DARK,Datelistener,2020,
                3,12);
        DatePicker datePicker=dpd_old.getDatePicker();
        Calendar limit=Calendar.getInstance();
        limit.set(2020,3,2);
        datePicker.setMaxDate(limit.getTimeInMillis());

        dpd_old.show();
    }

    private void new_version_datepick(){
        DatePickerDialog dpd=new DatePickerDialog(this,DatePickerDialog.THEME_DEVICE_DEFAULT_DARK,null,2020,
                3,12);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dpd.setOnDateSetListener(Datelistener);//dpi 24之後可以用這個寫法，若你在build.gradle設定minSdkVersion 低於24就會出現紅字
        }

        DatePicker datePicker=dpd.getDatePicker();
        Calendar limit=Calendar.getInstance();
        limit.set(2020,3,2);
        datePicker.setMaxDate(limit.getTimeInMillis());

        dpd.show();
    }
    //////////////Listener///////////////
    DatePickerDialog.OnDateSetListener Datelistener= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            birthday.setText(year+"/"+month+1+"/"+dayOfMonth);
        }
    };

    TimePickerDialog.OnTimeSetListener TimeListener= new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            time.setText(hourOfDay+":"+minute);
        }
    };

    View.OnClickListener rootListen= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sn.dismiss();
        }
    };

    ////////////Toast////////////
    private void mytoast(String mesg,boolean isWarn){
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.toast,null);
        TextView toast_mesg=view.findViewById(R.id.toast_mesg);
        toast_mesg.setText(mesg);

        ImageView toast_img_1=view.findViewById(R.id.toast_img_1);
        toast_img_1.setImageResource(isWarn?R.drawable.please:R.drawable.three_q);


        Toast toast=new Toast(this);
        toast.setGravity(Gravity.NO_GRAVITY,0,200);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);

        toast.show();
    }


}
