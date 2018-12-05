/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesitetab.workspace.foolproof;

import coursesite.CourseSiteApp;
import static coursesitetab.CourseSiteTabPropertyType.*;
import djf.modules.AppGUIModule;
import djf.ui.dialogs.AppDialogsFacade;
import djf.ui.foolproof.FoolproofDesign;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 *
 * @author Michael
 */
public class CourseSiteTabFoolproofDesign implements FoolproofDesign {
    CourseSiteApp app;

    public CourseSiteTabFoolproofDesign(CourseSiteApp initApp) {
        app = initApp;
    }

    @Override
    public void updateControls() {
        updateExportDir();
        updatePagesFoolproofDesign();
    }
    
    private void updatePagesFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        
        CheckBox homeBox = (CheckBox)gui.getGUINode(CS_TAB_HOME_CHECK_BOX);
        CheckBox syllabusBox = (CheckBox)gui.getGUINode(CS_TAB_SYLLABUS_CHECK_BOX);
        CheckBox scheduleBox = (CheckBox)gui.getGUINode(CS_TAB_SCHEDULE_CHECK_BOX);
        CheckBox hwBox = (CheckBox)gui.getGUINode(CS_TAB_HWS_CHECK_BOX);
        
        if (!homeBox.isSelected() && !syllabusBox.isSelected()
                && !scheduleBox.isSelected() && !hwBox.isSelected()) {
            
            AppDialogsFacade.showMessageDialog(gui.getWindow(), CS_TAB_PAGES_ERROR_TITLE, CS_TAB_PAGES_ERROR_MESSAGE);
            
            if (homeBox.focusedProperty().get()) {
                homeBox.selectedProperty().setValue(true);
            }
            if (syllabusBox.focusedProperty().get()) {
                syllabusBox.selectedProperty().setValue(true);
            }
            if (scheduleBox.focusedProperty().get()) {
                scheduleBox.selectedProperty().setValue(true);
            }
            if (hwBox.focusedProperty().get()) {
                hwBox.selectedProperty().setValue(true);
            }
        }
    }
    
    private void updateExportDir() {
        AppGUIModule gui = app.getGUIModule();
        Label exportDir = (Label) gui.getGUINode(CS_TAB_BANNER_EXPORT_DIR_LABEL);
        
        String dir = ".\\export\\";
        
        ComboBox subjectBox = (ComboBox)gui.getGUINode(CS_TAB_SUBJECT_COMBO_BOX);
        ComboBox numberBox = (ComboBox)gui.getGUINode(CS_TAB_NUMBER_COMBO_BOX);
        ComboBox semesterBox = (ComboBox)gui.getGUINode(CS_TAB_SEMESTER_COMBO_BOX);
        ComboBox yearBox = (ComboBox)gui.getGUINode(CS_TAB_YEAR_COMBO_BOX);
        
        String subject = (String)subjectBox.getValue();
        String number = (String)numberBox.getValue();;
        String semester = (String)semesterBox.getValue();;
        String year = (String)yearBox.getValue();;
        
        dir += subject + "_" + number + "_" + semester + "_" + year;
        
        dir += "\\public_html";
        exportDir.setText(dir);
    }
}
