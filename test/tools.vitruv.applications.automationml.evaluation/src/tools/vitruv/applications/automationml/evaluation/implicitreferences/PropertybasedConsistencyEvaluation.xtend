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

class PropertybasedConsistencyEvaluation extends AbstractAMLEvaluationTest {
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
	
	@Test
	def evaluateImplizitReferences() {
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
}