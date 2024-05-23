package model;

import java.util.ArrayList;

public class Department {
    private int Code;
    private String Name;
    private ArrayList<Course> course;
    
    public Department() {
    }

    public Department(int Code, String Name, ArrayList<Course> course) {
        this.Code = Code;
        this.Name = Name;
        this.course = course;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public ArrayList<Course> getCourse() {
        return course;
    }

    public void setCourse(ArrayList<Course> course) {
        this.course = course;
    }
    
}
