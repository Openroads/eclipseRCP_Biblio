 
package bilbioteka.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import biblioteka.interfaces.IModelData;
import biblioteka.views.PartView;

public class DeleteSelectedHandler {
	
	
	@Execute
	public void execute(MPart mpart) {
		PartView pView = (PartView) mpart.getObject();
		IModelData dataModel = pView.getModelImplementation();
		
		int[] idToDelete= pView.getSelectedBooksId();
		
		dataModel.deleteBook(idToDelete);
		System.out.println(dataModel.getAllBooks());
		pView.refreshView();
		
	}
	@CanExecute
	public boolean checkIfSelected(MPart mpart){
		//PartView pView = (PartView) mpart.getObject();
		
		/*System.out.println(pView.getSelectedBooksId().length);
		if(pView.getSelectedBooksId().length== 0 )
			return false;*/
		
		return true;
	}
		
}