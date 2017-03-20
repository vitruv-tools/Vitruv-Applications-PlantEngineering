package tools.vitruv.applications.plcopencaex.demo;

import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;

@SuppressWarnings("all")
public class JavaPcmProjectAndVsumGeneration {
  public void createProjectAndVsum() {
    throw new Error("Unresolved compilation problems:"
      + "\nVitruviusJavaBuilderApplicator cannot be resolved to a type."
      + "\nVitruviusEmfBuilderApplicator cannot be resolved to a type."
      + "\nThe method or field TuidManager is undefined"
      + "\nUserInteractor cannot be resolved."
      + "\nVitruviusJavaBuilderApplicator cannot be resolved."
      + "\nVitruviusEmfBuilderApplicator cannot be resolved."
      + "\nThe method or field PcmNamespace is undefined"
      + "\nThe method or field ProjectBuildUtils is undefined"
      + "\nThe method or field VitruviusJavaBuilder is undefined"
      + "\nThe method or field ProjectBuildUtils is undefined"
      + "\nThe method or field VitruviusEmfBuilder is undefined"
      + "\nThe method createVirtualModel() from the type JavaPcmProjectAndVsumGeneration refers to the missing type InternalVirtualModel"
      + "\ninstance cannot be resolved"
      + "\nreinitialize cannot be resolved"
      + "\nuserInteractor cannot be resolved"
      + "\naddToProject cannot be resolved"
      + "\ngetName cannot be resolved"
      + "\naddToProject cannot be resolved"
      + "\ngetName cannot be resolved"
      + "\nREPOSITORY_FILE_EXTENSION cannot be resolved"
      + "\nissueIncrementalBuild cannot be resolved"
      + "\nBUILDER_ID cannot be resolved"
      + "\nissueIncrementalBuild cannot be resolved"
      + "\nBUILDER_ID cannot be resolved");
  }
  
  private /* InternalVirtualModel */Object createVirtualModel() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field TestUtil is undefined"
      + "\nThe method createMetamodels() from the type JavaPcmProjectAndVsumGeneration refers to the missing type Metamodel"
      + "\nThe method createChangePropagationSpecifications() from the type JavaPcmProjectAndVsumGeneration refers to the missing type ChangePropagationSpecification"
      + "\ncreateVSUM cannot be resolved");
  }
  
  protected /* Iterable<Metamodel> */Object createMetamodels() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field PCMJavaRepositoryCreationUtil is undefined"
      + "\ncreatePcmJamoppMetamodels cannot be resolved");
  }
  
  protected /* Iterable<ChangePropagationSpecification> */Object createChangePropagationSpecifications() {
    throw new Error("Unresolved compilation problems:"
      + "\nJavaToPcmChangePropagationSpecification cannot be resolved."
      + "\nPcmToJavaChangePropagationSpecification cannot be resolved.");
  }
  
  protected IProject createTestProject(final String projectName) throws CoreException {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field TestUtil is undefined"
      + "\ngetProjectByName cannot be resolved"
      + "\nexists cannot be resolved"
      + "\n! cannot be resolved");
  }
  
  /**
   * copied from:
   * https://sdqweb.ipd.kit.edu/wiki/JDT_Tutorial:_Creating_Eclipse_Java_Projects_Programmatically
   * :)
   * 
   * @param testProject
   * @throws CoreException
   */
  private void createProject(final IProject testProject) throws CoreException {
    NullProgressMonitor _nullProgressMonitor = new NullProgressMonitor();
    testProject.create(_nullProgressMonitor);
    NullProgressMonitor _nullProgressMonitor_1 = new NullProgressMonitor();
    testProject.open(_nullProgressMonitor_1);
    final IProjectDescription description = testProject.getDescription();
    description.setNatureIds(new String[] { JavaCore.NATURE_ID });
    testProject.setDescription(description, null);
    final IJavaProject javaProject = JavaCore.create(testProject);
    final IFolder binFolder = testProject.getFolder("bin");
    binFolder.create(false, true, null);
    IPath _fullPath = binFolder.getFullPath();
    javaProject.setOutputLocation(_fullPath, null);
    final List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
    final IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
    boolean _notEquals = (!Objects.equal(null, vmInstall));
    if (_notEquals) {
      final LibraryLocation[] locations = JavaRuntime.getLibraryLocations(vmInstall);
      for (final LibraryLocation element : locations) {
        IPath _systemLibraryPath = element.getSystemLibraryPath();
        IClasspathEntry _newLibraryEntry = JavaCore.newLibraryEntry(_systemLibraryPath, null, null);
        entries.add(_newLibraryEntry);
      }
    }
    int _size = entries.size();
    IClasspathEntry[] _newArrayOfSize = new IClasspathEntry[_size];
    IClasspathEntry[] _array = entries.<IClasspathEntry>toArray(_newArrayOfSize);
    javaProject.setRawClasspath(_array, null);
    final IFolder sourceFolder = testProject.getFolder("src");
    sourceFolder.create(false, true, null);
    final IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(sourceFolder);
    final IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
    int _length = oldEntries.length;
    int _plus = (_length + 1);
    final IClasspathEntry[] newEntries = new IClasspathEntry[_plus];
    int _length_1 = oldEntries.length;
    System.arraycopy(oldEntries, 0, newEntries, 0, _length_1);
    int _length_2 = oldEntries.length;
    IPath _path = root.getPath();
    IClasspathEntry _newSourceEntry = JavaCore.newSourceEntry(_path);
    newEntries[_length_2] = _newSourceEntry;
    javaProject.setRawClasspath(newEntries, null);
  }
}
