package com.example.user.myparkingapp.User;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.Loader;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user.myparkingapp.Shared_Preferance;
import com.example.user.myparkingapp.Singleton;
import com.example.user.myparkingapp.User.Authentication.UserLoginActivity;
import com.example.user.myparkingapp.R;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;


import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UserDetailBookingActivity extends AppCompatActivity {
    String address,property_id, user_id, date, startTime, endTime;
    String format;
    private int CalendarHour, CalendarMinute;
    EditText dateOfBooking, start_time, end_time;
    Button submit, startTimePicker, endTimePicker, datePicker;
    TimePickerDialog timepickerdialog;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_booking);

        dateOfBooking = (EditText) findViewById(R.id.dateOFBooking);
        start_time = (EditText) findViewById(R.id.startTime);
        end_time = (EditText) findViewById(R.id.endTime);
        submit = (Button) findViewById(R.id.submit);
        datePicker = (Button) findViewById(R.id.datePicker);
        startTimePicker = (Button) findViewById(R.id.startTimrPicker);
        endTimePicker = (Button) findViewById(R.id.endTimePicker);


        if (getIntent() != null) {
            property_id = getIntent().getStringExtra("property_id");
            address = getIntent().getStringExtra("address");
        }

        myCalendar = Calendar.getInstance();
        endTimePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                CalendarHour = myCalendar.get(Calendar.HOUR_OF_DAY);
                CalendarMinute = myCalendar.get(Calendar.MINUTE);


                timepickerdialog = new TimePickerDialog(UserDetailBookingActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                if (hourOfDay == 0) {

                                    hourOfDay += 12;

                                    format = "AM";
                                } else if (hourOfDay == 12) {

                                    format = "PM";

                                } else if (hourOfDay > 12) {

                                    hourOfDay -= 12;

                                    format = "PM";

                                } else {

                                    format = "AM";
                                }


                                end_time.setText(hourOfDay + ":" + minute + format);
                            }
                        }, CalendarHour, CalendarMinute, false);
                timepickerdialog.show();

            }
        });

        startTimePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                CalendarHour = myCalendar.get(Calendar.HOUR_OF_DAY);
                CalendarMinute = myCalendar.get(Calendar.MINUTE);


                timepickerdialog = new TimePickerDialog(UserDetailBookingActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                if (hourOfDay == 0) {

                                    hourOfDay += 12;

                                    format = "AM";
                                } else if (hourOfDay == 12) {

                                    format = "PM";

                                } else if (hourOfDay > 12) {

                                    hourOfDay -= 12;

                                    format = "PM";

                                } else {

                                    format = "AM";
                                }


                                start_time.setText(hourOfDay + ":" + minute + format);
                            }
                        }, CalendarHour, CalendarMinute, false);
                timepickerdialog.show();

            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelDate();
            }

        };


        datePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(UserDetailBookingActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    private void updateLabelDate() {
        String myFormat = "yy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateOfBooking.setText(sdf.format(myCalendar.getTime()));
    }


    public void bookingDetails(View view) {

        user_id = Shared_Preferance.getInstance(getApplicationContext()).getUserId();
        date = dateOfBooking.getText().toString().trim();
        startTime = start_time.getText().toString().trim();
        endTime = end_time.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.0.116/parking_system/booking.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String value = jsonObject.getString("error");
String price=jsonObject.getString("Price");
                            if (value.equals("false")) {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), property_id, Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(UserDetailBookingActivity.this, UserBookingActivity.class);

                                intent.putExtra("property_id",property_id);
                                intent.putExtra("address",address);
                                intent.putExtra("price",price);
                                intent.putExtra("user_id",user_id);
                                intent.putExtra("startTime",startTime);
                                intent.putExtra("endTime",endTime);
                                intent.putExtra("type","two_wheeler");
                                startActivity(intent);
                            } else {
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserDetailBookingActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();

                param.put("start_time", "2018-3-2 02:30:00");
                param.put("end_time", "2018-3-2 04:30:00");
                param.put("property_id", "1");
                param.put("user_id", "1");
                param.put("type", "two_wheeler");
                return param;
            }
        };
        Singleton.getInstance(this).addToRequestQueue(stringRequest);

    }

}
