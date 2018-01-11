package de.fzi.intramodelconsistency.caex.demo.autocreate

import de.fzi.intramodelconsistency.caex.demo.CAEXProjectGeneration
import java.io.File
import java.io.IOException
import org.apache.commons.io.FileUtils
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.core.runtime.Platform
import org.eclipse.ui.IStartup

class Autostart implements IStartup {

	override earlyStartup() {
		//create caex demo project
		var project = new CAEXProjectGeneration().createProjectAndVsum		
		var projectPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().append(project.fullPath)
		//add caex example to demo
		copyDefaultFile(projectPath.append("src/Example.caex").toFile)
		project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor)
	}

	private def copyDefaultFile(File destFile) throws IOException{
		var bundle = Platform.getBundle("de.fzi.intramodelconsistency.caex.demo")
		var fileURL = bundle.getEntry("resources/Example.caex")
		
		FileUtils.copyURLToFile(fileURL,destFile)
	}

}
