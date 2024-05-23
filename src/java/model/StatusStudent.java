/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class StatusStudent {
    private String StudentCode ;
    private int Gr;
    private int Slot;
    private boolean status;
    private String describe ;
    private Date Time;
    public StatusStudent() {
    }

    public StatusStudent(String StudentCode, int Gr, int Slot, boolean status, String describe, Date Time) {
        this.StudentCode = StudentCode;
        this.Gr = Gr;
        this.Slot = Slot;
        this.status = status;
        this.describe = describe;
        this.Time = Time;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date Time) {
        this.Time = Time;
    }

    

    public String getStudentCode() {
        return StudentCode;
    }

    public void setStudentCode(String StudentCode) {
        this.StudentCode = StudentCode;
    }

    public int getGr() {
        return Gr;
    }

    public void setGr(int Gr) {
        this.Gr = Gr;
    }

    public int getSlot() {
        return Slot;
    }

    public void setSlot(int Slot) {
        this.Slot = Slot;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
