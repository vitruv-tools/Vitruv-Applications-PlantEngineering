package mir.reactions.iC_ReactToSystemUnitClassChanges;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsChangePropagationSpecification;
import tools.vitruv.framework.change.processing.ChangePropagationSpecification;

@SuppressWarnings("all")
public class IC_ReactToSystemUnitClassChangesChangePropagationSpecification extends AbstractReactionsChangePropagationSpecification implements ChangePropagationSpecification {
  public IC_ReactToSystemUnitClassChangesChangePropagationSpecification() {
    super(new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain(), 
    	new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain());
  }
  
  protected void setup() {
    this.addChangeMainprocessor(new mir.reactions.iC_ReactToSystemUnitClassChanges.ReactionsExecutor());
  }
}
