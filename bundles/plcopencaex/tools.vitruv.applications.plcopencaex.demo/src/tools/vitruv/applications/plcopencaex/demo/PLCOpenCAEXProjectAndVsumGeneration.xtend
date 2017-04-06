package tools.vitruv.applications.plcopencaex.demo

import java.util.ArrayList
import java.util.List
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.IProjectDescription
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.jdt.core.IClasspathEntry
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.IPackageFragmentRoot
import org.eclipse.jdt.core.JavaCore
import org.eclipse.jdt.launching.IVMInstall
import org.eclipse.jdt.launching.JavaRuntime
import org.eclipse.jdt.launching.LibraryLocation
import tools.vitruv.applications.plcopencaex.plcopen2caex.PLCOpen2CAEXChangePropagationSpecification
import tools.vitruv.domains.caex.CAEXDomain
import tools.vitruv.domains.caex.CAEXMetamodel
import tools.vitruv.domains.emf.builder.VitruviusEmfBuilder
import tools.vitruv.domains.emf.builder.VitruviusEmfBuilderApplicator
import tools.vitruv.domains.plcopen.PLCOpenDomain
import tools.vitruv.domains.plcopen.PLCOpenMetamodel
import tools.vitruv.framework.change.processing.ChangePropagationSpecification
import tools.vitruv.framework.metamodel.Metamodel
import tools.vitruv.framework.monitorededitor.ProjectBuildUtils
import tools.vitruv.framework.tests.util.TestUtil
import tools.vitruv.framework.tuid.TuidManager
import tools.vitruv.framework.userinteraction.impl.UserInteractor
import tools.vitruv.framework.vsum.InternalVirtualModel

class PLCOpenCAEXProjectAndVsumGeneration {
	
	public def void createProjectAndVsum() {
		TuidManager.instance.reinitialize();
        val project = createTestProject("testProject");
        val virtualModel = createVirtualModel();
        virtualModel.userInteractor = new UserInteractor();
		val VitruviusEmfBuilderApplicator emfBuilder = new VitruviusEmfBuilderApplicator();
		emfBuilder.addToProject(project , virtualModel.getName(), #[PLCOpenMetamodel.FILE_EXTENSION, CAEXMetamodel.FILE_EXTENSION]);
		// build the project
		ProjectBuildUtils.issueIncrementalBuild(project, VitruviusEmfBuilder.BUILDER_ID);
	}
	
	private def InternalVirtualModel createVirtualModel() {
		val metamodels = this.createMetamodels();
		val virtualModel = TestUtil.createVSUM("testProjectVsum", metamodels, createChangePropagationSpecifications());
		return virtualModel;
	}
	
	protected def Iterable<Metamodel> createMetamodels() {
		return #[new PLCOpenDomain().getMetamodel(), new CAEXDomain().getMetamodel()]
	}
	
	protected def Iterable<ChangePropagationSpecification> createChangePropagationSpecifications() {
		return #[new PLCOpen2CAEXChangePropagationSpecification()];
	}
	
	    // ensure that MockupProject is existing
	protected def IProject createTestProject(String projectName) throws CoreException {
        val project = TestUtil.getProjectByName(projectName);
        if (!project.exists()) {
            this.createProject(project);
        }
   		return project;
	}
	
	    /**
     * copied from:
     * https://sdqweb.ipd.kit.edu/wiki/JDT_Tutorial:_Creating_Eclipse_Java_Projects_Programmatically
     * :)
     *
     * @param testProject
     * @throws CoreException
     */
     // FIXME MK create a valid PLCOpen project!
    private def void createProject(IProject testProject) throws CoreException {
        testProject.create(new NullProgressMonitor());
        testProject.open(new NullProgressMonitor());
        val IProjectDescription description = testProject.getDescription();
        description.setNatureIds(#[JavaCore.NATURE_ID]);
        testProject.setDescription(description, null);
        val IJavaProject javaProject = JavaCore.create(testProject);
        val IFolder binFolder = testProject.getFolder("bin");
        binFolder.create(false, true, null);
        javaProject.setOutputLocation(binFolder.getFullPath(), null);
        val List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
        val IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
        if (null != vmInstall) {
            val LibraryLocation[] locations = JavaRuntime.getLibraryLocations(vmInstall);
            for (element : locations) {
                entries.add(JavaCore.newLibraryEntry(element.getSystemLibraryPath(), null, null));
            }
        }
        // add libs to project class path
        javaProject.setRawClasspath(entries.toArray(<IClasspathEntry>newArrayOfSize(entries.size())), null);
        val IFolder sourceFolder = testProject.getFolder("src");
        sourceFolder.create(false, true, null);
        val IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(sourceFolder);
        val IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
        val IClasspathEntry[] newEntries = <IClasspathEntry>newArrayOfSize(oldEntries.length + 1);
        System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
        newEntries.set(oldEntries.length, JavaCore.newSourceEntry(root.getPath()));
        javaProject.setRawClasspath(newEntries, null);
    }
}