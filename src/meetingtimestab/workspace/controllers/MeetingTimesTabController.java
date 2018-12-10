/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingtimestab.workspace.controllers;

import coursesite.CourseSiteApp;
import coursesite.data.CourseSiteData;
import djf.modules.AppGUIModule;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import static meetingtimestab.MeetingTimesTabPropertyType.*;
import meetingtimestab.data.LectureMeetingType;
import meetingtimestab.data.MeetingTimesTabData;
import meetingtimestab.data.RecitationLabMeetingType;
import meetingtimestab.transactions.AddLab_Transaction;
import meetingtimestab.transactions.AddLecture_Transaction;
import meetingtimestab.transactions.AddRecitation_Transaction;
import meetingtimestab.transactions.EditLecture_Transaction;
import meetingtimestab.transactions.RemoveLab_Transaction;
import meetingtimestab.transactions.RemoveLecture_Transaction;
import meetingtimestab.transactions.RemoveRecitation_Transaction;

/**
 *
 * @author Michael
 */
public class MeetingTimesTabController {
    CourseSiteApp app;

    public MeetingTimesTabController(CourseSiteApp initApp) {
        app = initApp;
    }
    
    public void processAddRecitation() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        MeetingTimesTabData data = (MeetingTimesTabData)siteData.getMeetingTimeData();

        RecitationLabMeetingType recitation = new RecitationLabMeetingType("?", "?", "?", "?", "?");
        AddRecitation_Transaction addRecitationTransaction = new AddRecitation_Transaction(data, recitation);
        app.processTransaction(addRecitationTransaction);
    }
    
    public void processAddLecture() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        MeetingTimesTabData data = (MeetingTimesTabData)siteData.getMeetingTimeData();
        
        LectureMeetingType lecture = new LectureMeetingType("?", "?", "?", "?");
        AddLecture_Transaction addLectureTransaction = new AddLecture_Transaction(data, lecture);
        app.processTransaction(addLectureTransaction);
    }
    
    public void processAddLab() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        MeetingTimesTabData data = (MeetingTimesTabData)siteData.getMeetingTimeData();

        RecitationLabMeetingType lab = new RecitationLabMeetingType("?", "?", "?", "?", "?");
        AddLab_Transaction addLabTransaction = new AddLab_Transaction(data, lab);
        app.processTransaction(addLabTransaction);
    }
    
    public void processRemoveLecture() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        MeetingTimesTabData data = (MeetingTimesTabData)siteData.getMeetingTimeData();
        
        LectureMeetingType lecture = data.getSelectedLecture();
        
        RemoveLecture_Transaction removeLectureTransaction = new RemoveLecture_Transaction(data, lecture);
        app.processTransaction(removeLectureTransaction);
    }
    
    public void processRemoveLab() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        MeetingTimesTabData data = (MeetingTimesTabData)siteData.getMeetingTimeData();
        
        RecitationLabMeetingType lab = data.getSelectedLab();
        
        RemoveLab_Transaction removeLabTransaction = new RemoveLab_Transaction(data, lab);
        app.processTransaction(removeLabTransaction);
    }
    
    public void processRemoveRecitation() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        MeetingTimesTabData data = (MeetingTimesTabData)siteData.getMeetingTimeData();
        
        RecitationLabMeetingType recitation = data.getSelectedRecitation();
        
        RemoveRecitation_Transaction removeRecitationTransaction = new RemoveRecitation_Transaction(data, recitation);
        app.processTransaction(removeRecitationTransaction);
    }
    
    public void processEditLecture(LectureMeetingType editlecture, int row) {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        MeetingTimesTabData data = (MeetingTimesTabData)siteData.getMeetingTimeData();
        
        LectureMeetingType initLecture = data.getOldLecture(row);

        EditLecture_Transaction editLectureTransaction = new EditLecture_Transaction(data, initLecture, editlecture.getSection(), editlecture.getDay(), 
                editlecture.getTime(), editlecture.getRoom(), row);
        app.processTransaction(editLectureTransaction);
    }
    
    public void processAddOldLecture() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        MeetingTimesTabData data = (MeetingTimesTabData)siteData.getMeetingTimeData();
        
        AppGUIModule gui = app.getGUIModule();

        TableView<LectureMeetingType> lecturesTableView = (TableView)gui.getGUINode(MEETING_TIMES_TAB_LECTURES_TABLE_VIEW);
        
        LectureMeetingType oldlecture = lecturesTableView.getSelectionModel().getSelectedItem();
        
        LectureMeetingType lecture = new LectureMeetingType(oldlecture.getSection(), oldlecture.getDay(), oldlecture.getTime(), oldlecture.getRoom());
        
        data.addOldLecture(lecture, lecturesTableView.getSelectionModel().getSelectedCells().get(0).getRow());
    }
    
    
}
