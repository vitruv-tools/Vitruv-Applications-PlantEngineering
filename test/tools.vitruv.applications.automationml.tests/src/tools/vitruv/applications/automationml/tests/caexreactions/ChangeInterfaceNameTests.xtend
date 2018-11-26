package tools.vitruv.applications.automationml.tests.caexreactions

import org.junit.Test
import static org.junit.Assert.assertEquals
import tools.vitruv.applications.automationml.tests.amlutils.CAEXModelFactory
import tools.vitruv.applications.automationml.utils.aml.AMLBasicStrings

class ChangeInterfaceNameTests extends AbstractCAEXReactionsTest {
	def createBasicModel(boolean basicLib) {
		val lib = CAEXModelFactory.createInterfaceClassLib(basicLib)
		CAEXRootElement.interfaceClassLib.add(lib)
		CAEXRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def testChangeInterfaceLibName() {
		createBasicModel(false)
		
		CAEXRootElement.interfaceClassLib.get(0).name = "AnotherLib"
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals("AnotherLib", CAEXRootElementVirtualModel.interfaceClassLib.get(0).name)
	}
	
	@Test
	def testChangeInterfaceLibNameWithRollback() {
		createBasicModel(true)
		
		CAEXRootElement.interfaceClassLib.get(0).name = "AnotherLib"
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(AMLBasicStrings.automationMLInterfaceBaseLibName, CAEXRootElementVirtualModel.interfaceClassLib.get(0).name)
	}
	
	@Test
	def testChangeInterfaceName() {
		createBasicModel(false)
		
		CAEXRootElement.interfaceClassLib.get(0).interfaceClass.get(0).name = "AnotherClass"
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals("AnotherClass", CAEXRootElementVirtualModel.interfaceClassLib.get(0).interfaceClass.get(0).name)
	}
	
	@Test
	def testChangeInterfaceBasicName() {
		createBasicModel(true)
		
		CAEXRootElement.interfaceClassLib.get(0).interfaceClass.get(0).name = "AnotherClass"
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(AMLBasicStrings.automationMLBaseInterfaceName, CAEXRootElementVirtualModel.interfaceClassLib.get(0).interfaceClass.get(0).name)
	}
	
	@Test
	def testChangeInterfaceConnectionName() {
		createBasicModel(true)
		
		CAEXRootElement.interfaceClassLib.get(0).interfaceClass.get(0).interfaceClass.get(0).name = "AnotherClass"
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(AMLBasicStrings.automationMLExternalBaseInterfaceName, CAEXRootElementVirtualModel.interfaceClassLib.get(0).interfaceClass.get(0).interfaceClass.get(0).name)
	}
	
	@Test
	def testChangeInterfacePLCopenName() {
		createBasicModel(true)
		
		CAEXRootElement.interfaceClassLib.get(0).interfaceClass.get(0).interfaceClass.get(0).interfaceClass.get(0).name = "AnotherClass"
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(AMLBasicStrings.externalPLCopenBaseInterfaceName, CAEXRootElementVirtualModel.interfaceClassLib.get(0).interfaceClass.get(0).interfaceClass.get(0).interfaceClass.get(0).name)
	}
	
	@Test
	def testChangeInterfaceColladaName() {
		createBasicModel(true)
		
		CAEXRootElement.interfaceClassLib.get(0).interfaceClass.get(0).interfaceClass.get(0).interfaceClass.get(1).name = "AnotherClass"
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(AMLBasicStrings.externalColladaBaseInterfaceName, CAEXRootElementVirtualModel.interfaceClassLib.get(0).interfaceClass.get(0).interfaceClass.get(0).interfaceClass.get(1).name)
	}
}