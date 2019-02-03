package tools.vitruv.applications.automationml.utils.externalInterfaceUtils;

import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.ExternalInterface;
import caex.caex30.caex.InterfaceClass;
import caex.caex30.caex.InternalElement;
import caex.caex30.caex.SystemUnitClass;

/**
 * class with some methods to search for interfaces in SystemUnitClasses and InternalElements
 * @author A.Lang
 */
public class InterfaceContainmentUtils {
	/**
	 * method to check, if an specific ExternalInterface is contained in a specific SystemUnitClass
	 * interfaceType or refBaseClassPath is necessary
	 * @return true if ExternalInterface is contained
	 */
	public static boolean checkSystemClassForExternalInterface(SystemUnitClass systemClass, ExternalInterfaceType interfaceType, String refBaseClassPath, CAEXFile root) {
		for(InterfaceClass currInterface : systemClass.getExternalInterface()) {
			if(currInterface instanceof ExternalInterface) {
				switch (interfaceType) {
					case PLCOPEN:
						if(InterfaceTypeUtils.isInterfaceInstanceOfPLCopenInterface((ExternalInterface)currInterface, root)) {
							return true;
						}
						break;
					case COLLADA:
						if(InterfaceTypeUtils.isInterfaceInstanceOfColladaInterface((ExternalInterface)currInterface, root)) {
							return true;
						}
						break;
					case DEFAULT:
						if(InterfaceTypeUtils.isInterfaceInstanceOfSpecificInterface((ExternalInterface)currInterface, refBaseClassPath, root)) {
							return true;
						}
						break;
				}
			}
		}
		return false;
	}
	
	/**
	 * method to check, if an specific ExternalInterface is contained in a specific SystemUnitClass
	 * @return true if ExternalInterface is contained
	 */
	public static boolean checkSystemClassForExternalInterface(SystemUnitClass systemClass, ExternalInterfaceType interfaceType, CAEXFile root) {
		return checkSystemClassForExternalInterface(systemClass, interfaceType, "", root);
	}
	
	/**
	 * method to check, if an specific ExternalInterface is contained in a specific SystemUnitClass
	 * @return true if ExternalInterface is contained
	 */
	public static boolean checkSystemClassForExternalInterface(SystemUnitClass systemClass, String refBaseClassPath, CAEXFile root) {
		return checkSystemClassForExternalInterface(systemClass, ExternalInterfaceType.DEFAULT, refBaseClassPath, root);
	}
	
	/**
	 * method to search for an specific ExternalInterface in a specific SystemUnitClass
	 * interfaceType or refBaseClassPath is necessary
	 */
	public static ExternalInterface getExternalInterfaceFromSystemClass(SystemUnitClass systemClass, ExternalInterfaceType interfaceType, String refBaseClassPath, CAEXFile root) {
		for(InterfaceClass currInterface : systemClass.getExternalInterface()) {
			if(currInterface instanceof ExternalInterface) {
				switch (interfaceType) {
					case PLCOPEN:
						if(InterfaceTypeUtils.isInterfaceInstanceOfPLCopenInterface((ExternalInterface)currInterface, root)) {
							return (ExternalInterface)currInterface;
						}
						break;
					case COLLADA:
						if(InterfaceTypeUtils.isInterfaceInstanceOfColladaInterface((ExternalInterface)currInterface, root)) {
							return (ExternalInterface)currInterface;
						}
						break;
					case DEFAULT:
						if(InterfaceTypeUtils.isInterfaceInstanceOfSpecificInterface((ExternalInterface)currInterface, refBaseClassPath, root)) {
							return (ExternalInterface)currInterface;
						}
						break;
				}
			}
		}
		return null;
	}
		
	/**
	 * method to search for an specific ExternalInterface in a specific SystemUnitClass
	 */
	public static ExternalInterface getExternalInterfaceFromSystemClass(SystemUnitClass systemClass, ExternalInterfaceType interfaceType, CAEXFile root) {
		return getExternalInterfaceFromSystemClass(systemClass, interfaceType, "", root);
	}
		
	/**
	 * method to search for an specific ExternalInterface in a specific SystemUnitClass
	 */
	public static ExternalInterface getExternalInterfaceFromSystemClass(SystemUnitClass systemClass, String refBaseClassPath, CAEXFile root) {
		return getExternalInterfaceFromSystemClass(systemClass, ExternalInterfaceType.DEFAULT, refBaseClassPath, root);
	}
	
	/**
	 * method to search for an specific ExternalInterface in a specific InternalElement
	 */
	public static ExternalInterface getExternalInterfaceFromInternalElement(InternalElement internalElement, String externalInterfaceName) {
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
