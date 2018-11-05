/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package officehourstab.transactions;

import jtps.jTPS_Transaction;
import officehourstab.data.OfficeHoursData;
import officehourstab.data.TeachingAssistantPrototype;
import officehourstab.data.TimeSlot;
import officehourstab.data.TimeSlot.DayOfWeek;

/**
 *
 * @author Michael
 */
public class ToggleOfficeHours_Transaction implements jTPS_Transaction{
    OfficeHoursData data;
    TimeSlot timeSlot;
    DayOfWeek dow;
    TeachingAssistantPrototype ta;
    
    public ToggleOfficeHours_Transaction(   OfficeHoursData initData, 
                                            TimeSlot initTimeSlot,
                                            DayOfWeek initDOW,
                                            TeachingAssistantPrototype initTA) {
        data = initData;
        timeSlot = initTimeSlot;
        dow = initDOW;
        ta = initTA;
    }

    @Override
    public void doTransaction() {
        timeSlot.toggleTA(dow, ta);
    }

    @Override
    public void undoTransaction() {
        timeSlot.toggleTA(dow, ta);
    }
}
