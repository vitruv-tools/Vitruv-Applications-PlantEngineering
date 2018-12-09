package tools.vitruv.applications.automationml.tests.amlutils

import caex.caex30.caex.SystemUnitClassLib
import caex.caex30.caex.InstanceHierarchy
import tools.vitruv.applications.automationml.utils.propertybasedtags.PropertyTags
import caex.caex30.caex.Attribute

class CAEXAttributeModelFactory extends CAEXModelFactory {
	static def SystemUnitClassLib createSystemUnitClassLib(boolean withSecondLayer, String tag) {
		val systemLib = caexFactory.createSystemUnitClassLib
		systemLib.name = "BspSystemLib"
		val systemClass = caexFactory.createSystemUnitClass
		systemClass.name = "BspSystemClass"
		systemLib.systemUnitClass.add(systemClass)
		
		if(withSecondLayer) {
			val secondClass = caexFactory.createSystemUnitClass
			secondClass.name = "AnotherSystemClass"
			systemClass.systemUnitClass.add(secondClass)
		}
		
		if(!tag.isNullOrEmpty) {
			systemClass.attribute.add(createAttribute(tag, false))
		}
		
		return systemLib
	}
	
	static def SystemUnitClassLib createSystemUnitClassLibWithInternalElement(String tag) {
		val systemLib = caexFactory.createSystemUnitClassLib
		systemLib.name = "BspSystemLib"
		
		val systemClass = caexFactory.createSystemUnitClass
		systemClass.name = "BspSystemClass"
		systemLib.systemUnitClass.add(systemClass)
		
		val internalElement = caexFactory.createInternalElement
		internalElement.name = "BspElem"
		systemClass.internalElement.add(internalElement)
		
		val secondClass = caexFactory.createSystemUnitClass
		secondClass.name = "AnotherSystemClass"
		systemLib.systemUnitClass.add(secondClass)
		
		if(!tag.isNullOrEmpty) {
			secondClass.attribute.add(createAttribute(tag, false))
		}
		
		return systemLib
	}
	
	static def SystemUnitClassLib createSystemUnitClassLibWithMoreClasses(boolean propertyTwice) {
		val systemLib = caexFactory.createSystemUnitClassLib
		systemLib.name = "BspSystemLib"
		val systemClass = caexFactory.createSystemUnitClass
		systemClass.name = "BspSystemClass"
		systemLib.systemUnitClass.add(systemClass)
		val secondClass = caexFactory.createSystemUnitClass
		secondClass.name = "AnotherSystemClass"
		systemLib.systemUnitClass.add(secondClass)
		
		systemClass.attribute.add(createAttribute(PropertyTags.COMPOSITION, false))
		systemClass.attribute.add(createAttribute(PropertyTags.MATERIAL, false))
		secondClass.attribute.add(createAttribute(PropertyTags.TECHNOLOGY, false))
		
		if(propertyTwice) {
			secondClass.attribute.add(createAttribute(PropertyTags.COMPOSITION, true))
		} else {
			secondClass.attribute.add(createAttribute(PropertyTags.BEHAVIOUR, false))
		}
		
		return systemLib
	}
	
	static def InstanceHierarchy createInstanceHierarchy() {
		val instanceHierarchy = caexFactory.createInstanceHierarchy
		instanceHierarchy.name = "BspHierarchy"
		val internalElem = caexFactory.createInternalElement
		internalElem.name = "BspElem"
		instanceHierarchy.internalElement.add(internalElem)
		return instanceHierarchy
	}
	
	static def Attribute createAttribute(String tag, boolean variation) {
		val newAttribute = caexFactory.createAttribute
		
		if (tag.equals(PropertyTags.BEHAVIOUR)) {
			newAttribute.name = "someBehaviourProperty"
			newAttribute.value = "DreiGradDrehen"					
		} else if (tag.equals(PropertyTags.SAFTY)) {
			newAttribute.name = "someSaftyProperty"
			newAttribute.value = "ZweiMeterAbstand"					
		} else if (tag.equals(PropertyTags.COMPOSITION)) {
			newAttribute.name = "someCompositionProperty"
			if(variation) {
				newAttribute.value = "VierBauteile"		
			} else {
				newAttribute.value = "DreiBauteile"
			}		
		} else if (tag.equals(PropertyTags.MATERIAL)) {
			newAttribute.name = "someMaterialProperty"
			newAttribute.value = "Blech"					
		} else if (tag.equals(PropertyTags.TECHNOLOGY)) {
			newAttribute.name = "someTechnologyProperty"
			newAttribute.value = "VielVerbrauch"					
		} else if (tag.equals(PropertyTags.RULE)) {
			newAttribute.name = "someRuleProperty"
			newAttribute.value = "MonatlicheVisitation"					
		}
		
		return newAttribute
	}
}