package mir.reactions.iC_Utilities;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsChangePropagationSpecification;
import tools.vitruv.framework.change.processing.ChangePropagationSpecification;

@SuppressWarnings("all")
public class IC_UtilitiesChangePropagationSpecification extends AbstractReactionsChangePropagationSpecification implements ChangePropagationSpecification {
  public IC_UtilitiesChangePropagationSpecification() {
    super(new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain(), 
    	new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain());
  }
  
  protected void setup() {
    this.addChangeMainprocessor(new mir.reactions.iC_Utilities.ReactionsExecutor());
  }
}
