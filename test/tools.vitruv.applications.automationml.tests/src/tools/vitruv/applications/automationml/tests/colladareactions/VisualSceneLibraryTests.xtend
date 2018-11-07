package tools.vitruv.applications.automationml.tests.colladareactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject

class VisualSceneLibraryTests extends AbstractColladaReactionsTest {
	def createBasicModel(boolean extended) {
		val geometryLib = colladaFactory.createLibraryGeometriesType
		geometryLib.name = "geoLib"
		val geometry = colladaFactory.createGeometryType
		geometry.id = "geo1ID"
		
		val visualSceneLib = colladaFactory.createLibraryVisualScenesType
		visualSceneLib.name = "visualLib"
		val visualScene = colladaFactory.createVisualSceneType
		visualScene.id = "visualSceneID"
		val node = colladaFactory.createNodeType1
		node.name = "bspNode"
		val instanceGeometry = colladaFactory.createInstanceGeometryType
		instanceGeometry.name = "geoInstance"
		
		val collada = colladaFactory.createCOLLADAType
		val scene = colladaFactory.createSceneType
		val instanceVisualScene = colladaFactory.createInstanceWithExtra
		
		geometryLib.geometry.add(geometry)
		collada.libraryGeometries.add(geometryLib)
		node.instanceGeometry.add(instanceGeometry)
		visualScene.node.add(node)
		visualSceneLib.visualScene.add(visualScene)
		collada.libraryVisualScenes.add(visualSceneLib)
		scene.instanceVisualScene = instanceVisualScene
		collada.scene = scene
		colladaRootElement.COLLADA = collada
		
		instanceGeometry.url = "geo1ID"
		instanceVisualScene.url = "visualSceneID"
		
		if(extended) {
			val geo2 = colladaFactory.createGeometryType
			geo2.id = "geo2ID"
			geometryLib.geometry.add(geo2)
			val geoInstance2 = colladaFactory.createInstanceGeometryType
			geoInstance2.name = "SecondInstance"
			val node2 = colladaFactory.createNodeType1
			node2.name = "anotherNode"
			node2.instanceGeometry.add(geoInstance2)
			visualScene.node.add(node2)
			geoInstance2.url = "geo2ID"
		}
		
		colladaRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def testRemoveVisualSceneLib() {
		createBasicModel(true)
		
		colladaRootElement.COLLADA.libraryVisualScenes.remove(0)
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		val second = new LinkedList<EObject>
		val third = new LinkedList<EObject>
		
		first.add(colladaRootElementVirtualModel.COLLADA.libraryGeometries.get(0).geometry.get(0))
		second.add(colladaRootElementVirtualModel.COLLADA.libraryGeometries.get(0).geometry.get(1))
		third.add(colladaRootElementVirtualModel.COLLADA.scene.instanceVisualScene)
		
		val four = correspondenceModel.getCorrespondingEObjects(first)
		val five = correspondenceModel.getCorrespondingEObjects(second)
		val six = correspondenceModel.getCorrespondingEObjects(third)
		
		assertEquals(0, colladaRootElementVirtualModel.COLLADA.libraryVisualScenes.size)
		assertEquals(0, four.size)
		assertEquals(0, five.size)
		assertEquals(0, six.size)
	}
	
	@Test
	def testRemoveVisualScene() {
		createBasicModel(false)
		
		colladaRootElement.COLLADA.libraryVisualScenes.get(0).visualScene.remove(0)
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		val second = new LinkedList<EObject>
		
		first.add(colladaRootElementVirtualModel.COLLADA.libraryGeometries.get(0).geometry.get(0))
		second.add(colladaRootElementVirtualModel.COLLADA.scene.instanceVisualScene)
		
		val three = correspondenceModel.getCorrespondingEObjects(first)
		val four = correspondenceModel.getCorrespondingEObjects(second)
		
		assertEquals(0, colladaRootElementVirtualModel.COLLADA.libraryVisualScenes.get(0).visualScene.size)
		assertEquals(0, three.size)
		assertEquals(0, four.size)
	}
	
	@Test
	def testRemoveNode() {
		createBasicModel(false)
		
		colladaRootElement.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.remove(0)
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(colladaRootElementVirtualModel.COLLADA.libraryGeometries.get(0).geometry.get(0))
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(0, colladaRootElementVirtualModel.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.size)
		assertEquals(0, two.size)
	}
	
	@Test
	def testRemoveGeometryInstance() {
		createBasicModel(false)
		
		colladaRootElement.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.remove(0)
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(colladaRootElementVirtualModel.COLLADA.libraryGeometries.get(0).geometry.get(0))
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(0, colladaRootElementVirtualModel.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.size)
		assertEquals(0, two.size)
	}
}