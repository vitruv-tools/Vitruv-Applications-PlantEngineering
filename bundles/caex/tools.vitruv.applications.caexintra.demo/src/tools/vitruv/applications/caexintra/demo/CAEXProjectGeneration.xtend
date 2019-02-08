package tools.vitruv.applications.caexintra.demo

import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.CoreException
import tools.vitruv.domains.emf.builder.VitruviusEmfBuilder
import tools.vitruv.domains.emf.builder.VitruviusEmfBuilderApplicator
import tools.vitruv.framework.change.processing.ChangePropagationSpecification
import tools.vitruv.framework.tuid.TuidManager
import tools.vitruv.framework.userinteraction.UserInteractor
import tools.vitruv.framework.vsum.InternalVirtualModel
import tools.vitruv.domains.caex.CAEXDomainProvider
import tools.vitruv.domains.caex.CAEXDomain
import tools.vitruv.framework.domains.VitruvDomain
import tools.vitruv.applications.caexintra.CAEXIntraConsistencyChangePropagationSpecification
import org.eclipse.core.resources.ResourcesPlugin
import tools.vitruv.framework.ui.monitorededitor.ProjectBuildUtils
import tools.vitruv.testutils.util.TestUtil

import static edu.kit.ipd.sdq.commons.util.org.eclipse.core.resources.IProjectUtil.*;
import tools.vitruv.framework.userinteraction.UserInteractionFactory

class CAEXProjectGeneration{
	
	def createProjectAndVsum() {
		TuidManager.instance.reinitialize();
        val project = createTestProject("testProject");
        val virtualModel = createVirtualModel("testProjectVsum");
		val VitruviusEmfBuilderApplicator emfBuilder = new VitruviusEmfBuilderApplicator();
		emfBuilder.addToProject(project , virtualModel.folder, #[CAEXDomain.FILE_EXTENSION]);
		// build the project
		ProjectBuildUtils.issueIncrementalBuild(project, VitruviusEmfBuilder.BUILDER_ID);
		return project
	}	
		
	private def InternalVirtualModel createVirtualModel(String vsumName) {
		val metamodels = this.getDomains();
		val project = ResourcesPlugin.workspace.root.getProject(vsumName);
		project.create(null);
    	project.open(null);
    	val userInteractor = UserInteractionFactory.instance.createUserInteractor(UserInteractionFactory.instance.createDialogInteractionResultProvider)
		val virtualModel = TestUtil.createVirtualModel(project.location.toFile, false, metamodels, createChangePropagationSpecifications(), userInteractor);
		return virtualModel;
	}
	
	protected def Iterable<VitruvDomain> getDomains() {
		return #[new CAEXDomainProvider().domain];
	}
	
	protected def Iterable<ChangePropagationSpecification> createChangePropagationSpecifications() {
		return #[new CAEXIntraConsistencyChangePropagationSpecification()];
	}
	
	protected def IProject createTestProject(String projectName) throws CoreException {
        var project = getWorkspaceProject(projectName);
        if (!project.exists()) {
            project = TestUtil.createPlatformProject(projectName, false);
        }
   		return project;
	}
	

	
}
			