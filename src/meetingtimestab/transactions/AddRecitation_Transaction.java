/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingtimestab.transactions;

import jtps.jTPS_Transaction;
import meetingtimestab.data.MeetingTimesTabData;
import meetingtimestab.data.RecitationLabMeetingType;

/**
 *
 * @author Michael
 */
public class AddRecitation_Transaction implements jTPS_Transaction{
    MeetingTimesTabData data;
    RecitationLabMeetingType recitation;
    
    public AddRecitation_Transaction(MeetingTimesTabData initData, RecitationLabMeetingType initRecitation) {
        data = initData;
        recitation = initRecitation;
    }

    @Override
    public void doTransaction() {
        data.addRecitation(recitation);
    }

    @Override
    public void undoTransaction() {
        data.removeRecitation(recitation);
    }
}

