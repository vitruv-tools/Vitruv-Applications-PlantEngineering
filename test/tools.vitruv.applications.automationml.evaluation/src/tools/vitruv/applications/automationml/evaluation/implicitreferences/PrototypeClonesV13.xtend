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
import tools.vitruv.applications.automationml.attributechanges.CombinedAttributeChangePropagationSpecification

class PrototypeClonesV13 extends AbstractAMLTest {
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
	
	@Test
	def evaluate() {
		val bla = CAEXRootElement
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertTrue(true)
	}
	
	override protected cleanup() {
		// nothing to cleanup
	}

	override protected createChangePropagationSpecifications() {
		return #[new CombinedAttributeChangePropagationSpecification]
	}
	
	override protected setup() {
		//Klassen Markierung
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)

		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		
		userInteractor.addNextSingleSelection(2)

		val docRootCAEX = preloadExistingCAEXModel("resources/fischertechnik_v13_without.caex")	
		createAndSynchronizeModel(CAEX_MODEL_NAME.projectCAEXModelPath, docRootCAEX)
	}
}