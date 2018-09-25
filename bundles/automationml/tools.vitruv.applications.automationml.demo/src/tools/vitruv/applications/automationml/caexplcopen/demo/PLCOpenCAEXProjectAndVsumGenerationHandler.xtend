package tools.vitruv.applications.automationml.caexplcopen.demo

import org.eclipse.core.commands.AbstractHandler
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException

class PLCOpenCAEXProjectAndVsumGenerationHandler extends AbstractHandler {
	
	override execute(ExecutionEvent event) throws ExecutionException {
		new PLCOpenCAEXProjectAndVsumGeneration().createProjectAndVsum();
		return null;
	}
	
}