package com.example.kalenpw.kalendar;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    CalendarView _Calendar = null;
    Button _ButtonSave = null;
    EditText _EditText = null;

    //TODO move to sepparate class
    Day  _SelectedDay = null;

    //HashMap<Day, String> _Schedule = new HashMap<Day, String>();
    ArrayList<Day> _Schedule = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //test();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set variables for widges
        _Calendar = (CalendarView) this.findViewById(R.id.calendarView);
        _ButtonSave = (Button) this.findViewById(R.id.button8);
        _EditText = (EditText) this.findViewById(R.id.editText);

        //TODO update _SelectedDay so app doesn't crash if a choice is made before changing
        //the date

        //Set up button
        _ButtonSave.setText(R.string.btn_edit);
        updateEditText();

        _ButtonSave.setOnClickListener(this);
        _Calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDateChanged(view, year, month, dayOfMonth);
            }
        });

    }

    private void test(){
        String str = new FileHandler("/storage/emulated/0/kalendar/Schedule.txt").getFileLines();

    }

    //Save button
    public void onClick(View view){
        String buttonText = _ButtonSave.getText().toString();

        if(buttonText.equals("Edit")){
            _ButtonSave.setText(R.string.btn_save);
            updateEditText();
        }
        else if(buttonText.equals("Save")){
            _ButtonSave.setText(R.string.btn_edit);
            updateEditText();
            closeOnScreenKeyboard(view);
            if(dayIsAlreadyInList(_SelectedDay)){
                int indexOfDay = getIndexOfSpecificDay(_SelectedDay);
                _SelectedDay.setMessage(_EditText.getText().toString());
                _Schedule.remove(indexOfDay);
                _Schedule.add(indexOfDay, _SelectedDay);
            }
            else{
                _SelectedDay.setMessage(_EditText.getText().toString());
                _Schedule.add(_SelectedDay);
            }
            saveFile();
        }

    }

    private void selectedDateChanged(CalendarView view, int year, int month, int dayOfMonth){
        //Switch back to view mode
        _ButtonSave.setText(R.string.btn_edit);
        updateEditText();
        _EditText.setText("");

        closeOnScreenKeyboard(view);

        Day selectedDay = new Day(year, month, dayOfMonth);
        _SelectedDay = selectedDay;
        if(dayIsAlreadyInList(selectedDay)){
            int indexOfFoundDay = getIndexOfSpecificDay(selectedDay);
            Day day = _Schedule.get(indexOfFoundDay);
            _EditText.setText(day.getMessage());

        }

    }

    private int getIndexOfSpecificDay(Day toFind){
        for(Day day : _Schedule){
            if(daysAreEqual(toFind, day)){
                return _Schedule.indexOf(day);
            }
        }
        return -1;
    }

    private void closeOnScreenKeyboard(View view){
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean daysAreEqual(Day dayOne, Day dayTwo){
        boolean areEqual = true;
        if(dayOne.getDay() != dayTwo.getDay() ||
                dayOne.getMonth() != dayTwo.getMonth() ||
                dayOne.getYear() != dayTwo.getYear()){
            areEqual = false;
        }
        return areEqual;
    }

    private boolean dayIsAlreadyInList(Day dayToCheck){
        boolean alreadyContains = false;
        for(Day day : _Schedule){
            if(daysAreEqual(day, dayToCheck)){
                alreadyContains = true;
                return alreadyContains;
            }
        }
        return alreadyContains;
    }

    private void saveFile(){
        String fileContent = getSaveString();
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard + "/kalendar");
        File file = new File(directory, "Schedule.txt");
        FileHandler fileHandler = new FileHandler(file);
        fileHandler.saveFile(fileContent);
    }

    private String getSaveString() {
        String str = "";
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/kalendar");
        directory.mkdirs();
        File file = new File(directory, "Schedule.txt");
        String path = file.getAbsolutePath();

        for (int i = 0; i < _Schedule.size(); i++) {
            if (_Schedule.get(i) != null) {
                str += _Schedule.get(i).getSaveName() + ",\n";
            }
        }
        return str;
    }

    private void updateEditText(){
        String btnText = _ButtonSave.getText().toString();
        if(btnText.equals("Save")){
            _EditText.setEnabled(true);
            _EditText.setFocusable(true);
            _EditText.setFocusableInTouchMode(true);
            _EditText.setCursorVisible(true);
        }
        else if(btnText.equals("Edit")){
            _EditText.setEnabled(true);
            _EditText.setFocusable(false);
            _EditText.setFocusableInTouchMode(false);
            _EditText.setCursorVisible(false);
        }
    }

    private File getStorageDirectory(){
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Kalendar");
        return file;
    }





}
