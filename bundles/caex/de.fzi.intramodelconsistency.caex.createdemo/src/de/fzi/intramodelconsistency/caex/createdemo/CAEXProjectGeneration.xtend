package de.fzi.intramodelconsistency.caex.createdemo

import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.CoreException
import tools.vitruv.domains.emf.builder.VitruviusEmfBuilder
import tools.vitruv.domains.emf.builder.VitruviusEmfBuilderApplicator
import tools.vitruv.framework.change.processing.ChangePropagationSpecification
import tools.vitruv.framework.monitorededitor.ProjectBuildUtils
import tools.vitruv.framework.tests.util.TestUtil
import tools.vitruv.framework.tuid.TuidManager
import tools.vitruv.framework.userinteraction.impl.UserInteractor
import tools.vitruv.framework.vsum.InternalVirtualModel
import tools.vitruv.domains.caex.CAEXDomainProvider
import tools.vitruv.domains.caex.CAEXDomain
import tools.vitruv.framework.domains.VitruvDomain
import de.fzi.intramodelconsistency.caex.CAEXIntraConsistencyChangePropagationSpecification
import java.io.File
import org.eclipse.core.resources.ResourcesPlugin

class CAEXProjectGeneration{
	
	def createProjectAndVsum() {
		TuidManager.instance.reinitialize();
        val project = createTestProject("caexProject");
        val virtualModel = createVirtualModel(new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + "/caexProjectVsum"));
        
        virtualModel.userInteractor = new UserInteractor();
		val VitruviusEmfBuilderApplicator emfBuilder = new VitruviusEmfBuilderApplicator();
		emfBuilder.addToProject(project , virtualModel.folder, #[CAEXDomain.FILE_EXTENSION]);
		// build the project
		ProjectBuildUtils.issueIncrementalBuild(project, VitruviusEmfBuilder.BUILDER_ID);
	}	
		
	private def InternalVirtualModel createVirtualModel(File vsumFolder) {
		val metamodels = this.getDomains();
		val virtualModel = TestUtil.createVirtualModel(vsumFolder, false, metamodels, createChangePropagationSpecifications(), new UserInteractor());
		return virtualModel;
	}
	
	protected def Iterable<VitruvDomain> getDomains() {
		return #[new CAEXDomainProvider().domain];
	}
	
	protected def Iterable<ChangePropagationSpecification> createChangePropagationSpecifications() {
		return #[new CAEXIntraConsistencyChangePropagationSpecification()];
	}
	
	protected def IProject createTestProject(String projectName) throws CoreException {
        var project = TestUtil.getProjectByName(projectName);
        if (!project.exists()) {
            project = TestUtil.createPlatformProject(projectName, false);
        }
   		return project;
	}
	

	
}
			