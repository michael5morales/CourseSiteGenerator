/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syllabusTab.workspace.controllers;

import coursesite.CourseSiteApp;
import djf.modules.AppGUIModule;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import syllabusTab.SyllabusTabPropertyType;

/**
 *
 * @author Michael
 */
public class SyllabusTabController {
    CourseSiteApp app;

    public SyllabusTabController(CourseSiteApp initApp) {
        app = initApp;
    }
    
    public void processEditTextAreaVisibility(SyllabusTabPropertyType node, SyllabusTabPropertyType btn) {
        AppGUIModule gui = app.getGUIModule();
         
        TextArea txtArea = (TextArea)gui.getGUINode(node);
        
        if (txtArea.managedProperty().getValue()) {
            processMinimizeTextArea(node, btn);
        } else {
            processExpandTextArea(node, btn);
        }
    }
    
    public void processExpandTextArea(SyllabusTabPropertyType node, SyllabusTabPropertyType btn) {
        AppGUIModule gui = app.getGUIModule();
         
        TextArea txtArea = (TextArea)gui.getGUINode(node);
        
        txtArea.managedProperty().set(true);
        txtArea.setVisible(true);
        txtArea.setDisable(false);
        
        Button expandBtn = (Button)gui.getGUINode(btn);
        
        expandBtn.setText("-");
    }
    
    public void processMinimizeTextArea(SyllabusTabPropertyType node, SyllabusTabPropertyType btn) {
        AppGUIModule gui = app.getGUIModule();
         
        TextArea txtArea = (TextArea)gui.getGUINode(node);
        
        txtArea.managedProperty().set(false);
        txtArea.setVisible(false);
        txtArea.setDisable(true);
        
        Button expandBtn = (Button)gui.getGUINode(btn);
        
        expandBtn.setText("+");
    }
}
