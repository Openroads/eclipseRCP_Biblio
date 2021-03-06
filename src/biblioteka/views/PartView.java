 
package biblioteka.views;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import biblioteka.book.Book;
import biblioteka.interfaces.IModelData;
import bilbioteka.test.MockBook;



public class PartView {

	private CheckboxTableViewer viewer;
	private IModelData dataModel;
	private Button selectAll;
	private Button btnAddNew;
	private Button btnChangeStatus;
	private Combo comboBox;
	private Job job;
	private Job jobChangeSt;
	@Inject 
	UISynchronize sync;
	
	@PostConstruct
	public void postConstruct(Composite parent,ESelectionService service,Shell shell) {
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
	    
	    selectAll = new Button(parent, SWT.NONE);
	   
	    selectAll.setText("Select All");
	    selectAll.setLayoutData( new GridData(SWT.LEFT,SWT.CENTER,true,false,1,1));
	    selectAll.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		viewer.setAllChecked(true);
	    	}
	    	});
	    
	   btnChangeStatus = new Button(parent, SWT.NONE);
	   btnChangeStatus.setText("Change status thread");
	   btnChangeStatus.setVisible(false);
	   btnChangeStatus.setLayoutData( new GridData(SWT.LEFT,SWT.CENTER,true,false,1,1));
	   btnChangeStatus.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		if(jobChangeSt==null)
	    			jobChangeSt = createChangeStatusJob((MockBook)dataModel);
	    		jobChangeSt.schedule();
	    	}
	    	});
	    
	    
	   File file = new File("/home/dariusz/ProjecsWorkspace/EclipseRCP/Biblioteka/bin/biblioteka/views/config");
	    
	    try {
			String className = new Scanner(file).nextLine();
			System.out.println(className);
			
			Class clas = Class.forName(className);
			
			this.dataModel = (IModelData) clas.newInstance();
			
			viewer.setInput(this.dataModel.getAllBooks());
			
			job =runTrackedThread(shell);
    		job.schedule();
			
		
	    } catch (FileNotFoundException e2) {
			System.out.println("File not found"+ e2);
	    	e2.printStackTrace();
		} catch (ClassNotFoundException e1) {
			System.out.println("Class not found "+ e1);
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			System.out.println("Instantation" +e1);
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			System.out.println("Illegal Acces" +e1);
			e1.printStackTrace();
		}
	   
	   
	   
	    comboBox.addSelectionListener(new SelectionAdapter() {
	    	
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
			    int modelChose = comboBox.getSelectionIndex();

			    if(modelChose == 0)
			    {
			    	btnChangeStatus.setVisible(false);
			    	/*try {
			    			dataModel = new XMLBook("/home/dariusz/ProjecsWorkspace/book.xml");
				    		
				    		viewer.setInput(dataModel.getAllBooks());
				    		
			    	} catch (JAXBException e1) {
						e1.printStackTrace();
					}*/
	
			    }else if(modelChose == 1)
			    {
			    	
			    	btnChangeStatus.setVisible(true);
			    	/*dataModel = new MockBook();
			    	viewer.setInput(dataModel.getAllBooks());
			    	
			    	if(job == null){	
			    		
				    	job =runTrackedThread(shell);
			    		job.schedule();
					    
			    	}*/
	
			    }
			
	    	}
			
		});
	    	//comboBox.notifyListeners(SWT.Selection,new Event());
	    
	}
	public void addNewBookListener(SelectionAdapter adapter)
	{
		btnAddNew.addSelectionListener(adapter);
	}
	
	public void deleteBookListener(SelectionAdapter adapter)
	{
		selectAll.addSelectionListener(adapter);
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
	public void unselectView()
	{
		viewer.setAllChecked(false);
	}
	@Focus
	public void setFocus()
	{
		comboBox.setFocus();
	}
	

	private Job runTrackedThread(Shell shell) {
		
	    Job job = new Job("Biblioteka checkstatus") {
	        
	    	@Override
	        protected IStatus run(IProgressMonitor monitor) {
	            
	        	while(true)
	        	{
	        		Integer[] id = dataModel.checkBooksStatus();
	        		
		        	if(id.length == 0)
		        	{
		        		try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
		        	}else
		        	{
		        	
		             sync.asyncExec(new Runnable() {
		                  @Override
		                  public void run() {
		                	  	Book book = null;
		                	  	  for(int i =0; i < id.length ; i++)
		                	  	  {
		                	  		 book= (Book)dataModel.getBookById(id[i]);
			                	  	  
				                      MessageDialog.openInformation(shell, "Message", "Book "+ book.getTitle() + " has changed status for \""+ book.getStatus()+"\"");
		                	  	  }
		                	  	  
			                      refreshView();
	
		                    }
		                });
		        	}
		             
	        	}
	        	//return Status.OK_STATUS;
	        	
	        }
	        
	    };

	    
		return job;
	
	}
	
	private Job createChangeStatusJob(MockBook dm) {
		
	    Job job = new Job("Biblioteka changestatus") {
	        
	    	@Override
	        protected IStatus run(IProgressMonitor monitor) {
	      
		             sync.asyncExec(new Runnable() {
		                  @Override
		                  public void run() {
		                 
			                  dm.changeRandomStatus();
	
		                    }
		                });
		        	
		        	return Status.OK_STATUS;
	        	}
	    };
	    
		return job;
	
	}
	
}