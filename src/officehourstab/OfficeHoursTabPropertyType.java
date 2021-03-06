/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package officehourstab;

/**
 *
 * @author Michael
 */
public enum OfficeHoursTabPropertyType {
     /* THESE ARE THE NODES IN OUR APP */
    // FOR SIMPLE OK/CANCEL DIALOG BOXES
    OH_OK_PROMPT,
    OH_CANCEL_PROMPT,
    
    OH_LEFT_PANE,
    OH_TAS_HEADER_PANE,
    OH_TAS_HEADER_LABEL,
    OH_TAS_HEADER_TEXT_FIELD,
    OH_TAS_TABLE_VIEW,
    OH_NAME_TABLE_COLUMN,
    OH_EMAIL_TABLE_COLUMN,
    OH_SLOTS_TABLE_COLUMN,
    OH_TYPE_TABLE_COLUMN,

    OH_ADD_TA_PANE,
    OH_NAME_TEXT_FIELD,
    OH_EMAIL_TEXT_FIELD,
    OH_ADD_TA_BUTTON,
    
    OH_GRADUATE_TA_RADIO_BUTTON,
    OH_UNDERGRADUATE_TA_RADIO_BUTTON,
    OH_ALL_RADIO_BUTTON,
    OH_RADIO_BUTTON_TOGGLE_GROUP,
    
    OH_GRID_PANE,
    OH_EDIT_TA_DIALOG_LABEL,
    OH_EDIT_TA_NAME_LABEL,         
    OH_EDIT_TA_EMAIL_LABEL, 
    OH_EDIT_TA_TYPE_LABEL,
    OH_GRADUATE_TA_RADIO_BUTTON_TEXT,
    OH_UNDERGRADUATE_TA_RADIO_BUTTON_TEXT,
    OH_EDIT_TA_OK_BUTTON_TEXT,
    OH_EDIT_TA_CANCEL_BUTTON_TEXT,

    OH_RIGHT_PANE,
    OH_OFFICE_HOURS_HEADER_PANE,
    OH_OFFICE_HOURS_HEADER_LABEL,
    OH_OFFICE_HOURS_TABLE_VIEW,
    OH_START_TIME_TABLE_COLUMN,
    OH_END_TIME_TABLE_COLUMN,
    OH_MONDAY_TABLE_COLUMN,
    OH_TUESDAY_TABLE_COLUMN,
    OH_WEDNESDAY_TABLE_COLUMN,
    OH_THURSDAY_TABLE_COLUMN,
    OH_FRIDAY_TABLE_COLUMN,
    OH_DAYS_OF_WEEK,
    OH_FOOLPROOF_SETTINGS,
    OH_MINUS_BUTTON,
    OH_TAS_LABEL,
    OH_START_TIME_LABEL,
    OH_END_TIME_LABEL,
    OH_NULL,
    OH_START_TIME_COMBO_BOX,
    OH_END_TIME_COMBO_BOX,
    TIME_OPTIONS,
    OH_DEFAULT_START_TIME,
    OH_DEFAULT_END_TIME,
    
    // FOR THE EDIT DIALOG
    OH_TA_EDIT_DIALOG,
    OH_TA_DIALOG_GRID_PANE,
    OH_TA_DIALOG_HEADER_LABEL, 
    OH_TA_DIALOG_NAME_LABEL,
    OH_TA_DIALOG_NAME_TEXT_FIELD,
    OH_TA_DIALOG_EMAIL_LABEL,
    OH_TA_DIALOG_EMAIL_TEXT_FIELD,
    OH_TA_DIALOG_TYPE_LABEL,
    OH_TA_DIALOG_TYPE_BOX,
    OH_TA_DIALOG_GRAD_RADIO_BUTTON,
    OH_TA_DIALOG_UNDERGRAD_RADIO_BUTTON,
    OH_TA_DIALOG_OK_BOX,
    OH_TA_DIALOG_OK_BUTTON, 
    OH_TA_DIALOG_CANCEL_BUTTON, 

    // THESE ARE FOR ERROR MESSAGES PARTICULAR TO THE APP
    OH_NO_TA_SELECTED_TITLE, OH_NO_TA_SELECTED_CONTENT
}
