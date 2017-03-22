package mir.reactions;

import tools.vitruv.framework.change.processing.impl.CompositeChangePropagationSpecification;

/**
 * The {@link class tools.vitruv.framework.change.processing.impl.CompositeChangePropagationSpecification} for transformations between the metamodels http://www.plcopen.org/xml/tc6_0201 and http://org.automationml.caex.
 * To add further change processors overwrite the setup method.
 */
public abstract class AbstractChangePropagationSpecificationTc6_0201ToCaex extends CompositeChangePropagationSpecification {
	private final tools.vitruv.framework.util.datatypes.MetamodelPair metamodelPair;
	
	public AbstractChangePropagationSpecificationTc6_0201ToCaex() {
		super(new tools.vitruv.framework.userinteraction.impl.UserInteractor());
		this.metamodelPair = new tools.vitruv.framework.util.datatypes.MetamodelPair("http://www.plcopen.org/xml/tc6_0201", "http://org.automationml.caex");
		setup();
	}
	
	public tools.vitruv.framework.util.datatypes.MetamodelPair getMetamodelPair() {
		return metamodelPair;
	}	
	
	/**
	 * Adds the reactions change processors to this {@link AbstractChangePropagationSpecificationTc6_0201ToCaex}.
	 * For adding further change processors overwrite this method and call the super method at the right place.
	 */
	protected void setup() {
		this.addChangeMainprocessor(new mir.reactions.reactionsTc6_0201ToCaex.plcopen2caex.ExecutorTc6_0201ToCaex(getUserInteracting()));
	}
	
}
