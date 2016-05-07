package com.example.rappapurapu.codingexercise;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rappapurapu on 5/6/2016.
 */
//@Table
public class projectMgr extends SugarRecord
{
    private String              name;
    private int                 empNumber;
    private ArrayList<project>  projects;

    public projectMgr(){}
    public projectMgr(String nm, int empNum, ArrayList<project> prj){
        name        = nm;
        empNumber   = empNum;
        projects    = prj;
    }

    public String getName(){return name;}
    public void setName(String pmName){name = pmName;}

    public int getEmpNumber(){return empNumber;}
    public void setEmpNumber(int empNum){empNumber = empNum;}

    public project getProject(String title){
        project p = null;
        for(project proj: projects){
            if(proj.title.equalsIgnoreCase(title))
                p = proj;
        }
        return p;
    }
    public void addProject(project proj){
        if(null == projects) {
            projects = new ArrayList<project>();
        }
        projects.add(proj);
    }
    public boolean cancelProject(project projInfo){

        if(null == projInfo) {
            System.out.println("Error: Invalid project input..");
            return false;
        }

        ArrayList<worker> wrkers = projInfo.wrkers;
        // 1. Suffle the employees to other projects A and C
        for(worker w: wrkers)
        {
            String workerName = w.name;
            String regex      = "[a-mA-M]";
            Pattern pattern   = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher   = pattern.matcher(workerName);
            String projName   = null;
            if(matcher.find()){
                // Add the worker to Project A
                projName = "Project A";
            }else{
                // Add the worker to Project C
                projName = "Project C";
            }
            project proj = getProject(projName);
            proj.addWrker(w);
        }

        // 2. Remove the project from project manager
        projects.remove(projInfo);
        return true;
    }
}
