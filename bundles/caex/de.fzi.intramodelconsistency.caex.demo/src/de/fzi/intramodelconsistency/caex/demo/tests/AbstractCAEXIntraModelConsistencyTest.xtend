package de.fzi.intramodelconsistency.caex.demo.tests

import de.fzi.intramodelconsistency.caex.CAEXIntraConsistencyChangePropagationSpecification
import java.util.NoSuchElementException
import org.automationml.caex.caex.CAEXFile
import org.automationml.caex.caex.CaexFactory
import org.automationml.caex.caex.DocumentRoot
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import tools.vitruv.domains.caex.CAEXDomainProvider
import tools.vitruv.testutils.TestUserInteractor
import tools.vitruv.testutils.VitruviusApplicationTest

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
		val docRoot = preloadExistingModel("resources/My.caex")	
		createAndSynchronizeModel(MODEL_NAME.projectModelPath,docRoot.CAEXFile)
	}
	
	protected def CaexFactory factory() {
		return CaexFactory.eINSTANCE
	}
	
	protected def DocumentRoot preloadExistingModel(String path) {
		var targetModel = getModelResource(URI.createURI(path))
		return targetModel.contents.get(0) as DocumentRoot
	}
	
	protected def TestUserInteractor getTestUserInteractor() {
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
