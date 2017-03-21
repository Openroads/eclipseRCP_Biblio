 
package bilbioteka.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import biblioteka.interfaces.IModelData;
import biblioteka.views.EditStatusDialog;
import biblioteka.views.PartView;

public class EditStatusHandler {
	
	@Execute
	public void execute(MPart mpart, Shell shell) {
		
		PartView partv = (PartView) mpart.getObject();
		IModelData dataM = partv.getModelImplementation();
		
		int[] idToChangeStatus = partv.getSelectedBooksId();
		if(idToChangeStatus.length<=0)
		{
			MessageDialog.openInformation(shell, "Status", "Select positions to change status");
		}else
		{
		
			
			EditStatusDialog dialog = new EditStatusDialog(shell);
	
	                // get the new values from the dialog
	                if (dialog.open() == Window.OK) {
	                	dataM.setBookStatus(idToChangeStatus, dialog.getStatus());
	                	partv.refreshView();
	                	partv.unselectView();
	                }
		}
	}
}