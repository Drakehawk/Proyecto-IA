package ddosattack.relogo

import repast.simphony.relogo.factories.AbstractReLogoGlobalsAndPanelFactory

public class UserGlobalsAndPanelFactory extends AbstractReLogoGlobalsAndPanelFactory{
	public void addGlobalsAndPanelComponents(){
		
		addSliderWL("numHosts", "Number of hosts", 1, 1, 20, 10)
		addSliderWL("numZombieHosts", "Number of Zombie Hosts", 1, 1, 10, 5)
		
		addMonitorWL("remainingHosts", "Remaining hosts", 5)
	}
}