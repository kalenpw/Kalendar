package com.example.kalenpw.kalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    CalendarView _Calendar = null;
    Button _ButtonSave = null;
    EditText _EditText = null;

    //TODO move to sepparate class
    Day  _SelectedDay = null;
    Day[] _Days = initializeArray();

    HashMap<Day, String> _Schedule = new HashMap<Day, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set variables for widges
        _Calendar = (CalendarView) this.findViewById(R.id.calendarView);
        _ButtonSave = (Button) this.findViewById(R.id.button8);
        _EditText = (EditText) this.findViewById(R.id.editText);

        //Set button values
        _ButtonSave.setText(R.string.btn_save);

        _ButtonSave.setOnClickListener(this);
        _Calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDateChanged(view, year, month, dayOfMonth);
            }
        });




    }

    //Save button
    public void onClick(View v){
        int index = indexFromDay(_SelectedDay);
        if(_Days[index] != null){
            _Days[index].setMessage(_EditText.getText().toString());
        }

//        if(dayAlreadyInList(_SelectedDay)){
//            _Schedule.put(_SelectedDay, _EditText.getText().toString());
//        }




    }

    private void selectedDateChanged(CalendarView view, int year, int month, int dayOfMonth){
        Day selectedDay = new Day(year, month, dayOfMonth);
        int index = indexFromDay(selectedDay);
        if(_Days[index] == null){
            _Days[index] = selectedDay;
        }

//        if(dayAlreadyInList(selectedDay)){
//
//        }
//        else{
//            _Schedule.put(selectedDay, "");
//        }

        _SelectedDay = selectedDay;
        //int index = indexFromDay(_SelectedDay);
        _EditText.setText(_Days[index].getMessage());
        //_EditText.setText(_Schedule.get(selectedDay));





    }

    private boolean dayAlreadyInList(Day day){
        boolean alreadyHas = false;
        for(Day d :_Schedule.keySet()){
            if(d.getDay() == day.getDay() && d.getMonth() == day.getMonth() && d.getYear() == day.getYear()){
                alreadyHas = true;
            }
        }
        return alreadyHas;
    }

    private Day[] initializeArray(){
        Day[] returnDay = new Day[20201131];
        for(int i = 0; i < 20201131; i++){
            returnDay[i] = null;
        }
        return returnDay;
    }

    private int indexFromDay(Day day){
        int dayOfMonth = day.getDay();
        int month = day.getMonth();
        int year = day.getYear();
        String combined = "" + year + month + dayOfMonth;
        int result = Integer.parseInt(combined);
        return result;

    }



}
