/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesite.files;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import coursesite.CourseSiteApp;
import coursesite.data.CourseSiteData;
import coursesitetab.data.CourseSiteTabData;
import static djf.AppPropertyType.APP_EXPORT_PAGE;
import static djf.AppPropertyType.APP_EXPORT_PATH;
import java.io.File;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import javax.json.stream.JsonParser;
import meetingtimestab.data.LectureMeetingType;
import meetingtimestab.data.MeetingTimesTabData;
import meetingtimestab.data.RecitationLabMeetingType;
import officehourstab.data.OfficeHoursData;
import officehourstab.data.TAType;
import static officehourstab.data.TAType.*;
import officehourstab.data.TeachingAssistantPrototype;
import officehourstab.data.TimeSlot;
import officehourstab.data.TimeSlot.DayOfWeek;
import properties_manager.PropertiesManager;
import scheduletab.data.ScheduleItem;
import scheduletab.data.ScheduleTabData;
import syllabusTab.data.SyllabusTabData;

/**
 *
 * @author Michael
 */
public class CourseSiteFiles implements AppFileComponent{
    // THIS IS THE APP ITSELF
    CourseSiteApp app;
    
    // THESE ARE USED FOR IDENTIFYING JSON TYPES
    static final String JSON_SITE = "siteTab";
    static final String JSON_SUBJECT = "subject";
    static final String JSON_NUMBER = "number";
    static final String JSON_SEMESTER = "semester";
    static final String JSON_YEAR = "year";
    static final String JSON_TITLE = "title";
    static final String JSON_LOGOS = "logos";
    static final String JSON_HREF = "href";
    static final String JSON_SRC = "src";
    static final String JSON_FAV_ICON = "favicon";
    static final String JSON_NAVBAR_IMAGE = "navbar";
    static final String JSON_LEFT_FOOTER_IMAGE = "bottom_left";
    static final String JSON_RIGHT_FOOTER_IMAGE = "bottom_right";
    static final String JSON_HOURS = "hours";
    
    static final String JSON_PAGES = "pages";
    
    static final String JSON_STYLE_SHEET = "style_sheet";
    
    static final String JSON_INSTRUCTOR = "instructor";
    static final String JSON_INSTRUCTOR_NAME = "name";
    static final String JSON_INSTRUCTOR_ROOM = "room";
    static final String JSON_INSTRUCTOR_EMAIL = "email";
    static final String JSON_INSTRUCTOR_HOME_PAGE = "link";
    
    static final String JSON_SYLLABUS_TAB = "syllabusTab";
    static final String JSON_DESCRIPTION = "description";
    static final String JSON_TOPICS = "topics";
    static final String JSON_PREREQUISITES = "prerequisites";
    static final String JSON_OUTCOMES = "outcomes";
    static final String JSON_TEXTBOOKS = "textbooks";
    static final String JSON_GRADED_COMPONENTS = "gradedComponents";
    static final String JSON_GRADED_NOTE = "gradingNote";
    static final String JSON_ACADEMIC_DISHONESTY = "academicDishonesty";
    static final String JSON_SPECIAL_ASSISTANCE = "specialAssistance";
    
    static final String JSON_MEETING_TIMES = "meetingTimesTab";
    static final String JSON_SECTION = "section";
    static final String JSON_DAY_TIME = "day_time";
    static final String JSON_LOCATION = "location";
    static final String JSON_TA1 = "ta_1";
    static final String JSON_TA2 = "ta_2";
    static final String JSON_DAYS = "days";
    static final String JSON_TIME = "time";
    static final String JSON_ROOM = "room";
    static final String JSON_LECTURES = "lectures";
    static final String JSON_LABS = "labs";
    static final String JSON_RECITATIONS = "recitations";
    
    static final String JSON_OFFICE_HOURS_TAB = "officeHoursTab";
    static final String JSON_GRAD_TAS = "grad_tas";
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_NAME = "name";
    static final String JSON_EMAIL = "email";
    static final String JSON_TYPE = "type";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_START_TIME = "time";
    static final String JSON_DAY_OF_WEEK = "day";
    static final String JSON_MONDAY = "monday";
    static final String JSON_TUESDAY = "tuesday";
    static final String JSON_WEDNESDAY = "wednesday";
    static final String JSON_THURSDAY = "thursday";
    static final String JSON_FRIDAY = "friday";
    
    static final String JSON_SCHEDULE = "scheduleTab";
    static final String JSON_DATE = "date";
    static final String JSON_TOPIC = "topic";
    static final String JSON_MONTH = "month";
    static final String JSON_DAY = "day";
    static final String JSON_HWS = "hws";
    static final String JSON_HOLIDAYS = "holidays";
    static final String JSON_LINK = "link";
     
