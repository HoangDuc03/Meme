
package model;

import java.util.Date;

public class Lecturer {
    private String Name, MSSV ;
    private boolean Gender;
    private String Mail, Phone, CCCD , Address ;
    private int Dept;
    private boolean Contract;
    private Date Csf ,Duration;

    public Lecturer() {
    }

    public Lecturer(String Name, String MSSV, boolean Gender, String Mail, String Phone, String CCCD, String Address, int Dept, boolean Contract, Date Csf, Date Duration) {
        this.Name = Name;
        this.MSSV = MSSV;
        this.Gender = Gender;
        this.Mail = Mail;
        this.Phone = Phone;
        this.CCCD = CCCD;
        this.Address = Address;
        this.Dept = Dept;
        this.Contract = Contract;
        this.Csf = Csf;
        this.Duration = Duration;
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

    public int getDept() {
        return Dept;
    }

    public void setDept(int Dept) {
        this.Dept = Dept;
    }

    public boolean isContract() {
        return Contract;
    }

    public void setContract(boolean Contract) {
        this.Contract = Contract;
    }

    public Date getCsf() {
        return Csf;
    }

    public void setCsf(Date Csf) {
        this.Csf = Csf;
    }

    public Date getDuration() {
        return Duration;
    }

    public void setDuration(Date Duration) {
        this.Duration = Duration;
    }
    
    
}
