/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package officehourstab.transactions;

import coursesite.CourseSiteApp;
import coursesite.data.CourseSiteData;
import java.util.ArrayList;
import java.util.HashMap;
import jtps.jTPS_Transaction;
import static officehourstab.OfficeHoursTabPropertyType.OH_FOOLPROOF_SETTINGS;
import officehourstab.data.OfficeHoursData;
import officehourstab.data.TeachingAssistantPrototype;
import officehourstab.data.TimeSlot;
import officehourstab.data.TimeSlot.DayOfWeek;

/**
 *
 * @author Michael
 */
public class RemoveTA_Transaction implements jTPS_Transaction{
    TeachingAssistantPrototype ta;
    HashMap<TimeSlot, ArrayList<DayOfWeek>> officeHours;
    CourseSiteApp app;

    
    public RemoveTA_Transaction(CourseSiteApp initApp, TeachingAssistantPrototype initTA, HashMap<TimeSlot, ArrayList<DayOfWeek>> initOfficeHours) {
        ta = initTA;
        officeHours = initOfficeHours;
        app = initApp;
    }

    @Override
    public void doTransaction() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        OfficeHoursData data = (OfficeHoursData)siteData.getOfficeHoursData();
        data.removeTA(ta, officeHours);
        app.getFoolproofModule().updateControls(OH_FOOLPROOF_SETTINGS);
    }

    @Override
    public void undoTransaction() {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        OfficeHoursData data = (OfficeHoursData)siteData.getOfficeHoursData();
        data.addTA(ta, officeHours);
        app.getFoolproofModule().updateControls(OH_FOOLPROOF_SETTINGS);
    }
}
