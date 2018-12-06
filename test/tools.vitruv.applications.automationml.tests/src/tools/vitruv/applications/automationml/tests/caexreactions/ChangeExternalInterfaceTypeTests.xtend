package tools.vitruv.applications.automationml.tests.caexreactions

import org.junit.Test
import static org.junit.Assert.assertEquals
import tools.vitruv.applications.automationml.tests.amlutils.CAEXModelFactory
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import tools.vitruv.applications.automationml.utils.aml.AMLBasicStrings
import caex.caex30.caex.InterfaceClass

class ChangeExternalInterfaceTypeTests extends AbstractCAEXReactionsTest {
	def createBasicModel() {
		val interfaceBasicLib = CAEXModelFactory.createInterfaceClassLib(true)
		val interfaceNormalLib = CAEXModelFactory.createInterfaceClassLib(false)
		val instanceHierarchy = CAEXModelFactory.createInstanceHierarchy(false, false)
		val systemUnitClassLib = CAEXModelFactory.createSystemUnitClassLib(false, false)
		
		CAEXRootElement.interfaceClassLib.add(interfaceBasicLib)
		CAEXRootElement.interfaceClassLib.add(interfaceNormalLib)
		CAEXRootElement.instanceHierarchy.add(instanceHierarchy)
		CAEXRootElement.systemUnitClassLib.add(systemUnitClassLib)
		
		CAEXRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def testAddExternalInterfaceTypeInInternalElement() {
		createBasicModel
		
		val externalInterface = CAEXModelFactory.createSomeExternalInterface
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.add(externalInterface)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspIfClass", (second.get(0).get(0) as InterfaceClass).name)
		assertEquals(0, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.size)
	}
	
	@Test
	def testAddExternalInterfaceTypeColladaInInternalElement() {
		createBasicModel
		
		val externalInterface = CAEXModelFactory.createColladaExternalInterface
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.add(externalInterface)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals(AMLBasicStrings.externalColladaBaseInterfaceName, (second.get(0).get(0) as InterfaceClass).name)
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.size)
	}
	
	@Test
	def testAddExternalInterfaceTypePLCopenInInternalElement() {
		createBasicModel
		
		val externalInterface = CAEXModelFactory.createPLCopenExternalInterface
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.add(externalInterface)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals(AMLBasicStrings.externalPLCopenBaseInterfaceName, (second.get(0).get(0) as InterfaceClass).name)
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.size)
	}
	
	@Test
	def testAddExternalInterfaceTypeInSystemClassWithCloneAdaptation() {
		createBasicModel
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		userInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val externalInterface = CAEXModelFactory.createSomeExternalInterface
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.add(externalInterface)
		userInteractor.addNextConfirmationInput(true)
		userInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspIfClass", (second.get(0).get(0) as InterfaceClass).name)
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.size)
		
		val third = new LinkedList<EObject>
		third.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0))
		val forth = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, forth.size)
		assertEquals(1, forth.get(0).size)
		assertEquals("BspIfClass", (forth.get(0).get(0) as InterfaceClass).name)
	}
	
	@Test
	def testAddExternalInterfaceTypeInSystemClassWithoutCloneAdaptation() {
		createBasicModel
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		userInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val externalInterface = CAEXModelFactory.createSomeExternalInterface
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.add(externalInterface)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspIfClass", (second.get(0).get(0) as InterfaceClass).name)
		assertEquals(0, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.size)
	}
	
	@Test
	def testChangeExternalInterfaceTypeFromPLCopenToCollada() {
		createBasicModel
		
		val externalInterface = CAEXModelFactory.createPLCopenExternalInterface
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.add(externalInterface)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).refBaseClassPath = AMLBasicStrings.completeColladaBaseInterfacePath
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals(AMLBasicStrings.externalColladaBaseInterfaceName, (second.get(0).get(0) as InterfaceClass).name)
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.size)
	}
	
	@Test
	def testChangeExternalInterfaceTypeFromPLCopenToX() {
		createBasicModel
		
		val externalInterface = CAEXModelFactory.createPLCopenExternalInterface
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.add(externalInterface)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).refBaseClassPath = "BspIfLib/BspIfClass"
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspIfClass", (second.get(0).get(0) as InterfaceClass).name)
		assertEquals(0, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.size)
	}
	
	@Test
	def testChangeExternalInterfaceTypeFromColladaToPLCopen() {
		createBasicModel
		
		val externalInterface = CAEXModelFactory.createColladaExternalInterface
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.add(externalInterface)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).refBaseClassPath = AMLBasicStrings.completePLCopenBaseInterfacePath
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals(AMLBasicStrings.externalPLCopenBaseInterfaceName, (second.get(0).get(0) as InterfaceClass).name)
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.size)
	}
	
	@Test
	def testChangeExternalInterfaceTypeFromColladaToX() {
		createBasicModel
		
		val externalInterface = CAEXModelFactory.createColladaExternalInterface
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.add(externalInterface)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).refBaseClassPath = "BspIfLib/BspIfClass"
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspIfClass", (second.get(0).get(0) as InterfaceClass).name)
		assertEquals(0, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.size)
	}
	
	@Test
	def testChangeExternalInterfaceTypeFromXToCollada() {
		createBasicModel
		
		val externalInterface = CAEXModelFactory.createSomeExternalInterface
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.add(externalInterface)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).refBaseClassPath = AMLBasicStrings.completeColladaBaseInterfacePath
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals(AMLBasicStrings.externalColladaBaseInterfaceName, (second.get(0).get(0) as InterfaceClass).name)
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.size)
	}
	
	@Test
	def testChangeExternalInterfaceTypeFromXToPLCopen() {
		createBasicModel
		
		val externalInterface = CAEXModelFactory.createSomeExternalInterface
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.add(externalInterface)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).refBaseClassPath = AMLBasicStrings.completePLCopenBaseInterfacePath
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals(AMLBasicStrings.externalPLCopenBaseInterfaceName, (second.get(0).get(0) as InterfaceClass).name)
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.size)
	}
}