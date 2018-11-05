/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package officehourstab.workspace.controllers;

import coursesite.CourseSiteApp;
import coursesite.data.CourseSiteData;
import djf.modules.AppGUIModule;
import djf.ui.dialogs.AppDialogsFacade;
import javafx.collections.ObservableList;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static officehourstab.OfficeHoursTabPropertyType.*;
import officehourstab.data.OfficeHoursData;
import officehourstab.data.TAType;
import officehourstab.data.TeachingAssistantPrototype;
import officehourstab.data.TimeSlot;
import officehourstab.data.TimeSlot.DayOfWeek;
import officehourstab.transactions.AddTA_Transaction;
import officehourstab.transactions.EditTA_Transaction;
import officehourstab.transactions.ToggleOfficeHours_Transaction;
import officehourstab.workspace.dialogs.TADialog;

/**
 *
 * @author Michael
 */
public class OfficeHoursTabController {
    
    CourseSiteApp app;

    public OfficeHoursTabController(CourseSiteApp initApp) {
        app = initApp;
    }

    public void processAddTA() {
        AppGUIModule gui = app.getGUIModule();
        TextField nameTF = (TextField) gui.getGUINode(OH_NAME_TEXT_FIELD);
        String name = nameTF.getText();
        TextField emailTF = (TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD);
        String email = emailTF.getText();
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        OfficeHoursData data = (OfficeHoursData)siteData.getOfficeHoursData();
        TAType type = data.getSelectedType();
        if (data.isLegalNewTA(name, email)) {
            TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name.trim(), email.trim(), type);
            AddTA_Transaction addTATransaction = new AddTA_Transaction(data, ta);
            app.processTransaction(addTATransaction);

            // NOW CLEAR THE TEXT FIELDS
            nameTF.setText("");
            emailTF.setText("");
            nameTF.requestFocus();
        }
        app.getFoolproofModule().updateControls(OH_FOOLPROOF_SETTINGS);
    }

    public void processVerifyTA() {

    }

    public void processToggleOfficeHours() {
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        ObservableList<TablePosition> selectedCells = officeHoursTableView.getSelectionModel().getSelectedCells();
        if (selectedCells.size() > 0) {
            TablePosition cell = selectedCells.get(0);
            int cellColumnNumber = cell.getColumn();
            CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
            OfficeHoursData data = (OfficeHoursData)siteData.getOfficeHoursData();
            if (data.isDayOfWeekColumn(cellColumnNumber)) {
                DayOfWeek dow = data.getColumnDayOfWeek(cellColumnNumber);
                TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(OH_TAS_TABLE_VIEW);
                TeachingAssistantPrototype ta = taTableView.getSelectionModel().getSelectedItem();
                if (ta != null) {
                    TimeSlot timeSlot = officeHoursTableView.getSelectionModel().getSelectedItem();
                    ToggleOfficeHours_Transaction transaction = new ToggleOfficeHours_Transaction(data, timeSlot, dow, ta);
                    app.processTransaction(transaction);
                }
                else {
                    Stage window = app.getGUIModule().getWindow();
                    AppDialogsFacade.showMessageDialog(window, OH_NO_TA_SELECTED_TITLE, OH_NO_TA_SELECTED_CONTENT);
                }
            }
            int row = cell.getRow();
            cell.getTableView().refresh();
        }
    }

    public void processTypeTA() {
        app.getFoolproofModule().updateControls(OH_FOOLPROOF_SETTINGS);
    }

    public void processEditTA() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        OfficeHoursData data = (OfficeHoursData)siteData.getOfficeHoursData();        if (data.isTASelected()) {
            TeachingAssistantPrototype taToEdit = data.getSelectedTA();
            TADialog taDialog = (TADialog)app.getGUIModule().getDialog(OH_TA_EDIT_DIALOG);
            taDialog.showEditDialog(taToEdit);
            TeachingAssistantPrototype editTA = taDialog.getEditTA();
            if (editTA != null) {
                EditTA_Transaction transaction = new EditTA_Transaction(taToEdit, editTA.getName(), editTA.getEmail(), editTA.getType());
                app.processTransaction(transaction);
            }
        }
    }

    public void processSelectAllTAs() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        OfficeHoursData data = (OfficeHoursData)siteData.getOfficeHoursData();
        data.selectTAs(TAType.All);
    }

    public void processSelectGradTAs() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        OfficeHoursData data = (OfficeHoursData)siteData.getOfficeHoursData();
        data.selectTAs(TAType.Graduate);
    }

    public void processSelectUndergradTAs() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        OfficeHoursData data = (OfficeHoursData)siteData.getOfficeHoursData();
        data.selectTAs(TAType.Undergraduate);
    }

    public void processSelectTA() {
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        officeHoursTableView.refresh();
    }
}
