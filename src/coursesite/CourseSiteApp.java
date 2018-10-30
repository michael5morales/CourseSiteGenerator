/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesite;

import djf.AppTemplate;
import djf.components.AppClipboardComponent;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import djf.components.AppWorkspaceComponent;
import java.util.Locale;
import coursesite.data.CourseSiteData;
import coursesite.files.CourseSiteFiles;
import coursesite.workspace.CourseSiteWorkspace;
import static javafx.application.Application.launch;

/**
 *
 * @author Michael
 */
public class CourseSiteApp extends AppTemplate{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	Locale.setDefault(Locale.US);
	launch(args);
    }

    @Override
    public AppClipboardComponent buildClipboardComponent(AppTemplate app) {
        return null;
    }

    @Override
    public AppDataComponent buildDataComponent(AppTemplate app) {
        return new CourseSiteData(this);
    }

    @Override
    public AppFileComponent buildFileComponent() {
        return new CourseSiteFiles(this);
    }

    @Override
    public AppWorkspaceComponent buildWorkspaceComponent(AppTemplate app) {
        return new CourseSiteWorkspace(this);        
    }
    
}
