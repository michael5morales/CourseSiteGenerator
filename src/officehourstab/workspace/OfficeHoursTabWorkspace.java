/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package officehourstab.workspace;

import coursesite.CourseSiteApp;
import static coursesite.workspace.style.CSStyle.*;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import djf.ui.AppNodesBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static officehourstab.OfficeHoursTabPropertyType.*;
import officehourstab.data.TeachingAssistantPrototype;
import officehourstab.data.TimeSlot;
import officehourstab.workspace.controllers.OfficeHoursTabController;
import officehourstab.workspace.foolproof.OfficeHoursTabFoolproofDesign;
import properties_manager.PropertiesManager;

/**
 *
 * @author Michael
 */
public class OfficeHoursTabWorkspace {
    CourseSiteApp app;
    public Tab officeHoursTab;
    
    public OfficeHoursTabWorkspace(CourseSiteApp app) {
        this.app = app;
        
        // LAYOUT THE APP
        initLayout();

        // INIT THE EVENT HANDLERS
        initControllers();
        
        //INIT FOOLPROOF DESIGN
        initFoolproofDesign();
    }   
    
    public void initLayout() {
        
        Tab officeHoursTab = new Tab();
        
        // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder csBuilder = app.getGUIModule().getNodesBuilder();

        officeHoursTab.setText("Office Hours");
        officeHoursTab.closableProperty().setValue(false);
     
        ScrollPane scrollPane = new ScrollPane();
        
        VBox officeHoursPane = csBuilder.buildVBox(this, null, CLASS_CS_PANE, ENABLED);
        
        VBox officeHoursBox = csBuilder.buildVBox(this, officeHoursPane, CLASS_CS_BOX, ENABLED);
        
        //BUILD TA TABLE SECTION
        HBox taTableBox = csBuilder.buildHBox(this, officeHoursBox, EMPTY_TEXT, ENABLED);
        
        csBuilder.buildTextButton(OH_MINUS_BUTTON, taTableBox, CLASS_CS_PROMPT, !ENABLED);
        csBuilder.buildLabel(OH_TAS_LABEL, taTableBox, CLASS_CS_PROMPT, ENABLED);
        
        HBox radioButtonsBox = csBuilder.buildHBox(this, taTableBox, EMPTY_TEXT, ENABLED);
        ToggleGroup tg = new ToggleGroup();
        csBuilder.buildRadioButton(OH_ALL_RADIO_BUTTON, radioButtonsBox, CLASS_OH_RADIO_BUTTON, ENABLED, tg, true);
        csBuilder.buildRadioButton(OH_GRADUATE_TA_RADIO_BUTTON, radioButtonsBox, CLASS_OH_RADIO_BUTTON, ENABLED, tg, false);
        csBuilder.buildRadioButton(OH_UNDERGRADUATE_TA_RADIO_BUTTON, radioButtonsBox, CLASS_OH_RADIO_BUTTON, ENABLED, tg, false);

        TableView<TeachingAssistantPrototype> taTable = csBuilder.buildTableView(OH_TAS_TABLE_VIEW, officeHoursBox, CLASS_OH_TABLE_VIEW, ENABLED);
        taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TableColumn nameColumn = csBuilder.buildTableColumn(OH_NAME_TABLE_COLUMN, taTable, CLASS_OH_COLUMN);
        TableColumn emailColumn = csBuilder.buildTableColumn(OH_EMAIL_TABLE_COLUMN, taTable, CLASS_OH_COLUMN);
        TableColumn slotsColumn = csBuilder.buildTableColumn(OH_SLOTS_TABLE_COLUMN, taTable, CLASS_OH_CENTERED_COLUMN);
        TableColumn typeColumn = csBuilder.buildTableColumn(OH_TYPE_TABLE_COLUMN, taTable, CLASS_OH_COLUMN);
        nameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<String, String>("email"));
        slotsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("slots"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("type"));
        nameColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0 / 5.0));
        emailColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(2.0 / 5.0));
        slotsColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0 / 5.0));
        typeColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0 / 5.0));
        
        HBox taBox = csBuilder.buildHBox(OH_ADD_TA_PANE, officeHoursBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextField(OH_NAME_TEXT_FIELD, taBox, CLASS_CS_TEXT_FIELD, ENABLED);
        csBuilder.buildTextField(OH_EMAIL_TEXT_FIELD, taBox, CLASS_CS_TEXT_FIELD, ENABLED);
        csBuilder.buildTextButton(OH_ADD_TA_BUTTON, taBox, CLASS_CS_BUTTON, !ENABLED);
        
        //TA BOX STYLING
        taTableBox.setAlignment(Pos.CENTER_LEFT);
        taTableBox.setSpacing(5.0);
        radioButtonsBox.setAlignment(Pos.CENTER_LEFT);
        radioButtonsBox.setSpacing(10.0);
        taBox.setSpacing(5.0);
        
        HBox spacingBox = csBuilder.buildHBox(this, officeHoursBox, EMPTY_TEXT, ENABLED);
        spacingBox.paddingProperty().setValue(new Insets(20.0, 3.0, 3.0, 3.0));
        
        //BUILD OFFICE HOURS BOX
        HBox ohBox = csBuilder.buildHBox(this, officeHoursBox, EMPTY_TEXT, ENABLED);
        
        csBuilder.buildLabel(OH_OFFICE_HOURS_HEADER_LABEL, ohBox, CLASS_CS_PROMPT, ENABLED);
        
        HBox startEndBox = csBuilder.buildHBox(this, ohBox, EMPTY_TEXT, ENABLED);
        
        HBox startBox = csBuilder.buildHBox(this, startEndBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(OH_START_TIME_LABEL, startBox, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildComboBox(this, OH_NULL, OH_NULL, startBox, EMPTY_TEXT, ENABLED);
        
        HBox endBox = csBuilder.buildHBox(this, startEndBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(OH_END_TIME_LABEL, endBox, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildComboBox(this, OH_NULL, OH_NULL, endBox, EMPTY_TEXT, ENABLED);
        
        TableView<TimeSlot> officeHoursTable = csBuilder.buildTableView(OH_OFFICE_HOURS_TABLE_VIEW, officeHoursBox, CLASS_OH_OFFICE_HOURS_TABLE_VIEW, ENABLED);
        setupOfficeHoursColumn(OH_START_TIME_TABLE_COLUMN, officeHoursTable, CLASS_OH_TIME_COLUMN, "startTime");
        setupOfficeHoursColumn(OH_END_TIME_TABLE_COLUMN, officeHoursTable, CLASS_OH_TIME_COLUMN, "endTime");
        setupOfficeHoursColumn(OH_MONDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "monday");
        setupOfficeHoursColumn(OH_TUESDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "tuesday");
        setupOfficeHoursColumn(OH_WEDNESDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "wednesday");
        setupOfficeHoursColumn(OH_THURSDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "thursday");
        setupOfficeHoursColumn(OH_FRIDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "friday");

        
        //OFFICE HOUES BOX STYLING
        ohBox.setSpacing(100.0);
        ohBox.setAlignment(Pos.CENTER_LEFT);
        startEndBox.setSpacing(20.0);
        startBox.setSpacing(10.0);
        startBox.setAlignment(Pos.CENTER_LEFT);
        endBox.setSpacing(10.0);
        endBox.setAlignment(Pos.CENTER_LEFT);
        
        //OFFICE HOURS TAB STYLING
        officeHoursBox.paddingProperty().setValue(new Insets(3.0, 3.0, 3.0, 3.0));
        officeHoursBox.setSpacing(3.0);
        officeHoursPane.paddingProperty().setValue(new Insets(3.0, 3.0, 3.0, 3.0));
        
        scrollPane.setContent(officeHoursPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        
        // AND PUT EVERYTHING IN THE WORKSPACE
        officeHoursTab.setContent(scrollPane);
        this.officeHoursTab = officeHoursTab;
        
        
    }
    
    private void setupOfficeHoursColumn(Object columnId, TableView tableView, String styleClass, String columnDataProperty) {
        AppNodesBuilder builder = app.getGUIModule().getNodesBuilder();
        TableColumn<TeachingAssistantPrototype, String> column = builder.buildTableColumn(columnId, tableView, styleClass);
        column.setCellValueFactory(new PropertyValueFactory<TeachingAssistantPrototype, String>(columnDataProperty));
        column.prefWidthProperty().bind(tableView.widthProperty().multiply(1.0 / 7.0));
        column.setCellFactory(col -> {
            return new TableCell<TeachingAssistantPrototype, String>() {
                @Override
                protected void updateItem(String text, boolean empty) {
                    super.updateItem(text, empty);
                    if (text == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // CHECK TO SEE IF text CONTAINS THE NAME OF
                        // THE CURRENTLY SELECTED TA
                        setText(text);
                        TableView<TeachingAssistantPrototype> tasTableView = (TableView) app.getGUIModule().getGUINode(OH_TAS_TABLE_VIEW);
                        TeachingAssistantPrototype selectedTA = tasTableView.getSelectionModel().getSelectedItem();
                        if (selectedTA == null) {
                            setStyle("");
                        } else if (text.contains(selectedTA.getName())) {
                            setStyle("-fx-background-color: yellow");
                        } else {
                            setStyle("");
                        }
                    }
                }
            };
        });
    }
    
    public void initControllers() {
        OfficeHoursTabController controller = new OfficeHoursTabController((CourseSiteApp) app);
        AppGUIModule gui = app.getGUIModule();

        // FOOLPROOF DESIGN STUFF
        TextField nameTextField = ((TextField) gui.getGUINode(OH_NAME_TEXT_FIELD));
        TextField emailTextField = ((TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD));

        nameTextField.textProperty().addListener(e -> {
            controller.processTypeTA();
        });
        emailTextField.textProperty().addListener(e -> {
            controller.processTypeTA();
        });

        // FIRE THE ADD EVENT ACTION
        nameTextField.setOnAction(e -> {
            controller.processAddTA();
        });
        emailTextField.setOnAction(e -> {
            controller.processAddTA();
        });
        ((Button) gui.getGUINode(OH_ADD_TA_BUTTON)).setOnAction(e -> {
            controller.processAddTA();
        });
        ((Button) gui.getGUINode(OH_MINUS_BUTTON)).setOnAction(e -> {
            controller.processRemoveTA();
        });

        TableView officeHoursTableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        officeHoursTableView.getSelectionModel().setCellSelectionEnabled(true);
        officeHoursTableView.setOnMouseClicked(e -> {
            controller.processToggleOfficeHours();
        });

        // DON'T LET ANYONE SORT THE TABLES
        TableView tasTableView = (TableView) gui.getGUINode(OH_TAS_TABLE_VIEW);
        for (int i = 0; i < officeHoursTableView.getColumns().size(); i++) {
            ((TableColumn) officeHoursTableView.getColumns().get(i)).setSortable(false);
        }
        for (int i = 0; i < tasTableView.getColumns().size(); i++) {
            ((TableColumn) tasTableView.getColumns().get(i)).setSortable(false);
        }

        tasTableView.setOnMouseClicked(e -> {
            app.getFoolproofModule().updateAll();
            if (e.getClickCount() == 2) {
                controller.processEditTA();
            }
            controller.processSelectTA();
        });

        RadioButton allRadio = (RadioButton) gui.getGUINode(OH_ALL_RADIO_BUTTON);
        allRadio.setOnAction(e -> {
            controller.processSelectAllTAs();
        });
        RadioButton gradRadio = (RadioButton) gui.getGUINode(OH_GRADUATE_TA_RADIO_BUTTON);
        gradRadio.setOnAction(e -> {
            controller.processSelectGradTAs();
        });
        RadioButton undergradRadio = (RadioButton) gui.getGUINode(OH_UNDERGRADUATE_TA_RADIO_BUTTON);
        undergradRadio.setOnAction(e -> {
            controller.processSelectUndergradTAs();
        });
    }

    public void initFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        AppFoolproofModule foolproofSettings = app.getFoolproofModule();
        foolproofSettings.registerModeSettings(OH_FOOLPROOF_SETTINGS,
                new OfficeHoursTabFoolproofDesign((CourseSiteApp) app));
    }
}
