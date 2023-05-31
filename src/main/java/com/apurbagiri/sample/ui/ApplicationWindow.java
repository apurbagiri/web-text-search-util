package com.apurbagiri.sample.ui;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.apurbagiri.sample.constants.UIConstants;

public class ApplicationWindow {

	private Shell shell;
	private Composite searchParamGroup;
	private DateTime dateTime;
	private static Text outputWindow;
	private Display display;
	public static StringBuffer outputString;	
	private static ProgressBar progressBar;
	private boolean isSearchComplete = false;	
	
	public ApplicationWindow(){
		outputString = new StringBuffer();
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ApplicationWindow window = new ApplicationWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createApplicationShell();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		System.exit(0);		
	}
	private void createApplicationShell() {
		shell = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shell.setText(UIConstants.APP_TITLE);
		shell.setSize(700, 800);
		//shell.setImage(null);
		GridLayout shellLayout = new GridLayout(1, true);
		shell.setLayout(shellLayout);	
		addShellContents();
	}
	
	
	private void addShellContents() {
		
		searchParamGroup = new Composite(shell, SWT.BORDER);
		searchParamGroup.setLayout(new GridLayout(4, false));
		GridData searchParamGroupGridData = new GridData(SWT.FILL, SWT.END, true, false);
		searchParamGroupGridData.horizontalSpan = 3;
		searchParamGroup.setLayoutData(searchParamGroupGridData);
		
		addEnvironmentRow();
		addEmptyLine(searchParamGroup);
		addSearchOptionRow();
		addSearchTextRow();
		addSearchButton();
		addOutputWindow(shell);
		addLogExplorerRow();
		startConsoleOutput();
	
	}

	private void addEnvironmentRow() {
		
		Group grpEnvironment = new Group(searchParamGroup, SWT.NONE);
		grpEnvironment.setText("Environment");
		grpEnvironment.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1));
		grpEnvironment.setLayout(new GridLayout(2, false));
				
		Button btnRadioButton = new Button(grpEnvironment, SWT.RADIO);
		btnRadioButton.setText("PROD");
		
		Button btnRadioButton_1 = new Button(grpEnvironment, SWT.RADIO);
		btnRadioButton_1.setText("MO");
	}
	
	private void addSearchOptionRow() {
		
		Group groupSearchFilter = new Group(searchParamGroup, SWT.NONE);
		groupSearchFilter.setText("Search Filters");
		groupSearchFilter.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1));
		groupSearchFilter.setLayout(new GridLayout(5, false));
		
		Label lblNewLabel_1 = new Label(groupSearchFilter, SWT.NONE);
		lblNewLabel_1.setText("Log Date:");
		
		dateTime = new DateTime(groupSearchFilter, SWT.BORDER);
		
		Label label = new Label(groupSearchFilter, SWT.NONE);
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		
		Label lblNewLabel_2 = new Label(groupSearchFilter, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("Max Lines:");
		
		Text text_1 = new Text(groupSearchFilter, SWT.BORDER);
		new Label(searchParamGroup, SWT.NONE);
		
	}
	
	private void addSearchTextRow() {
		Group groupSearchText = new Group(searchParamGroup, SWT.NONE);
		groupSearchText.setText("Search Text");
		groupSearchText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1));
		groupSearchText.setLayout(new GridLayout(1, false));
				
		Text text = new Text(groupSearchText, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
	}
	
	private void addSearchButton() {
		Button btnNewButton = new Button(searchParamGroup, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.RIGHT, SWT.CENTER, true, true, 4, 1);
		gd_btnNewButton.widthHint = 120;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("Search");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) { 
	    		//updateProgressBar();
	    		updateConsole("Hello");
	        }     
		});
	}



	private void addLineSeparator(Composite shell) {
		Label separator = new Label(shell, SWT.SEPARATOR | SWT.SHADOW_OUT | SWT.HORIZONTAL);
		GridData separatorData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		separatorData.horizontalSpan = 3;
		separator.setLayoutData(separatorData);
	}	
	
	private void addLogExplorerRow() {
		
		Composite exploreButtonComposite = new Composite(shell, SWT.BORDER);
		exploreButtonComposite.setLayout(new GridLayout(3, false));
		GridData exploreButtonCompositeGridData = new GridData(SWT.FILL, SWT.END, true, false);
		exploreButtonCompositeGridData.horizontalSpan = 1;
		exploreButtonComposite.setLayoutData(exploreButtonCompositeGridData);

		Button btnNewButton = new Button(exploreButtonComposite, SWT.NONE);
		btnNewButton.setText("Output Directory");

		btnNewButton.addSelectionListener(new SelectionAdapter() {       
	        @Override
	        public void widgetSelected(SelectionEvent e) {      
				try {
					Runtime.getRuntime().exec("explorer.exe /select," + "C:");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        }
	    });
		
		Button btnNewButton2 = new Button(exploreButtonComposite, SWT.NONE);
		btnNewButton2.setText("In-App Viewer");
		btnNewButton2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
		        LogViewerWindow window = new LogViewerWindow(display);
		        window.open();	            
			}
		});		
		
		progressBar = new ProgressBar(exploreButtonComposite, SWT.SMOOTH);
		GridData progressBarGridData = new GridData(SWT.END, SWT.CENTER, true, false);
		progressBarGridData.horizontalSpan = 1;
		progressBar.setLayoutData(progressBarGridData);
		progressBar.setMaximum(100);		
	}


	private void addOutputWindow(Composite composite) {
		Label outputWindowLabel = new Label(composite, SWT.NONE);
		outputWindowLabel.setText("Console Output:");
		GridData outputWindowLabelData = new GridData(GridData.FILL_HORIZONTAL);
		outputWindowLabelData.verticalAlignment = SWT.TOP;
		outputWindowLabelData.horizontalSpan = 3;
		outputWindowLabel.setLayoutData(outputWindowLabelData);

		outputWindow = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData outputWindowData = new GridData();
		outputWindowData.horizontalAlignment = SWT.FILL;
		outputWindowData.grabExcessHorizontalSpace = true;
		outputWindowData.verticalAlignment = SWT.FILL;
		outputWindowData.grabExcessVerticalSpace = true;
		outputWindowData.horizontalSpan = 3;
		outputWindow.setLayoutData(outputWindowData);
		outputWindow.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text) e.widget;
				text.setSelection(text.getCharCount());				
			}
		});
		outputWindow.setEditable(false);

	}	
	
	private void addEmptyLine(Composite composite) {
		Label separator = new Label(composite, SWT.NO_BACKGROUND | SWT.NONE | SWT.HORIZONTAL);
		GridData separatorData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		separatorData.horizontalSpan = 5;
		separator.setLayoutData(separatorData);
	}	

	

	private void updateConsole(String outputMessage) {
		outputString.append("\n" + outputMessage);
	}	


	private void startConsoleOutput() {
		new Thread(new Runnable() {
			public void run() {
				while (!progressBar.isDisposed()) {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							outputWindow.setText(outputString.toString());
						}
					});

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}


	private void startProgressBar() {
		new Thread(new Runnable() {
			private int progress = 0;
			private static final int INCREMENT = 1;

			public void run() {
				while (!progressBar.isDisposed()) {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							if (!isSearchComplete) {
								progressBar
										.setSelection((progress += INCREMENT) % (progressBar.getMaximum() + INCREMENT));
							} else {
								progressBar.setSelection(progressBar.getMaximum());
								progress = 0;
							}
						}
					});

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}	
	
}
