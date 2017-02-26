package com.example.kalenpw.kalendar;

import android.content.Context;
import android.hardware.input.InputManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

        //Disable edit text:
        _EditText.setFocusable(false);
        _EditText.setEnabled(false);
        _EditText.setCursorVisible(false);

        //Set button values
        _ButtonSave.setText(R.string.btn_edit);

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
        String buttonText = _ButtonSave.getText().toString();
        if(buttonText.equals("Edit")){
            _ButtonSave.setText(R.string.btn_save);
            updateEditText();
//            _EditText.setFocusable(true);
//            _EditText.setEnabled(true);
//            _EditText.setCursorVisible(true);
//            _EditText.setInputType(InputType.TYPE_CLASS_TEXT);


        }
        else if(buttonText.equals("Save")){
            _ButtonSave.setText(R.string.btn_edit);
            updateEditText();
//            _EditText.setFocusable(true);
//            _EditText.setEnabled(true);
//            _EditText.setCursorVisible(true);
            int index = indexFromDay(_SelectedDay);
            if(_Days[index] != null){
                _Days[index].setMessage(_EditText.getText().toString());
            }

//        if(dayAlreadyInList(_SelectedDay)){
//            _Schedule.put(_SelectedDay, _EditText.getText().toString());
//        }

        }
        //System.out.println(buttonText);


    }

    private void selectedDateChanged(CalendarView view, int year, int month, int dayOfMonth){
        //Change button to edit in case user was entering something it doesn't save
        _ButtonSave.setText(R.string.btn_edit);
        updateEditText();

        //Close onscreenkeyboard if it was left open
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        Day selectedDay = new Day(year, month, dayOfMonth);
        _SelectedDay = selectedDay;
        int index = indexFromDay(selectedDay);
        //If no entries for day create one
        if(_Days[index] == null){
            _Days[index] = selectedDay;
        }

        _EditText.setText(_Days[index].getMessage());

//        if(dayAlreadyInList(selectedDay)){
//
//        }
//        else{
//            _Schedule.put(selectedDay, "");
//        }
//        _EditText.setText(_Schedule.get(selectedDay));







    }

    private void updateEditText(){
        String btnText = _ButtonSave.getText().toString();
        if(btnText.equals("Save")){
            _EditText.setEnabled(true);
            _EditText.setFocusable(true);
            _EditText.setFocusableInTouchMode(true);
            _EditText.setCursorVisible(true);
            //_EditText.setCursorVisible(true);
            //_EditText.setInputType(InputType.TYPE_CLASS_TEXT);
//            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            im.showSoftInput(_EditText, InputMethodManager.SHOW_IMPLICIT);



        }
        else if(btnText.equals("Edit")){
            _EditText.setEnabled(true);
            _EditText.setFocusable(false);
            _EditText.setCursorVisible(false);


        }
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
