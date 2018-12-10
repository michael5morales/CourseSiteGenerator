/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesitetab.data;

import coursesite.CourseSiteApp;
import static coursesitetab.CourseSiteTabPropertyType.*;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Michael
 */
public class CourseSiteTabData implements AppDataComponent {
    CourseSiteApp app;
    
    
    public CourseSiteTabData(CourseSiteApp initApp) {
        app = initApp;
    }
    
    public void initBannerBox(String subject, String number, String semester, String year, String title) {
        AppGUIModule gui = app.getGUIModule();
        
        ComboBox subjectBox = (ComboBox)gui.getGUINode(CS_TAB_SUBJECT_COMBO_BOX);
        subjectBox.getSelectionModel().select(subject);
        
        ComboBox numberBox = (ComboBox)gui.getGUINode(CS_TAB_NUMBER_COMBO_BOX);
        numberBox.getSelectionModel().select(number);
        
        ComboBox semesterBox = (ComboBox)gui.getGUINode(CS_TAB_SEMESTER_COMBO_BOX);
        semesterBox.getSelectionModel().select(semester);
        
        ComboBox yearBox = (ComboBox)gui.getGUINode(CS_TAB_YEAR_COMBO_BOX);
        yearBox.getSelectionModel().select(year);
        
        TextField titleTextField = (TextField)gui.getGUINode(CS_TAB_TITLE_TEXT_FIELD);
        titleTextField.setText(title);
    }
    
    public void initPages(ArrayList<String> pages) {
        AppGUIModule gui = app.getGUIModule();
        
        CheckBox homeCheckBox = (CheckBox)gui.getGUINode(CS_TAB_HOME_CHECK_BOX);
        CheckBox syllabusCheckBox = (CheckBox)gui.getGUINode(CS_TAB_SYLLABUS_CHECK_BOX);
        CheckBox scheduleCheckBox = (CheckBox)gui.getGUINode(CS_TAB_SCHEDULE_CHECK_BOX);
        CheckBox hwsCheckBox = (CheckBox)gui.getGUINode(CS_TAB_HWS_CHECK_BOX);
        
        for (String page : pages) {
            if (page.equals(homeCheckBox.getText()))
                homeCheckBox.selectedProperty().set(true);
            if (page.equals(syllabusCheckBox.getText()) ) 
                syllabusCheckBox.selectedProperty().set(true);
            if (page.equals(scheduleCheckBox.getText()) ) 
                scheduleCheckBox.selectedProperty().set(true);
            if (page.equals(hwsCheckBox.getText()) ) 
                hwsCheckBox.selectedProperty().set(true);
        }
        
    }
    
    public void initInstructor(String name, String room, String email, String homePage, String hours) {
        AppGUIModule gui = app.getGUIModule();
        
        TextField nameField = (TextField) gui.getGUINode(CS_TAB_NAME_TEXT_FIELD);
        TextField emailField = (TextField) gui.getGUINode(CS_TAB_EMAIL_TEXT_FIELD);
        TextField roomField = (TextField) gui.getGUINode(CS_TAB_ROOM_TEXT_FIELD);
        TextField homePageField = (TextField) gui.getGUINode(CS_TAB_HOME_PAGE_TEXT_FIELD);
        TextArea hoursTxtArea = (TextArea) gui.getGUINode(CS_TAB_OH_TEXT_AREA);
        
        nameField.setText(name);
        emailField.setText(email);
        roomField.setText(room);
        homePageField.setText(homePage);
        hoursTxtArea.setText(hours);
    }
    
    public String getExportDir() {
        AppGUIModule gui = app.getGUIModule();
        Label label = (Label)gui.getGUINode(CS_TAB_BANNER_EXPORT_DIR_LABEL);
        return label.getText();
    }
    
    //HELPER METHODS FOR SAVING TO JSON FILES
    public String getSubject() {
        AppGUIModule gui = app.getGUIModule();
        ComboBox subjectBox = (ComboBox)gui.getGUINode(CS_TAB_SUBJECT_COMBO_BOX);
        String selected = subjectBox.getValue().toString();
        return selected;
    }
    
    public String getNumber() {
        AppGUIModule gui = app.getGUIModule();
        ComboBox numberBox = (ComboBox)gui.getGUINode(CS_TAB_NUMBER_COMBO_BOX);
        String selected = numberBox.getValue().toString();
        return selected;
    }
   
    public String getSemester() {
        AppGUIModule gui = app.getGUIModule();
        ComboBox semesterBox = (ComboBox)gui.getGUINode(CS_TAB_SEMESTER_COMBO_BOX);
        String selected = semesterBox.getValue().toString();
        return selected;
    }
   