    public CourseSiteFiles(CourseSiteApp initApp) {
        app = initApp;
    }
    
     @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
         //CLEAR THE OLD DATA OUT
        CourseSiteData siteData = (CourseSiteData)data;
        siteData.reset();
        // LOAD THE JSON FILE WITH ALL THE DATA
        JsonObject json = loadJSONFile(filePath);
        
        CourseSiteTabData siteTabDataManager = (CourseSiteTabData)siteData.getSiteTabData();
        
        JsonObject siteTabJson = json.getJsonObject(JSON_SITE);
        
        String subject = siteTabJson.getString(JSON_SUBJECT);
        String number = siteTabJson.getString(JSON_NUMBER);
        String semester = siteTabJson.getString(JSON_SEMESTER);
        String year = siteTabJson.getString(JSON_YEAR);
        String title = siteTabJson.getString(JSON_TITLE);
        
        siteTabDataManager.initBannerBox(subject, number, semester, year, title);
        
        JsonArray jsonPagesArray = siteTabJson.getJsonArray(JSON_PAGES);
        ArrayList<String> selectedPages = new ArrayList<>();
        for (int i = 0; i < jsonPagesArray.size(); i++) {
            JsonObject page = jsonPagesArray.getJsonObject(i);
            selectedPages.add(page.getString(JSON_NAME));
        }
        
        siteTabDataManager.initPages(selectedPages);
        
        JsonObject instructorBoxJson = siteTabJson.getJsonObject(JSON_INSTRUCTOR);
        
        String name = instructorBoxJson.getString(JSON_INSTRUCTOR_NAME);
        String room = instructorBoxJson.getString(JSON_INSTRUCTOR_ROOM);
        String email = instructorBoxJson.getString(JSON_INSTRUCTOR_EMAIL);
        String homePage = instructorBoxJson.getString(JSON_INSTRUCTOR_HOME_PAGE);
        String hours = instructorBoxJson.getString(JSON_HOURS);
        
        siteTabDataManager.initInstructor(name, room, email, homePage, hours);
        
        SyllabusTabData syllabusTabDataManager = (SyllabusTabData)siteData.getSyllabusTabData();

        JsonObject syllabusTabJson = json.getJsonObject(JSON_SYLLABUS_TAB);
        
        String description = syllabusTabJson.getString(JSON_DESCRIPTION);
        
        JsonArray topicsJsonArray = syllabusTabJson.getJsonArray(JSON_TOPICS);
        String topics = "";
        
        ArrayList<String> topicsArray = new ArrayList<>();
        for (int i = 0; i < topicsJsonArray.size(); i++) {
            topicsArray.add(topicsJsonArray.getString(i));
        }
        
        for (String topic : topicsArray) {
            topics += topic;
            topics += "\n";
        }
        
        String prerequisites = syllabusTabJson.getString(JSON_PREREQUISITES);
     
        JsonArray outcomesJsonArray = syllabusTabJson.getJsonArray(JSON_OUTCOMES);
        String outcomes = "";
        
        ArrayList<String> outcomesArray = new ArrayList<>();
        for (int i = 0; i < outcomesJsonArray.size(); i++) {
            outcomesArray.add(outcomesJsonArray.getString(i));
        }
        
        for (String outcome : outcomesArray) {
            outcomes += outcome;
            outcomes += "\n";
        }
        
        String textbooks = syllabusTabJson.getString(JSON_TEXTBOOKS);
        String gradedComponents = syllabusTabJson.getString(JSON_GRADED_COMPONENTS);
        String gradedNote = syllabusTabJson.getString(JSON_GRADED_NOTE);
        String academicDishonesty = syllabusTabJson.getString(JSON_ACADEMIC_DISHONESTY);
        String specialAssistance = syllabusTabJson.getString(JSON_SPECIAL_ASSISTANCE);
        
        syllabusTabDataManager.initData(description, topics, prerequisites, outcomes, textbooks, gradedComponents, gradedNote, academicDishonesty, specialAssistance);
        
        MeetingTimesTabData meetingTabDataManager = (MeetingTimesTabData)siteData.getMeetingTimeData();
        
        JsonObject meetingTimesTabJson = json.getJsonObject(JSON_MEETING_TIMES);
        
        JsonArray lecturesJsonArray = meetingTimesTabJson.getJsonArray(JSON_LECTURES);
        
