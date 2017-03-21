package biblioteka.views;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;

public class EditStatusDialog extends Dialog {
	private Combo comboStatus;
	private String choose;
	@Override
	protected Control createDialogArea(Composite parent) {	
		
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		comboStatus = new Combo(container,SWT.READ_ONLY | SWT.BORDER);
		comboStatus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        String items[] = {"Wypożyczona","Do wypożyczenia"};
        comboStatus.setItems(items);
        comboStatus.select(1);
		return container;
	}	
	public EditStatusDialog(Shell parentShell) {
		super(parentShell);

	}
	
    @Override
    protected boolean isResizable() {
        return true;
    }

   
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
            createButton(parent, IDialogConstants.OK_ID, "Set", true);
            createButton(parent, IDialogConstants.CANCEL_ID,
                            IDialogConstants.CANCEL_LABEL, false);
    }

    
    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Chose new status");
     }
    @Override
    protected void okPressed() {
    		
    		this.choose = comboStatus.getText();

            super.okPressed();
          
    }
    public String getStatus()
    {
    	return this.choose;
    }


}