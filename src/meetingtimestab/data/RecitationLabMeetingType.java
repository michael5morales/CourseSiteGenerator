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
    private final StringProperty dayTime;
    private final StringProperty room;
    private final StringProperty firstTA;
    private final StringProperty secondTA;
    
    public RecitationLabMeetingType(String initSection, String initDayTime, String initRoom, String initTA1, String initTA2) {
        section = new SimpleStringProperty(initSection);
        dayTime = new SimpleStringProperty(initDayTime);
        room = new SimpleStringProperty(initRoom);
        firstTA = new SimpleStringProperty(initTA1);
        secondTA = new SimpleStringProperty(initTA2);
    }
    
    public String getSection() {
        return section.get();
    }
    
    public void setSection(String initSection) {
        section.set(initSection);
    }
    
    public String getDayTime() {
        return dayTime.get();
    }
    
    public void setDayTime(String initDayTime) {
        dayTime.set(initDayTime);
    }
    
    public String getRoom() {
        return room.get();
    }
    
    public void setRoom(String initRoom) {
        room.set(initRoom);
    }
    
    public String getFirstTA() {
        return firstTA.get();
    }
    
    public void setFirstTA(String initTA) {
        firstTA.set(initTA);
    }
    
     public String getSecondTA() {
        return secondTA.get();
    }
    
    public void setSecondTA(String initTA) {
        secondTA.set(initTA);
    }
}
