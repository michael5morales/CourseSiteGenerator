/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduletab.workspace.controllers;

import coursesite.CourseSiteApp;
import coursesite.data.CourseSiteData;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import static scheduletab.ScheduleTabPropertyType.*;
import scheduletab.data.ScheduleItem;
import scheduletab.data.ScheduleTabData;
import scheduletab.transactions.AddScheduleItem_Transaction;
import scheduletab.transactions.EditScheduleItem_Transaction;
import scheduletab.transactions.RemoveScheduleItem_Transaction;
/**
 *
 * @author Michael
 */
public class ScheduleTabController {
    
     CourseSiteApp app;

    public ScheduleTabController(CourseSiteApp initApp) {
        app = initApp;
    }
    
    public void processAddItem() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        ScheduleTabData data = (ScheduleTabData)siteData.getScheduleTabData();
        
        //GET NODES
        ComboBox typeBox = (ComboBox) app.getGUIModule().getGUINode(SCHEDULE_TAB_TYPE_COMBO_BOX);
        DatePicker dateBox = (DatePicker) app.getGUIModule().getGUINode(SCHEDULE_TAB_ADD_DATE_DATE_PICKER);
        TextField titleTextField = (TextField) app.getGUIModule().getGUINode(SCHEDULE_TAB_TITLE_TEXT_AREA);
        TextField topicTextField = (TextField) app.getGUIModule().getGUINode(SCHEDULE_TAB_TOPIC_TEXT_AREA);
        TextField linkTextField = (TextField) app.getGUIModule().getGUINode(SCHEDULE_TAB_LINK_TEXT_AREA);
        
        //CONVERT TO STRINGS
        String type = (String)typeBox.getValue();
        String date = dateBox.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        String title = titleTextField.getText();
        String topic = topicTextField.getText();
        String link = linkTextField.getText();
        
        ScheduleItem schedule = new ScheduleItem(type, date, title, topic, link);
        AddScheduleItem_Transaction addScheduleItemTransaction= new AddScheduleItem_Transaction(data, schedule);
        app.processTransaction(addScheduleItemTransaction);
        
        
        typeBox.getSelectionModel().clearSelection();
        typeBox.setValue(null);
        dateBox.setValue(LocalDate.now());
        titleTextField.clear();
        topicTextField.clear();
        linkTextField.clear();
    }
    
    public void processRemoveItem() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        ScheduleTabData data = (ScheduleTabData)siteData.getScheduleTabData();
        
        ScheduleItem schedule = data.getSelectedItem();
        
        RemoveScheduleItem_Transaction removeScheduleItemTransaction= new RemoveScheduleItem_Transaction(data, schedule);
        app.processTransaction(removeScheduleItemTransaction);
    }
    
    public void processUpdateItem() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        ScheduleTabData data = (ScheduleTabData)siteData.getScheduleTabData();
        
        ScheduleItem item = data.getSelectedItem();
       
        //GET NODES
        ComboBox typeBox = (ComboBox) app.getGUIModule().getGUINode(SCHEDULE_TAB_TYPE_COMBO_BOX);
        DatePicker dateBox = (DatePicker) app.getGUIModule().getGUINode(SCHEDULE_TAB_ADD_DATE_DATE_PICKER);
        TextField titleTextField = (TextField) app.getGUIModule().getGUINode(SCHEDULE_TAB_TITLE_TEXT_AREA);
        TextField topicTextField = (TextField) app.getGUIModule().getGUINode(SCHEDULE_TAB_TOPIC_TEXT_AREA);
        TextField linkTextField = (TextField) app.getGUIModule().getGUINode(SCHEDULE_TAB_LINK_TEXT_AREA);
        
        //CONVERT TO STRINGS
        String type = (String)typeBox.getValue();
        String date = dateBox.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        String title = titleTextField.getText();
        String topic = topicTextField.getText();
        String link = linkTextField.getText();
        
        EditScheduleItem_Transaction editScheduleItemTransaction= new EditScheduleItem_Transaction(item, type, date, title, topic, link);
        app.processTransaction(editScheduleItemTransaction);
    }
    
    public void processLoadItem()  {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        ScheduleTabData data = (ScheduleTabData)siteData.getScheduleTabData();
        
        ComboBox typeBox = (ComboBox) app.getGUIModule().getGUINode(SCHEDULE_TAB_TYPE_COMBO_BOX);
        DatePicker dateBox = (DatePicker) app.getGUIModule().getGUINode(SCHEDULE_TAB_ADD_DATE_DATE_PICKER);
        TextField titleTextField = (TextField) app.getGUIModule().getGUINode(SCHEDULE_TAB_TITLE_TEXT_AREA);
        TextField topicTextField = (TextField) app.getGUIModule().getGUINode(SCHEDULE_TAB_TOPIC_TEXT_AREA);
        TextField linkTextField = (TextField) app.getGUIModule().getGUINode(SCHEDULE_TAB_LINK_TEXT_AREA);
        
        ScheduleItem item = data.getSelectedItem();
        
        typeBox.getSelectionModel().select(item.getType());
        
        String date = item.getDate();
        LocalDate localDate = null;
        
        String[] dateArray = date.split("/");
        
        int month = Integer.parseInt(dateArray[1]);
        int day = Integer.parseInt(dateArray[0]);
        int year = 2000 + Integer.parseInt(dateArray[2]);
        
        localDate = LocalDate.of(year, month, day);
        dateBox.setValue(localDate);
        
        titleTextField.setText(item.getTitle());
        topicTextField.setText(item.getTopic());
        linkTextField.setText(item.getLink());
    }
    
    public void processClearSelection() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        ScheduleTabData data = (ScheduleTabData)siteData.getScheduleTabData();
        
        ComboBox typeBox = (ComboBox) app.getGUIModule().getGUINode(SCHEDULE_TAB_TYPE_COMBO_BOX);
        DatePicker dateBox = (DatePicker) app.getGUIModule().getGUINode(SCHEDULE_TAB_ADD_DATE_DATE_PICKER);
        TextField titleTextField = (TextField) app.getGUIModule().getGUINode(SCHEDULE_TAB_TITLE_TEXT_AREA);
        TextField topicTextField = (TextField) app.getGUIModule().getGUINode(SCHEDULE_TAB_TOPIC_TEXT_AREA);
        TextField linkTextField = (TextField) app.getGUIModule().getGUINode(SCHEDULE_TAB_LINK_TEXT_AREA);
        
        TableView<ScheduleItem> table = (TableView) app.getGUIModule().getGUINode(SCHEDULE_TAB_ITEMS_TABLE_VIEW);
        
        table.getSelectionModel().clearSelection();
        typeBox.getSelectionModel().clearSelection();
        typeBox.setValue(null);
        dateBox.setValue(LocalDate.now());
        titleTextField.clear();
        topicTextField.clear();
        linkTextField.clear();
    }
    
}
