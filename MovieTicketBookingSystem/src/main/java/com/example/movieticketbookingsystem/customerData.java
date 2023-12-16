package com.example.movieticketbookingsystem;

import java.util.Date;
import java.sql.Time;


public class customerData {
    private int id;
    private String type;
    private String title;
    private int quantity;
    private double total;
    private Date date;
    private Time time;


    public customerData(int id, String type, String title, int quantity, double total, Date date, Time time){
            this.id = id;
            this.type = type;
            this.title = title;
            this.quantity = quantity;
            this.total = total;
            this.date = date;
            this.time = time;
    }

    public Integer getId(){
        return id;
    }
    public String getType(){
        return type;
    }
    public String getTitle(){
        return title;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public double getTotal(){
        return total;
    }

    public Date getDate(){
        return date;
    }
    public Time getTime(){
        return time;
    }
}
