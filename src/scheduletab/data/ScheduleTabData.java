/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduletab.data;

import coursesite.CourseSiteApp;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import static scheduletab.ScheduleTabPropertyType.*;


/**
 *
 * @author Michael
 */
public class ScheduleTabData implements AppDataComponent {
    CourseSiteApp app;
    ObservableList<ScheduleItem> scheduleItems;
    ArrayList<ScheduleItem> allItems = new ArrayList<>();
    
    public ScheduleTabData(CourseSiteApp initApp) {
        app = initApp;
        AppGUIModule gui = app.getGUIModule();
        
        TableView<ScheduleItem> scheduleItemsTableView = (TableView)gui.getGUINode(SCHEDULE_TAB_ITEMS_TABLE_VIEW);
        scheduleItems = scheduleItemsTableView.getItems();
        
        for (ScheduleItem item: scheduleItems) {
            allItems.add(item);
        }
    }
    
    public int getItemMonth(ScheduleItem item) {
        int month = 0;
        
        return month;
    }
    
    public int getItemDay(ScheduleItem item) {
        int day = 0;
        
        return day;
    }
    
    public void addItem(ScheduleItem item) {
        this.scheduleItems.add(item);
        this.allItems.add(item);
    }
    
    public void removeItem(ScheduleItem item) {
        for (int i = 0; i < scheduleItems.size(); i++) {
            if (scheduleItems.get(i) == item) {
                scheduleItems.remove(i);
                break;
            } 
        } 
        
        for (int i = 0; i < allItems.size(); i++) {
            if (allItems.get(i) == item) {
                allItems.remove(i);
                break;
            } 
        } 
    } 
    
    public int getDateValue(String date) {
        int value = 0;
        
        String[] dateNums = date.split("/");
        
        value += 12 * 30 * (Integer.parseInt(dateNums[2]));
        
        value += Integer.parseInt(dateNums[1]);
        
        value += 30 * (Integer.parseInt(dateNums[0]));
        
        return value;
    }
    
    public void updateTable() {
        AppGUIModule gui = app.getGUIModule();
        DatePicker startingDate = (DatePicker)gui.getGUINode(SCHEDULE_TAB_START_DATE_DATE_PICKER);
        DatePicker endingDate = (DatePicker)gui.getGUINode(SCHEDULE_TAB_END_DATE_DATE_PICKER);
        
        String start = startingDate.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        String end = endingDate.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        
        int startValue = getDateValue(start);
        int endValue = getDateValue(end);
        
        ArrayList<ScheduleItem> temp = new ArrayList<>();
        
        for (int i = 0; i < allItems.size(); i++) {
            String date = formatDate(allItems.get(i).getDate());
            int value = getDateValue(date);
            if (value <= endValue && value >= startValue) {
                temp.add(allItems.get(i));
            }
        }
        
        scheduleItems.clear();
            
            for (ScheduleItem item: temp) {
                scheduleItems.add(item);
            }
    }
    
    private String formatDate(String date) {
        String[] dates = date.split("/");
        if (dates[2].equals("2018")) {
            dates[2] = "18";
        }
        return dates[0] + "/" + dates[1] + "/" + dates[2];
    }
    
    public boolean isEmpty() {
        return allItems.isEmpty();
    }
    
    public boolean isSelected() {
        AppGUIModule gui = app.getGUIModule();
        TableView<ScheduleItem> scheduleTable = (TableView)gui.getGUINode(SCHEDULE_TAB_ITEMS_TABLE_VIEW);
        return scheduleTable.getSelectionModel().getSelectedItem() != null;
    }
    
    public String getStartingMonth() {
        AppGUIModule gui = app.getGUIModule();
        DatePicker datePicker = (DatePicker) gui.getGUINode(SCHEDULE_TAB_START_DATE_DATE_PICKER);
        String date = datePicker.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        String[] nums = date.split("/");
        return nums[0];
    }
    
    public String getStartingDay() {
        AppGUIModule gui = app.getGUIModule();
        DatePicker datePicker = (DatePicker) gui.getGUINode(SCHEDULE_TAB_START_DATE_DATE_PICKER);
        String date = datePicker.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        String[] nums = date.split("/");
        return nums[1];
    }
    
    public String getEndingMonth() {
        AppGUIModule gui = app.getGUIModule();
        DatePicker datePicker = (DatePicker) gui.getGUINode(SCHEDULE_TAB_END_DATE_DATE_PICKER);
        String date = datePicker.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        String[] nums = date.split("/");
        return nums[0];
    }
    
    public String getEndingDay() {
        AppGUIModule gui = app.getGUIModule();
        DatePicker datePicker = (DatePicker) gui.getGUINode(SCHEDULE_TAB_END_DATE_DATE_PICKER);
        String date = datePicker.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        String[] nums = date.split("/");
        return nums[1];
    }
    
    public void setStartingDate(String initDay, String initMonth) {
        AppGUIModule gui = app.getGUIModule();
        String date = initDay + "/" + initMonth + "/2018";
        LocalDate startDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/M/yyyy"));
        DatePicker datePicker = (DatePicker)gui.getGUINode(SCHEDULE_TAB_START_DATE_DATE_PICKER);
        datePicker.setValue(startDate);
    }
    
    public void setEndingDate(String initDay, String initMonth) {
        AppGUIModule gui = app.getGUIModule();
        String date = initDay + "/" + initMonth + "/2018";
        LocalDate endDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/M/yyyy"));
        DatePicker datePicker = (DatePicker)gui.getGUINode(SCHEDULE_TAB_END_DATE_DATE_PICKER);
        datePicker.setValue(endDate);
    }
    
    public ScheduleItem getSelectedItem() {
        AppGUIModule gui = app.getGUIModule();
        TableView<ScheduleItem> scheduleTable = (TableView)gui.getGUINode(SCHEDULE_TAB_ITEMS_TABLE_VIEW);
        return scheduleTable.getSelectionModel().getSelectedItem();
    }
    
    @Override
    public void reset() {
        scheduleItems.clear();
        allItems.clear();
    }
    
    public Iterator<ScheduleItem> ScheduleItemIterator() {
        return new ScheduleIterator();
    }
    
    private class ScheduleIterator implements Iterator {
        Iterator scheduleIt = scheduleItems.iterator();

        public ScheduleIterator() {}
        
        @Override
        public boolean hasNext() {
            if (scheduleIt.hasNext())
                return true;
            else
                return false;                
        }

        @Override
        public Object next() {
            if (scheduleIt.hasNext())
                return scheduleIt.next();
            else
                return null;
        }
    }
    
}