        for (int i = 0; i < lecturesJsonArray.size(); i++) {
            JsonObject lectureJson = lecturesJsonArray.getJsonObject(i);
            String section = lectureJson.getString(JSON_SECTION);
            String days = lectureJson.getString(JSON_DAYS);
            String time = lectureJson.getString(JSON_TIME);
            String lectureRoom = lectureJson.getString(JSON_ROOM);
            
            LectureMeetingType lecture = new LectureMeetingType(section,days,time, lectureRoom);
            
            meetingTabDataManager.addLecture(lecture);
        }
        
        JsonArray recitationsJsonArray = meetingTimesTabJson.getJsonArray(JSON_RECITATIONS);
        
        for (int i = 0; i < recitationsJsonArray.size(); i++) {
            JsonObject recitationJson = recitationsJsonArray.getJsonObject(i);
            String section = recitationJson.getString(JSON_SECTION);
            String days = recitationJson.getString(JSON_DAY_TIME);
            String lectureRoom = recitationJson.getString(JSON_LOCATION);
            String ta1 = recitationJson.getString(JSON_TA1);
            String ta2 = recitationJson.getString(JSON_TA2);
            
            RecitationLabMeetingType recitation = new RecitationLabMeetingType(section, days, lectureRoom, ta1, ta2);
            
            meetingTabDataManager.addRecitation(recitation);
        }
        
        JsonArray labsJsonArray = meetingTimesTabJson.getJsonArray(JSON_LABS);
        
        for (int i = 0; i < labsJsonArray.size(); i++) {
            JsonObject labJson = labsJsonArray.getJsonObject(i);
            String section = labJson.getString(JSON_SECTION);
            String days = labJson.getString(JSON_DAY_TIME);
            String lectureRoom = labJson.getString(JSON_ROOM);
            String ta1 = labJson.getString(JSON_TA1);
            String ta2 = labJson.getString(JSON_TA2);
            
            RecitationLabMeetingType lab = new RecitationLabMeetingType(section, days, lectureRoom, ta1, ta2);
            
            meetingTabDataManager.addLab(lab);
        }
        
        OfficeHoursData ohTabDataManager = (OfficeHoursData)siteData.getOfficeHoursData();
        
        JsonObject ohTabJson = json.getJsonObject(JSON_OFFICE_HOURS_TAB);
        
        String startHour = ohTabJson.getString(JSON_START_HOUR);
        String endHour = ohTabJson.getString(JSON_END_HOUR);
        ohTabDataManager.initHours(startHour, endHour);
        
        // LOAD ALL THE TAs
        loadTAs(ohTabDataManager, ohTabJson, JSON_GRAD_TAS);
        loadTAs(ohTabDataManager, ohTabJson, JSON_UNDERGRAD_TAS);

