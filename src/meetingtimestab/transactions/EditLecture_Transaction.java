/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingtimestab.transactions;

import jtps.jTPS_Transaction;
import meetingtimestab.data.LectureMeetingType;
import meetingtimestab.data.MeetingTimesTabData;

/**
 *
 * @author Michael
 */
public class EditLecture_Transaction implements jTPS_Transaction{
    MeetingTimesTabData data;
    LectureMeetingType lecture;
    String day;
    String section;
    String time; 
    String room;
    int row;
    
    public EditLecture_Transaction(MeetingTimesTabData initData, LectureMeetingType initlecture, String section, 
            String day, String time, String room, int row) {
        data = initData;
        lecture = initlecture;
        this.day = day;
        this.section = section;
        this.time = time;
        this.room = room;
        this.row = row;
    }

    @Override
    public void doTransaction() {
        LectureMeetingType editLecture = new LectureMeetingType(section, day, time, room);

        data.replaceLecture(editLecture, row);
    }

    @Override
    public void undoTransaction() {
        data.replaceLecture(lecture, row);
    }
}
