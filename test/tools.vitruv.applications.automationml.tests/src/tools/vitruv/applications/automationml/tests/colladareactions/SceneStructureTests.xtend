package tools.vitruv.applications.automationml.tests.colladareactions

import org.junit.Test

import static org.junit.Assert.assertNotNull
import tools.vitruv.applications.automationml.tests.amlutils.ColladaModelFactory

class SceneStructureTests extends AbstractColladaReactionsTest {
	def createBasicModel() {
		val scene = ColladaModelFactory.createScene
		colladaRootElement.scene = scene
		colladaRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def testRemoveScene() {
		createBasicModel
		
		colladaRootElement.scene = null
		colladaRootElement.saveAndSynchronizeChanges
		
		assertNotNull(colladaRootElementVirtualModel.scene)
	}
	
	@Test
	def testRemoveVisualSceneInstance() {
		createBasicModel
		
		colladaRootElement.scene.instanceVisualScene = null
		colladaRootElement.saveAndSynchronizeChanges
		
		assertNotNull(colladaRootElementVirtualModel.scene.instanceVisualScene)
	}
}