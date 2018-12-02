/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package officehourstab.workspace.foolproof;

import coursesite.CourseSiteApp;
import coursesite.data.CourseSiteData;
import static coursesite.workspace.style.CSStyle.*;
import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import static officehourstab.OfficeHoursTabPropertyType.*;
import officehourstab.data.OfficeHoursData;

/**
 *
 * @author Michael
 */
public class OfficeHoursTabFoolproofDesign implements FoolproofDesign {
    CourseSiteApp app;

    public OfficeHoursTabFoolproofDesign(CourseSiteApp initApp) {
        app = initApp;
    }

    @Override
    public void updateControls() {
        updateAddTAFoolproofDesign();
        updateEditTAFoolproofDesign();
        updateRemoveTAFoolproofDesign();
        updateEndTimeComboBoxFoolproofDesign();
        
    }

    private void updateAddTAFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        
        // FOOLPROOF DESIGN STUFF FOR ADD TA BUTTON
        TextField nameTextField = ((TextField) gui.getGUINode(OH_NAME_TEXT_FIELD));
        TextField emailTextField = ((TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD));
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        OfficeHoursData data = (OfficeHoursData)siteData.getOfficeHoursData();
        Button addTAButton = (Button) gui.getGUINode(OH_ADD_TA_BUTTON);

        // FIRST, IF NO TYPE IS SELECTED WE'LL JUST DISABLE
        // THE CONTROLS AND BE DONE WITH IT
        boolean isTypeSelected = data.isTATypeSelected();
        if (!isTypeSelected) {
            nameTextField.setDisable(true);
            emailTextField.setDisable(true);
            addTAButton.setDisable(true);
            return;
        } // A TYPE IS SELECTED SO WE'LL CONTINUE
        else {
            nameTextField.setDisable(false);
            emailTextField.setDisable(false);
            addTAButton.setDisable(false);
        }

        // NOW, IS THE USER-ENTERED DATA GOOD?
        boolean isLegalNewTA = data.isLegalNewTA(name, email);

        // ENABLE/DISABLE THE CONTROLS APPROPRIATELY
        addTAButton.setDisable(!isLegalNewTA);
        if (isLegalNewTA) {
            nameTextField.setOnAction(addTAButton.getOnAction());
            emailTextField.setOnAction(addTAButton.getOnAction());
        } else {
            nameTextField.setOnAction(null);
            emailTextField.setOnAction(null);
        }

        // UPDATE THE CONTROL TEXT DISPLAY APPROPRIATELY
        boolean isLegalNewName = data.isLegalNewName(name);
        boolean isLegalNewEmail = data.isLegalNewEmail(email);
        foolproofTextField(nameTextField, isLegalNewName);
        foolproofTextField(emailTextField, isLegalNewEmail);
    }
    
    private void updateEditTAFoolproofDesign() {
        
    }
    
    private void updateEndTimeComboBoxFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        
        // FOOLPROOF DESIGN STUFF FOR REMOVE TA BUTTON
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        OfficeHoursData data = (OfficeHoursData)siteData.getOfficeHoursData();
        
        ComboBox endTimes = (ComboBox) gui.getGUINode(OH_END_TIME_COMBO_BOX);
        ArrayList<String> timeList = data.getEndTimes();
        ObservableList<String> endList = endTimes.getItems();
        endList.clear();
        
        for (String time : timeList) {
            endList.add(time);
        }
        
        endTimes.setItems(endList);
        endTimes.getSelectionModel().selectLast();
    }
    
    private void updateRemoveTAFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        
        // FOOLPROOF DESIGN STUFF FOR REMOVE TA BUTTON
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        OfficeHoursData data = (OfficeHoursData)siteData.getOfficeHoursData();
        Button removeTAButton = (Button) gui.getGUINode(OH_MINUS_BUTTON);
        
        if (data.isTASelected()) {
            removeTAButton.setDisable(false);
        } else {
            removeTAButton.setDisable(true);
        }
    }
    
    public void foolproofTextField(TextField textField, boolean hasLegalData) {
        if (hasLegalData) {
            textField.getStyleClass().remove(CLASS_OH_TEXT_FIELD_ERROR);
            if (!textField.getStyleClass().contains(CLASS_CS_TEXT_FIELD)) {
                textField.getStyleClass().add(CLASS_CS_TEXT_FIELD);
            }
        } else {
            textField.getStyleClass().remove(CLASS_CS_TEXT_FIELD);
            if (!textField.getStyleClass().contains(CLASS_OH_TEXT_FIELD_ERROR)) {
                textField.getStyleClass().add(CLASS_OH_TEXT_FIELD_ERROR);
            }
        }
    }
}
