package de.fzi.intramodelconsistency.caex.createdemo;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;

public class QuickStart implements IStartup {

	@Override
	public void earlyStartup() {
		new de.fzi.intramodelconsistency.caex.createdemo.CAEXProjectGeneration().createProjectAndVsum();
		/*try {
			//PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("Java");
			final IWorkbench workbench = PlatformUI.getWorkbench();
			workbench.getDisplay().asyncExec(new Runnable() {
				public void run() {
					IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
					if (window != null) {
						try {
							window.getActivePage().showView("org.eclipse.jdt.ui.JavaPerspective");
						} catch (Exception e) {
							e.printStackTrace();
						}
					openWizard(window, "CAEX.presentation.CAEXModelWizardID");	
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}

	@Deprecated
	public void openWizard(IWorkbenchWindow activeWorkbenchWindow, String id) {
		Shell shell = activeWorkbenchWindow.getShell();
		IWizardDescriptor descriptor = PlatformUI.getWorkbench().getNewWizardRegistry().findWizard(id);

		if (descriptor == null)
			return;
		IWizard wizard;
		try {
			wizard = descriptor.createWizard();
			WizardDialog wd = new  WizardDialog(shell,
					wizard);
			wd.setTitle(wizard.getWindowTitle());
			wd.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
