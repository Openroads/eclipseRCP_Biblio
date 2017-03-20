package biblioteka.views;



import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
//import org.apache.commons.lang3.StringUtils;

public class NewBookDialog extends Dialog {
        private Text txtId,txtTitle,txtAuthors,txtPublYear;
        private Combo comboStatus;
        private int intId;
        Label lblMessage;
        private String id = "";
        private String title = "";
        private String authors = "";
        private String publYear = "";
        private String status = "";

        public NewBookDialog(Shell parentShell) {
                super(parentShell);

        }

        @Override
        protected Control createDialogArea(Composite parent) {
                Composite container = (Composite) super.createDialogArea(parent);
                GridLayout layout = new GridLayout(2, false);
                layout.marginRight = 5;
                layout.marginLeft = 10;
                container.setLayout(layout);

                Label lblId = createLabel(container, "Id:");
                txtId = createTextFields(container);
                Label lblTitle = createLabel(container, "Title:");
                txtTitle = createTextFields(container);
                Label lblAuthors = createLabel(container, "Authors:");
                txtAuthors = createTextFields(container);
                Label lblPublYear = createLabel(container, "Publication date:");
                txtPublYear = createTextFields(container);
                Label lblStatus = createLabel(container, "Status:");
                comboStatus = new Combo(container,SWT.READ_ONLY | SWT.BORDER);
                comboStatus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
                        1, 1));
                String items[] = {"Wypożyczona","Do wypożyczenia"};
                comboStatus.setItems(items);
                comboStatus.select(1);
                Label lblbreak = createLabel(container, "asd");
                lblbreak.setVisible(false);
                lblMessage = new Label(container, SWT.CENTER);
                lblMessage.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false,2,2));
                lblMessage.setVisible(true);
                Device device = Display.getCurrent();
                Color red = new Color (device, 255, 0, 0);
                lblMessage.setBackground(red);
                lblMessage.setText("Space for errors message ");
               /* txtUser.addModifyListener(new ModifyListener() {

                        @Override
                        public void modifyText(ModifyEvent e) {
                                Text textWidget = (Text) e.getSource();
                                String userText = textWidget.getText();
                                user = userText;
                        }
                });


                txtPassword.setText(password);
                txtPassword.addModifyListener(new ModifyListener() {

                        @Override
                        public void modifyText(ModifyEvent e) {
                                Text textWidget = (Text) e.getSource();
                                String passwordText = textWidget.getText();
                                password = passwordText;
                        }
                });*/
                return container;
        }
        
        private Label createLabel(Composite container,String labelText)
        {
        	 Label label = new Label(container, SWT.NONE);
        	 label.setText(labelText);
             return label;
        }
        private Text createTextFields(Composite container)
        {
        	Text txt = new Text(container, SWT.BORDER);
        	txt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
                    1, 1));
        	return txt;
        }
        @Override
        protected boolean isResizable() {
            return true;
        }

       
        @Override
        protected void createButtonsForButtonBar(Composite parent) {
                createButton(parent, IDialogConstants.OK_ID, "Add", true);
                createButton(parent, IDialogConstants.CANCEL_ID,
                                IDialogConstants.CANCEL_LABEL, false);
        }

        @Override
        protected Point getInitialSize() {
                return new Point(450, 300);
        }
        
        @Override
        protected void configureShell(Shell shell) {
            super.configureShell(shell);
            shell.setText("Add new book");
         }
        @Override
        protected void okPressed() {
        		
        		// TODO do walidacji
                this.id 		= txtId.getText();
                this.title 		= txtTitle.getText();
                this.authors 	= txtAuthors.getText();
                this.publYear	= txtPublYear.getText();
                this.status 	= comboStatus.getText();
                this.intId 		= Integer.valueOf(id);
                super.okPressed();
              
        }

		public int getId() {
			
			return intId;
		}

		public String getTitle() {
			return title;
		}

		public String getAuthors() {
			return authors;
		}

		public String getPublYear() {
			return publYear;
		}

		public String getStatus() {
			return status;
		}



}