/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class Group {
    private int Code;
    private String Name;
    private ArrayList<Student> Student;
    public Group() {
    }

    public Group(int Code, String Name) {
        this.Code = Code;
        this.Name = Name;
    }

    public Group(int Code, String Name, ArrayList<Student> Student) {
        this.Code = Code;
        this.Name = Name;
        this.Student = Student;
    }

    public ArrayList<Student> getStudent() {
        return Student;
    }

    public void setStudent(ArrayList<Student> Student) {
        this.Student = Student;
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
    
    
}
