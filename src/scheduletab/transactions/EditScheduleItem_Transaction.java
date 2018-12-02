/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduletab.transactions;

import jtps.jTPS_Transaction;
import scheduletab.data.ScheduleItem;

/**
 *
 * @author Michael
 */
public class EditScheduleItem_Transaction implements jTPS_Transaction{
    ScheduleItem itemToEdit;
    String oldType, newType;
    String oldDate, newDate;
    String oldTitle, newTitle;
    String oldTopic, newTopic;
    String oldLink, newLink;
    
    public EditScheduleItem_Transaction(ScheduleItem initItemToEdit, 
            String type, String date, String title, String topic, String link) {
        itemToEdit = initItemToEdit;
        oldType = initItemToEdit.getType();
        oldDate = initItemToEdit.getDate();
        oldTitle = initItemToEdit.getTitle();
        oldTopic = initItemToEdit.getTopic();
        oldLink = initItemToEdit.getLink();
        newType = type;
        newDate = date;
        newTitle = title;
        newTopic = topic;
        newLink = link;
    }


    @Override
    public void doTransaction() {
        itemToEdit.setType(newType);
        itemToEdit.setDate(newDate);
        itemToEdit.setTitle(newTitle);
        itemToEdit.setTopic(newTopic);
        itemToEdit.setLink(newLink);
       
    }

    @Override
    public void undoTransaction() {
        itemToEdit.setType(oldType);
        itemToEdit.setDate(oldDate);
        itemToEdit.setTitle(oldTitle);
        itemToEdit.setTopic(oldTopic);
        itemToEdit.setLink(oldLink);
        
    }
}

