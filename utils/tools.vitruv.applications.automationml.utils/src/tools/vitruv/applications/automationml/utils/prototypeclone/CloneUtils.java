package tools.vitruv.applications.automationml.utils.prototypeclone;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import caex.caex30.caex.Attribute;
import caex.caex30.caex.ExternalInterface;
import caex.caex30.caex.InterfaceClass;
import caex.caex30.caex.InternalElement;
import caex.caex30.caex.SystemUnitClass;

public class CloneUtils {
	// Filtert aus einer Liste von InternalElements diejenigen heraus, bei denen bereits die Angaben im Prototyp zu ExternalInterfaces überschrieben wurden
	//TODO Problem: bereits geänderte systemUnitClass wird übergeben!!
	public static List<InternalElement> filterChangedClones(List<InternalElement> internalElements, SystemUnitClass systemUnitClass) {
		List<InternalElement> filteredList = new LinkedList<InternalElement>();
		for(InternalElement currInternalElement : internalElements) {
			if(checkInternalElementForChanges(currInternalElement, systemUnitClass)) {
				filteredList.add(currInternalElement);
			}
		}
		return filteredList;
	}
		
	//TODO Problem wie oben
	public static boolean hasCloneNotChanged(InternalElement internalElement, SystemUnitClass systemUnitClass) {
		return checkInternalElementForChanges(internalElement, systemUnitClass);
	}
	
	// Überprüft ein InternalElement, ob die Angaben aus dem Prototyp zu ExternalInterfaces bereits überschrieben wurden (true: unverändert)
	private static boolean checkInternalElementForChanges(InternalElement internalElement, SystemUnitClass systemUnitClass) {
		EList<InterfaceClass> interfacesElement = internalElement.getExternalInterface();
		EList<InterfaceClass> interfacesClass = systemUnitClass.getExternalInterface();
		if(interfacesElement.size() != interfacesClass.size()) {
			return false;
		}
			
		for(int i=0; i<interfacesElement.size(); i++) {
			if(interfacesElement.get(i) instanceof ExternalInterface) {
				if(interfacesClass.get(i) instanceof ExternalInterface) {
					if(!checkExternalInterfaceForChanges((ExternalInterface)interfacesElement.get(i), (ExternalInterface)interfacesClass.get(i))) {
						return false;
					}
				} else {
					return false;
				}
			} else {
				if(interfacesClass.get(i) instanceof ExternalInterface) {
					return false;
				}
			}
		}
			
		return true;
	}
		
	// Hilfsmethode: divide et impera InternalElement -> ExternalInterface
	private static boolean checkExternalInterfaceForChanges(ExternalInterface interfaceFromElement, ExternalInterface interfaceFromClass) {
		if(!interfaceFromElement.getID().equals(interfaceFromClass.getID())) {
			return false;
		}
		if(!interfaceFromElement.getName().equals(interfaceFromClass.getName())) {
			return false;
		}
		if(!interfaceFromElement.getRefBaseClassPath().equals(interfaceFromClass.getRefBaseClassPath())) {
			return false;
		}
			
		EList<Attribute> attributesElement = interfaceFromElement.getAttribute();
		EList<Attribute> attributesClass = interfaceFromClass.getAttribute();
		if(attributesElement.size() != attributesClass.size()) {
			return false;
		}
			
		for(int i=0; i<attributesElement.size(); i++) {
			if(!checkAttributeForChanges(attributesElement.get(i), attributesClass.get(i))) {
				return false;
			}
		}
			
		return true;
	}
		
	// Hilfsmethode: divide et impera ExternalInterface -> Attribute
	private static boolean checkAttributeForChanges(Attribute attributeFromElement, Attribute attributeFromClass) {
		if(!attributeFromElement.getName().equals(attributeFromClass.getName())) {
			return false;
		}
		if(!attributeFromElement.getAttributeDataType().equals(attributeFromClass.getAttributeDataType())) {
			return false;
		}
		if(!attributeFromElement.getValue().equals(attributeFromClass.getValue())) {
			return false;
		}
		
		EList<Attribute> attributesElement = attributeFromElement.getAttribute();
		EList<Attribute> attributesClass = attributeFromClass.getAttribute();
		if(attributesElement.size() != attributesClass.size()) {
			return false;
		}
		
		for(int i=0; i<attributesElement.size(); i++) {
			if(!checkAttributeForChanges(attributesElement.get(i), attributesClass.get(i))) {
				return false;
			}
		}
		
		return true;
	}
}
