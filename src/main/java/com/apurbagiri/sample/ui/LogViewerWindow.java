package com.apurbagiri.sample.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class LogViewerWindow {

	private Shell shell;
	private Display display;
	private Map<String, String> filePathMap = new HashMap<String, String>();

	public LogViewerWindow(Display display) {
		this.display = display;
	}
	
	public void open() {
		createViewerContent();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }		
	}

	private void createViewerContent() {
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN & ~SWT.MAX | SWT.ON_TOP);
		shell.pack();
		shell.setSize(1920,1080);		
		shell.open();
		shell.layout();
		shell.setText("Log Viewer");
		addShellContent();
	}
	
	private void addShellContent() {
		
	    final TabFolder tabFolder = new TabFolder(shell, SWT.BORDER);
	    int tabCount = filePathMap.size();
	    for (int loopIndex = 0; loopIndex < 5; loopIndex++) {
	      TabItem tabItem = new TabItem(tabFolder, SWT.NULL);
	      tabItem.setText("Tab " + loopIndex);

	      Text text = new Text(tabFolder, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
	      text.setText("This is page " + loopIndex);
	      tabItem.setControl(text);
	    }
	    tabFolder.setSize(1000, 550);
		
	}
	
}
