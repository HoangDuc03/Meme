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
public class Course {
    private int Code;
    private String Name, sign;
    private ArrayList<Group> Gr;

    public Course() {
    }

    public Course(int Code, String Name, String sign) {
        this.Code = Code;
        this.Name = Name;
        this.sign = sign;
    }

    public Course(int Code, String Name, String sign, ArrayList<Group> Gr) {
        this.Code = Code;
        this.Name = Name;
        this.sign = sign;
        this.Gr = Gr;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public ArrayList<Group> getGr() {
        return Gr;
    }

    public void setGr(ArrayList<Group> Gr) {
        this.Gr = Gr;
    }

    
    
    
    
    
}
