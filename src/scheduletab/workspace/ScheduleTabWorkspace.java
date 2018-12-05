/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduletab.workspace;

import coursesite.CourseSiteApp;
import coursesite.data.CourseSiteData;
import static coursesite.workspace.style.CSStyle.*;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import djf.ui.AppNodesBuilder;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;
import static scheduletab.ScheduleTabPropertyType.*;
import scheduletab.data.ScheduleItem;
import scheduletab.data.ScheduleTabData;
import scheduletab.workspace.controllers.ScheduleTabController;
import scheduletab.workspace.foolproof.ScheduleTabFoolproofDesign;

/**
 *
 * @author Michael
 */
public class ScheduleTabWorkspace {
    CourseSiteApp app;
    public Tab scheduleTab;
    
    public ScheduleTabWorkspace(CourseSiteApp app) {
        this.app = app;
        
        // LAYOUT THE APP
        initLayout();

        // INIT THE EVENT HANDLERS
        initControllers();
        
        //INIT FOOLPROOF DESIGN
        initFoolproofDesign();
    }   
    
    public void initLayout() {
        
        Tab scheduleTab = new Tab();
        
        // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder csBuilder = app.getGUIModule().getNodesBuilder();

        scheduleTab.setText("Schedule");
        scheduleTab.closableProperty().setValue(false);
     
        ScrollPane scrollPane = new ScrollPane();
        
        VBox schedulePane = csBuilder.buildVBox(this, null, CLASS_CS_PANE, ENABLED);
        
        //BUILD CALENDAR BOUNDARIES BOX
        VBox boundaryBox = csBuilder.buildVBox(this, schedulePane, CLASS_CS_BOX, ENABLED);
        csBuilder.buildLabel(SCHEDULE_TAB_CALENDAR_LABEL, boundaryBox, CLASS_CS_PROMPT, ENABLED);
        
        HBox calendarBox = csBuilder.buildHBox(this, boundaryBox, EMPTY_TEXT, ENABLED);
        
        HBox startBox = csBuilder.buildHBox(this, calendarBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(SCHEDULE_TAB_STARTING_LABEL, startBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildDatePicker(SCHEDULE_TAB_START_DATE_DATE_PICKER, startBox, EMPTY_TEXT, ENABLED);
        
        HBox endBox = csBuilder.buildHBox(this, calendarBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(SCHEDULE_TAB_ENDING_LABEL, endBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildDatePicker(SCHEDULE_TAB_END_DATE_DATE_PICKER, endBox, EMPTY_TEXT, ENABLED);

        //CALENDAR BOUNDARIES BOX STYLING
        calendarBox.setSpacing(20.0);
        boundaryBox.paddingProperty().setValue(new Insets(3.0, 3.0, 3.0, 3.0));
        boundaryBox.setSpacing(3.0);
        startBox.setSpacing(5.0);
        startBox.setAlignment(Pos.CENTER_LEFT);
        endBox.setSpacing(5.0);
        endBox.setAlignment(Pos.CENTER_LEFT);
        
        //BUILD SCHEDULE ITEMS BOX
        VBox itemsBox = csBuilder.buildVBox(this, schedulePane, CLASS_CS_BOX, ENABLED);
        
        HBox labelBox = csBuilder.buildHBox(this, itemsBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(SCHEDULE_TAB_MINUS_BUTTON, labelBox, CLASS_CS_BUTTON, ENABLED);
        csBuilder.buildLabel(SCHEDULE_TAB_SCHEDULE_LABEL, labelBox, CLASS_CS_PROMPT, ENABLED);
        
        TableView<ScheduleItem> itemsTable = csBuilder.buildTableView(SCHEDULE_TAB_ITEMS_TABLE_VIEW, itemsBox, EMPTY_TEXT, ENABLED);
        TableColumn typeColumn = csBuilder.buildTableColumn(SCHEDULE_TAB_TYPE_COLUMN, itemsTable, EMPTY_TEXT);
        TableColumn dateColumn = csBuilder.buildTableColumn(SCHEDULE_TAB_DATE_COLUMN, itemsTable, EMPTY_TEXT);
        TableColumn titleColumn = csBuilder.buildTableColumn(SCHEDULE_TAB_TITLE_COLUMN, itemsTable, EMPTY_TEXT);
        TableColumn topicColumn = csBuilder.buildTableColumn(SCHEDULE_TAB_TOPIC_COLUMN, itemsTable, EMPTY_TEXT);
        typeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("type"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<String, String>("date"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<String, String>("title"));
        topicColumn.setCellValueFactory(new PropertyValueFactory<String, String>("topic"));
        typeColumn.prefWidthProperty().bind(itemsTable.widthProperty().multiply(1.0 / 5.0));
        dateColumn.prefWidthProperty().bind(itemsTable.widthProperty().multiply(1.0 / 5.0));
        titleColumn.prefWidthProperty().bind(itemsTable.widthProperty().multiply(1.0 / 5.0));
        topicColumn.prefWidthProperty().bind(itemsTable.widthProperty().multiply(2.0 / 5.0));
        
        //SCHEDULE ITEMS BOX STYLING
        labelBox.setAlignment(Pos.CENTER_LEFT);
        itemsBox.paddingProperty().setValue(new Insets(3.0, 3.0, 3.0, 3.0));
        itemsBox.setSpacing(3.0);
        
        //BUILD ADD/EDIT BOX
        GridPane addBox = csBuilder.buildGridPane(this, schedulePane, CLASS_CS_BOX, ENABLED);
        
        csBuilder.buildLabel(SCHEDULE_TAB_ADD_LABEL, addBox, 0, 0, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(SCHEDULE_TAB_TYPE_LABEL, addBox, 0, 1, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(SCHEDULE_TAB_DATE_LABEL, addBox, 0, 2, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(SCHEDULE_TAB_TITLE_LABEL, addBox, 0, 3, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(SCHEDULE_TAB_TOPIC_LABEL, addBox, 0, 4, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(SCHEDULE_TAB_LINK_LABEL, addBox, 0, 5, 1, 1, CLASS_CS_PROMPT, ENABLED);
        
        csBuilder.buildComboBox(SCHEDULE_TAB_TYPE_COMBO_BOX, addBox, 1, 1, 1, 1, EMPTY_TEXT, ENABLED, SCHEDULE_NULL, SCHEDULE_NULL);
        
        csBuilder.buildDatePicker(SCHEDULE_TAB_ADD_DATE_DATE_PICKER, addBox, 1, 2, 1, 1, EMPTY_TEXT, ENABLED);
        
        csBuilder.buildTextField(SCHEDULE_TAB_TITLE_TEXT_FIELD, addBox, 1, 3, 1, 1, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextField(SCHEDULE_TAB_TOPIC_TEXT_FIELD, addBox, 1, 4, 1, 1, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextField(SCHEDULE_TAB_LINK_TEXT_FIELD, addBox, 1, 5, 1, 1, EMPTY_TEXT, ENABLED);
        
        HBox buttonsBox = csBuilder.buildHBox(this, addBox, 0, 6, 3, 3, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(SCHEDULE_TAB_ADD_BUTTON, buttonsBox, CLASS_CS_BUTTON, ENABLED);
        csBuilder.buildTextButton(SCHEDULE_TAB_CLEAR_BUTTON, buttonsBox, CLASS_CS_BUTTON, ENABLED);
        
        //ADD/EDIT BOX STYLING
        buttonsBox.setSpacing(15.0);
        addBox.paddingProperty().setValue(new Insets(3.0, 3.0, 3.0, 3.0));
        addBox.setHgap(10.0);
        addBox.setVgap(5.0);
        
        //SCHEDULE TAB STYLING
        schedulePane.paddingProperty().setValue(new Insets(5.0, 5.0, 5.0, 5.0));
        schedulePane.setSpacing(5.0);
        
        scrollPane.setContent(schedulePane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        
        // AND PUT EVERYTHING IN THE WORKSPACE
        scheduleTab.setContent(scrollPane);
        this.scheduleTab = scheduleTab;
        
    }
    
    public void initControllers() {
        ScheduleTabController controller = new ScheduleTabController((CourseSiteApp) app);
        AppGUIModule gui = app.getGUIModule();
        
        TableView tableView = (TableView) gui.getGUINode(SCHEDULE_TAB_ITEMS_TABLE_VIEW);
        
        ((Button) gui.getGUINode(SCHEDULE_TAB_ADD_BUTTON)).setOnAction(e -> {
            TableView<ScheduleItem> table = (TableView)gui.getGUINode(SCHEDULE_TAB_ITEMS_TABLE_VIEW);
            if (table.getSelectionModel().getSelectedItem() == null ) {
                controller.processAddItem();
            }else {
                controller.processUpdateItem();
                tableView.refresh();
            }
            app.getFoolproofModule().updateControls(SCHEDULE_TAB_FOOLPROOF_SETTINGS);
        });
        
        ((Button) gui.getGUINode(SCHEDULE_TAB_MINUS_BUTTON)).setOnAction(e -> {
            controller.processRemoveItem();
        }); 
       
        tableView.setOnMouseClicked(e -> {
            app.getFoolproofModule().updateControls(SCHEDULE_TAB_FOOLPROOF_SETTINGS);
            controller.processLoadItem();
        });
        
        ((Button) gui.getGUINode(SCHEDULE_TAB_CLEAR_BUTTON)).setOnAction(e -> {
        controller.processClearSelection();
        app.getFoolproofModule().updateControls(SCHEDULE_TAB_FOOLPROOF_SETTINGS);
        });
        
        ((TextField) gui.getGUINode(SCHEDULE_TAB_TITLE_TEXT_FIELD)).textProperty().addListener(e -> {
            Button addButton = (Button)gui.getGUINode(SCHEDULE_TAB_ADD_BUTTON);
            if (tableView.getSelectionModel().getSelectedItem() == null) 
            addButton.setText("Add");
        });
        ((TextField) gui.getGUINode(SCHEDULE_TAB_TOPIC_TEXT_FIELD)).textProperty().addListener(e -> {
            Button addButton = (Button)gui.getGUINode(SCHEDULE_TAB_ADD_BUTTON);
            if (tableView.getSelectionModel().getSelectedItem() == null) 
            addButton.setText("Add");
        });
        ((TextField) gui.getGUINode(SCHEDULE_TAB_LINK_TEXT_FIELD)).textProperty().addListener(e -> {
            Button addButton = (Button)gui.getGUINode(SCHEDULE_TAB_ADD_BUTTON);
            if (tableView.getSelectionModel().getSelectedItem() == null) 
            addButton.setText("Add");
        });
        
        ((ComboBox) gui.getGUINode(SCHEDULE_TAB_TYPE_COMBO_BOX)).setOnAction(e -> {
            Button addButton = (Button)gui.getGUINode(SCHEDULE_TAB_ADD_BUTTON);
            if (tableView.getSelectionModel().getSelectedItem() == null) 
            addButton.setText("Add");
        });
        
        ((DatePicker) gui.getGUINode(SCHEDULE_TAB_ADD_DATE_DATE_PICKER)).setOnAction(e -> {
            Button addButton = (Button)gui.getGUINode(SCHEDULE_TAB_ADD_BUTTON);
            if (tableView.getSelectionModel().getSelectedItem() == null) 
            addButton.setText("Add");
        });
        
        ((DatePicker) gui.getGUINode(SCHEDULE_TAB_START_DATE_DATE_PICKER)).setOnAction(e -> {
            app.getFoolproofModule().updateControls(SCHEDULE_TAB_FOOLPROOF_SETTINGS);
        });
        ((DatePicker) gui.getGUINode(SCHEDULE_TAB_END_DATE_DATE_PICKER)).setOnAction(e -> {
            app.getFoolproofModule().updateControls(SCHEDULE_TAB_FOOLPROOF_SETTINGS);
        });
    }
    
    public void initFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        AppFoolproofModule foolproofSettings = app.getFoolproofModule();
        foolproofSettings.registerModeSettings(SCHEDULE_TAB_FOOLPROOF_SETTINGS,
                new ScheduleTabFoolproofDesign((CourseSiteApp) app));
    }
}