    public String getYear() {
        AppGUIModule gui = app.getGUIModule();
        ComboBox yearBox = (ComboBox)gui.getGUINode(CS_TAB_YEAR_COMBO_BOX);
        String selected = yearBox.getValue().toString();
        return selected;
    }
   
    public String getTitle() {
        AppGUIModule gui = app.getGUIModule();
        TextField titleTextField = (TextField)gui.getGUINode(CS_TAB_TITLE_TEXT_FIELD);
        String title = titleTextField.getText();
        return title;
    }
    
    public ArrayList<String> getPages() {
        AppGUIModule gui = app.getGUIModule();
        ArrayList<String> selected = new ArrayList<>();
        CheckBox homeCheckBox = (CheckBox)gui.getGUINode(CS_TAB_HOME_CHECK_BOX);
        CheckBox syllabusCheckBox = (CheckBox)gui.getGUINode(CS_TAB_SYLLABUS_CHECK_BOX);
        CheckBox scheduleCheckBox = (CheckBox)gui.getGUINode(CS_TAB_SCHEDULE_CHECK_BOX);
        CheckBox hwsCheckBox = (CheckBox)gui.getGUINode(CS_TAB_HWS_CHECK_BOX);
        
        if (homeCheckBox.isSelected()) 
            selected.add(homeCheckBox.getText());
        if (syllabusCheckBox.isSelected()) 
            selected.add(syllabusCheckBox.getText());
        if (scheduleCheckBox.isSelected()) 
            selected.add(scheduleCheckBox.getText());
        if (hwsCheckBox.isSelected()) 
            selected.add(hwsCheckBox.getText());
        return selected;
    }
    
    public String getHours() {
        AppGUIModule gui = app.getGUIModule();
        TextArea txtArea = (TextArea) gui.getGUINode(CS_TAB_OH_TEXT_AREA);
        String hours = txtArea.getText();
        return hours;
    }
   
    public String getFavIconURL() {
        AppGUIModule gui = app.getGUIModule();
        ImageView favicon = (ImageView) gui.getGUINode(CS_TAB_ICON_IMAGE_VIEW);
        Image imageUrl = favicon.getImage();
        String url = imageUrl.impl_getUrl();
        return url.substring(5);
    }
    
    public String getNavbarURL() {
        AppGUIModule gui = app.getGUIModule();
        ImageView favicon = (ImageView) gui.getGUINode(CS_TAB_NAVBAR_IMAGE_VIEW);
        Image imageUrl = favicon.getImage();
        String url = imageUrl.impl_getUrl();
        return url.substring(5);
    }
    
    public String getLeftFooterURL() {
        AppGUIModule gui = app.getGUIModule();
        ImageView favicon = (ImageView) gui.getGUINode(CS_TAB_LEFT_FOOTER_IMAGE_VIEW);
        Image imageUrl = favicon.getImage();
        String url = imageUrl.impl_getUrl();
        return url.substring(5);
    }
    
    public String getRightFooterURL() {
        AppGUIModule gui = app.getGUIModule();
        ImageView favicon = (ImageView) gui.getGUINode(CS_TAB_RIGHT_FOOTER_IMAGE_VIEW);
        Image imageUrl = favicon.getImage();
        String url = imageUrl.impl_getUrl();
        return url.substring(5);
    }
    
    public String getInstructorName() {
        AppGUIModule gui = app.getGUIModule();
        TextField nameTextField = (TextField)gui.getGUINode(CS_TAB_NAME_TEXT_FIELD);
        String name = nameTextField.getText();
        return name;
    }
    
    public String getRoom() {
        AppGUIModule gui = app.getGUIModule();
        TextField roomTextField = (TextField)gui.getGUINode(CS_TAB_ROOM_TEXT_FIELD);
        String room = roomTextField.getText();
        return room;
    }
    
    public String getEmail() {
        AppGUIModule gui = app.getGUIModule();
        TextField emailTextField = (TextField)gui.getGUINode(CS_TAB_EMAIL_TEXT_FIELD);
        String email = emailTextField.getText();
        return email;
    }
    
    public String getHomepage() {
        AppGUIModule gui = app.getGUIModule();
        TextField homePageTextField = (TextField)gui.getGUINode(CS_TAB_HOME_PAGE_TEXT_FIELD);
        String homePage = homePageTextField.getText();
        return homePage;
    }
    
    @Override
    public void reset() {
        
    }
}
