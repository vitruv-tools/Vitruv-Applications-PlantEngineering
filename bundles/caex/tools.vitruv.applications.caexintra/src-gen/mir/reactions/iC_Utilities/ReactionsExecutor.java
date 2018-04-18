package mir.reactions.iC_Utilities;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;
import tools.vitruv.extensions.dslsruntime.reactions.RoutinesFacadesProvider;

@SuppressWarnings("all")
class ReactionsExecutor extends AbstractReactionsExecutor {
  public ReactionsExecutor() {
    super(new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain(), 
    	new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain());
  }
  
  protected RoutinesFacadesProvider createRoutinesFacadesProvider() {
    return new mir.routines.iC_Utilities.RoutinesFacadesProvider();
  }
  
  protected void setup() {
    
  }
}
