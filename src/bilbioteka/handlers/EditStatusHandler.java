 
package bilbioteka.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import biblioteka.book.Book;
import biblioteka.interfaces.IModelData;
import biblioteka.views.EditStatusDialog;
import biblioteka.views.NewBookDialog;
import biblioteka.views.PartView;

public class EditStatusHandler {
	
	@Execute
	public void execute(MPart mpart, Shell shell) {
		
		PartView partv = (PartView) mpart.getObject();
		IModelData dataM = partv.getModelImplementation();

		EditStatusDialog dialog = new EditStatusDialog(shell);

                // get the new values from the dialog
                if (dialog.open() == Window.OK) {
                        

                }
        
	}
}