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
public class RecitationLabMeetingType {
    private final StringProperty section;
    private final StringProperty daysAndTime;
    private final StringProperty room;
    private final StringProperty ta1;
    private final StringProperty ta2;
    
    public RecitationLabMeetingType(String initSection, String initDayTime, String initRoom, String initTA1, String initTA2) {
        section = new SimpleStringProperty(initSection);
        daysAndTime = new SimpleStringProperty(initDayTime);
        room = new SimpleStringProperty(initRoom);
        ta1 = new SimpleStringProperty(initTA1);
        ta2 = new SimpleStringProperty(initTA2);
    }
    
    public String getSection() {
        return section.get();
    }
    
    public void setSection(String initSection) {
        section.set(initSection);
    }
    
    public String getDayTime() {
        return daysAndTime.get();
    }
    
    public void setDayTime(String initDayTime) {
        daysAndTime.set(initDayTime);
    }
    
    public String getRoom() {
        return room.get();
    }
    
    public void setRoom(String initRoom) {
        room.set(initRoom);
    }
    
    public String getTA1() {
        return ta1.get();
    }
    
    public void setTA1(String initTA) {
        ta1.set(initTA);
    }
    
     public String getTA2() {
        return ta2.get();
    }
    
    public void setTA2(String initTA) {
        ta2.set(initTA);
    }
}
