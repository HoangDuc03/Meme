/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Slots {
    private int Code;
    private String Name,Sign ,Day ;
    private int Slot;
    private int CodeLect;

    public String getSign() {
        return Sign;
    }

    public void setSign(String Sign) {
        this.Sign = Sign;
    }
    private int SlotNumber;
    private String Room;

    public Slots(int Code, String Name, String Day, int Slot) {
        this.Code = Code;
        this.Name = Name;
        this.Day = Day;
        this.Slot = Slot;
    }

    public Slots(int Code, String Name, String Day, int Slot, int CodeLect, int SlotNumber, String Room) {
        this.Code = Code;
        this.Name = Name;
        this.Day = Day;
        this.Slot = Slot;
        this.CodeLect = CodeLect;
        this.SlotNumber = SlotNumber;
        this.Room = Room;
    }
    
    
    public Slots() {
    }

    public int getSlotNumber() {
        return SlotNumber;
    }

    public void setSlotNumber(int SlotNumber) {
        this.SlotNumber = SlotNumber;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String Room) {
        this.Room = Room;
    }
    
    public int getCodeLect() {
        return CodeLect;
    }

    public void setCodeLect(int CodeLect) {
        this.CodeLect = CodeLect;
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

    public String getDay() {
        return Day;
    }

    public void setDay(String Day) {
        this.Day = Day;
    }

    public int getSlot() {
        return Slot;
    }

    public void setSlot(int Slot) {
        this.Slot = Slot;
    }
    
}
