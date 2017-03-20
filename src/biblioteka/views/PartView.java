 
package biblioteka.views;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import biblioteka.book.Book;
import biblioteka.interfaces.IModelData;
import bilbioteka.test.MockBook;
import bilbioteka.test.XMLBook;


public class PartView {

	private CheckboxTableViewer viewer;
	private IModelData dataModel;
	private Button btnDeleteSelected;
	private Button btnAddNew;
	private Combo comboBox;
	@PostConstruct
	public void postConstruct(Composite parent,ESelectionService service) {
	    System.out.println("JEST");
		parent.setLayout(new GridLayout(2, false));
	    
	    comboBox = new Combo(parent,SWT.READ_ONLY);
	    String items[] = {"Implementacja XML","Mocked-Database"};
	    comboBox.setItems(items);
	    comboBox.select(1);
	    
	    viewer = CheckboxTableViewer.newCheckList(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);	    
	    viewer.setContentProvider(new ArrayContentProvider());
	    
	    
	    Table table = viewer.getTable();
	    table.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 3, 1));
	    table.setLinesVisible(true);
	    
	    
	    TableViewerColumn  viewerColumn1 = new TableViewerColumn(viewer,SWT.LEFT);
	    TableColumn column1 = viewerColumn1.getColumn();
	    column1.setText("Title");
	    column1.setWidth(150);
	    viewerColumn1.setLabelProvider(new ColumnLabelProvider(){
	    	@Override
	    	public String getText(Object element)
	    	{
				return ((Book)element).getTitle();
	    		
	    		
	    	}
	    });
	    TableViewerColumn viewerColumn2 = new TableViewerColumn(viewer, SWT.LEFT);
	    TableColumn column2 = viewerColumn2.getColumn();
	    column2.setText("Authors");
	    column2.setWidth(200);
	    viewerColumn2.setLabelProvider(new ColumnLabelProvider(){
	    	@Override
	    	public String getText(Object element)
	    	{
				return ((Book)element).getAuthors();
	    		
	    		
	    	}
	    });
	    TableViewerColumn viewerColumn3 = new TableViewerColumn(viewer, SWT.LEFT);
	    TableColumn column3 = viewerColumn3.getColumn();
	    column3.setText("Publication Year");
	    column3.setWidth(150);
	    viewerColumn3.setLabelProvider(new ColumnLabelProvider(){
	    	@Override
	    	public String getText(Object element)
	    	{
				return ((Book)element).getPublicationYear();
	    		
	    		
	    	}
	    });
	    TableViewerColumn viewerColumn4 = new TableViewerColumn(viewer, SWT.LEFT);
	    TableColumn column4 = viewerColumn4.getColumn();
	    column4.setText("Status");
	    column4.setWidth(180);
	    viewerColumn4.setLabelProvider(new ColumnLabelProvider(){
	    	@Override
	    	public String getText(Object element)
	    	{
				return ((Book)element).getStatus();
	    		
	    		
	    	}
	    });
	    
	    
	    table.setHeaderVisible(true);
	    
	    
	    GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 2;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        viewer.getControl().setLayoutData(gridData);
	    
	    /****************** Buttons ******************/
	    
	    //btnDeleteSelected = new Button(parent, SWT.NONE);
	   
	    //btnDeleteSelected.setText("Delete selected");
	    
	   // btnChangeStatus = new Button(parent, SWT.NONE);
	   // btnChangeStatus.setText("Change status");
	   // btnAddNew = new Button(parent, SWT.NONE);
	    //btnAddNew.setText("Add new");
	    
	    
	    comboBox.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
			    int modelChose = comboBox.getSelectionIndex();
			    if(modelChose == 0)
			    {
				    //viewer.setInput(dataModel.getAllBooks());
			    	try {

			    		dataModel = new XMLBook("/home/dariusz/ProjecsWorkspace/book.xml");
			    		viewer.setInput(dataModel.getAllBooks());
			    	} catch (JAXBException e1) {
						e1.printStackTrace();
					}
	
			    }else if(modelChose == 1)
			    {
			    	dataModel = new MockBook();
				    viewer.setInput(dataModel.getAllBooks());
	
			    }
			
	    	}
			
		});
	    comboBox.notifyListeners(SWT.Selection,new Event());
	 
	    
	    
	    /* btnDeleteSelected.addSelectionListener(new SelectionAdapter() {
    	@Override
    	public void widgetSelected(SelectionEvent e) {
    		Object[] books = viewer.getCheckedElements();
    		
    		System.out.println(books[0].toString());
    		System.out.println(comboBox.getSelectionIndex());
    		
    	}
    });*/

	}
	
	public void addNewBookListener(SelectionAdapter adapter)
	{
		btnAddNew.addSelectionListener(adapter);
	}
	
	public void deleteBookListener(SelectionAdapter adapter)
	{
		btnDeleteSelected.addSelectionListener(adapter);
	}
	
	public int getSelectedModelImplementation()
	{
		return comboBox.getSelectionIndex();
	}
	
	public IModelData getModelImplementation()
	{
		return this.dataModel;
	}
	public int[] getSelectedBooksId()
	{
		Object[] bId = viewer.getCheckedElements();
		int[] id = new int[bId.length];
		
		for(int i =0 ; i < bId.length; i++)
			id[i] = Integer.parseInt(bId[i].toString());
		return id;
		
	}
	public void refreshView()
    {
		viewer.setInput(this.dataModel.getAllBooks());
    }
	
	@Focus
	public void setFocus()
	{
		comboBox.setFocus();
	}
	
	
	
}