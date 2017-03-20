 
package bilbioteka.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import biblioteka.book.Book;
import biblioteka.interfaces.IModelData;
import biblioteka.views.NewBookDialog;
import biblioteka.views.PartView;

public class AddNewBookHandler {
	
	@Execute
	public void execute(MPart mpart, Shell shell) {
		PartView partv = (PartView) mpart.getObject();
		IModelData dataM = partv.getModelImplementation();

		NewBookDialog dialog = new NewBookDialog(shell);

                // get the new values from the dialog
                if (dialog.open() == Window.OK) {
                        
                        int id  = dialog.getId();
                        String title = dialog.getTitle();
                        String authors = dialog.getAuthors();
                        String publYear = dialog.getPublYear();
                        Book book = new Book(id,title,authors,publYear,dialog.getStatus());
                        dataM.addNewBook(book);
                        System.out.println(title);
                        partv.refreshView();
                       // System.out.println(author);
                }
        
	}
		
}