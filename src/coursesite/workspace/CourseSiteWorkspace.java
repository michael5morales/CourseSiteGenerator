/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesite.workspace;

import coursesite.CourseSiteApp;
import djf.components.AppWorkspaceComponent;
import djf.ui.AppNodesBuilder;
import java.awt.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import properties_manager.PropertiesManager;

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
       
        Tab siteTab = new Tab("Site");
        Tab syllabusTab = new Tab("Syllabus");
        Tab meetingTab = new Tab("Meeting Times");
        Tab ohTab = new Tab("Office Hours");
        Tab scheduleTab = new Tab("Schedule");
        
        siteTab.closableProperty().setValue(false);
        syllabusTab.closableProperty().setValue(false);
        meetingTab.closableProperty().setValue(false);
        ohTab.closableProperty().setValue(false);
        scheduleTab.closableProperty().setValue(false);
        
        tPane.getTabs().add(siteTab);
        tPane.getTabs().add(syllabusTab);
        tPane.getTabs().add(meetingTab);
        tPane.getTabs().add(ohTab);
        tPane.getTabs().add(scheduleTab);
        
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
