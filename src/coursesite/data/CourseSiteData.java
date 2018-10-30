/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesite.data;

import coursesite.CourseSiteApp;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;

/**
 *
 * @author Michael
 */
public class CourseSiteData implements AppDataComponent{
    CourseSiteApp app;
    
     public CourseSiteData(CourseSiteApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
        AppGUIModule gui = app.getGUIModule();
     }
     
     /**
     * Called each time new work is created or loaded, it resets all data
     * and data structures such that they can be used for new values.
     */
     @Override
    public void reset() {
        
    }
}
