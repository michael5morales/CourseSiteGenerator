/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduletab.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Michael
 */
public class ScheduleItem {
    private final StringProperty type;
    private final StringProperty date;
    private final StringProperty title;
    private final StringProperty topic;
    private final StringProperty link;
    
    public ScheduleItem(String initType, String initDate, String initTitle, String initTopic, String initLink) {
        type = new SimpleStringProperty(initType);
        date = new SimpleStringProperty(initDate);
        title = new SimpleStringProperty(initTitle);
        topic = new SimpleStringProperty(initTopic);
        link = new SimpleStringProperty(initLink);
    }
    
    public String getType() {
        return type.get();
    }
    
    public void setType(String initType) {
        type.set(initType);
    }
    
    public String getDate() {
        return date.get();
    }
    
    public void setDate(String initDate) {
        date.set(initDate);
    }
    
    public String getTitle() {
        return title.get();
    }
    
    public void setTitle(String initTitle) {
        title.set(initTitle);
    }
    
    public String getTopic() {
        return topic.get();
    }
    
    public void setTopic(String initTopic) {
        topic.set(initTopic);
    }
    
    public String getLink() {
        return link.get();
    }
    
    public void setLink(String initLink) {
        link.set(initLink);
    }
    
}
