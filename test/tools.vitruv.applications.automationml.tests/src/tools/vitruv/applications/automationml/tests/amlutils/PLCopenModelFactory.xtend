package tools.vitruv.applications.automationml.tests.amlutils

import org.plcopen.xml.tc60201.Tc60201Factory
import org.plcopen.xml.tc60201.TypesType
import org.plcopen.xml.tc60201.PouType1

class PLCopenModelFactory {
	protected static def Tc60201Factory plcopenFactory() {
		return Tc60201Factory.eINSTANCE
	}
	
	static def TypesType createPLCStructure(boolean extended) {
		val type = plcopenFactory.createTypesType
		val pous = plcopenFactory.createPousType
		val pou = plcopenFactory.createPouType1
		
		type.name = "bspType"
		pous.name = "bspPous"
		pou.name = "bspPou"
		
		type.pous = pous
		pous.pou.add(pou)
		
		if(extended) {
			val secondPou = plcopenFactory.createPouType1
			secondPou.name = "anotherPou"
			pous.pou.add(secondPou)
		}
		
		return type
	}
	
	static def PouType1 createPou() {
		return plcopenFactory.createPouType1
	}
}