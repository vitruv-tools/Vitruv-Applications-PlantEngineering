package tools.vitruv.applications.automationml.utils.clonechangestests;

import java.util.LinkedList;
import java.util.List;

import caex.caex30.caex.InternalElement;
import caex.caex30.caex.SystemUnitClass;

public class InternalElementChecker {
	public static boolean checkInternalElementForChanges(InternalElement internalElement, SystemUnitClass systemUnitClass) {
		//TODO
		return true;
	}
	
	public static List<InternalElement> filterInternalElementsForChanges(List<InternalElement> internalElements, SystemUnitClass systemUnitClass) {
		List<InternalElement> filteredList = new LinkedList<InternalElement>();
		for(InternalElement currInternalElement : internalElements) {
			if(checkInternalElementForChanges(currInternalElement, systemUnitClass)) {
				filteredList.add(currInternalElement);
			}
		}
		return filteredList;
	}
}
