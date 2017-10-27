package de.fzi.intramodelconsistency.caex.demo

import CAEX.CAEXFactory
import CAEX.CAEXFile
import CAEX.DocumentRoot
import de.fzi.intramodelconsistency.caex.CAEXIntraConsistencyChangePropagationSpecification
import org.eclipse.emf.common.util.URI
import tools.vitruv.domains.caex.CAEXDomainProvider
import tools.vitruv.framework.tests.VitruviusApplicationTest

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
	
	@Deprecated
	private def DocumentRoot createInitialModel() {
		val caexModel = CAEXFactory.eINSTANCE.createDocumentRoot;
        val caexFile = CAEXFactory.eINSTANCE.createCAEXFile;
        caexFile.fileName = "model"
        caexModel.CAEXFile = caexFile;
	
		return caexModel;
	}
	
	protected def CAEXFactory factory() {
		return CAEXFactory.eINSTANCE
	}
	
	protected def DocumentRoot preloadExistingModel(String path) {
		var targetModel = getModelResource(URI.createURI(path))
		return targetModel.contents.get(0) as DocumentRoot
	}

}
