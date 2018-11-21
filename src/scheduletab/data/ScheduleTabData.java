/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduletab.data;

import coursesite.CourseSiteApp;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import static scheduletab.ScheduleTabPropertyType.*;


/**
 *
 * @author Michael
 */
public class ScheduleTabData implements AppDataComponent {
    CourseSiteApp app;
    ObservableList<ScheduleItem> scheduleItems;
    
    public ScheduleTabData(CourseSiteApp initApp) {
        app = initApp;
        AppGUIModule gui = app.getGUIModule();
        
        TableView<ScheduleItem> scheduleItemsTableView = (TableView)gui.getGUINode(SCHEDULE_TAB_ITEMS_TABLE_VIEW);
        scheduleItems = scheduleItemsTableView.getItems();
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
        scheduleItems.add(item);
    }
    
    @Override
    public void reset() {
        scheduleItems.clear();
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
