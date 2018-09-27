package tools.vitruv.applications.automationml.utils.caexresolver;

import java.util.LinkedList;
import java.util.List;
import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.InternalElement;

public class CAEXInternalElementResolver {
	// liefert alle internen Elemente, die eine SystemUnitClass referenzieren
	//TODO überhaupt notwendig??
	/*public static List<InternalElement> getInternalElementsForSystemUnitClass(String nameOfSystemUnitClass, CAEXFile caexFile) {
		List<InternalElement> internalElements = null;	//TODO EcoreUtil2.getAllContentsOfType(caexFile, InternalElement)
		LinkedList<InternalElement> relevantElements = new LinkedList<InternalElement>();
		
		for(int i = 0; i < internalElements.size(); i++) {
			if(internalElements.get(i).getRefBaseSystemUnitPath().equals(nameOfSystemUnitClass)) {
				relevantElements.addFirst(internalElements.get(i));
			}
		}
		
		return relevantElements;
	}*/
}
