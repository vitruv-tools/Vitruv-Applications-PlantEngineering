package tools.vitruv.applications.automationml.evaluation.implicitreferences

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import org.eclipse.emf.compare.EMFCompare
import caex.caex30.caex.InternalElement
import caex.caex30.caex.CAEXFactory
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import tools.vitruv.applications.automationml.caexchanges.CombinedCAEXChangePropagationSpecification
import tools.vitruv.applications.automationml.tests.amlutils.AbstractAMLTest

class ImplicitReferencesV11 extends AbstractAMLTest {
	/*
	@Test
	def testSomeSmallThings() {
		val fileA = preloadExistingCAEXModel("resources/small_demo_example.caex")
		val fileB = preloadExistingCAEXModel("resources/big_demo_example.caex")
		
		val scope0 = EMFCompare.createDefaultScope(fileA, fileB)
		val comparison = EMFCompare.builder.build.compare(scope0)

		System.out.println("")
		System.out.println("****************************************************************")
		System.out.println("Einige kleine Tests")
		System.out.println("Konflikte: " + comparison.conflicts.size)
		System.out.println("Unterschiede: " + comparison.differences.size)
		System.out.println("****************************************************************")
		System.out.println("")
		
		assertTrue(true)
	}
	*/
	
	def createInternalsForTurntable(InternalElement turntable) {
		turntable.internalElement.add(createInternalElementWithReference("inductive_sensor_turntable", "IAF_ModelSystemUnitClassLib/parts/inductive_sensor"))
		turntable.internalElement.add(createInternalElementWithReference("rotation_assembly", "IAF_ModelSystemUnitClassLib/parts/rotating_assembly"))
		turntable.internalElement.add(createInternalElementWithReference("motor_line_turntable", "IAF_ModelSystemUnitClassLib/parts/motor"))
		turntable.internalElement.add(createInternalElementWithReference("motor_rotation_assembly", "IAF_ModelSystemUnitClassLib/parts/motor"))
	}
	
	def createInternalsForConveyor(InternalElement conveyor) {
		conveyor.internalElement.add(createInternalElementWithReference("frame_conveyer", "IAF_ModelSystemUnitClassLib/parts/frame"))
		conveyor.internalElement.add(createInternalElementWithReference("line_conveyer", "IAF_ModelSystemUnitClassLib/parts/line_conveyer"))
		conveyor.internalElement.add(createInternalElementWithReference("inductive_sensor_conveyer", "IAF_ModelSystemUnitClassLib/parts/inductive_sensor"))
		conveyor.internalElement.add(createInternalElementWithReference("motor_line_conveyer", "IAF_ModelSystemUnitClassLib/parts/motor"))
	}
	
	def InternalElement createInternalElementWithReference(String name, String prototype) {
		userInteractor.addNextConfirmationInput(false)
		val elem = CAEXFactory.eINSTANCE.createInternalElement
		elem.name = name
		elem.refBaseSystemUnitPath = prototype
		return elem
	}
	
	/*
	@Test
	def evaluate() {
		val manufacturingSystem = CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).internalElement.get(0).internalElement.get(0).internalElement.get(0)
		createInternalsForTurntable(manufacturingSystem.internalElement.get(0))
		createInternalsForConveyor(manufacturingSystem.internalElement.get(1))
		createInternalsForConveyor(manufacturingSystem.internalElement.get(2))
		createInternalsForTurntable(manufacturingSystem.internalElement.get(3))
		createInternalsForConveyor(manufacturingSystem.internalElement.get(4))
		createInternalsForTurntable(manufacturingSystem.internalElement.get(5))
		createInternalsForTurntable(manufacturingSystem.internalElement.get(6))
		createInternalsForConveyor(manufacturingSystem.internalElement.get(7))
		CAEXRootElement.saveAndSynchronizeChanges()
		
		val version13 = preloadExistingCAEXModel("resources/mechanicalPlantModelFlexMan_v13_string_references.caex")
		
		val scope0 = EMFCompare.createDefaultScope(CAEXRootElementVirtualModel, version13)
		val comparison = EMFCompare.builder.build.compare(scope0)

		System.out.println("")
		System.out.println("****************************************************************")
		System.out.println("Evaluation Implizite Referenzen")
		System.out.println("Konflikte: " + comparison.conflicts.size)
		System.out.println("Unterschiede: " + comparison.differences.size)
		System.out.println("****************************************************************")
		System.out.println("")
		
		assertTrue(true)
	}
	*/
	
	def createRoleRequirements(String roleName) {
		val role = CAEXFactory.eINSTANCE.createRoleRequirements
		role.refBaseRoleClassPath = roleName
		return role
	}
	
	//@Test
	def evaluate() {
		val bla = CAEXRootElement
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(1).internalElement.get(0).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/frame"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(1).internalElement.get(1).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/line_conveyer"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(1).internalElement.get(2).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/inductive_sensor"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(1).internalElement.get(3).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/motor"
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(2).internalElement.get(1).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/inductive_sensor"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(2).internalElement.get(2).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/line_turntable"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(2).internalElement.get(3).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/rotating_assembly"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(2).internalElement.get(4).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/upper_frame_turntable"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(2).internalElement.get(5).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/lower_frame_turntable"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(2).internalElement.get(6).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/limit_switch"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(2).internalElement.get(7).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/limit_switch"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(2).internalElement.get(8).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/motor"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(2).internalElement.get(9).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/motor"
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(3).internalElement.get(0).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/tool_holder_bracket"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(3).internalElement.get(1).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/tool_holder"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(3).internalElement.get(2).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/processing_tool"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(3).internalElement.get(3).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/processing_tool"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(3).internalElement.get(4).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/processing_tool"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(3).internalElement.get(5).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/stator"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(3).internalElement.get(6).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/limit_switch"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(3).internalElement.get(7).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/limit_switch"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(3).internalElement.get(8).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/limit_switch"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(3).internalElement.get(9).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/motor"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(3).internalElement.get(10).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/motor"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(3).internalElement.get(11).refBaseSystemUnitPath = "IAF_ModelSystemUnitClassLib/parts/motor"
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertTrue(true)
	}
	
	override protected cleanup() {
		// nothing to cleanup
	}

	override protected createChangePropagationSpecifications() {
		return #[new CombinedCAEXChangePropagationSpecification]
	}
	
	override protected setup() {
		val docRootCAEX = preloadExistingCAEXModel("resources/fischertechnik_v11_quelle.caex")	
		createAndSynchronizeModel(CAEX_MODEL_NAME.projectCAEXModelPath, docRootCAEX)
	}
}