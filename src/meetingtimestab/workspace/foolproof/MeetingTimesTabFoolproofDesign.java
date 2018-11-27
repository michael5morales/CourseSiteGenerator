/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingtimestab.workspace.foolproof;

import coursesite.CourseSiteApp;
import coursesite.data.CourseSiteData;
import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import javafx.scene.control.Button;
import static meetingtimestab.MeetingTimesTabPropertyType.*;
import meetingtimestab.data.MeetingTimesTabData;

/**
 *
 * @author Michael
 */
public class MeetingTimesTabFoolproofDesign implements FoolproofDesign {
    CourseSiteApp app;

    public MeetingTimesTabFoolproofDesign(CourseSiteApp initApp) {
        app = initApp;
    }

    @Override
    public void updateControls() {
        updateRemoveFoolproofDesign();
    }
    
    private void updateRemoveFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        
        // FOOLPROOF DESIGN STUFF FOR REMOVE TA BUTTON
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        MeetingTimesTabData data = (MeetingTimesTabData)siteData.getMeetingTimeData();
        
        Button removeLectureButton = (Button) gui.getGUINode(MEETING_TIMES_TAB_MINUS_BUTTON1);
        
        if (data.isLectureSelected()) {
            removeLectureButton.setDisable(false);
        } else {
            removeLectureButton.setDisable(true);
        }
        
        Button removeLabButton = (Button) gui.getGUINode(MEETING_TIMES_TAB_MINUS_BUTTON2);
        
        if (data.isLabSelected()) {
            removeLabButton.setDisable(false);
        } else {
            removeLabButton.setDisable(true);
        }
        
        Button removeRecitationButton = (Button) gui.getGUINode(MEETING_TIMES_TAB_MINUS_BUTTON3);
        
        if (data.isRecitationSelected()) {
            removeRecitationButton.setDisable(false);
        } else {
            removeRecitationButton.setDisable(true);
        }
        
    }
    
}
