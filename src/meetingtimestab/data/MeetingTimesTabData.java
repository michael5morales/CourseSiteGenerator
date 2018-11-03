/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingtimestab.data;

import coursesite.CourseSiteApp;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import static meetingtimestab.MeetingTimesTabPropertyType.*;

/**
 *
 * @author Michael
 */
public class MeetingTimesTabData implements AppDataComponent{
    CourseSiteApp app;
    
    ObservableList<RecitationLabMeetingType> recitations;
    ObservableList<RecitationLabMeetingType> labs;
    
    public MeetingTimesTabData(CourseSiteApp initApp) {
        app = initApp;
        AppGUIModule gui = app.getGUIModule();
        
        TableView<RecitationLabMeetingType> recitationsTableView = (TableView)gui.getGUINode(MEETING_TIMES_TAB_RECITATIONS_TABLE_VIEW);
        recitations = recitationsTableView.getItems();
        
        TableView<RecitationLabMeetingType> labsTableView = (TableView)gui.getGUINode(MEETING_TIMES_TAB_LABS_TABLE_VIEW);
        labs = labsTableView.getItems();
    }
    
    @Override
    public void reset() {
        recitations.clear();
        labs.clear();
    }
}
