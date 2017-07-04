package de.fzi.intramodelconsistency.caex

import CAEX.CAEXPackage
import CAEX.DocumentRoot
import CAEX.SystemUnitClass
import CAEX.util.CAEXResourceFactoryImpl
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil
import tools.vitruv.framework.correspondence.CorrespondenceModel
import tools.vitruv.framework.tuid.AttributeTuidCalculatorAndResolver
import tools.vitruv.framework.util.VitruviusConstants

class CAEXIntraConsistencyTools {
	
	
	static def GenTuidFromPath(CorrespondenceModel cm, EObject anyElem, String path) {
		var TuidSeparator = AttributeTuidCalculatorAndResolver.SUBDIVIDER;
		
		if(path == null || path == "") return null		
		var root = EcoreUtil.getRootContainer(anyElem) as DocumentRoot;
		var fileTuid = cm.calculateTuidFromEObject(root.CAEXFile);
		
		var split = path.split("/");
		
		var finalStr = fileTuid + VitruviusConstants.tuidSegmentSeperator + "systemUnitClassLib-_-SystemUnitClassLib-_-name=" + split.get(0)
		for(var i=1; i<split.length;i++)
			finalStr += VitruviusConstants.tuidSegmentSeperator + "systemUnitClass" + TuidSeparator + "SystemUnitClass" + TuidSeparator + "name=" + split.get(i)
		return finalStr
	}
}