        // AND THEN ALL THE OFFICE HOURS
        JsonArray jsonOfficeHoursArray = ohTabJson.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String startTime = jsonOfficeHours.getString(JSON_START_TIME);
            DayOfWeek dow = DayOfWeek.valueOf(jsonOfficeHours.getString(JSON_DAY_OF_WEEK));
            String taName = jsonOfficeHours.getString(JSON_NAME);
            TeachingAssistantPrototype ta = ohTabDataManager.getTAWithName(taName);
            TimeSlot timeSlot = ohTabDataManager.getTimeSlot(startTime);
            System.out.println(ta.getName());
            timeSlot.toggleTA(dow, ta);
        }
        
        ScheduleTabData scheduleTabDataManager = (ScheduleTabData)siteData.getScheduleTabData();
        
        JsonObject scheduleTabJson = json.getJsonObject(JSON_SCHEDULE);
        
        JsonArray holdiayJsonArray = scheduleTabJson.getJsonArray(JSON_HOLIDAYS);
        
        for (int i = 0; i < holdiayJsonArray.size(); i++) {
            JsonObject scheduleItemJson = holdiayJsonArray.getJsonObject(i);
            String type = "Holiday";
            String month = scheduleItemJson.getString(JSON_MONTH);
            String day = scheduleItemJson.getString(JSON_DAY);
            String date = month + "/" + day + "/2018";
            String itemTitle = scheduleItemJson.getString(JSON_TITLE);
            String link = scheduleItemJson.getString(JSON_LINK);
            
            ScheduleItem item = new ScheduleItem(type, date, itemTitle, "", link);
            
            scheduleTabDataManager.addItem(item);
        }
        
        JsonArray lectureJsonArray = scheduleTabJson.getJsonArray(JSON_LECTURES);
        
        for (int i = 0; i < lectureJsonArray.size(); i++) {
            JsonObject lectureJson = lectureJsonArray.getJsonObject(i);
            String type = "Lecture";
            String month = lectureJson.getString(JSON_MONTH);
            String day = lectureJson.getString(JSON_DAY);
            String date = month + "/" + day + "/2018";
            String itemTitle = lectureJson.getString(JSON_TITLE);
            String topic = lectureJson.getString(JSON_TOPIC);
            String link = lectureJson.getString(JSON_LINK);
            
            ScheduleItem item = new ScheduleItem(type, date, itemTitle, topic, link);
            
            scheduleTabDataManager.addItem(item);
        }
        
        JsonArray recitationJsonArray = scheduleTabJson.getJsonArray(JSON_RECITATIONS);
        
        for (int i = 0; i < recitationJsonArray.size(); i++) {
            JsonObject recitationJson = recitationJsonArray.getJsonObject(i);
            String type = "Recitation";
            String month = recitationJson.getString(JSON_MONTH);
            String day = recitationJson.getString(JSON_DAY);
            String date = month + "/" + day + "/2018";
            String itemTitle = recitationJson.getString(JSON_TITLE);
            String topic = recitationJson.getString(JSON_TOPIC);
            String link = recitationJson.getString(JSON_LINK);
            
            ScheduleItem item = new ScheduleItem(type, date, itemTitle, topic, link);
            
            scheduleTabDataManager.addItem(item);
        }
        
        JsonArray hwJsonArray = scheduleTabJson.getJsonArray(JSON_HWS);
        
        for (int i = 0; i < hwJsonArray.size(); i++) {
            JsonObject hwJson = hwJsonArray.getJsonObject(i);
            String type = "HW";
            String month = hwJson.getString(JSON_MONTH);
            String day = hwJson.getString(JSON_DAY);
            String date = month + "/" + day + "/2018";
            String itemTitle = hwJson.getString(JSON_TITLE);
            String topic = hwJson.getString(JSON_TOPIC);
            String link = hwJson.getString(JSON_LINK);
            
            ScheduleItem item = new ScheduleItem(type, date, itemTitle, topic, link);
            
            scheduleTabDataManager.addItem(item);
        }
        
    }
    
    
    private void loadTAs(OfficeHoursData data, JsonObject json, String tas) {
        JsonArray jsonTAArray = json.getJsonArray(tas);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            TAType type;
            if (tas.equals("grad_tas")) {
                type = Graduate;
            } else {
                type = Undergraduate;
            }
            TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name, email, type);
            data.addTA(ta);
        }     
    }
    
     // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    
    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
	// GET THE DATA
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        
        //SITE TAB SAVE DATA
        CourseSiteTabData siteTabDataManager = (CourseSiteTabData)siteData.getSiteTabData();
        
        JsonObject favIcon = Json.createObjectBuilder() 
                .add(JSON_HREF, siteTabDataManager.getFavIconURL())
                .build();
        JsonObject navbar= Json.createObjectBuilder() 
                .add(JSON_HREF, "http://www.stonybrook.edu")
                .add(JSON_SRC, siteTabDataManager.getNavbarURL())
                .build();
        JsonObject leftFooter= Json.createObjectBuilder()
                .add(JSON_HREF, "http://www.cs.stonybrook.edu")
                .add(JSON_SRC, siteTabDataManager.getLeftFooterURL())
                .build();
        JsonObject rightFooter= Json.createObjectBuilder() 
                .add(JSON_HREF, "http://www.cs.stonybrook.edu")
                .add(JSON_SRC, siteTabDataManager.getRightFooterURL())
                .build();
        
        JsonObject siteTabStyleJson = Json.createObjectBuilder()
            .add(JSON_FAV_ICON, favIcon)
            .add(JSON_NAVBAR_IMAGE, navbar)
            .add(JSON_LEFT_FOOTER_IMAGE, leftFooter)
            .add(JSON_RIGHT_FOOTER_IMAGE, rightFooter)
            .build();
        
        JsonObject instructorJson = Json.createObjectBuilder()
            .add(JSON_INSTRUCTOR_NAME, siteTabDataManager.getInstructorName())
            .add(JSON_INSTRUCTOR_HOME_PAGE, siteTabDataManager.getHomepage())
            .add(JSON_INSTRUCTOR_EMAIL, siteTabDataManager.getEmail())
            .add(JSON_INSTRUCTOR_ROOM, siteTabDataManager.getRoom())
            .add(JSON_HOURS, siteTabDataManager.getHours())
            .build();
        
        JsonArrayBuilder pagesArrayBuilder = Json.createArrayBuilder();
        ArrayList<String> pagesList = siteTabDataManager.getPages();
            
        for (String page : pagesList) {
            String link = "";
            
            switch (page) {
                case "Home":
                    link = "index.html";
                    break;
                case "Syllabus":
                    link = "syllabus.html";
                    break;
                case "Schedule":
                    link = "schedule.html";
                    break;
                case "HWs":
                    link = "hws.html";
                    break;
            }
            
            JsonObject siteTabPagesJson = Json.createObjectBuilder()
                .add(JSON_NAME, page)
                .add(JSON_LINK ,link)
                .build();
            pagesArrayBuilder.add(siteTabPagesJson);
        }
            
        JsonArray pagesArray = pagesArrayBuilder.build();
        
        JsonObject siteTabJson = Json.createObjectBuilder()
            .add(JSON_SUBJECT, siteTabDataManager.getSubject())
            .add(JSON_NUMBER, siteTabDataManager.getNumber())
            .add(JSON_SEMESTER, siteTabDataManager.getSemester())
            .add(JSON_YEAR, siteTabDataManager.getYear())
            .add(JSON_TITLE, siteTabDataManager.getTitle())
            .add(JSON_LOGOS, siteTabStyleJson)   
            .add(JSON_INSTRUCTOR, instructorJson)
            .add(JSON_PAGES ,pagesArray)
            .build();
        
        
        SyllabusTabData syllabusTabDataManager = (SyllabusTabData)siteData.getSyllabusTabData();
        
        JsonArrayBuilder topicsArrayBuilder = Json.createArrayBuilder();
        String[] topicsList = syllabusTabDataManager.getTopicsTextArea();
            
        for (String topic : topicsList) {
            topicsArrayBuilder.add(topic);
        }    
        
        JsonArrayBuilder outcomesArrayBuilder = Json.createArrayBuilder();
        String[] outcomesList = syllabusTabDataManager.getOutcomesTextArea();
            
        for (String outcome : outcomesList) {
            outcomesArrayBuilder.add(outcome);
        } 
        
        JsonObject syllabusTabJson = Json.createObjectBuilder()
                .add(JSON_DESCRIPTION, syllabusTabDataManager.getDescriptionTextArea())
                .add(JSON_TOPICS, topicsArrayBuilder)
                .add(JSON_PREREQUISITES, syllabusTabDataManager.getPreRequisitesTextArea())
                .add(JSON_OUTCOMES, outcomesArrayBuilder)
                .add(JSON_TEXTBOOKS, syllabusTabDataManager.getTextbooksTextArea())
                .add(JSON_GRADED_COMPONENTS, syllabusTabDataManager.getGradedComponentsTextArea())
                .add(JSON_GRADED_NOTE, syllabusTabDataManager.getGradedNoteTextArea())
                .add(JSON_ACADEMIC_DISHONESTY, syllabusTabDataManager.getAcademicDishonestyTextArea())
                .add(JSON_SPECIAL_ASSISTANCE, syllabusTabDataManager.getSpecialAssistanceTextArea())
                .build();
        
        MeetingTimesTabData meetingTimesDataManager = (MeetingTimesTabData)siteData.getMeetingTimeData();
        
        JsonArrayBuilder lecturesArrayBuilder = Json.createArrayBuilder();

        Iterator<LectureMeetingType> lectureIterator = meetingTimesDataManager.LectureMeetingIterator();
         while (lectureIterator.hasNext()) {
             
            LectureMeetingType lecture = lectureIterator.next();
	    JsonObject lectureJson = Json.createObjectBuilder()
		.add(JSON_SECTION, lecture.getSection())
		.add(JSON_DAYS, lecture.getDay())
                .add(JSON_TIME, lecture.getTime())
                .add(JSON_ROOM, lecture.getRoom())
                .build();

            lecturesArrayBuilder.add(lectureJson);
         }
        
        JsonArrayBuilder recitationsArrayBuilder = Json.createArrayBuilder();

        Iterator<RecitationLabMeetingType> recitationIterator = meetingTimesDataManager.RecitationMeetingIterator();
         while (recitationIterator.hasNext()) {
             
            RecitationLabMeetingType recitation = recitationIterator.next();
	    JsonObject taJson = Json.createObjectBuilder()
		.add(JSON_SECTION, recitation.getSection())
		.add(JSON_DAY_TIME, recitation.getDayTime())
                .add(JSON_LOCATION, recitation.getRoom())
                .add(JSON_TA1, recitation.getFirstTA())
                .add(JSON_TA2, recitation.getSecondTA())
                .build();

            recitationsArrayBuilder.add(taJson);
         }
        
        JsonArrayBuilder labsArrayBuilder = Json.createArrayBuilder();

        Iterator<RecitationLabMeetingType> labIterator = meetingTimesDataManager.LabMeetingIterator();
         while (labIterator.hasNext()) {
             
            RecitationLabMeetingType lab = labIterator.next();
	    JsonObject taJson = Json.createObjectBuilder()
		.add(JSON_SECTION, lab.getSection())
		.add(JSON_DAY_TIME, lab.getDayTime())
                .add(JSON_LOCATION, lab.getRoom())
                .add(JSON_TA1, lab.getFirstTA())
                .add(JSON_TA2, lab.getSecondTA())
                .build();

            labsArrayBuilder.add(taJson);
         }
         
        JsonObject meetingTimesTabJson = Json.createObjectBuilder()
                .add(JSON_LECTURES, lecturesArrayBuilder)
                .add(JSON_LABS, labsArrayBuilder)
                .add(JSON_RECITATIONS, recitationsArrayBuilder)
                .build();
        
        OfficeHoursData officeHoursTabDataManager = (OfficeHoursData)siteData.getOfficeHoursData();

	JsonArrayBuilder gradTAsArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder undergradTAsArrayBuilder = Json.createArrayBuilder();
	Iterator<TeachingAssistantPrototype> tasIterator = officeHoursTabDataManager.teachingAssistantsIterator();
        while (tasIterator.hasNext()) {
            TeachingAssistantPrototype ta = tasIterator.next();
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail())
                    .build();
            if (ta.getType().equals(TAType.Graduate.toString()))
                gradTAsArrayBuilder.add(taJson);
            else
                undergradTAsArrayBuilder.add(taJson);
	}
        JsonArray gradTAsArray = gradTAsArrayBuilder.build();
	JsonArray undergradTAsArray = undergradTAsArrayBuilder.build();

	JsonArrayBuilder officeHoursArrayBuilder = Json.createArrayBuilder();
        Iterator<TimeSlot> timeSlotsIterator = officeHoursTabDataManager.officeHoursIterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            for (int i = 0; i < DayOfWeek.values().length; i++) {
                DayOfWeek dow = DayOfWeek.values()[i];
                tasIterator = timeSlot.getTAsIterator(dow);
                while (tasIterator.hasNext()) {
                    TeachingAssistantPrototype ta = tasIterator.next();
                    JsonObject tsJson = Json.createObjectBuilder()
                        .add(JSON_START_TIME, timeSlot.getStartTime().replace(":", "_"))
                        .add(JSON_DAY_OF_WEEK, dow.toString())
                        .add(JSON_NAME, ta.getName())
                            .build();
                    officeHoursArrayBuilder.add(tsJson);
                }
            }
	}
	JsonArray officeHoursArray = officeHoursArrayBuilder.build();
        
	
	JsonObject officeHoursTabJson = Json.createObjectBuilder()
		.add(JSON_START_HOUR, "" + officeHoursTabDataManager.getStartHour())
		.add(JSON_END_HOUR, "" + officeHoursTabDataManager.getEndHour())
                .add(JSON_INSTRUCTOR, instructorJson)
                .add(JSON_GRAD_TAS, gradTAsArray)
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, officeHoursArray)
		.build();
        
        ScheduleTabData scheduleTabdataManager = (ScheduleTabData)siteData.getScheduleTabData();
        
        
        
        JsonArrayBuilder holidaysArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder lecturesTAsArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder recitationArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder hwsArrayBuilder = Json.createArrayBuilder();
        
	Iterator<ScheduleItem> scheduleItemIterator = scheduleTabdataManager.ScheduleItemIterator();
        while (tasIterator.hasNext()) {
            ScheduleItem item = scheduleItemIterator.next();
            if (item.getType().equals("Holiday")) {
                JsonObject itemJson = Json.createObjectBuilder()
		    .add(JSON_MONTH, scheduleTabdataManager.getItemMonth(item))
                    .add(JSON_DAY, scheduleTabdataManager.getItemDay(item))
                    .add(JSON_TITLE, item.getTitle())
                    .add(JSON_LINK, item.getLink())
                    .build();
                holidaysArrayBuilder.add(itemJson);
            } else {
                JsonObject itemJson = Json.createObjectBuilder()
		    .add(JSON_MONTH, scheduleTabdataManager.getItemMonth(item))
                    .add(JSON_DAY, scheduleTabdataManager.getItemDay(item))
                    .add(JSON_TITLE, item.getTitle())
                    .add(JSON_TOPIC, item.getTopic())
                    .add(JSON_LINK, item.getLink())
                    .build();
                
                 if (item.getType().equals("Lecture")) {
                    lecturesTAsArrayBuilder.add(itemJson);
                 } else if (item.getType().equals("Recitation")) {
                    recitationArrayBuilder.add(itemJson);
                 } else if (item.getType().equals("HW")) {
                    hwsArrayBuilder.add(itemJson);
                 } 
            }             
	}
        
        JsonArray holidaysArray = holidaysArrayBuilder.build();
        JsonArray lecturesArray = lecturesTAsArrayBuilder.build();
	JsonArray recitationArray = recitationArrayBuilder.build();
        JsonArray hwsArray = hwsArrayBuilder.build();
        
        JsonObject scheduleJson = Json.createObjectBuilder()
                .add(JSON_HOLIDAYS, holidaysArray)
                .add(JSON_LECTURES, lecturesArray)
                .add(JSON_RECITATIONS, recitationArray)
                .add(JSON_HWS, hwsArray)
                .build();
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(JSON_SITE, siteTabJson)
                .add(JSON_SYLLABUS_TAB, syllabusTabJson)
                .add(JSON_MEETING_TIMES, meetingTimesTabJson)
                .add(JSON_OFFICE_HOURS_TAB, officeHoursTabJson)
                .add(JSON_SCHEDULE, scheduleJson)
		.build();
	
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
     @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        CourseSiteData siteData =  (CourseSiteData)app.getDataComponent();
        
        CourseSiteTabData siteTabDataManager = (CourseSiteTabData)siteData.getSiteTabData();

        String exportPath = siteTabDataManager.getExportDir();
        
        exportPath = exportPath.replace("/index_html", "");
        
        //CREATE FILE STRUCTURE
        new File(exportPath + "/js").mkdirs();
        new File(exportPath + "/css").mkdirs();
        new File(exportPath + "/images").mkdirs();
        
        //ADD IMAGES
        Path newFaviconPath = Paths.get(exportPath + "/images/SBUWhiteShieldIcon.png");
        Path currentFaviconPath = Paths.get(siteTabDataManager.getFavIconURL());
        Files.copy(currentFaviconPath, newFaviconPath, REPLACE_EXISTING);
        
        Path newNavbarPath = Paths.get(exportPath + "/images/SBUDarkRedShieldLogo.png");
        Path currentNavbarPath = Paths.get(siteTabDataManager.getNavbarURL());
        Files.copy(currentNavbarPath, newNavbarPath, REPLACE_EXISTING);
        
        Path newtBottomLeftPath = Paths.get(exportPath + "/images/SBUWhiteShieldLogo.png");
        Path currentBottomLeftPath = Paths.get(siteTabDataManager.getLeftFooterURL());
        Files.copy(currentBottomLeftPath, newtBottomLeftPath, REPLACE_EXISTING);
        
        Path newBootmRightPath = Paths.get(exportPath + "/images/SBUCSLogo.png");
        Path currenBottomRightPath = Paths.get(siteTabDataManager.getRightFooterURL());
        Files.copy(currenBottomRightPath, newBootmRightPath, REPLACE_EXISTING);
        
        //ADD CSS
        Path newPath = Paths.get(exportPath + "/css/sea_wolf.css");
        Path currentPath = Paths.get("./export/requiredFiles/css/sea_wolf.css");
        Files.copy(currentPath, newPath, REPLACE_EXISTING);
        
        newPath = Paths.get(exportPath + "/css/course_homepage_layout.css");
        currentPath = Paths.get("./export/requiredFiles/css/course_homepage_layout.css");
        Files.copy(currentPath, newPath, REPLACE_EXISTING);
        
        //ADD JS
        newPath = Paths.get(exportPath + "/js/HWsBuilder.js");
        currentPath = Paths.get("./export/requiredFiles/js/HWsBuilder.js");
        Files.copy(currentPath, newPath, REPLACE_EXISTING);
        
        newPath = Paths.get(exportPath + "/js/jquery.min.js");
        currentPath = Paths.get("./export/requiredFiles/js/jquery.min.js");
        Files.copy(currentPath, newPath, REPLACE_EXISTING);
        
        newPath = Paths.get(exportPath + "/js/OfficeHoursBuilder.js");
        currentPath = Paths.get("./export/requiredFiles/js/OfficeHoursBuilder.js");
        Files.copy(currentPath, newPath, REPLACE_EXISTING);
        
        newPath = Paths.get(exportPath + "/js/PageBuilder.js");
        currentPath = Paths.get("./export/requiredFiles/js/PageBuilder.js");
        Files.copy(currentPath, newPath, REPLACE_EXISTING);
        
        newPath = Paths.get(exportPath + "/js/ScheduleBuilder.js");
        currentPath = Paths.get("./export/requiredFiles/js/ScheduleBuilder.js");
        Files.copy(currentPath, newPath, REPLACE_EXISTING);
        
        newPath = Paths.get(exportPath + "/js/SectionsBuilder.js");
        currentPath = Paths.get("./export/requiredFiles/js/SectionsBuilder.js");
        Files.copy(currentPath, newPath, REPLACE_EXISTING);
        
        newPath = Paths.get(exportPath + "/js/SyllabusBuilder.js");
        currentPath = Paths.get("./export/requiredFiles/js/SyllabusBuilder.js");
        Files.copy(currentPath, newPath, REPLACE_EXISTING);
        
        //ADD HTML
        Path newIndexHTMLPath = Paths.get(exportPath + "/index.html");
        Path currentIndexHTMLPath = Paths.get("./export/requiredFiles/html/index.html");
        Files.copy(currentIndexHTMLPath, newIndexHTMLPath, REPLACE_EXISTING);
        
        Path newHWHTMLPath = Paths.get(exportPath + "/hws.html");
        Path currentHWHTMLPath = Paths.get("./export/requiredFiles/html/hws.html");
        Files.copy(currentHWHTMLPath, newHWHTMLPath, REPLACE_EXISTING);
        
        Path newScheduleHTMLPath = Paths.get(exportPath + "/schedule.html");
        Path currentScheduleHTMLPath = Paths.get("./export/requiredFiles/html/schedule.html");
        Files.copy(currentScheduleHTMLPath, newScheduleHTMLPath, REPLACE_EXISTING);
        
        Path newSyllabusHTMLPath = Paths.get(exportPath + "/syllabus.html");
        Path currentSyllabusHTMLPath = Paths.get("./export/requiredFiles/html/syllabus.html");
        Files.copy(currentSyllabusHTMLPath, newSyllabusHTMLPath, REPLACE_EXISTING);
        
        //ADD JSON
        JsonObject json = loadJSONFile("./work/" + filePath);
         
        JsonObject siteTabJson = json.getJsonObject(JSON_SITE);
        
       // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(siteTabJson);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(exportPath +"/js/PageData.Json");
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(siteTabJson);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(exportPath +"/js/PageData.Json");
	pw.write(prettyPrinted);
	pw.close();
                
        JsonObject syllabusTabJson = json.getJsonObject(JSON_SYLLABUS_TAB);
        
        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	writerFactory = Json.createWriterFactory(properties);
	sw = new StringWriter();
	jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(syllabusTabJson);
	jsonWriter.close();

	// INIT THE WRITER
	os = new FileOutputStream(exportPath +"/js/SyllabusData.Json");
	jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(syllabusTabJson);
	prettyPrinted = sw.toString();
	pw = new PrintWriter(exportPath +"/js/SyllabusData.Json");
	pw.write(prettyPrinted);
	pw.close();
        
        JsonObject meetingTimesTabJson = json.getJsonObject(JSON_MEETING_TIMES);
        
        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	writerFactory = Json.createWriterFactory(properties);
	sw = new StringWriter();
	jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(meetingTimesTabJson);
	jsonWriter.close();

	// INIT THE WRITER
	os = new FileOutputStream(exportPath +"/js/SectionsData.Json");
	jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(meetingTimesTabJson);
	prettyPrinted = sw.toString();
	pw = new PrintWriter(exportPath +"/js/SectionsData.Json");
	pw.write(prettyPrinted);
	pw.close();
        
        JsonObject officeHoursTabJson = json.getJsonObject(JSON_OFFICE_HOURS_TAB);
        
        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	writerFactory = Json.createWriterFactory(properties);
	sw = new StringWriter();
	jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(officeHoursTabJson);
	jsonWriter.close();

	// INIT THE WRITER
	os = new FileOutputStream(exportPath +"/js/OfficeHoursData.Json");
	jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(officeHoursTabJson);
	prettyPrinted = sw.toString();
	pw = new PrintWriter(exportPath +"/js/OfficeHoursData.Json");
	pw.write(prettyPrinted);
	pw.close();
        
        JsonObject sscheduleTabJson = json.getJsonObject(JSON_SCHEDULE);
        
        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	writerFactory = Json.createWriterFactory(properties);
	sw = new StringWriter();
	jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(sscheduleTabJson);
	jsonWriter.close();

	// INIT THE WRITER
	os = new FileOutputStream(exportPath +"/js/ScheduleData.Json");
	jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(sscheduleTabJson);
	prettyPrinted = sw.toString();
	pw = new PrintWriter(exportPath +"/js/ScheduleData.Json");
	pw.write(prettyPrinted);
	pw.close();
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        props.addProperty(APP_EXPORT_PATH, exportPath + "/");
        
    }
}
