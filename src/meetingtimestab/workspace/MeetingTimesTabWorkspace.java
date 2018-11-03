/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingtimestab.workspace;

import coursesite.CourseSiteApp;
import static coursesite.workspace.style.CSStyle.*;
import static djf.modules.AppGUIModule.ENABLED;
import djf.ui.AppNodesBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static meetingtimestab.MeetingTimesTabPropertyType.*;
import meetingtimestab.data.LectureMeetingType;
import meetingtimestab.data.RecitationLabMeetingType;
import properties_manager.PropertiesManager;

/**
 *
 * @author Michael
 */
public class MeetingTimesTabWorkspace {
    CourseSiteApp app;
    public Tab meetingTimesTab;
    
    public MeetingTimesTabWorkspace(CourseSiteApp app) {
        this.app = app;
        
        // LAYOUT THE APP
        initLayout();

        // INIT THE EVENT HANDLERS
        initControllers();
        
        //INIT FOOLPROOF DESIGN
        initFoolproofDesign();
    }   
    
    public void initLayout() {
        
        Tab meetingTimesTab = new Tab();
        
        // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder csBuilder = app.getGUIModule().getNodesBuilder();

        meetingTimesTab.setText("Meeting Times");
        meetingTimesTab.closableProperty().setValue(false);
     
        ScrollPane scrollPane = new ScrollPane();
        
        VBox meetingTimesPane = csBuilder.buildVBox(this, null, CLASS_CS_PANE, ENABLED);
        
        //BUILD LECTURES BOX
        VBox lecturesBox = csBuilder.buildVBox(this, meetingTimesPane, CLASS_CS_BOX, ENABLED);
        
        HBox lecturesLabelBox = csBuilder.buildHBox(this, lecturesBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(MEETING_TIMES_TAB_PLUS_BUTTON1, lecturesLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(MEETING_TIMES_TAB_MINUS_BUTTON1, lecturesLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(MEETING_TIMES_TAB_LECTURES_LABEL, lecturesLabelBox, CLASS_CS_PROMPT, ENABLED);

        TableView<LectureMeetingType> lecturesTable = csBuilder.buildTableView(MEETING_TIMES_TAB_LECTURES_TABLE_VIEW, lecturesBox, CLASS_MT_TABLE_VIEW, ENABLED);
        TableColumn lecturesSectionColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LECTURES_SECTION_COLUMN, lecturesTable, CLASS_OH_COLUMN);
        TableColumn lecturesDayColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LECTURES_DAYS_COLUMN, lecturesTable, CLASS_OH_COLUMN);
        TableColumn lecturesTimeColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LECTURES_TIME_COLUMN, lecturesTable, CLASS_OH_COLUMN);
        TableColumn lecturesRoomColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LECTURES_ROOM_COLUMN, lecturesTable, CLASS_OH_COLUMN);
        lecturesSectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        lecturesDayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("day"));
        lecturesTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("time"));
        lecturesRoomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        for (int i = 0; i < lecturesTable.getColumns().size(); i++) {
            ((TableColumn)lecturesTable.getColumns().get(i)).prefWidthProperty().bind(lecturesTable.widthProperty().multiply(1.0/4.0));
        }
        
        //LECTURES BOX STYLING
        lecturesBox.paddingProperty().setValue(new Insets(15.0, 15.0, 15.0, 15.0));
        lecturesLabelBox.setSpacing(15.0);
        lecturesLabelBox.setAlignment(Pos.CENTER_LEFT);
        lecturesBox.setSpacing(5.0);
        
        //BUILD RECITATIONS BOX
        VBox recitationsBox = csBuilder.buildVBox(this, meetingTimesPane, CLASS_CS_BOX, ENABLED);
        
        HBox recitationsLabelBox = csBuilder.buildHBox(this, recitationsBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(MEETING_TIMES_TAB_PLUS_BUTTON2, recitationsLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(MEETING_TIMES_TAB_MINUS_BUTTON2, recitationsLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(MEETING_TIMES_TAB_RECITATIONS_LABEL, recitationsLabelBox, CLASS_CS_PROMPT, ENABLED);

        TableView<RecitationLabMeetingType> recitationsTable = csBuilder.buildTableView(MEETING_TIMES_TAB_RECITATIONS_TABLE_VIEW, recitationsBox, CLASS_MT_TABLE_VIEW, ENABLED);
        TableColumn recitationSectionColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_RECITATIONS_SECTION_COLUMN, recitationsTable, CLASS_OH_COLUMN);
        TableColumn recitationDayTimeColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_RECITATIONS_DAY_TIME_COLUMN, recitationsTable, CLASS_OH_COLUMN);
        TableColumn recitationRoomColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_RECITATIONS_ROOM_COLUMN, recitationsTable, CLASS_OH_COLUMN);
        TableColumn recitationTA1Column = csBuilder.buildTableColumn(MEETING_TIMES_TAB_RECITATIONS_TA1_COLUMN, recitationsTable, CLASS_OH_COLUMN);
        TableColumn recitationTA2Column = csBuilder.buildTableColumn(MEETING_TIMES_TAB_RECITATIONS_TA2_COLUMN, recitationsTable, CLASS_OH_COLUMN);
        recitationSectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        recitationDayTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("daysAndTime"));
        recitationRoomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        recitationTA1Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta1"));
        recitationTA2Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta2"));
        for (int i = 0; i < recitationsTable.getColumns().size(); i++) {
            ((TableColumn)recitationsTable.getColumns().get(i)).prefWidthProperty().bind(recitationsTable.widthProperty().multiply(1.0/5.0));
        }
        
        //RECITATIONS BOX STYLING
        recitationsBox.paddingProperty().setValue(new Insets(15.0, 15.0, 15.0, 15.0));
        recitationsLabelBox.setSpacing(15.0);
        recitationsLabelBox.setAlignment(Pos.CENTER_LEFT);
        recitationsBox.setSpacing(5.0);
        
        //BUILD LABS BOX
        VBox labsBox = csBuilder.buildVBox(this, meetingTimesPane, CLASS_CS_BOX, ENABLED);
        
        HBox labsLabelBox = csBuilder.buildHBox(this, labsBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(MEETING_TIMES_TAB_PLUS_BUTTON3, labsLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(MEETING_TIMES_TAB_MINUS_BUTTON3, labsLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(MEETING_TIMES_TAB_LABS_LABEL, labsLabelBox, CLASS_CS_PROMPT, ENABLED);

        TableView<RecitationLabMeetingType> labsTable = csBuilder.buildTableView(MEETING_TIMES_TAB_LABS_TABLE_VIEW, labsBox, CLASS_MT_TABLE_VIEW, ENABLED);
        TableColumn labsSectionColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LABS_SECTION_COLUMN, labsTable, CLASS_OH_COLUMN);
        TableColumn labsDayTimeColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LABS_DAY_TIME_COLUMN, labsTable, CLASS_OH_COLUMN);
        TableColumn labsRoomColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LABS_ROOM_COLUMN, labsTable, CLASS_OH_COLUMN);
        TableColumn labsTA1Column = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LABS_TA1_COLUMN, labsTable, CLASS_OH_COLUMN);
        TableColumn labsTA2Column = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LABS_TA2_COLUMN, labsTable, CLASS_OH_COLUMN);
        labsSectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        labsDayTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("daysAndTime"));
        labsRoomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        labsTA1Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta1"));
        labsTA2Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta2"));
        for (int i = 0; i < labsTable.getColumns().size(); i++) {
            ((TableColumn)labsTable.getColumns().get(i)).prefWidthProperty().bind(labsTable.widthProperty().multiply(1.0/5.0));
        }
        
        //LABS BOX STYLING
        labsBox.paddingProperty().setValue(new Insets(15.0, 15.0, 15.0, 15.0));
        labsLabelBox.setSpacing(15.0);
        labsLabelBox.setAlignment(Pos.CENTER_LEFT);
        labsBox.setSpacing(5.0);
        
        //MEETING TIMES TAB STYLING
        meetingTimesPane.paddingProperty().setValue(new Insets(3.0, 1.0, 1.0, 1.0));
        meetingTimesPane.setSpacing(3.0);
        
        scrollPane.setContent(meetingTimesPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        
        // AND PUT EVERYTHING IN THE WORKSPACE
        meetingTimesTab.setContent(scrollPane);
        this.meetingTimesTab = meetingTimesTab;
        
    }
    
    public void initControllers() {
        
    }
    
    public void initFoolproofDesign() {
        
    }
}
