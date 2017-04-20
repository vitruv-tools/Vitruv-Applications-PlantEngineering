package tools.vitruv.applications.plcopencaex.demo

import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.CoreException
import tools.vitruv.applications.plcopencaex.plcopen2caex.PLCOpen2CAEXChangePropagationSpecification
import tools.vitruv.domains.emf.builder.VitruviusEmfBuilder
import tools.vitruv.domains.emf.builder.VitruviusEmfBuilderApplicator
import tools.vitruv.framework.change.processing.ChangePropagationSpecification
import tools.vitruv.framework.monitorededitor.ProjectBuildUtils
import tools.vitruv.framework.tests.util.TestUtil
import tools.vitruv.framework.tuid.TuidManager
import tools.vitruv.framework.userinteraction.impl.UserInteractor
import tools.vitruv.framework.vsum.InternalVirtualModel
import tools.vitruv.domains.plcopen.PLCOpenDomainProvider
import tools.vitruv.domains.plcopen.PLCOpenDomain
import tools.vitruv.domains.caex.CAEXDomainProvider
import tools.vitruv.domains.caex.CAEXDomain
import tools.vitruv.framework.domains.VitruvDomain

class PLCOpenCAEXProjectAndVsumGeneration {
	
	public def void createProjectAndVsum() {
		TuidManager.instance.reinitialize();
        val project = createTestProject("testProject");
        val virtualModel = createVirtualModel("testProjectVsum");
        virtualModel.userInteractor = new UserInteractor();
		val VitruviusEmfBuilderApplicator emfBuilder = new VitruviusEmfBuilderApplicator();
		emfBuilder.addToProject(project , virtualModel.getName(), #[PLCOpenDomain.FILE_EXTENSION, CAEXDomain.FILE_EXTENSION]);
		// build the project
		ProjectBuildUtils.issueIncrementalBuild(project, VitruviusEmfBuilder.BUILDER_ID);
	}
	
	private def InternalVirtualModel createVirtualModel(String vsumName) {
		val metamodels = this.getDomains();
		val virtualModel = TestUtil.createVirtualModel(vsumName, false, metamodels, createChangePropagationSpecifications());
		return virtualModel;
	}
	
	protected def Iterable<VitruvDomain> getDomains() {
		return #[new PLCOpenDomainProvider().domain, new CAEXDomainProvider().domain];
	}
	
	protected def Iterable<ChangePropagationSpecification> createChangePropagationSpecifications() {
		return #[new PLCOpen2CAEXChangePropagationSpecification()];
	}
	
	protected def IProject createTestProject(String projectName) throws CoreException {
        var project = TestUtil.getProjectByName(projectName);
        if (!project.exists()) {
            project = TestUtil.createProject(projectName, false);
        }
   		return project;
	}

}