package tools.vitruv.applications.plcopencaex.demo

import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.CoreException
import tools.vitruv.applications.plcopencaex.plcopen2caex.PLCOpen2CAEXChangePropagationSpecification
import tools.vitruv.applications.plcopencaex.caex2plcopen.CAEX2PLCopenChangePropagationSpecification
import tools.vitruv.domains.emf.builder.VitruviusEmfBuilder
import tools.vitruv.domains.emf.builder.VitruviusEmfBuilderApplicator
import tools.vitruv.framework.change.processing.ChangePropagationSpecification
import tools.vitruv.testutils.util.TestUtil
import tools.vitruv.framework.tuid.TuidManager
import tools.vitruv.framework.userinteraction.impl.UserInteractor
import tools.vitruv.framework.vsum.InternalVirtualModel
import tools.vitruv.domains.plcopen.PLCOpenDomainProvider
import tools.vitruv.domains.plcopen.PLCOpenDomain
import tools.vitruv.domains.caex.CAEXDomainProvider
import tools.vitruv.domains.caex.CAEXDomain
import tools.vitruv.framework.domains.VitruvDomain
import tools.vitruv.framework.ui.monitorededitor.ProjectBuildUtils
import org.eclipse.core.resources.ResourcesPlugin
import static edu.kit.ipd.sdq.commons.util.org.eclipse.core.resources.IProjectUtil.*;

class PLCOpenCAEXProjectAndVsumGeneration {
	
	public def void createProjectAndVsum() {
		TuidManager.instance.reinitialize();
        val project = createTestProject("testProject");
        val virtualModel = createVirtualModel("testProjectVsum");
        virtualModel.userInteractor = new UserInteractor();
		val VitruviusEmfBuilderApplicator emfBuilder = new VitruviusEmfBuilderApplicator();
		emfBuilder.addToProject(project , virtualModel.folder, #[PLCOpenDomain.FILE_EXTENSION, CAEXDomain.FILE_EXTENSION]);
		// build the project
		ProjectBuildUtils.issueIncrementalBuild(project, VitruviusEmfBuilder.BUILDER_ID);
	}
	
	private def InternalVirtualModel createVirtualModel(String vsumName) {
		val metamodels = this.getDomains();
		val project = ResourcesPlugin.workspace.root.getProject(vsumName);
		project.create(null);
    	project.open(null);
		val virtualModel = TestUtil.createVirtualModel(project.location.toFile, false, metamodels, createChangePropagationSpecifications(),
			new UserInteractor()
		);
		return virtualModel;
	}
	
	protected def Iterable<VitruvDomain> getDomains() {
		return #[new PLCOpenDomainProvider().domain, new CAEXDomainProvider().domain];
	}
	
	protected def Iterable<ChangePropagationSpecification> createChangePropagationSpecifications() {
		return #[new PLCOpen2CAEXChangePropagationSpecification, new CAEX2PLCopenChangePropagationSpecification];
	}
	
	protected def IProject createTestProject(String projectName) throws CoreException {
        var project = getWorkspaceProject(projectName);
        if (!project.exists()) {
            project = TestUtil.createPlatformProject(projectName, false);
        }
   		return project;
	}

}