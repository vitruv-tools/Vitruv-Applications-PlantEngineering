package tools.vitruv.applications.automationml.tests.amlutils

import java.util.NoSuchElementException

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import tools.vitruv.domains.caex.CAEXDomainProvider
import tools.vitruv.testutils.VitruviusApplicationTest
import org.plcopen.xml.tc60201.ProjectType

import org.plcopen.xml.tc60201.Tc60201Factory
import tools.vitruv.domains.plcopen.PLCOpenDomainProvider
import tools.vitruv.testutils.TestUserInteraction
import caex.caex30.caex.CAEXFile
import caex.caex30.caex.CAEXFactory
import tools.vitruv.domains.collada.ColladaDomainProvider
import tools.vitruv.domains.aml.AMLDomainProvider
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.khronos.collada.COLLADAType
import org.khronos.collada.ColladaFactory
import edu.kit.sdq.aml_aggregator.AMLProject
import edu.kit.sdq.aml_aggregator.Aml_aggregatorFactory

abstract class AbstractAMLTest extends VitruviusApplicationTest {
	protected static val AGGREGATOR_MODEL_FILE_EXTENSION = "aml_aggregator"
	protected static val AGGREGATOR_MODEL_NAME = "model"
	protected static val CAEX_MODEL_FILE_EXTENSION = "caex";
	protected static val CAEX_MODEL_NAME = "model";
	protected static val PLCOPEN_MODEL_FILE_EXTENSION = "tc60201"
	protected static val PLCOPEN_MODEL_NAME = "model"
	protected static val COLLADA_MODEL_FILE_EXTENSION = "dae"
	protected static val COLLADA_MODEL_NAME = "model"

	protected def String getProjectAggregatorModelPath(String modelName) {
		"model/" + modelName + "." + AGGREGATOR_MODEL_FILE_EXTENSION
	}

	protected def String getProjectCAEXModelPath(String modelName) {
		"model/" + modelName + "." + CAEX_MODEL_FILE_EXTENSION
	}
	
	protected def String getProjectPLCopenModelPath(String modelName) {
		"model/" + modelName + "." + PLCOPEN_MODEL_FILE_EXTENSION
	}
	
	protected def String getProjectColladaModelPath(String modelName) {
		"model/" + modelName + "." + COLLADA_MODEL_FILE_EXTENSION
	}

	protected def AMLProject getAggregatorRootElement() {
		return AGGREGATOR_MODEL_NAME.projectAggregatorModelPath.firstRootElement as AMLProject
	}

	protected def CAEXFile getCAEXRootElement() {
		return CAEX_MODEL_NAME.projectCAEXModelPath.firstRootElement as CAEXFile
	}
	
	protected def ProjectType getPLCopenRootElement() {
		return PLCOPEN_MODEL_NAME.projectPLCopenModelPath.firstRootElement as ProjectType
	}
	
	protected def COLLADAType getColladaRootElement() {
		return COLLADA_MODEL_NAME.projectColladaModelPath.firstRootElement as COLLADAType
	}
	
	protected def AMLProject getAggregatorRootElementVirtualModel() {
		virtualModel.getModelInstance(AGGREGATOR_MODEL_NAME.projectAggregatorModelPath.modelVuri).firstRootEObject as AMLProject
	}
	
	protected def CAEXFile getCAEXRootElementVirtualModel() {
		virtualModel.getModelInstance(CAEX_MODEL_NAME.projectCAEXModelPath.modelVuri).firstRootEObject as CAEXFile
	}
	
	protected def ProjectType getPLCopenRootElementVirtualModel() {
		virtualModel.getModelInstance(PLCOPEN_MODEL_NAME.projectPLCopenModelPath.modelVuri).firstRootEObject as ProjectType
	}
	
	protected def COLLADAType getColladaRootElementVirtualModel() {
		virtualModel.getModelInstance(COLLADA_MODEL_NAME.projectColladaModelPath.modelVuri).firstRootEObject as COLLADAType
	}

	override protected getVitruvDomains() {
		return #[new CAEXDomainProvider().domain, new PLCOpenDomainProvider().domain, new ColladaDomainProvider().domain, new AMLDomainProvider().domain]
	}
	
	protected def AMLProject preloadExistingAggregatorModel(String path) {
		val dslURI = URI.createURI(path)
        val resourceSet = new ResourceSetImpl()
        val resource = resourceSet.getResource(dslURI, true)
        val targetModel = resource.getContents().get(0)
		return targetModel as AMLProject
	}
	
	protected def CAEXFile preloadExistingCAEXModel(String path) {
		val dslURI = URI.createURI(path)
        val resourceSet = new ResourceSetImpl()
        val resource = resourceSet.getResource(dslURI, true)
        val targetModel = resource.getContents().get(0)
		return targetModel as CAEXFile
	}
	
	protected def ProjectType preloadExistingPLCopenModel(String path) {
		val dslURI = URI.createURI(path)
        val resourceSet = new ResourceSetImpl()
        val resource = resourceSet.getResource(dslURI, true)
        val targetModel = resource.getContents().get(0)
		return targetModel as ProjectType
	}
	
	protected def COLLADAType preloadExistingColladaModel(String path) {
		val dslURI = URI.createURI(path)
        val resourceSet = new ResourceSetImpl()
        val resource = resourceSet.getResource(dslURI, true)
        val targetModel = resource.getContents().get(0)
		return targetModel as COLLADAType
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