package tools.vitruv.applications.automationml.tests.amlutils

import caex.caex30.caex.CAEXFactory
import caex.caex30.caex.SystemUnitClassLib
import caex.caex30.caex.InstanceHierarchy
import caex.caex30.caex.ExternalInterface
import caex.caex30.caex.InterfaceClassLib
import tools.vitruv.applications.automationml.utils.aml.AMLBasicStrings
import caex.caex30.caex.Attribute

class CAEXModelFactory {
	protected static def CAEXFactory caexFactory() {
		return CAEXFactory.eINSTANCE
	}

	static def SystemUnitClassLib createSystemUnitClassLib(boolean withSecondClass, boolean withSecondLayer) {
		val systemLib = caexFactory.createSystemUnitClassLib
		systemLib.name = "BspSystemLib"
		val systemClass = caexFactory.createSystemUnitClass
		systemClass.name = "BspSystemClass"
		systemLib.systemUnitClass.add(systemClass)
		
		if(withSecondClass) {
			val secondClass = caexFactory.createSystemUnitClass
			secondClass.name = "AnotherSystemClass"
			if(withSecondLayer) {
				systemClass.systemUnitClass.add(secondClass)
			} else {
				systemLib.systemUnitClass.add(secondClass)
			}
		}
		
		return systemLib
	}
	
	static def InstanceHierarchy createInstanceHierarchy(boolean withSecondElement, boolean withSecondLayer) {
		val instanceHierarchy = caexFactory.createInstanceHierarchy
		instanceHierarchy.name = "BspHierarchy"
		val internalElem = caexFactory.createInternalElement
		internalElem.name = "BspElem"
		instanceHierarchy.internalElement.add(internalElem)
		
		if(withSecondElement) {
			val secondElem = caexFactory.createInternalElement
			secondElem.name = "anotherElem"
			if(withSecondLayer) {
				internalElem.internalElement.add(secondElem)
			} else {
				instanceHierarchy.internalElement.add(secondElem)
			}
		}
		
		return instanceHierarchy
	}
	
	static def ExternalInterface createSomeExternalInterface() {
		val interface = caexFactory.createExternalInterface
		interface.name = "extIf"
		interface.refBaseClassPath = "BspIfLib/BspIfClass"
		return interface
	}
	
	static def ExternalInterface createPLCopenExternalInterface() {
		val interface = caexFactory.createExternalInterface
		interface.name = "extIfPlc"
		interface.refBaseClassPath = AMLBasicStrings.completePLCopenBaseInterfacePath
		//interface.attribute.add(createRefUriAttribute)
		return interface
	}
	
	static def ExternalInterface createColladaExternalInterface() {
		val interface = caexFactory.createExternalInterface
		interface.name = "extIfCollada"
		interface.refBaseClassPath = AMLBasicStrings.completeColladaBaseInterfacePath
		//interface.attribute.add(createRefUriAttribute)
		return interface
	}
	
	static def Attribute createRefUriAttribute() {
		val attribute = caexFactory.createAttribute
		attribute.name = "refURI"
		attribute.value = ""
		attribute.attributeDataType = "xs:anyURI"
		return attribute
	}
	
	static def InterfaceClassLib createInterfaceClassLib(boolean basicLib) {
		if(basicLib) {
			return createBasicInterfaceClassLib
		}
		
		val interfaceLib = caexFactory.createInterfaceClassLib
		interfaceLib.name = "BspIfLib"
		val interface = caexFactory.createInterfaceClass
		interface.name = "BspIfClass"
		interfaceLib.interfaceClass.add(interface)
		
		return interfaceLib
	}
	
	private static def InterfaceClassLib createBasicInterfaceClassLib() {
		val interfaceLib = caexFactory.createInterfaceClassLib
		val basicInterface = caexFactory.createInterfaceClass
		val basicConnectionInterface = caexFactory.createInterfaceClass
		val basicPLCopenInterface = caexFactory.createInterfaceClass
		val basicColladaInterface = caexFactory.createInterfaceClass
		
		interfaceLib.name = AMLBasicStrings.automationMLInterfaceBaseLibName
		basicInterface.name = AMLBasicStrings.automationMLBaseInterfaceName
		basicConnectionInterface.name = AMLBasicStrings.automationMLExternalBaseInterfaceName
		basicPLCopenInterface.name = AMLBasicStrings.externalPLCopenBaseInterfaceName
		basicColladaInterface.name = AMLBasicStrings.externalColladaBaseInterfaceName
		
		basicConnectionInterface.interfaceClass.add(basicPLCopenInterface)
		basicConnectionInterface.interfaceClass.add(basicColladaInterface)
		basicInterface.interfaceClass.add(basicConnectionInterface)
		interfaceLib.interfaceClass.add(basicInterface)
		
		return interfaceLib
	}
}