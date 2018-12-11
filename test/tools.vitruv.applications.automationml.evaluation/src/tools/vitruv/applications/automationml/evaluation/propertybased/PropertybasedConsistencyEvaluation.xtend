package tools.vitruv.applications.automationml.evaluation.propertybased

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import org.eclipse.emf.compare.EMFCompare

class PropertybasedConsistencyEvaluation extends AbstractAMLEvaluationTest {
	def createBasicModel() {
		val fileA = preloadExistingCAEXModel("resources/small_demo_example.caex")
		val fileB = preloadExistingCAEXModel("resources/big_demo_example.caex")
		//val fileC = preloadExistingCAEXModel("resources/empty_demo_example.caex")
		
		val scope0 = EMFCompare.createDefaultScope(fileA, fileB)
		val comparison = EMFCompare.builder.build.compare(scope0)

		System.out.println("Konflikte: " + comparison.conflicts.size)
		System.out.println("Unterschiede: " + comparison.differences.size)
		System.out.println("Gleichheiten: " + comparison.equivalences.size)
		System.out.println("Treffer: " + comparison.matches.size)
	}
	
	@Test
	def testSomeSmallThings() {
		createBasicModel()
		assertTrue(true)
	}
}