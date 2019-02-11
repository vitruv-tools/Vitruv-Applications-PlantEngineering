package tools.vitruv.applications.automationml.tests.plcopenreactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull
import tools.vitruv.applications.automationml.tests.amlutils.PLCopenModelFactory

class PLCopenStructureTests extends AbstractPLCopenReactionsTest {
	def createBasicModel(boolean extended) {
		val type = PLCopenModelFactory.createPLCStructure(extended)
		PLCopenRootElement.types = type
		
		PLCopenRootElement.saveAndSynchronizeChanges
	}
	
	//@Test
	def testRemoveLonelyPou() {
		createBasicModel(false)
		
		PLCopenRootElement.types.pous.pou.remove(0)
		PLCopenRootElement.saveAndSynchronizeChanges
		
		assertEquals(1, PLCopenRootElementVirtualModel.types.pous.pou.size)
	}
	
	//@Test
	def testRemovePairwisePou() {
		createBasicModel(true)
		
		PLCopenRootElement.types.pous.pou.remove(0)
		PLCopenRootElement.saveAndSynchronizeChanges
		
		assertEquals(1, PLCopenRootElementVirtualModel.types.pous.pou.size)
	}
	
	//@Test
	def testRemovePous() {
		createBasicModel(false)
		
		PLCopenRootElement.types.pous = null
		PLCopenRootElement.saveAndSynchronizeChanges
		
		assertNotNull(PLCopenRootElementVirtualModel.types.pous)
		assertEquals(1, PLCopenRootElementVirtualModel.types.pous.pou.size)
	}
	
	//@Test
	def testRemoveType() {
		createBasicModel(false)
		
		PLCopenRootElement.types = null
		PLCopenRootElement.saveAndSynchronizeChanges
		
		assertNotNull(PLCopenRootElementVirtualModel.types)
		assertNotNull(PLCopenRootElementVirtualModel.types.pous)
		assertEquals(1, PLCopenRootElementVirtualModel.types.pous.pou.size)
	}
}