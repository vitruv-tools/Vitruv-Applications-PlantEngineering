package tools.vitruv.applications.plcopencaex.demo;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import tools.vitruv.applications.plcopencaex.demo.JavaPcmProjectAndVsumGeneration;

@SuppressWarnings("all")
public class JavaPcmProjectAndVsumGenerationHandler extends AbstractHandler {
  @Override
  public Object execute(final ExecutionEvent event) throws ExecutionException {
    JavaPcmProjectAndVsumGeneration _javaPcmProjectAndVsumGeneration = new JavaPcmProjectAndVsumGeneration();
    _javaPcmProjectAndVsumGeneration.createProjectAndVsum();
    return null;
  }
}
