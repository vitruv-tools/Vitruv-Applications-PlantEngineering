package de.fzi.intramodelconsistency.caex.createdemo

import org.eclipse.core.commands.AbstractHandler
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException

class CAEXProjectGenerationHandler extends AbstractHandler{
	
	override execute(ExecutionEvent event) throws ExecutionException {
		new CAEXProjectGeneration().createProjectAndVsum();
		return null
	}
	
}
