/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Major {
    private String Name , Sign, Namechange , Signchange,Major;
    private int term;

    public Major() {
    }

    public Major(String Name, String Sign, String Namechange, String Signchange,String Major , int term) {
        this.Name = Name;
        this.Sign = Sign;
        this.Namechange = Namechange;
        this.Signchange = Signchange;
        this.Major = Major;
        this.term = term;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String Major) {
        this.Major = Major;
    }
    
    public String getSignchange() {
        return Signchange;
    }

    public void setSignchange(String Signchange) {
        this.Signchange = Signchange;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNamechange() {
        return Namechange;
    }

    public void setNamechange(String Namechange) {
        this.Namechange = Namechange;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String Sign) {
        this.Sign = Sign;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
    
    
}
