package tools.vitruv.applications.automationml.tests.colladareactions

import org.junit.Test

import static org.junit.Assert.assertNotNull

class SceneStructureTests extends AbstractColladaReactionsTest {
	def createBasicModel() {
		val collada = colladaFactory.createCOLLADAType
		val scene = colladaFactory.createSceneType
		val visualInstance = colladaFactory.createInstanceWithExtra
		
		scene.instanceVisualScene = visualInstance
		collada.scene = scene
		colladaRootElement.COLLADA = collada
		
		colladaRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def testRemoveScene() {
		colladaRootElement.COLLADA.scene = null
		
		colladaRootElement.saveAndSynchronizeChanges
		
		assertNotNull(colladaRootElementVirtualModel.COLLADA.scene)
	}
	
	@Test
	def testRemoveVisualSceneInstance() {
		colladaRootElement.COLLADA.scene.instanceVisualScene = null
		
		colladaRootElement.saveAndSynchronizeChanges
		
		assertNotNull(colladaRootElementVirtualModel.COLLADA.scene.instanceVisualScene)
	}
}