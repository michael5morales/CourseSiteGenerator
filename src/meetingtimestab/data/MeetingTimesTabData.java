/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingtimestab.data;

import coursesite.CourseSiteApp;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import static meetingtimestab.MeetingTimesTabPropertyType.*;

/**
 *
 * @author Michael
 */
public class MeetingTimesTabData implements AppDataComponent{
    CourseSiteApp app;
    
    ObservableList<RecitationLabMeetingType> recitations;
    ObservableList<RecitationLabMeetingType> labs;
    ObservableList<LectureMeetingType> lectures;
    
    public MeetingTimesTabData(CourseSiteApp initApp) {
        app = initApp;
        AppGUIModule gui = app.getGUIModule();
        
        TableView<RecitationLabMeetingType> recitationsTableView = (TableView)gui.getGUINode(MEETING_TIMES_TAB_RECITATIONS_TABLE_VIEW);
        recitations = recitationsTableView.getItems();
        
        TableView<RecitationLabMeetingType> labsTableView = (TableView)gui.getGUINode(MEETING_TIMES_TAB_LABS_TABLE_VIEW);
        labs = labsTableView.getItems();
        
        TableView<LectureMeetingType> lecturesTableView = (TableView)gui.getGUINode(MEETING_TIMES_TAB_LECTURES_TABLE_VIEW);
        lectures = lecturesTableView.getItems();
    }
    
    public void addLecture(LectureMeetingType lecture) {
        lectures.add(lecture);
    }
    
    public void addRecitation(RecitationLabMeetingType recitation) {
        recitations.add(recitation);
    }
    
    public void addLab(RecitationLabMeetingType lab) {
        labs.add(lab);
    }
    
    public void removeLecture(LectureMeetingType lecture) { 
        for (int i = 0; i < lectures.size(); i++) {
            if (lectures.get(i) == lecture) {
                lectures.remove(i);
                break;
            }
        }    
    }
        
    public void removeRecitation(RecitationLabMeetingType recitation) { 
        for (int i = 0; i < recitations.size(); i++) {
            if (recitations.get(i) == recitation) {
                recitations.remove(i);
                break;
            }
        }    
    }
    
    public void removeLab(RecitationLabMeetingType lab) { 
        for (int i = 0; i < labs.size(); i++) {
            if (labs.get(i) == lab) {
                labs.remove(i);
                break;
            }
        }    
    }
    
    @Override
    public void reset() {
        recitations.clear();
        labs.clear();
        lectures.clear();
    }
    
    public Iterator<RecitationLabMeetingType> LabMeetingIterator() {
        return new LabIterator();
    }
    
    public Iterator<RecitationLabMeetingType> RecitationMeetingIterator() {
        return new RecitationIterator();
    }
    
    public Iterator<LectureMeetingType> LectureMeetingIterator() {
        return new LectureIterator();
    }
    
    private class LectureIterator implements Iterator {
        Iterator lectureIt = lectures.iterator();

        public LectureIterator() {}
        
        @Override
        public boolean hasNext() {
            if (lectureIt.hasNext())
                return true;
            else
                return false;                
        }

        @Override
        public Object next() {
            if (lectureIt.hasNext())
                return lectureIt.next();
            else
                return null;
        }
    }
    
    private class RecitationIterator implements Iterator {
        Iterator recitationIt = recitations.iterator();

        public RecitationIterator() {}
        
        @Override
        public boolean hasNext() {
            if (recitationIt.hasNext())
                return true;
            else
                return false;                
        }

        @Override
        public Object next() {
            if (recitationIt.hasNext())
                return recitationIt.next();
            else
                return null;
        }
    }
    
   private class LabIterator implements Iterator {
        Iterator labIt = labs.iterator();

        public LabIterator() {}
        
        @Override
        public boolean hasNext() {
            if (labIt.hasNext())
                return true;
            else
                return false;                
        }

        @Override
        public Object next() {
            if (labIt.hasNext())
                return labIt.next();
            else
                return null;
        }
    }
}
