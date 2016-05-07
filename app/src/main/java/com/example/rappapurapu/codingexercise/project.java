package com.example.rappapurapu.codingexercise;


import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by rappapurapu on 5/6/2016.
 */
//@Table
    public class project extends SugarRecord
{
    String              title;
    ArrayList<worker>   wrkers;

    public project(){}
    public project(String title, ArrayList<worker>workers){
        title   = title;
        wrkers  = workers;
    }
    public project(String title, worker w){
        title   = title;
        wrkers  = new ArrayList<worker>();
        this.addWrker(w);
    }

    public String   getTitle(){return title;}
    public void     setTitle(String name){title = name;}

    public ArrayList<worker> getWrkers(){
        return wrkers;
    }

    public void addWrker(worker w){
        if(null == wrkers) {
            wrkers = new ArrayList<worker>();
        }
        wrkers.add(w);
    }

    public void removeWorkder(worker w1){
        if(null == wrkers)
            return;

        wrkers.remove(w1);
    }

    public void printProjectDetails(){
        System.out.println("Project Title = " + title);
        System.out.println("Project Workders Count = " + wrkers.size());

        for(worker elem:wrkers){
            elem.printWorker();
        }
    }
}
