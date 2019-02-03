package tools.vitruv.applications.automationml.utils.aml;

import org.eclipse.xtext.EcoreUtil2;

import caex.caex30.caex.Attribute;
import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.ExternalInterface;
import caex.caex30.caex.InternalElement;
import caex.caex30.caex.SystemUnitClass;
import tools.vitruv.applications.automationml.utils.externalInterfaceUtils.ExternalInterfaceType;
import tools.vitruv.applications.automationml.utils.externalInterfaceUtils.InterfaceContainmentUtils;

/**
 * class with some methods to search for attributes in caex files
 * @author A.Lang
 */
public class CAEXUtils {
	/**
	 * method to search for a specific attribute in an ExternalInterface
	 */
	public static Attribute getAttributeInExternalInterface(ExternalInterface externalInterface, String attributeName) {
		for(Attribute currAttribute : externalInterface.getAttribute()) {
			if(currAttribute.getName().equals(attributeName)) {
				return currAttribute;
			}
		}
		return null;
	}
	
	/**
	 * method to search for a URI attribute in an ExternalInterface for collada files
	 */
	public static Attribute getUriAttributeInColladaInterface(SystemUnitClass systemClass) {
		Class<CAEXFile> caexFile = CAEXFile.class;
		CAEXFile root = EcoreUtil2.getContainerOfType(systemClass, caexFile);
		ExternalInterface externalInterface = InterfaceContainmentUtils.getExternalInterfaceFromSystemClass(systemClass, ExternalInterfaceType.COLLADA, root);
		return getAttributeInExternalInterface(externalInterface, "refUri");
	}
	
	/**
	 * method to search for a URI attribute in an ExternalInterface for plcopen files
	 */
	public static Attribute getUriAttributeInPLCopenInterface(SystemUnitClass systemClass) {
		Class<CAEXFile> caexFile = CAEXFile.class;
		CAEXFile root = EcoreUtil2.getContainerOfType(systemClass, caexFile);
		ExternalInterface externalInterface = InterfaceContainmentUtils.getExternalInterfaceFromSystemClass(systemClass, ExternalInterfaceType.PLCOPEN, root);
		return getAttributeInExternalInterface(externalInterface, "refUri");
	}
	
	/**
	 * method to search for a specific attribute in an SystemUnitClass (-> Prototype)
	 */
	public static Attribute getAttributeInPrototype(String name, SystemUnitClass systemClass) {
		return getAttribute(name, systemClass);
	}
	
	/**
	 * method to search for a specific attribute in an InternalElement (-> Clone)
	 */
	public static Attribute getAttributeInClone(String name, InternalElement element) {
		return getAttribute(name, element);
	}
	
	private static Attribute getAttribute(String name, SystemUnitClass object) {
		for(Attribute currAtt : object.getAttribute()) {
			if(currAtt.getName().equals(name)) {
				return currAtt;
			}
		}
		return null;
	}
}
