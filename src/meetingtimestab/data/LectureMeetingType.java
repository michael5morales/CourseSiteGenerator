/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingtimestab.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Michael
 */
public class LectureMeetingType {
    private final StringProperty section;
    private final StringProperty day;
    private final StringProperty time;
    private final StringProperty room;
    
    public LectureMeetingType(String initSection, String initDay, String initTime, String initRoom) {
        section = new SimpleStringProperty(initSection);
        day = new SimpleStringProperty(initDay);
        time = new SimpleStringProperty(initTime);
        room = new SimpleStringProperty(initRoom);
    }
    
    public String getSection() {
        return section.get();
    }
    
    public void setSection(String initSection) {
        section.set(initSection);
    }
    
    public String getDay() {
        return day.get();
    }
    
    public void setDay(String initDay) {
        day.set(initDay);
    }
    
    public String getTime() {
        return time.get();
    }
    
    public void setTime(String initTime) {
        time.set(initTime);
    }
    
    public String getRoom() {
        return room.get();
    }
    
    public void setRoom(String initRoom) {
        room.set(initRoom);
    }
 
}
