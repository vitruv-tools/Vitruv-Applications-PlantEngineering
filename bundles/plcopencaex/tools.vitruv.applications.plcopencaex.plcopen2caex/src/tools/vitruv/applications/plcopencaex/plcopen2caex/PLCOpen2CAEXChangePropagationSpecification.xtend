package tools.vitruv.applications.plcopencaex.plcopen2caex

import tools.vitruv.framework.change.description.TransactionalChange
import tools.vitruv.framework.change.processing.impl.AbstractChangePropagationSpecification
import tools.vitruv.framework.correspondence.CorrespondenceModel

class PLCOpen2CAEXChangePropagationSpecification extends AbstractChangePropagationSpecification {
		// FIXME MK PLCOpen add "extends <<generated spec>>" and remove everything else
	new() {
		super(null)
	}
	
	override doesHandleChange(TransactionalChange arg0, CorrespondenceModel arg1) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getMetamodelPair() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override propagateChange(TransactionalChange arg0, CorrespondenceModel arg1) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}