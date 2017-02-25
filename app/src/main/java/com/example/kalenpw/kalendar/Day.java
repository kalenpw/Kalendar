package com.example.kalenpw.kalendar;

/**
 * Created by kalenpw on 2/24/17.
 */

public class Day {
    //Fields
    private int _Year = -1;
    private int _Month = -1;
    private int _Day = -1;

    private String _Message;

    //Constructor
    public Day(int newYear, int newMonth, int newDay){
        _Year = newYear;
        _Month = newMonth;
        _Day = newDay;
    }

    //Getters & Setters
    public int getYear(){
        return _Year;
    }
    public int getMonth(){
        return _Month;
    }
    public int getDay(){
        return _Day;
    }
    public void setMessage(String newMessage){
        _Message = newMessage;
    }
    public String getMessage(){
        return _Message;
    }


    public boolean isSameDay(Day day){
        boolean isEqual = false;
        if(_Day == day.getDay() && _Month == day.getMonth() && _Year == day.getYear()){
            isEqual = true;
        }
        return isEqual;
    }

}
