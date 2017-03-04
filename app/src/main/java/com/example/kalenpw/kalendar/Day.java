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

    public Day(int newYear, int newMonth, int newDay, String newMessage){
        _Year = newYear;
        _Month = newMonth;
        _Day = newDay;
        _Message = newMessage;
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

    public String getSaveName(){
        String str = "";
        str += _Year;
        str += ", " + _Month;
        str += ", " + _Day;
        str += ", " + _Message;
        return str;
    }

    /**
     * Checks if the given day is equal to the day it is being compared against
     * @param day
     * @return boolean - true if same day false otherwise
     */
    public boolean isSameDay(Day day){
        boolean isEqual = false;
        if(_Day == day.getDay() && _Month == day.getMonth() && _Year == day.getYear()){
            isEqual = true;
        }
        return isEqual;
    }

}
