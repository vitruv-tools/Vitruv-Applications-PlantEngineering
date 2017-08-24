package de.fzi.intramodelconsistency.caex

import CAEX.DocumentRoot
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import tools.vitruv.framework.correspondence.CorrespondenceModel
import tools.vitruv.framework.tuid.AttributeTuidCalculatorAndResolver
import tools.vitruv.framework.util.VitruviusConstants

class CAEXIntraConsistencyTools {	
	
	static def generateTuidFromPath(CorrespondenceModel cm, EObject anyElem, String path) {
		var TUID_SEPARATOR = AttributeTuidCalculatorAndResolver.SUBDIVIDER;
		var TUID_SEGMENT_SEPARATOR = VitruviusConstants.tuidSegmentSeperator;
		
		if(path === null || path == "") return null		
		var root = EcoreUtil.getRootContainer(anyElem) as DocumentRoot;
		var fileTuid = cm.calculateTuidFromEObject(root.CAEXFile);
		
		var split = path.split("/");
		
		var finalStr = fileTuid + TUID_SEGMENT_SEPARATOR + "systemUnitClassLib-_-SystemUnitClassLib-_-name=" + split.get(0)
		for(var i=1; i<split.length;i++)
			finalStr += TUID_SEGMENT_SEPARATOR + "systemUnitClass" + TUID_SEPARATOR + "SystemUnitClass" + TUID_SEPARATOR + "name=" + split.get(i)
		return finalStr
	}
}