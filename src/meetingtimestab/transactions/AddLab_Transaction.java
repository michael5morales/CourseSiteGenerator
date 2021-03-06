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
public class AddLab_Transaction implements jTPS_Transaction{
    MeetingTimesTabData data;
    RecitationLabMeetingType lab;
    
    public AddLab_Transaction(MeetingTimesTabData initData, RecitationLabMeetingType initLab) {
        data = initData;
        lab = initLab;
    }

    @Override
    public void doTransaction() {
        data.addLab(lab);
    }

    @Override
    public void undoTransaction() {
        data.removeLab(lab);
    }
}

