package tools.vitruv.applications.automationml.utils.caexresolver;

import java.util.LinkedList;
import java.util.List;

import org.automationml.caex.caex.CAEXFile;
import org.automationml.caex.caex.InternalElement;
import org.eclipse.xtext.EcoreUtil2;

public class CAEXInternalElementResolver {
	// liefert alle internen Elemente, die eine SystemUnitClass referenzieren
	public static List<InternalElement> getInternalElementsForSystemUnitClass(String nameOfSystemUnitClass, CAEXFile caexFile) {
		List<InternalElement> internalElements = null;//EcoreUtil2.getAllContentsOfType(caexFile, InternalElement)
		LinkedList<InternalElement> relevantElements = new LinkedList<InternalElement>();
		
		for(int i = 0; i < internalElements.size(); i++) {
			if(internalElements.get(i).getRefBaseSystemUnitPath().equals(nameOfSystemUnitClass)) {
				relevantElements.addFirst(internalElements.get(i));
			}
		}
		
		return relevantElements;
	}
}
