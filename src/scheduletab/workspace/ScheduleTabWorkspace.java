/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduletab.workspace;

import coursesite.CourseSiteApp;
import static coursesite.workspace.style.CSStyle.*;
import static djf.modules.AppGUIModule.ENABLED;
import djf.ui.AppNodesBuilder;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;
import static scheduletab.ScheduleTabPropertyType.*;
import scheduletab.data.ScheduleItem;

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
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setValue(LocalDate.now());
        startBox.getChildren().add(startDatePicker);
        
        HBox endBox = csBuilder.buildHBox(this, calendarBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(SCHEDULE_TAB_ENDING_LABEL, endBox, EMPTY_TEXT, ENABLED);
        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setValue(LocalDate.now());
        endBox.getChildren().add(endDatePicker);
        
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
        
        TableView<ScheduleItem> itemsTable = csBuilder.buildTableView(this, itemsBox, EMPTY_TEXT, ENABLED);
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
        
        csBuilder.buildComboBox(this, addBox, 1, 1, 1, 1, EMPTY_TEXT, ENABLED, SCHEDULE_NULL, SCHEDULE_NULL);
        
        DatePicker editDatePicker = new DatePicker();
        editDatePicker.setValue(LocalDate.now());
        addBox.add(editDatePicker, 1, 2);
        
        csBuilder.buildTextField(SCHEDULE_TAB_TITLE_TEXT_AREA, addBox, 1, 3, 1, 1, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextField(SCHEDULE_TAB_TOPIC_TEXT_AREA, addBox, 1, 4, 1, 1, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextField(SCHEDULE_TAB_LINK_TEXT_AREA, addBox, 1, 5, 1, 1, EMPTY_TEXT, ENABLED);
        
        HBox buttonsBox = csBuilder.buildHBox(this, addBox, 0, 6, 3, 3, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(SCHEDULE_TAB_ADD_BUTTON, buttonsBox, CLASS_CS_BUTTON, ENABLED);
        csBuilder.buildTextButton(SCHEDULE_TAB_CLEAR_BUTTON, buttonsBox, CLASS_CS_BUTTON, ENABLED);
        
        //ADD/EDIT BOX STYLING
        buttonsBox.setSpacing(15.0);
        addBox.paddingProperty().setValue(new Insets(3.0, 3.0, 3.0, 3.0));
        addBox.setHgap(10.0);
        addBox.setVgap(5.0);
        
        //SCHEDULE TAB STYLING
        schedulePane.paddingProperty().setValue(new Insets(3.0, 3.0, 3.0, 3.0));
        schedulePane.setSpacing(3.0);
        
        scrollPane.setContent(schedulePane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        
        // AND PUT EVERYTHING IN THE WORKSPACE
        scheduleTab.setContent(scrollPane);
        this.scheduleTab = scheduleTab;
        
    }
    
    public void initControllers() {
        
    }
    
    public void initFoolproofDesign() {
        
    }
}