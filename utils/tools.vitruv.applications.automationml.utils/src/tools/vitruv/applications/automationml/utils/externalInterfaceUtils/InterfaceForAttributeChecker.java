package tools.vitruv.applications.automationml.utils.externalInterfaceUtils;

import caex.caex30.caex.Attribute;
import caex.caex30.caex.ExternalInterface;

public class InterfaceForAttributeChecker {
	
	// sucht bestimmtes Attribut in einem ExternalInterface
	public static Attribute getAttributeInExternalInterface(ExternalInterface externalInterface, String attributeName) {
		for(Attribute currAttribute : externalInterface.getAttribute()) {
			if(currAttribute.getName().equals(attributeName)) {
				return currAttribute;
			}
		}
		return null;
	}
}
