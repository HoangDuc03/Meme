/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Student {
    private String Name, MSSV ;
    private boolean Gender;
    private String Mail, Phone, CCCD , Address , Major;
    private int semester;

    public Student() {
    }

    public Student(String Name, String MSSV) {
        this.Name = Name;
        this.MSSV = MSSV;
    }
    
    
    public Student(String Name, String MSSV, boolean Gender, String Mail, String Phone, String CCCD, String Address, String Major, int semester) {
        this.Name = Name;
        this.MSSV = MSSV;
        this.Gender = Gender;
        this.Mail = Mail;
        this.Phone = Phone;
        this.CCCD = CCCD;
        this.Address = Address;
        this.Major = Major;
        this.semester = semester;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String Major) {
        this.Major = Major;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean Gender) {
        this.Gender = Gender;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    
}
