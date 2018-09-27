package tools.vitruv.applications.automationml.utils.caexresolver;

import caex.caex30.caex.ExternalInterface;

public class CAEXInheritanceResolver {
	// überprüft, ob ein Interface aus der InterfaceBibliothek (externalInterface) im Vererbungsbaum unter einem anderen Interface (interfaceClass) steht
	public static boolean isInterfaceInstanceOf(ExternalInterface externalInterface, String interfaceClass) {
		ExternalInterface currInterface = externalInterface;
		while(currInterface != null) {
			if(currInterface.getName().equals(interfaceClass)) {
				return true;
			}
			if(currInterface.getBaseClass() instanceof ExternalInterface) {
				currInterface = (ExternalInterface)currInterface.getBaseClass();
			} else {
				return false;
			}
		}
		return false;
	}
}
