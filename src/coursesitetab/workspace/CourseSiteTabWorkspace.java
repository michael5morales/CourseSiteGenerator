/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesitetab.workspace;

import coursesite.CourseSiteApp;
import static coursesite.workspace.style.CSStyle.*;
import static coursesitetab.CourseSiteTabPropertyType.*;
import static djf.modules.AppGUIModule.ENABLED;
import djf.ui.AppNodesBuilder;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author Michael
 */
public class CourseSiteTabWorkspace {
    CourseSiteApp app;
    public Tab siteTab;
    
    public CourseSiteTabWorkspace(CourseSiteApp app) {
        this.app = app;
        
        // LAYOUT THE APP
        initLayout();

        // INIT THE EVENT HANDLERS
        initControllers();
        
        //INIT FOOLPROOF DESIGN
        initFoolproofDesign();
    }   
    
    private void initLayout() {
        
        Tab courseSiteTab = new Tab();
        
        // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder csBuilder = app.getGUIModule().getNodesBuilder();

        courseSiteTab.setText("Site");
        courseSiteTab.closableProperty().setValue(false);
     
        ScrollPane scrollPane = new ScrollPane();
        
        VBox sitePane = csBuilder.buildVBox(this, null, CLASS_CS_PANE, ENABLED);
        
        //BUILD BANNER BOX
        GridPane bannerBox = csBuilder.buildGridPane(this, sitePane, CLASS_CS_BOX, ENABLED);
        
        //BUILD BANNER LABELS
        csBuilder.buildLabel(CS_TAB_BANNER_LABEL, bannerBox, 0, 0, 1, 1, CLASS_CS_HEADER_LABEL, ENABLED);
        csBuilder.buildLabel(CS_TAB_BANNER_SUBJECT_LABEL, bannerBox, 0, 1, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(CS_TAB_BANNER_NUMBER_LABEL, bannerBox, 2, 1, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(CS_TAB_BANNER_SEMESTER_LABEL, bannerBox, 0, 2, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(CS_TAB_BANNER_YEAR_LABEL, bannerBox, 2, 2, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(CS_TAB_BANNER_TITLE_LABEL, bannerBox, 0, 3, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(CS_TAB_BANNER_EXPORT_LABEL, bannerBox, 0, 4, 1, 1, CLASS_CS_PROMPT, ENABLED);

        csBuilder.buildLabel(this, bannerBox, 1, 4, 1, 1, CLASS_CS_PROMPT, ENABLED);
        
        //BUILD OTHER BANNER OBJECTS
        csBuilder.buildComboBox(this, bannerBox, 1, 1, 1, 1, CLASS_CS_TEXT_FIELD, ENABLED, CS_NULL_LIST, CS_NULL_LIST);
        csBuilder.buildComboBox(this, bannerBox, 1, 2, 1, 1, CLASS_CS_TEXT_FIELD, ENABLED, CS_NULL_LIST, CS_NULL_LIST);
        csBuilder.buildComboBox(this, bannerBox, 3, 1, 1, 1, CLASS_CS_TEXT_FIELD, ENABLED, CS_NULL_LIST, CS_NULL_LIST);
        csBuilder.buildComboBox(this, bannerBox, 3, 2, 1, 1, CLASS_CS_TEXT_FIELD, ENABLED, CS_NULL_LIST, CS_NULL_LIST);
        
        csBuilder.buildTextField(this, bannerBox, 1, 3, 3, 1, CLASS_CS_TEXT_FIELD, ENABLED);
        
        //BANNER BOX STYLING
        bannerBox.paddingProperty().setValue(new Insets(15.0, 0.0, 15.0, 15.0));
        bannerBox.setHgap(10); 
        bannerBox.setVgap(10);
        
        
        //BUILD PAGES BOX
        HBox pagesBox = csBuilder.buildHBox(this, sitePane, CLASS_CS_BOX, ENABLED);
         
        //BUILD LABEL
        csBuilder.buildLabel(CS_TAB_PAGES_LABEL, pagesBox, CLASS_CS_HEADER_LABEL, ENABLED);
        
        //BUILD CHECKBOXES
        csBuilder.buildCheckBox(CS_TAB_HOME_CHECK_BOX, pagesBox, CLASS_CS_CHECKBOX, ENABLED);
        csBuilder.buildCheckBox(CS_TAB_SYLLABUS_CHECK_BOX, pagesBox, CLASS_CS_CHECKBOX, ENABLED);
        csBuilder.buildCheckBox(CS_TAB_SCHEDULE_CHECK_BOX, pagesBox, CLASS_CS_CHECKBOX, ENABLED);
        csBuilder.buildCheckBox(CS_TAB_HWS_CHECK_BOX, pagesBox, CLASS_CS_CHECKBOX, ENABLED);
        
        //PAGES BOX STYLING
        pagesBox.paddingProperty().setValue(new Insets(15.0, 0.0, 15.0, 15.0));
        pagesBox.setSpacing(30.0);
        
        
        //BUILD STYLE BOX
        GridPane styleBox = csBuilder.buildGridPane(this, sitePane, CLASS_CS_BOX, ENABLED);
        
        //BUILD LABELS
        csBuilder.buildLabel(CS_TAB_STYLE_LABEL, styleBox, 0, 0, 1, 1, CLASS_CS_HEADER_LABEL, ENABLED);
        csBuilder.buildLabel(CS_TAB_FONTS_LABEL, styleBox, 0, 5, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(CS_TAB_NOTE_LABEL, styleBox, 0, 6, 3, 1, CLASS_CS_PROMPT, ENABLED);
        
        //BUILD BUTTONS
        csBuilder.buildTextButton(CS_TAB_ICON_BUTTON, styleBox, 0, 1, 1, 1, CLASS_CS_BUTTON, ENABLED);
        csBuilder.buildTextButton(CS_TAB_NAV_IMAGE_BUTTON, styleBox, 0, 2, 1, 1, CLASS_CS_BUTTON, ENABLED);
        csBuilder.buildTextButton(CS_TAB_LEFT_FOOTER_BUTTON, styleBox, 0, 3, 1, 1, CLASS_CS_BUTTON, ENABLED);
        csBuilder.buildTextButton(CS_TAB_RIGHT_FOOTER_BUTTON, styleBox, 0, 4, 1, 1, CLASS_CS_BUTTON, ENABLED);
        
        //BUILD IMAGES
        //BUILD CHECKBOXES
        csBuilder.buildComboBox(CS_TAB_STYLE_CHECK_BOX, styleBox, 1, 4, 1, 1, EMPTY_TEXT, ENABLED, CS_NULL_LIST, CS_NULL_LIST);
        
        //STYLE BOX STYLING
        styleBox.paddingProperty().setValue(new Insets(15.0, 0.0, 15.0, 15.0));
        styleBox.setHgap(10); 
        styleBox.setVgap(10);
        
        //BUILD INSTRUCTOR BOX
        GridPane instructorBox = csBuilder.buildGridPane(this, sitePane, CLASS_CS_BOX, ENABLED);
        
        //BUILD LABELS
        csBuilder.buildLabel(CS_TAB_INSTRUCTOR_LABEL, instructorBox, 0, 0, 1, 1, CLASS_CS_HEADER_LABEL, ENABLED);
        csBuilder.buildLabel(CS_TAB_NAME_LABEL, instructorBox, 0, 1, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(CS_TAB_EMAIL_LABEL, instructorBox, 0, 2, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(CS_TAB_ROOM_LABEL, instructorBox, 2, 1, 1, 1, CLASS_CS_PROMPT, ENABLED);
        csBuilder.buildLabel(CS_TAB_HOME_PAGE_LABEL, instructorBox, 2, 2, 1, 1, CLASS_CS_PROMPT, ENABLED);

        //BUILD TEXT BOXES
        csBuilder.buildTextField(CS_TAB_NAME_TEXT_FIELD, instructorBox, 1, 1, 1, 1, CLASS_CS_TEXT_FIELD, ENABLED);
        csBuilder.buildTextField(CS_TAB_EMAIL_TEXT_FIELD, instructorBox, 1, 2, 1, 1, CLASS_CS_TEXT_FIELD, ENABLED);
        csBuilder.buildTextField(CS_TAB_ROOM_TEXT_FIELD, instructorBox, 3, 1, 1, 1, CLASS_CS_TEXT_FIELD, ENABLED);
        csBuilder.buildTextField(CS_TAB_HOME_PAGE_TEXT_FIELD, instructorBox, 3, 2, 1, 1, CLASS_CS_TEXT_FIELD, ENABLED);
        
        //BUILD HBOX FOR OFFICE HOURS
        HBox ohBox = csBuilder.buildHBox(this, instructorBox, 0, 3, 1, 2, EMPTY_TEXT, ENABLED);
        
        csBuilder.buildTextButton(CS_TAB_PLUS_BUTTON, ohBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(CS_TAB_OFFICE_HOURS_LABEL, ohBox, CLASS_CS_PROMPT, ENABLED);
        
        //BUILD TEXTAREA
        
        //INSTRUCTOR BOX STYLING
        instructorBox.paddingProperty().setValue(new Insets(15.0, 0.0, 15.0, 15.0));
        instructorBox.setHgap(10); 
        instructorBox.setVgap(10);
        
        //SITE TAB STYLING
        sitePane.paddingProperty().setValue(new Insets(3.0, 0.0, 0.0, 1.0));
        sitePane.setSpacing(3.0);
        
        scrollPane.setContent(sitePane);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        
        // AND PUT EVERYTHING IN THE WORKSPACE
        courseSiteTab.setContent(scrollPane);
        this.siteTab = courseSiteTab;
    }
    
    private void initControllers() {
        
    }
    
    private void initFoolproofDesign() {
        
    }

}
