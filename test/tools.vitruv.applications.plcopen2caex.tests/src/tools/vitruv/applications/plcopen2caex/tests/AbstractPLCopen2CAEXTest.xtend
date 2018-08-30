package tools.vitruv.applications.plcopen2caex.tests

import tools.vitruv.applications.plcopencaex.plcopen2caex.PLCOpen2CAEXChangePropagationSpecification
import java.util.NoSuchElementException

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import tools.vitruv.domains.caex.CAEXDomainProvider
import tools.vitruv.testutils.VitruviusApplicationTest
import org.plcopen.xml.tc60201.ProjectType
import org.automationml.caex.caex.CAEXFile
import org.automationml.caex.caex.CaexFactory
import org.plcopen.xml.tc60201.Tc60201Factory
import tools.vitruv.domains.plcopen.PLCOpenDomainProvider
import tools.vitruv.testutils.TestUserInteraction

class AbstractPLCopen2CAEXTest extends VitruviusApplicationTest {
	private static val CAEX_MODEL_FILE_EXTENSION = "caex";
	private static val CAEX_MODEL_NAME = "model";
	private static val PLCOPEN_MODEL_FILE_EXTENSION = "tc60201"
	private static val PLCOPEN_MODEL_NAME = "model"

	private def String getProjectCAEXModelPath(String modelName) {
		"model/" + modelName + "." + CAEX_MODEL_FILE_EXTENSION;
	}
	
	private def String getProjectPLCopenModelPath(String modelName) {
		"model/" + modelName + "." + PLCOPEN_MODEL_FILE_EXTENSION;
	}

	override protected cleanup() {
		// nothing to cleanup
	}

	protected def CAEXFile getCAEXRootElement() {
		return CAEX_MODEL_NAME.projectCAEXModelPath.firstRootElement as CAEXFile
	}
	
	protected def ProjectType getPLCopenRootElement() {
		return PLCOPEN_MODEL_NAME.projectPLCopenModelPath.firstRootElement as ProjectType
	}

	protected def org.automationml.caex.caex.DocumentRoot getCAEXRoot() {
		val res = getModelResource(CAEX_MODEL_NAME.projectCAEXModelPath)
		return res.contents.get(0) as org.automationml.caex.caex.DocumentRoot 
	}
	
	protected def org.plcopen.xml.tc60201.DocumentRoot getPLCopenRoot(){
		val res = getModelResource(PLCOPEN_MODEL_NAME.projectPLCopenModelPath)
		return res.contents.get(0) as org.plcopen.xml.tc60201.DocumentRoot
	}
	
	protected def CAEXFile getRootElementVirtualCAEXModel() {
		virtualModel.getModelInstance(CAEX_MODEL_NAME.projectCAEXModelPath.modelVuri).firstRootEObject as CAEXFile
	}
	
	protected def ProjectType getRootElementVirtualPLCopenModel(){
		virtualModel.getModelInstance(PLCOPEN_MODEL_NAME.projectPLCopenModelPath.modelVuri).firstRootEObject as ProjectType
	}

	override protected createChangePropagationSpecifications() {
		return #[new PLCOpen2CAEXChangePropagationSpecification]
	}

	override protected getVitruvDomains() {
		return #[new CAEXDomainProvider().domain, new PLCOpenDomainProvider().domain]
	}
	
	override protected setup() {
		val docRootCAEX = preloadExistingCAEXModel("resources/Bsp.caex")	
		createAndSynchronizeModel(CAEX_MODEL_NAME.projectCAEXModelPath, docRootCAEX)
		val docRootPLCopen = preloadExistingPLCopenModel(("resources/bsp_sensor.tc60201"))
		createAndSynchronizeModel(PLCOPEN_MODEL_NAME.projectPLCopenModelPath, docRootPLCopen)
	}
	
	protected def CaexFactory caexFactory() {
		return CaexFactory.eINSTANCE
	}
	
	protected def Tc60201Factory plcopenFactory() {
		return Tc60201Factory.eINSTANCE
	}
	
	protected def CAEXFile preloadExistingCAEXModel(String path) {
		var targetModel = getModelResource(URI.createURI(path))
		return targetModel.contents.get(0) as CAEXFile
	}
	
	protected def ProjectType preloadExistingPLCopenModel(String path) {
		var targetModel = getModelResource(URI.createURI(path))
		return targetModel.contents.get(0) as ProjectType
	}
	
	protected def TestUserInteraction getTestUserInteractor() {
		super.userInteractor
	}
	
	protected def EObject findByPath(EObject start, String path) {
		if(path === null) return null;
		val next = path.split("/").get(0) ?: path as String
		var all = start.eContents
		var found = all.filter[eClass.getEStructuralFeature("name") !== null && next.equals((eGet(eClass.getEStructuralFeature("name")) as String))]
		if (found.empty) throw new NoSuchElementException('''No element with name='�next�' was found at entity �start�!''')
		
		if(path.contains("/")) return findByPath(found.get(0), path.split("/").stream.skip(1).toArray.fold("",[s,t|s+"/"+t]).substring(1))
		return found.get(0)
	}

}