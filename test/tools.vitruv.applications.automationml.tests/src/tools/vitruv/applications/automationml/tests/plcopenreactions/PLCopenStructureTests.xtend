package tools.vitruv.applications.automationml.tests.plcopenreactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

class PLCopenStructureTests extends AbstractPLCopenReactionsTest {
	def createBasicModel(){
		val type = plcopenFactory.createTypesType
		val pous = plcopenFactory.createPousType
		val pou = plcopenFactory.createPouType1
		
		pous.pou.add(pou)
		type.pous = pous
		PLCopenRootElement.types = type
		
		PLCopenRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def testRemovePou() {
		createBasicModel
		
		PLCopenRootElement.types.pous.pou.remove(0)
		PLCopenRootElement.saveAndSynchronizeChanges
		
		assertEquals(1, PLCopenRootElementVirtualModel.types.pous.pou.size)
	}
	
	@Test
	def testRemovePous() {
		createBasicModel
		
		PLCopenRootElement.types.pous = null
		PLCopenRootElement.saveAndSynchronizeChanges
		
		assertNotNull(PLCopenRootElementVirtualModel.types.pous)
	}
	
	@Test
	def testRemoveType() {
		createBasicModel
		
		PLCopenRootElement.types = null
		PLCopenRootElement.saveAndSynchronizeChanges
		
		assertNotNull(PLCopenRootElementVirtualModel.types)
	}
	
	@Test
	def testCreatePou() {
		createBasicModel
		
		PLCopenRootElement.types.pous.pou.add(plcopenFactory.createPouType1)
		PLCopenRootElement.saveAndSynchronizeChanges
		
		assertEquals(1, PLCopenRootElementVirtualModel.types.pous.pou.size)
	}
}