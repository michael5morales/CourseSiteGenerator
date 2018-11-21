/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesite.workspace;

import coursesite.CourseSiteApp;
import coursesitetab.workspace.CourseSiteTabWorkspace;
import djf.components.AppWorkspaceComponent;
import djf.ui.AppNodesBuilder;
import java.awt.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import meetingtimestab.workspace.MeetingTimesTabWorkspace;
import officehourstab.workspace.OfficeHoursTabWorkspace;
import properties_manager.PropertiesManager;
import scheduletab.workspace.ScheduleTabWorkspace;
import syllabusTab.workspace.SyllabusTabWorkspace;

/**
 *
 * @author Michael
 */
public class CourseSiteWorkspace extends AppWorkspaceComponent{
    
    public CourseSiteWorkspace (CourseSiteApp app) {
        super(app);
        
        // LAYOUT THE APP
        initLayout();

        // INIT THE EVENT HANDLERS
        initControllers();

        // 
        initFoolproofDesign();
    }
    
    private void initLayout() {
        // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder csBuilder = app.getGUIModule().getNodesBuilder();

        TabPane tPane = new TabPane();

        CourseSiteTabWorkspace coursesiteTab = new CourseSiteTabWorkspace((CourseSiteApp) app);
        tPane.getTabs().add(coursesiteTab.siteTab);
        
        SyllabusTabWorkspace syllabusTab = new SyllabusTabWorkspace((CourseSiteApp) app);
        tPane.getTabs().add(syllabusTab.syllabusTab);
        
        MeetingTimesTabWorkspace meetingTimesTab = new MeetingTimesTabWorkspace((CourseSiteApp) app);
        tPane.getTabs().add(meetingTimesTab.meetingTimesTab);
        
        OfficeHoursTabWorkspace officeHoursTab = new OfficeHoursTabWorkspace((CourseSiteApp) app);
        tPane.getTabs().add(officeHoursTab.officeHoursTab);
        
        ScheduleTabWorkspace scheduleTab = new ScheduleTabWorkspace((CourseSiteApp) app);
        tPane.getTabs().add(scheduleTab.scheduleTab);
            
        tPane.setTabMinWidth(tPane.getWidth()/5.0);
        
        
        workspace = new BorderPane();
        
        ((BorderPane)workspace).setCenter(tPane);
    }
    
    private void initControllers() {
        
    }
    
    private void initFoolproofDesign () {
        
    }
    
    @Override
    public void processWorkspaceKeyEvent(KeyEvent ke) {
        // WE AREN'T USING THIS FOR THIS APPLICATION
    }

    @Override
    public void showNewDialog() {
        // WE AREN'T USING THIS FOR THIS APPLICATION
    }
    
}
