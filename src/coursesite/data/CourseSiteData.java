/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesite.data;

import coursesite.CourseSiteApp;
import coursesitetab.data.CourseSiteTabData;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import meetingtimestab.data.MeetingTimesTabData;
import officehourstab.data.OfficeHoursData;
import scheduletab.data.ScheduleTabData;
import syllabusTab.data.SyllabusTabData;

/**
 *
 * @author Michael
 */
public class CourseSiteData implements AppDataComponent{
    CourseSiteApp app;
    AppDataComponent officeHoursData;
    AppDataComponent syllabusTabData;
    AppDataComponent siteTabData;
    AppDataComponent meetingTabData;
    AppDataComponent scheduleTabData;
    
     public CourseSiteData(CourseSiteApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
        AppGUIModule gui = app.getGUIModule();
        officeHoursData = new OfficeHoursData(initApp);
        syllabusTabData = new SyllabusTabData(initApp);
        siteTabData = new CourseSiteTabData(initApp);
        meetingTabData = new MeetingTimesTabData(initApp);
        scheduleTabData = new ScheduleTabData(initApp);
     }
     
     /**
     * Called each time new work is created or loaded, it resets all data
     * and data structures such that they can be used for new values.
     */
     @Override
    public void reset() {
        officeHoursData.reset();
        syllabusTabData.reset();
        siteTabData.reset();
        meetingTabData.reset();
        scheduleTabData.reset();
    }
    
    public AppDataComponent getOfficeHoursData() {
        return officeHoursData;
    }
    
    public AppDataComponent getSyllabusTabData() {
        return syllabusTabData;
    }
    
    public AppDataComponent getSiteTabData() {
        return siteTabData;
    }
    
    public AppDataComponent getMeetingTimeData() {
        return meetingTabData;
    }
    
    public AppDataComponent getScheduleTabData() {
        return scheduleTabData;
    }
    
}
