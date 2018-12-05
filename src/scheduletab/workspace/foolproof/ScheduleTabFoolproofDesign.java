/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduletab.workspace.foolproof;

import coursesite.CourseSiteApp;
import coursesite.data.CourseSiteData;
import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import static scheduletab.ScheduleTabPropertyType.*;
import scheduletab.data.ScheduleItem;
import scheduletab.data.ScheduleTabData;

/**
 *
 * @author Michael
 */
public class ScheduleTabFoolproofDesign implements FoolproofDesign {
    CourseSiteApp app;

    public ScheduleTabFoolproofDesign(CourseSiteApp initApp) {
        app = initApp;
    }

    @Override
    public void updateControls() {
        updateAddEditButton();
        updateTable();
    }
    
    private void updateAddEditButton() {
        AppGUIModule gui = app.getGUIModule();
        
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        ScheduleTabData data = (ScheduleTabData)siteData.getScheduleTabData();
        
        Button addEditButton = (Button)gui.getGUINode(SCHEDULE_TAB_ADD_BUTTON);
        
        if (data.isSelected()) {
            addEditButton.setText("Update");
        } else {
            addEditButton.setText("Add/Update");
        }
    }
    
    private void updateTable() {
       AppGUIModule gui = app.getGUIModule();
        
       CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
       ScheduleTabData data = (ScheduleTabData)siteData.getScheduleTabData();

       
       if (!data.isEmpty()) {
            data.updateTable();
       }
    }
}
