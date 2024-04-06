package com.example.customerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyCustomeCalender extends AppCompatActivity {
    private mySQLiteDBHandler dbHandler;
    private EditText editText;
    private CalendarView calendarView;
    private String selectedDate;
    private SQLiteDatabase sqLiteDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custome_calender);
        editText = findViewById(R.id.editText);
        getSupportActionBar().hide();
        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);
                ReadDatabase(view);
            }
        });

        try {
            dbHandler = new mySQLiteDBHandler(this, "CalendarDatabase", null, 1);
            sqLiteDatabase = dbHandler.getWritableDatabase();
            sqLiteDatabase.execSQL("CREATE TABLE  EventCalendar(Date TEXT, Event TEXT)");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void InsertDatabase(View view) {
        String eventText = editText.getText().toString().trim();
        if (eventText.isEmpty()) {
            Toast.makeText(this, "Please enter an event", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("Date", selectedDate);
        contentValues.put("Event", eventText);
        long eventId = sqLiteDatabase.insert("EventCalendar", null, contentValues);

        if (eventId != -1) {
            // Set a notification for the event
            setEventNotification(selectedDate, eventText);
            Toast.makeText(this, "Event inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to insert event", Toast.LENGTH_SHORT).show();
        }
    }


    public void UpdateDatabase(View view) {
        String eventText = editText.getText().toString().trim();
        if (eventText.isEmpty()) {
            Toast.makeText(this, "Please enter an event to update", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("Event", eventText);

        int rowsAffected = sqLiteDatabase.update("EventCalendar", contentValues, "Date = ?", new String[]{selectedDate});

        if (rowsAffected > 0) {
            Toast.makeText(this, "Event updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No event found for the selected date", Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteEvent(View view) {
        if (selectedDate == null) {
            Toast.makeText(this, "Select a date first", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            sqLiteDatabase.delete("EventCalendar", "Date = ?", new String[]{selectedDate});
            editText.setText("");
            Toast.makeText(this, "Event deleted successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to delete event", Toast.LENGTH_SHORT).show();
        }
    }

    private void setEventNotification(String selectedDate, String eventText) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = sdf.parse(selectedDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Set the notification time (you can adjust this as needed)
            calendar.set(Calendar.HOUR_OF_DAY, 8); // 14 AM
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            // Create an intent for the notification
            Intent intent = new Intent(this, NotificationReceiver.class);
            intent.putExtra("event", eventText);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            // Set the alarm for the notification
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void ShowEvents(View view) {
        StringBuilder eventsText = new StringBuilder();

        String query = "SELECT * FROM EventCalendar";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int dateIndex = cursor.getColumnIndex("Date");
            int eventIndex = cursor.getColumnIndex("Event");
            do {
                if (dateIndex != -1 && eventIndex != -1) {
                    String date = cursor.getString(dateIndex);
                    String event = cursor.getString(eventIndex);
                    eventsText.append(date).append(": ").append(event).append("\n");
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        if (eventsText.length() > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Events");
            builder.setMessage(eventsText.toString());
            builder.setPositiveButton("OK", null);
            builder.show();
        } else {
            Toast.makeText(this, "No events found", Toast.LENGTH_SHORT).show();
        }
    }


    public void ReadDatabase (View view){
        String query = "SELECT Event FROM EventCalendar WHERE Date = '" + selectedDate + "'";
        try {
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                editText.setText(cursor.getString(0));
            } else {
                editText.setText("");
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            editText.setText("");
        }
    }


}