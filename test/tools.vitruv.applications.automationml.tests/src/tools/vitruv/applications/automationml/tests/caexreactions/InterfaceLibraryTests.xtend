package tools.vitruv.applications.automationml.tests.caexreactions

import org.junit.Test
import static org.junit.Assert.assertEquals
import tools.vitruv.applications.automationml.tests.amlutils.CAEXModelFactory

class InterfaceLibraryTests extends AbstractCAEXReactionsTest {
	def createBasicModel(boolean basicLib) {
		val lib = CAEXModelFactory.createInterfaceClassLib(basicLib)
		CAEXRootElement.interfaceClassLib.add(lib)
		
		if(!basicLib) {
			val hierarchy = CAEXModelFactory.createInstanceHierarchy(false, false)
			CAEXRootElement.instanceHierarchy.add(hierarchy)
			val externalInterface = CAEXModelFactory.createSomeExternalInterface
			hierarchy.internalElement.get(0).externalInterface.add(externalInterface)
		}
		
		CAEXRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def testRemoveInterfaceLibWithRollback() {
		createBasicModel(true)
		
		CAEXRootElement.interfaceClassLib.remove(0)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(1, CAEXRootElementVirtualModel.interfaceClassLib.size)
	}
	
	@Test
	def testRemoveInterfaceBasic() {
		createBasicModel(true)
		
		CAEXRootElement.interfaceClassLib.get(0).interfaceClass.remove(0)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(1, CAEXRootElementVirtualModel.interfaceClassLib.get(0).interfaceClass.size)
	}
	
	@Test
	def testRemoveInterfaceConnection() {
		createBasicModel(true)
		
		CAEXRootElement.interfaceClassLib.get(0).interfaceClass.get(0).interfaceClass.remove(0)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(1, CAEXRootElementVirtualModel.interfaceClassLib.get(0).interfaceClass.get(0).interfaceClass.size)
	}
	
	@Test
	def testRemoveInterfacePLCopen() {
		createBasicModel(true)
		
		CAEXRootElement.interfaceClassLib.get(0).interfaceClass.get(0).interfaceClass.get(0).interfaceClass.remove(0)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(2, CAEXRootElementVirtualModel.interfaceClassLib.get(0).interfaceClass.get(0).interfaceClass.get(0).interfaceClass.size)
	}
	
	@Test
	def testRemoveInterfaceCollada() {
		createBasicModel(true)
		
		CAEXRootElement.interfaceClassLib.get(0).interfaceClass.get(0).interfaceClass.get(0).interfaceClass.remove(1)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(2, CAEXRootElementVirtualModel.interfaceClassLib.get(0).interfaceClass.get(0).interfaceClass.get(0).interfaceClass.size)
	}
}