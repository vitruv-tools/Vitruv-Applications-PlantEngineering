package tools.vitruv.applications.automationml.utils.externalInterfaceUtils;

import caex.caex30.caex.ExternalInterface;
import caex.caex30.caex.InterfaceClass;
import caex.caex30.caex.InternalElement;
import caex.caex30.caex.SystemUnitClass;
import tools.vitruv.applications.automationml.utils.caexresolver.CAEXInheritanceResolver;

public class SystemUnitClassForInterfaceChecker {
	public static enum InterfaceType{
		PLCOPEN,
		COLLADA,
		DEFAULT
	}
	
	// Prüft, ob ein bestimmtes Interface in einer Klasse enthalten ist (true: ist enthalten)
	// Falls das Interface ein PLCopen oder ein Collada Interface sein soll, wird refBaseClassPath nicht berücksichtigt, nur bei Default
	// Auch Vererbung wird berücksichtigt
	public static boolean checkForExternalInterface(SystemUnitClass systemClass, InterfaceType interfaceType, String refBaseClassPath) {
		for(InterfaceClass currInterface : systemClass.getExternalInterface()) {
			if(currInterface instanceof ExternalInterface) {
				switch (interfaceType) {
					case PLCOPEN:
						if(CAEXInheritanceResolver.isInstanceOfPLCopenInterface((ExternalInterface)currInterface)) {
							return true;
						}
						break;
					case COLLADA:
						if(CAEXInheritanceResolver.isInstanceOfColladaInterface((ExternalInterface)currInterface)) {
							return true;
						}
						break;
					case DEFAULT:
						if(CAEXInheritanceResolver.isInstanceOfSpecificInterface((ExternalInterface)currInterface, refBaseClassPath)) {
							return true;
						}
						break;
				}
			}
		}
		return false;
	}
	
	public static boolean checkForExternalInterface(SystemUnitClass systemClass, InterfaceType interfaceType) {
		return checkForExternalInterface(systemClass, interfaceType, "");
	}
	
	public static boolean checkForExternalInterface(SystemUnitClass systemClass, String refBaseClassPath) {
		return checkForExternalInterface(systemClass, InterfaceType.DEFAULT, refBaseClassPath);
	}
	
	// Liefert ein bestimmtes Interface zurück, falls vorhanden
	// Falls das Interface ein PLCopen oder ein Collada Interface sein soll, wird refBaseClassPath nicht berücksichtigt, nur bei Default
	// Auch Vererbung wird berücksichtigt
	public static ExternalInterface getExternalInterface(SystemUnitClass systemClass, InterfaceType interfaceType, String refBaseClassPath) {
		for(InterfaceClass currInterface : systemClass.getExternalInterface()) {
			if(currInterface instanceof ExternalInterface) {
				switch (interfaceType) {
					case PLCOPEN:
						if(CAEXInheritanceResolver.isInstanceOfPLCopenInterface((ExternalInterface)currInterface)) {
							return (ExternalInterface)currInterface;
						}
						break;
					case COLLADA:
						if(CAEXInheritanceResolver.isInstanceOfColladaInterface((ExternalInterface)currInterface)) {
							return (ExternalInterface)currInterface;
						}
						break;
					case DEFAULT:
						if(CAEXInheritanceResolver.isInstanceOfSpecificInterface((ExternalInterface)currInterface, refBaseClassPath)) {
							return (ExternalInterface)currInterface;
						}
						break;
				}
			}
		}
		return null;
	}
		
	public static ExternalInterface getExternalInterface(SystemUnitClass systemClass, InterfaceType interfaceType) {
		return getExternalInterface(systemClass, interfaceType, "");
	}
		
	public static ExternalInterface getExternalInterface(SystemUnitClass systemClass, String refBaseClassPath) {
		return getExternalInterface(systemClass, InterfaceType.DEFAULT, refBaseClassPath);
	}
	
	/*public static ExternalInterface getExternalInterfaceFromClass(SystemUnitClass systemClass, String externalInterfaceName) {
		for(InterfaceClass currInterface : systemClass.getExternalInterface()) {
			if(currInterface instanceof ExternalInterface) {
				if(currInterface.getName().equals(externalInterfaceName)) {
					return (ExternalInterface)currInterface;
				}
			}
		}
		return null;
	}*/
	
	// Methode, um ein internalElement zu durchsuchen, ob ein ExternalInterface enthalten ist
	public static ExternalInterface getExternalInterfaceFromElement(InternalElement internalElement, String externalInterfaceName) {
		for(InterfaceClass currInterface : internalElement.getExternalInterface()) {
			if(currInterface instanceof ExternalInterface) {
				if(currInterface.getName().equals(externalInterfaceName)) {
					return (ExternalInterface)currInterface;
				}
			}
		}
		return null;
	}
}
