package tools.vitruv.applications.automationml.utils.aml;

import org.eclipse.xtext.EcoreUtil2;

import caex.caex30.caex.Attribute;
import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.ExternalInterface;
import caex.caex30.caex.SystemUnitClass;
import tools.vitruv.applications.automationml.utils.externalInterfaceUtils.ExternalInterfaceType;
import tools.vitruv.applications.automationml.utils.externalInterfaceUtils.SystemUnitClassForInterfaceChecker;

public class CAEXUtils {
	// sucht bestimmtes Attribut in einem ExternalInterface
	public static Attribute getAttributeInExternalInterface(ExternalInterface externalInterface, String attributeName) {
		for(Attribute currAttribute : externalInterface.getAttribute()) {
			if(currAttribute.getName().equals(attributeName)) {
				return currAttribute;
			}
		}
		return null;
	}
	
	public static Attribute getUriAttributeInColladaInterface(SystemUnitClass systemClass) {
		Class<CAEXFile> caexFile = CAEXFile.class;
		CAEXFile root = EcoreUtil2.getContainerOfType(systemClass, caexFile);
		ExternalInterface externalInterface = SystemUnitClassForInterfaceChecker.getExternalInterface(systemClass, ExternalInterfaceType.COLLADA, root);
		return getAttributeInExternalInterface(externalInterface, "refUri");
	}
	
	public static Attribute getUriAttributeInPLCopenInterface(SystemUnitClass systemClass) {
		Class<CAEXFile> caexFile = CAEXFile.class;
		CAEXFile root = EcoreUtil2.getContainerOfType(systemClass, caexFile);
		ExternalInterface externalInterface = SystemUnitClassForInterfaceChecker.getExternalInterface(systemClass, ExternalInterfaceType.PLCOPEN, root);
		return getAttributeInExternalInterface(externalInterface, "refUri");
	}
}
