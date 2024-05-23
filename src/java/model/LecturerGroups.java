/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class LecturerGroups {
    private int CodeL ;
    private String nameL;
    private int CodeG ;
    private String nameG;

    public LecturerGroups() {
    }

    public LecturerGroups(int CodeL, String nameL, int CodeG, String nameG) {
        this.CodeL = CodeL;
        this.nameL = nameL;
        this.CodeG = CodeG;
        this.nameG = nameG;
    }

    public int getCodeL() {
        return CodeL;
    }

    public void setCodeL(int CodeL) {
        this.CodeL = CodeL;
    }

    public String getNameL() {
        return nameL;
    }

    public void setNameL(String nameL) {
        this.nameL = nameL;
    }

    public int getCodeG() {
        return CodeG;
    }

    public void setCodeG(int CodeG) {
        this.CodeG = CodeG;
    }

    public String getNameG() {
        return nameG;
    }

    public void setNameG(String nameG) {
        this.nameG = nameG;
    }
}
