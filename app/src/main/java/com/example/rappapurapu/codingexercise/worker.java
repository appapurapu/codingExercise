package com.example.rappapurapu.codingexercise;

/**
 * Created by rappapurapu on 5/6/2016.
 */
//@Table
public class worker {
    String  name;
    int     empNumber;

    public void setName(String empName){name = empName;}
    public String getName(){return name;}

    public void setEmpNumber(int num){empNumber = num;}
    public int  getEmpNumber(){return empNumber;}
    public void printWorker(){
        System.out.println("name = " + name);
        System.out.println("Employee Number = " + empNumber);
    }
}
