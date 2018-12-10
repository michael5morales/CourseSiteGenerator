/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syllabusTab.data;

import coursesite.CourseSiteApp;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import javafx.scene.control.TextArea;
import static syllabusTab.SyllabusTabPropertyType.*;

/**
 *
 * @author Michael
 */
public class SyllabusTabData implements AppDataComponent {
    CourseSiteApp app;
    
    
    public SyllabusTabData(CourseSiteApp initApp) {
        app = initApp;
    }
    
    public void initData(String description, String topics, String prerequisites, String outcomes,
            String textbooks, String gradedComponents, String gradedNote, String academicDishonesty,
            String specialAssistance) {
        
        AppGUIModule gui = app.getGUIModule();
        
        TextArea descriptionTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_DESCRIPTION_TEXT_AREA);
        TextArea topicsTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_TOPICS_TEXT_AREA);
        TextArea preRequisitesTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_PREREQUISITES_TEXT_AREA);
        TextArea outcomesTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_OUTCOMES_TEXT_AREA);
        TextArea textbooksTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_TEXTBOOKS_TEXT_AREA);
        TextArea componentsTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_GRADED_COMPONENTS_TEXT_AREA);
        TextArea noteTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_GRADED_NOTE_TEXT_AREA);
        TextArea academicTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_ACADEMIC_DISHONESTY_TEXT_AREA);
        TextArea assistanceTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_SPECIAL_ASSISTANCE_TEXT_AREA);
        
        descriptionTextArea.setText(description);
        topicsTextArea.setText(topics);
        preRequisitesTextArea.setText(prerequisites);
        outcomesTextArea.setText(outcomes);
        textbooksTextArea.setText(textbooks);
        componentsTextArea.setText(gradedComponents);
        noteTextArea.setText(gradedNote);
        academicTextArea.setText(academicDishonesty);
        assistanceTextArea.setText(specialAssistance);
        
    }
    
    @Override
    public void reset() {
        AppGUIModule gui = app.getGUIModule();
        ((TextArea)gui.getGUINode(SYLLABUS_TAB_DESCRIPTION_TEXT_AREA)).clear();
        ((TextArea)gui.getGUINode(SYLLABUS_TAB_TOPICS_TEXT_AREA)).clear();
        ((TextArea)gui.getGUINode(SYLLABUS_TAB_PREREQUISITES_TEXT_AREA)).clear();
        ((TextArea)gui.getGUINode(SYLLABUS_TAB_OUTCOMES_TEXT_AREA)).clear();
        ((TextArea)gui.getGUINode(SYLLABUS_TAB_TEXTBOOKS_TEXT_AREA)).clear();
        ((TextArea)gui.getGUINode(SYLLABUS_TAB_GRADED_COMPONENTS_TEXT_AREA)).clear();
        ((TextArea)gui.getGUINode(SYLLABUS_TAB_GRADED_NOTE_TEXT_AREA)).clear();
        ((TextArea)gui.getGUINode(SYLLABUS_TAB_ACADEMIC_DISHONESTY_TEXT_AREA)).clear();
        ((TextArea)gui.getGUINode(SYLLABUS_TAB_SPECIAL_ASSISTANCE_TEXT_AREA)).clear();
    }
    
    public String getDescriptionTextArea() {
        AppGUIModule gui = app.getGUIModule();
        TextArea descriptionTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_DESCRIPTION_TEXT_AREA);
        String description = descriptionTextArea.getText();
        return description;
    }
    
    public String[] getTopicsTextArea() {
        AppGUIModule gui = app.getGUIModule();
        TextArea topicsTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_TOPICS_TEXT_AREA);
        String topics = topicsTextArea.getText();
        String[] topicList = topics.split("\n");
        return topicList;
    }
    
    public String getPreRequisitesTextArea() {
        AppGUIModule gui = app.getGUIModule();
        TextArea preRequisitesTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_PREREQUISITES_TEXT_AREA);
        String preRequisites = preRequisitesTextArea.getText();
        return preRequisites;
    }
    
    public String[] getOutcomesTextArea() {
        AppGUIModule gui = app.getGUIModule();
        TextArea outcomesTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_OUTCOMES_TEXT_AREA);
        String outcomes = outcomesTextArea.getText();
        String[] outcomesList = outcomes.split("\n");
        return outcomesList;
    }
    
    public String getTextbooksTextArea() {
        AppGUIModule gui = app.getGUIModule();
        TextArea textbooksTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_TEXTBOOKS_TEXT_AREA);
        String textbooks = textbooksTextArea.getText();
        return textbooks;
    }
    
    public String getGradedComponentsTextArea() {
        AppGUIModule gui = app.getGUIModule();
        TextArea componentsTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_GRADED_COMPONENTS_TEXT_AREA);
        String components = componentsTextArea.getText();
        return components;
    }
    
    public String getGradedNoteTextArea() {
        AppGUIModule gui = app.getGUIModule();
        TextArea noteTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_GRADED_NOTE_TEXT_AREA);
        String note = noteTextArea.getText();
        return note;
    }
    
    public String getAcademicDishonestyTextArea() {
        AppGUIModule gui = app.getGUIModule();
        TextArea academicTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_ACADEMIC_DISHONESTY_TEXT_AREA);
        String academic = academicTextArea.getText();
        return academic;
    }
    
    public String getSpecialAssistanceTextArea() {
        AppGUIModule gui = app.getGUIModule();
        TextArea assistanceTextArea = (TextArea)gui.getGUINode(SYLLABUS_TAB_SPECIAL_ASSISTANCE_TEXT_AREA);
        String assistance = assistanceTextArea.getText();
        return assistance;
    }
    
}
