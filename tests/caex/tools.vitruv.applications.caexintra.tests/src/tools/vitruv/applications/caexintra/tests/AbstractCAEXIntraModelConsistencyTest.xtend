package tools.vitruv.applications.caexintra.tests

import tools.vitruv.applications.caexintra.CAEXIntraConsistencyChangePropagationSpecification
import java.util.NoSuchElementException
import caex.caex30.caex.CAEXFile
import caex.caex30.caex.CAEXFactory
import caex.caex30.caex.DocumentRoot
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import tools.vitruv.domains.caex.CAEXDomainProvider
import tools.vitruv.testutils.TestUserInteraction
import tools.vitruv.testutils.VitruviusApplicationTest
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl

class AbstractCAEXIntraModelConsistencyTest extends VitruviusApplicationTest {

	private static val MODEL_FILE_EXTENSION = "caex";
	private static val MODEL_NAME = "model";

	private def String getProjectModelPath(String modelName) {
		"model/" + modelName + "." + MODEL_FILE_EXTENSION;
	}

	override protected cleanup() {
		// nothing to cleanup
	}

	protected def CAEXFile getRootElement() {
		return MODEL_NAME.projectModelPath.firstRootElement as CAEXFile
	}

	protected def DocumentRoot getRoot() {
		val res = getModelResource(MODEL_NAME.projectModelPath)
		return res.contents.get(0) as DocumentRoot 
	}
	
	protected def CAEXFile getRootElementVirtualModel() {
		virtualModel.getModelInstance(MODEL_NAME.projectModelPath.modelVuri).firstRootEObject as CAEXFile
	}

	override protected createChangePropagationSpecifications() {
		return #[new CAEXIntraConsistencyChangePropagationSpecification]
	}

	override protected getVitruvDomains() {
		return #[new CAEXDomainProvider().domain]
	}
	
	override protected setup() {
		val docRoot = preloadExistingModel("resources/Test.caex")	
		createAndSynchronizeModel(MODEL_NAME.projectModelPath,docRoot)
	}
	
	protected def CAEXFactory factory() {
		return CAEXFactory.eINSTANCE
	}
	
	protected def CAEXFile preloadExistingModel(String path) {
		val uri = URI.createURI(path)
        val resourceSet = new ResourceSetImpl()
        val resource = resourceSet.getResource(uri, true)
        val targetModel = resource.getContents().get(0)
		return targetModel as CAEXFile
	}
	
	protected def TestUserInteraction getTestUserInteractor() {
		super.userInteractor
	}
	
	protected def EObject findByPath(EObject start, String path) {
		if(path === null) return null;
		val next = path.split("/").get(0) ?: path as String
		var all = start.eContents
		var found = all.filter[eClass.getEStructuralFeature("name") !== null && next.equals((eGet(eClass.getEStructuralFeature("name")) as String))]
		if (found.empty) throw new NoSuchElementException('''No element with name='«next»' was found at entity «start»!''')
		
		if(path.contains("/")) return findByPath(found.get(0), path.split("/").stream.skip(1).toArray.fold("",[s,t|s+"/"+t]).substring(1))
		return found.get(0)
	}

}
