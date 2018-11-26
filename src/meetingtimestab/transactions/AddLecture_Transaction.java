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
public class AddLecture_Transaction implements jTPS_Transaction{
    MeetingTimesTabData data;
    LectureMeetingType lecture;
    
    public AddLecture_Transaction(MeetingTimesTabData initData, LectureMeetingType initLecture) {
        data = initData;
        lecture = initLecture;
    }

    @Override
    public void doTransaction() {
        data.addLecture(lecture);
    }

    @Override
    public void undoTransaction() {
        data.removeLecture(lecture);
    }
}
