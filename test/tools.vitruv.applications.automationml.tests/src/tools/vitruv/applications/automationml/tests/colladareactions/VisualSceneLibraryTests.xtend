package tools.vitruv.applications.automationml.tests.colladareactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import tools.vitruv.applications.automationml.tests.amlutils.ColladaModelFactory

class VisualSceneLibraryTests extends AbstractColladaReactionsTest {
	def createBasicModel(boolean extended) {
		val geometryLib = ColladaModelFactory.createGeometryLib(extended)
		val visualSceneLib = ColladaModelFactory.createVisualSceneLib(extended)
		val scene = ColladaModelFactory.createScene
		
		colladaRootElement.libraryGeometries.add(geometryLib)
		colladaRootElement.libraryVisualScenes.add(visualSceneLib)
		colladaRootElement.scene = scene
		
		colladaRootElement.saveAndSynchronizeChanges
		
		if(extended) {
			colladaRootElement.libraryVisualScenes.get(0).visualScene.get(1).node.get(0).instanceGeometry.get(0).url = "AnotherGeoID"
		}
		
		colladaRootElement.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url = "BspGeoID"
		colladaRootElement.scene.instanceVisualScene.url = "BspVisualSceneID"
		
		colladaRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def testRemoveVisualSceneLib() {
		createBasicModel(true)
		
		colladaRootElement.libraryVisualScenes.remove(0)
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		val second = new LinkedList<EObject>
		val third = new LinkedList<EObject>
		
		first.add(colladaRootElementVirtualModel.libraryGeometries.get(0).geometry.get(0))
		second.add(colladaRootElementVirtualModel.libraryGeometries.get(0).geometry.get(1))
		third.add(colladaRootElementVirtualModel.scene.instanceVisualScene)
		
		val four = correspondenceModel.getCorrespondingEObjects(first)
		val five = correspondenceModel.getCorrespondingEObjects(second)
		val six = correspondenceModel.getCorrespondingEObjects(third)
		
		assertEquals(0, colladaRootElementVirtualModel.libraryVisualScenes.size)
		assertEquals(0, four.size)
		assertEquals(0, five.size)
		assertEquals(0, six.size)
	}
	
	@Test
	def testRemoveVisualScene() {
		createBasicModel(false)
		
		colladaRootElement.libraryVisualScenes.get(0).visualScene.remove(0)
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		val second = new LinkedList<EObject>
		
		first.add(colladaRootElementVirtualModel.libraryGeometries.get(0).geometry.get(0))
		second.add(colladaRootElementVirtualModel.scene.instanceVisualScene)
		
		val three = correspondenceModel.getCorrespondingEObjects(first)
		val four = correspondenceModel.getCorrespondingEObjects(second)
		
		assertEquals(0, colladaRootElementVirtualModel.libraryVisualScenes.get(0).visualScene.size)
		assertEquals(0, three.size)
		assertEquals(0, four.size)
	}
	
	@Test
	def testRemoveNode() {
		createBasicModel(false)
		
		colladaRootElement.libraryVisualScenes.get(0).visualScene.get(0).node.remove(0)
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(colladaRootElementVirtualModel.libraryGeometries.get(0).geometry.get(0))
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(0, colladaRootElementVirtualModel.libraryVisualScenes.get(0).visualScene.get(0).node.size)
		assertEquals(0, two.size)
	}
	
	@Test
	def testRemoveGeometryInstance() {
		createBasicModel(false)
		
		colladaRootElement.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.remove(0)
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(colladaRootElementVirtualModel.libraryGeometries.get(0).geometry.get(0))
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(0, colladaRootElementVirtualModel.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.size)
		assertEquals(0, two.size)
	}
}