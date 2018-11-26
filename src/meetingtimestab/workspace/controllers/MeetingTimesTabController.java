/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingtimestab.workspace.controllers;

import coursesite.CourseSiteApp;
import coursesite.data.CourseSiteData;
import meetingtimestab.data.LectureMeetingType;
import meetingtimestab.data.MeetingTimesTabData;
import meetingtimestab.data.RecitationLabMeetingType;
import meetingtimestab.transactions.AddLab_Transaction;
import meetingtimestab.transactions.AddLecture_Transaction;
import meetingtimestab.transactions.AddRecitation_Transaction;

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
}
