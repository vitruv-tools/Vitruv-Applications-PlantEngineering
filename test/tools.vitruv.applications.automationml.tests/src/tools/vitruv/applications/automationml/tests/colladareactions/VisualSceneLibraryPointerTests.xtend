package tools.vitruv.applications.automationml.tests.colladareactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import org.khronos.collada.GeometryType
import tools.vitruv.applications.automationml.tests.amlutils.ColladaModelFactory

class VisualSceneLibraryPointerTests extends AbstractColladaReactionsTest {
	def createBasicModel(boolean extended) {
		val geometryLib = ColladaModelFactory.createGeometryLib(extended)
		val visualSceneLib = ColladaModelFactory.createVisualSceneLib(false)
		val scene = ColladaModelFactory.createScene

		colladaRootElement.libraryGeometries.add(geometryLib)
		colladaRootElement.libraryVisualScenes.add(visualSceneLib)
		colladaRootElement.scene = scene
		
		colladaRootElement.scene.instanceVisualScene.url = "BspVisualSceneID"

		colladaRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def testChangeVisualSceneID() {
		createBasicModel(false)
		
		val newString = "NewId5678"
		colladaRootElement.libraryVisualScenes.get(0).visualScene.get(0).id = newString
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(newString, colladaRootElementVirtualModel.scene.instanceVisualScene.url)
	}
	
	@Test
	def testAddReferenceToGeometry() {
		createBasicModel(false)
		
		val newUrl = "BspGeoID"
		colladaRootElement.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url = newUrl
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(colladaRootElementVirtualModel.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0))
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, two.size)
		assertEquals(1, two.get(0).size)
		assertEquals(newUrl, (two.get(0).get(0) as GeometryType).id)
	}
	
	@Test
	def testChangeReferenceToGeometry() {
		createBasicModel(true)
		
		val newUrlA = "BspGeoID"
		colladaRootElement.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url = newUrlA
		colladaRootElement.saveAndSynchronizeChanges
		
		val newUrlB = "AnotherGeoID"
		colladaRootElement.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url = newUrlB
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(colladaRootElementVirtualModel.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0))
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, two.size)
		assertEquals(1, two.get(0).size)
		assertEquals(newUrlB, (two.get(0).get(0) as GeometryType).id)
	}
	
	@Test
	def testChangeReferenceToGeometryWithRollback() {
		createBasicModel(false)
		
		colladaRootElement.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url = "NotExistentUrl"
		userInteractor.addNextConfirmationInput(false)
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(null, colladaRootElementVirtualModel.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url)
	}
	
	@Test
	def testChangeReferenceToGeometryWithAimCreation() {
		createBasicModel(false)
		
		val newString = "NotExistentUrl"
		colladaRootElement.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url = newString
		userInteractor.addNextConfirmationInput(true)
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(2, colladaRootElementVirtualModel.libraryGeometries.size)
		assertEquals(newString, colladaRootElementVirtualModel.libraryGeometries.get(1).geometry.get(0).id)
	}
}