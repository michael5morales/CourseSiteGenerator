/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesitetab.workspace.controller;

import coursesite.CourseSiteApp;
import coursesitetab.CourseSiteTabPropertyType;
import djf.AppPropertyType;
import djf.modules.AppGUIModule;
import djf.ui.dialogs.AppDialogsFacade;
import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Michael
 */
public class CourseSiteTabController {
    CourseSiteApp app;

    public CourseSiteTabController(CourseSiteApp initApp) {
        app = initApp;
    }
    
    public void processEditTextAreaVisibility(CourseSiteTabPropertyType node, CourseSiteTabPropertyType btn) {
        AppGUIModule gui = app.getGUIModule();
         
        TextArea txtArea = (TextArea)gui.getGUINode(node);
        
        if (txtArea.managedProperty().getValue()) {
            processMinimizeTextArea(node, btn);
        } else {
            processExpandTextArea(node, btn);
        }
    }
    
    public void processExpandTextArea(CourseSiteTabPropertyType node, CourseSiteTabPropertyType btn) {
        AppGUIModule gui = app.getGUIModule();
         
        TextArea txtArea = (TextArea)gui.getGUINode(node);
        
        txtArea.managedProperty().set(true);
        txtArea.setVisible(true);
        txtArea.setDisable(false);
        
        Button expandBtn = (Button)gui.getGUINode(btn);
        
        expandBtn.setText("-");
    }
    
    public void processMinimizeTextArea(CourseSiteTabPropertyType node, CourseSiteTabPropertyType btn) {
        AppGUIModule gui = app.getGUIModule();
         
        TextArea txtArea = (TextArea)gui.getGUINode(node);
        
        txtArea.managedProperty().set(false);
        txtArea.setVisible(false);
        txtArea.setDisable(true);
        
        Button expandBtn = (Button)gui.getGUINode(btn);
        
        expandBtn.setText("+");
    }
    
    public void processChangeImage(CourseSiteTabPropertyType node) {
        AppGUIModule gui = app.getGUIModule();
        ImageView imageView = (ImageView)gui.getGUINode(node); 
        File selectedFile = AppDialogsFacade.showOpenImageDialog(gui.getWindow(), AppPropertyType.APP_TITLE);
        String file = "file:" + selectedFile.getPath();
        imageView.setImage(new Image(file));
    }
}
