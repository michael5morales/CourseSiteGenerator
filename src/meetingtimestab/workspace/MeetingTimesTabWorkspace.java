/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingtimestab.workspace;

import coursesite.CourseSiteApp;
import static coursesite.workspace.style.CSStyle.*;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import djf.ui.AppNodesBuilder;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static meetingtimestab.MeetingTimesTabPropertyType.*;
import meetingtimestab.data.LectureMeetingType;
import meetingtimestab.data.RecitationLabMeetingType;
import meetingtimestab.workspace.controllers.MeetingTimesTabController;
import meetingtimestab.workspace.foolproof.MeetingTimesTabFoolproofDesign;
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
        csBuilder.buildTextButton(MEETING_TIMES_TAB_MINUS_BUTTON1, lecturesLabelBox, EMPTY_TEXT, !ENABLED);
        csBuilder.buildLabel(MEETING_TIMES_TAB_LECTURES_LABEL, lecturesLabelBox, CLASS_CS_HEADER_LABEL, ENABLED);

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
        
        lecturesTable.setEditable(ENABLED);

        lecturesSectionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lecturesDayColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lecturesTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lecturesRoomColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        
        //LECTURES BOX STYLING
        lecturesBox.paddingProperty().setValue(new Insets(15.0, 15.0, 15.0, 15.0));
        lecturesLabelBox.setSpacing(15.0);
        lecturesLabelBox.setAlignment(Pos.CENTER_LEFT);
        lecturesBox.setSpacing(5.0);
        
        //BUILD RECITATIONS BOX
        VBox recitationsBox = csBuilder.buildVBox(this, meetingTimesPane, CLASS_CS_BOX, ENABLED);
        
        HBox recitationsLabelBox = csBuilder.buildHBox(this, recitationsBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(MEETING_TIMES_TAB_PLUS_BUTTON2, recitationsLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(MEETING_TIMES_TAB_MINUS_BUTTON2, recitationsLabelBox, EMPTY_TEXT, !ENABLED);
        csBuilder.buildLabel(MEETING_TIMES_TAB_RECITATIONS_LABEL, recitationsLabelBox, CLASS_CS_HEADER_LABEL, ENABLED);

        TableView<RecitationLabMeetingType> recitationsTable = csBuilder.buildTableView(MEETING_TIMES_TAB_RECITATIONS_TABLE_VIEW, recitationsBox, CLASS_MT_TABLE_VIEW, ENABLED);
        TableColumn recitationSectionColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_RECITATIONS_SECTION_COLUMN, recitationsTable, CLASS_OH_COLUMN);
        TableColumn recitationDayTimeColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_RECITATIONS_DAY_TIME_COLUMN, recitationsTable, CLASS_OH_COLUMN);
        TableColumn recitationRoomColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_RECITATIONS_ROOM_COLUMN, recitationsTable, CLASS_OH_COLUMN);
        TableColumn recitationTA1Column = csBuilder.buildTableColumn(MEETING_TIMES_TAB_RECITATIONS_TA1_COLUMN, recitationsTable, CLASS_OH_COLUMN);
        TableColumn recitationTA2Column = csBuilder.buildTableColumn(MEETING_TIMES_TAB_RECITATIONS_TA2_COLUMN, recitationsTable, CLASS_OH_COLUMN);
        recitationSectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        recitationDayTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("dayTime"));
        recitationRoomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        recitationTA1Column.setCellValueFactory(new PropertyValueFactory<String, String>("firstTA"));
        recitationTA2Column.setCellValueFactory(new PropertyValueFactory<String, String>("secondTA"));
        for (int i = 0; i < recitationsTable.getColumns().size(); i++) {
            ((TableColumn)recitationsTable.getColumns().get(i)).prefWidthProperty().bind(recitationsTable.widthProperty().multiply(1.0/5.0));
        }
        
        recitationsTable.setEditable(ENABLED);

        recitationSectionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        recitationDayTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        recitationRoomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        recitationTA1Column.setCellFactory(TextFieldTableCell.forTableColumn());
        recitationTA2Column.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        //RECITATIONS BOX STYLING
        recitationsBox.paddingProperty().setValue(new Insets(15.0, 15.0, 15.0, 15.0));
        recitationsLabelBox.setSpacing(15.0);
        recitationsLabelBox.setAlignment(Pos.CENTER_LEFT);
        recitationsBox.setSpacing(5.0);
        
        //BUILD LABS BOX
        VBox labsBox = csBuilder.buildVBox(this, meetingTimesPane, CLASS_CS_BOX, ENABLED);
        
        HBox labsLabelBox = csBuilder.buildHBox(this, labsBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(MEETING_TIMES_TAB_PLUS_BUTTON3, labsLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(MEETING_TIMES_TAB_MINUS_BUTTON3, labsLabelBox, EMPTY_TEXT, !ENABLED);
        csBuilder.buildLabel(MEETING_TIMES_TAB_LABS_LABEL, labsLabelBox, CLASS_CS_HEADER_LABEL, ENABLED);

        TableView<RecitationLabMeetingType> labsTable = csBuilder.buildTableView(MEETING_TIMES_TAB_LABS_TABLE_VIEW, labsBox, CLASS_MT_TABLE_VIEW, ENABLED);
        TableColumn labsSectionColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LABS_SECTION_COLUMN, labsTable, CLASS_OH_COLUMN);
        TableColumn labsDayTimeColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LABS_DAY_TIME_COLUMN, labsTable, CLASS_OH_COLUMN);
        TableColumn labsRoomColumn = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LABS_ROOM_COLUMN, labsTable, CLASS_OH_COLUMN);
        TableColumn labsTA1Column = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LABS_TA1_COLUMN, labsTable, CLASS_OH_COLUMN);
        TableColumn labsTA2Column = csBuilder.buildTableColumn(MEETING_TIMES_TAB_LABS_TA2_COLUMN, labsTable, CLASS_OH_COLUMN);
        labsSectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        labsDayTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("dayTime"));
        labsRoomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        labsTA1Column.setCellValueFactory(new PropertyValueFactory<String, String>("firstTA"));
        labsTA2Column.setCellValueFactory(new PropertyValueFactory<String, String>("secondTA"));
        for (int i = 0; i < labsTable.getColumns().size(); i++) {
            ((TableColumn)labsTable.getColumns().get(i)).prefWidthProperty().bind(labsTable.widthProperty().multiply(1.0/5.0));
        }
        
        labsTable.setEditable(ENABLED);

        labsSectionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        labsDayTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        labsRoomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        labsTA1Column.setCellFactory(TextFieldTableCell.forTableColumn());
        labsTA2Column.setCellFactory(TextFieldTableCell.forTableColumn());
        
        //LABS BOX STYLING
        labsBox.paddingProperty().setValue(new Insets(15.0, 15.0, 15.0, 15.0));
        labsLabelBox.setSpacing(15.0);
        labsLabelBox.setAlignment(Pos.CENTER_LEFT);
        labsBox.setSpacing(5.0);
        
        //MEETING TIMES TAB STYLING
        meetingTimesPane.paddingProperty().setValue(new Insets(5.0, 5.0, 5.0, 5.0));
        meetingTimesPane.setSpacing(5.0);
        
        scrollPane.setContent(meetingTimesPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        
        // AND PUT EVERYTHING IN THE WORKSPACE
        meetingTimesTab.setContent(scrollPane);
        this.meetingTimesTab = meetingTimesTab;
        
    }
    
    public void initControllers() {
        MeetingTimesTabController controller = new MeetingTimesTabController((CourseSiteApp) app);
        AppGUIModule gui = app.getGUIModule();
        
        
        
        ((Button) gui.getGUINode(MEETING_TIMES_TAB_PLUS_BUTTON1)).setOnAction(e -> {
            controller.processAddLecture();
        });
        
        ((Button) gui.getGUINode(MEETING_TIMES_TAB_PLUS_BUTTON2)).setOnAction(e -> {
            controller.processAddRecitation();
        });
        
        ((Button) gui.getGUINode(MEETING_TIMES_TAB_PLUS_BUTTON3)).setOnAction(e -> {
            controller.processAddLab();
         });

        ((Button) gui.getGUINode(MEETING_TIMES_TAB_MINUS_BUTTON1)).setOnAction(e -> {
            controller.processRemoveLecture();
            app.getFoolproofModule().updateAll();
        });  
        
        ((Button) gui.getGUINode(MEETING_TIMES_TAB_MINUS_BUTTON2)).setOnAction(e -> {
            app.getFoolproofModule().updateAll();
        });  
        
        ((Button) gui.getGUINode(MEETING_TIMES_TAB_MINUS_BUTTON3)).setOnAction(e -> {
            controller.processRemoveLab();
            app.getFoolproofModule().updateAll();
        });  
                
        TableView lecturesTableView = (TableView) gui.getGUINode(MEETING_TIMES_TAB_LECTURES_TABLE_VIEW);
        
        lecturesTableView.setOnMouseClicked(e -> {
            app.getFoolproofModule().updateAll();
        });
        
        
        TableColumn sectionColumn = (TableColumn)lecturesTableView.getColumns().get(0);
        TableColumn daysColumn = (TableColumn)lecturesTableView.getColumns().get(0);
        TableColumn timeColumn = (TableColumn)lecturesTableView.getColumns().get(0);
        TableColumn roomColumn = (TableColumn)lecturesTableView.getColumns().get(0);
        
        sectionColumn.setOnEditStart(e -> {
            controller.processAddOldLecture();
        });
        
        sectionColumn.setOnEditCommit(
            new EventHandler<CellEditEvent<LectureMeetingType, String>>() {
                @Override
                public void handle(CellEditEvent<LectureMeetingType, String> t) {
                    LectureMeetingType newLecture = (LectureMeetingType) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                    newLecture.setSection(t.getNewValue());
                    
                    controller.processEditLecture(newLecture, t.getTablePosition().getRow());       
                }
            }
        );
        
        TableView recitationsTableView = (TableView) gui.getGUINode(MEETING_TIMES_TAB_RECITATIONS_TABLE_VIEW);
        
        recitationsTableView.setOnMouseClicked(e -> {
            app.getFoolproofModule().updateAll();
        });
        
        TableView labsTableView = (TableView) gui.getGUINode(MEETING_TIMES_TAB_LABS_TABLE_VIEW);
        
        labsTableView.setOnMouseClicked(e -> {
            app.getFoolproofModule().updateAll();
        });
        
        
        
    }
    
    public void initFoolproofDesign() {
        AppFoolproofModule foolproofSettings = app.getFoolproofModule();
        foolproofSettings.registerModeSettings(MEETING_TIMES_TAB_FOOLPROOF_SETTINGS,
                new MeetingTimesTabFoolproofDesign((CourseSiteApp) app));
    }
    
}
