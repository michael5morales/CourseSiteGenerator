/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syllabusTab.workspace;

import coursesite.CourseSiteApp;
import static coursesite.workspace.style.CSStyle.*;
import static djf.modules.AppGUIModule.ENABLED;
import static syllabustab.SyllabusTabPropertyType.*;
import djf.ui.AppNodesBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author Michael
 */
public class SyllabusTabWorkspace {
    CourseSiteApp app;
    public Tab syllabusTab;
    
     public SyllabusTabWorkspace(CourseSiteApp app) {
        this.app = app;
        
        // LAYOUT THE APP
        initLayout();

        // INIT THE EVENT HANDLERS
        initControllers();
        
        //INIT FOOLPROOF DESIGN
        initFoolproofDesign();
    }  
     
    private void initLayout() {
         
        Tab syllabusTab = new Tab();
        
        // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder csBuilder = app.getGUIModule().getNodesBuilder();

        syllabusTab.setText("Syllabus");
        syllabusTab.closableProperty().setValue(false);
        
        ScrollPane scrollPane = new ScrollPane();
        
        VBox syllabusPane = csBuilder.buildVBox(this, null, CLASS_CS_PANE, ENABLED);
        
        //BUILD DESCRIPTION BOX
        VBox descriptionBox = csBuilder.buildVBox(this, syllabusPane, CLASS_CS_BOX, ENABLED);
        
        //BUILD HBOX FOR BUTTON AND LABEL
        HBox descriptionLabelBox = csBuilder.buildHBox(this, descriptionBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(SYLLABUS_TAB_PLUS_BUTTON1, descriptionLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(SYLLABUS_TAB_DESCRIPTION_LABEL, descriptionLabelBox, CLASS_CS_PROMPT, ENABLED);

        //BUILD TEXTAREA
        csBuilder.buildTextArea(this, descriptionBox, EMPTY_TEXT, ENABLED);
        
        //TOPICS BOX STYLING
        descriptionBox.paddingProperty().setValue(new Insets(15.0, 0.0, 15.0, 15.0));
        descriptionLabelBox.setSpacing(15.0);
        descriptionLabelBox.setAlignment(Pos.CENTER_LEFT);
        
        //BUILD TOPICS BOX
        VBox topicsBox = csBuilder.buildVBox(this, syllabusPane, CLASS_CS_BOX, ENABLED);
        
        //BUILD HBOX FOR BUTTON AND LABEL
        HBox topicsLabelBox = csBuilder.buildHBox(this, topicsBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(SYLLABUS_TAB_PLUS_BUTTON2, topicsLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(SYLLABUS_TAB_TOPICS_LABEL, topicsLabelBox, CLASS_CS_PROMPT, ENABLED);

        //BUILD TEXTAREA
        //csBuilder.buildTextArea(this, topicsBox, EMPTY_TEXT, !ENABLED);
        
        //TOPICS BOX STYLING
        topicsBox.paddingProperty().setValue(new Insets(15.0, 0.0, 15.0, 15.0));
        topicsLabelBox.setSpacing(15.0);
        topicsLabelBox.setAlignment(Pos.CENTER_LEFT);
        
        //BUILD PREREQ BOX
        VBox prereqBox = csBuilder.buildVBox(this, syllabusPane, CLASS_CS_BOX, ENABLED);
        
        //BUILD HBOX FOR BUTTON AND LABEL
        HBox prereqLabelBox = csBuilder.buildHBox(this, prereqBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(SYLLABUS_TAB_PLUS_BUTTON3, prereqLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(SYLLABUS_TAB_PREREQUISITES_LABEL, prereqLabelBox, CLASS_CS_PROMPT, ENABLED);

        //BUILD TEXTAREA
        //csBuilder.buildTextArea(this, prereqBox, EMPTY_TEXT, !ENABLED);
        
        //TOPICS BOX STYLING
        prereqBox.paddingProperty().setValue(new Insets(15.0, 0.0, 15.0, 15.0));
        prereqLabelBox.setSpacing(15.0);
        prereqLabelBox.setAlignment(Pos.CENTER_LEFT);
        
        //BUILD OUTCOMES BOX
        VBox outcomesBox = csBuilder.buildVBox(this, syllabusPane, CLASS_CS_BOX, ENABLED);
        
        //BUILD HBOX FOR BUTTON AND LABEL
        HBox outcomesLabelBox = csBuilder.buildHBox(this, outcomesBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(SYLLABUS_TAB_PLUS_BUTTON4, outcomesLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(SYLLABUS_TAB_OUTCOMES_LABEL, outcomesLabelBox, CLASS_CS_PROMPT, ENABLED);

        //BUILD TEXTAREA
        //csBuilder.buildTextArea(this, outcomesBox, EMPTY_TEXT, !ENABLED);
        
        //TOPICS BOX STYLING
        outcomesBox.paddingProperty().setValue(new Insets(15.0, 0.0, 15.0, 15.0));
        outcomesLabelBox.setSpacing(15.0);
        outcomesLabelBox.setAlignment(Pos.CENTER_LEFT);
        
        //BUILD TEXTBOOKS BOX
        VBox textbooksBox = csBuilder.buildVBox(this, syllabusPane, CLASS_CS_BOX, ENABLED);
        
        //BUILD HBOX FOR BUTTON AND LABEL
        HBox textbooksLabelBox = csBuilder.buildHBox(this, textbooksBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(SYLLABUS_TAB_PLUS_BUTTON5, textbooksLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(SYLLABUS_TAB_TEXTBOOKS_LABEL, textbooksLabelBox, CLASS_CS_PROMPT, ENABLED);

        //BUILD TEXTAREA
        //csBuilder.buildTextArea(this, textbooksBox, EMPTY_TEXT, !ENABLED);
        
        //TOPICS BOX STYLING
        textbooksBox.paddingProperty().setValue(new Insets(15.0, 0.0, 15.0, 15.0));
        textbooksLabelBox.setSpacing(15.0);
        textbooksLabelBox.setAlignment(Pos.CENTER_LEFT);
        
        //BUILD GRADED COMPONENTS BOX
        VBox componentsBox = csBuilder.buildVBox(this, syllabusPane, CLASS_CS_BOX, ENABLED);
        
        //BUILD HBOX FOR BUTTON AND LABEL
        HBox componentsLabelBox = csBuilder.buildHBox(this, componentsBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(SYLLABUS_TAB_PLUS_BUTTON6, componentsLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(SYLLABUS_TAB_GRADED_COMPONENTS_LABEL, componentsLabelBox, CLASS_CS_PROMPT, ENABLED);

        //BUILD TEXTAREA
        //csBuilder.buildTextArea(this, componentsBox, EMPTY_TEXT, !ENABLED);
        
        //TOPICS BOX STYLING
        componentsBox.paddingProperty().setValue(new Insets(15.0, 0.0, 15.0, 15.0));
        componentsLabelBox.setSpacing(15.0);
        componentsLabelBox.setAlignment(Pos.CENTER_LEFT);
        
        //BUILD GRADED NOTE BOX
        VBox noteBox = csBuilder.buildVBox(this, syllabusPane, CLASS_CS_BOX, ENABLED);
        
        //BUILD HBOX FOR BUTTON AND LABEL
        HBox noteLabelBox = csBuilder.buildHBox(this, noteBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(SYLLABUS_TAB_PLUS_BUTTON7, noteLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(SYLLABUS_TAB_GRADING_NOTE_LABEL, noteLabelBox, CLASS_CS_PROMPT, ENABLED);

        //BUILD TEXTAREA
        //csBuilder.buildTextArea(this, noteBox, EMPTY_TEXT, !ENABLED);
        
        //TOPICS BOX STYLING
        noteBox.paddingProperty().setValue(new Insets(15.0, 0.0, 15.0, 15.0));
        noteLabelBox.setSpacing(15.0);
        noteLabelBox.setAlignment(Pos.CENTER_LEFT);
        
        //BUILD ACADEMIC DISHONESTY BOX
        VBox academicBox = csBuilder.buildVBox(this, syllabusPane, CLASS_CS_BOX, ENABLED);
        
        //BUILD HBOX FOR BUTTON AND LABEL
        HBox academicLabelBox = csBuilder.buildHBox(this, academicBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(SYLLABUS_TAB_PLUS_BUTTON8, academicLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(SYLLABUS_TAB_ACADEMIC_DISHONESTY_LABEL, academicLabelBox, CLASS_CS_PROMPT, ENABLED);

        //BUILD TEXTAREA
        //csBuilder.buildTextArea(this, academicBox, EMPTY_TEXT, !ENABLED);
        
        //TOPICS BOX STYLING
        academicBox.paddingProperty().setValue(new Insets(15.0, 0.0, 15.0, 15.0));
        academicLabelBox.setSpacing(15.0);
        academicLabelBox.setAlignment(Pos.CENTER_LEFT);
        
        //BUILD SPECIAL ASSISTANCE BOX
        VBox specialBox = csBuilder.buildVBox(this, syllabusPane, CLASS_CS_BOX, ENABLED);
        
        //BUILD HBOX FOR BUTTON AND LABEL
        HBox specialLabelBox = csBuilder.buildHBox(this, specialBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildTextButton(SYLLABUS_TAB_PLUS_BUTTON9, specialLabelBox, EMPTY_TEXT, ENABLED);
        csBuilder.buildLabel(SYLLABUS_TAB_SPECIAL_ASSISTANCE_LABEL, specialLabelBox, CLASS_CS_PROMPT, ENABLED);

        //BUILD TEXTAREA
        //csBuilder.buildTextArea(this, specialBox, EMPTY_TEXT, !ENABLED);
        
        //TOPICS BOX STYLING
        specialBox.paddingProperty().setValue(new Insets(15.0, 0.0, 15.0, 15.0));
        specialLabelBox.setSpacing(15.0);
        specialLabelBox.setAlignment(Pos.CENTER_LEFT);
        
        //SYLLABUS TAB STYLING
        syllabusPane.paddingProperty().setValue(new Insets(3.0, 0.0, 0.0, 1.0));
        syllabusPane.setSpacing(3.0);
        
        scrollPane.setContent(syllabusPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

    
        // AND PUT EVERYTHING IN THE WORKSPACE
        syllabusTab.setContent(scrollPane);
        this.syllabusTab = syllabusTab;
    }
    
    private void initControllers() {
        
    }
    
    private void initFoolproofDesign() {
        
    }
}
