/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class MarkOfStudent {
    private int Code;
    private Mark CodeMarks;
    private String CodeSTD;
    private int mark;

    public MarkOfStudent() {
    }

    public MarkOfStudent(int Code, Mark CodeMarks, String CodeSTD, int mark) {
        this.Code = Code;
        this.CodeMarks = CodeMarks;
        this.CodeSTD = CodeSTD;
        this.mark = mark;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public Mark getCodeMarks() {
        return CodeMarks;
    }

    public void setCodeMarks(Mark CodeMarks) {
        this.CodeMarks = CodeMarks;
    }

    public String getCodeSTD() {
        return CodeSTD;
    }

    public void setCodeSTD(String CodeSTD) {
        this.CodeSTD = CodeSTD;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
    
}
