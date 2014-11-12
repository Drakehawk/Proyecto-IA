package ddosattack.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import ddosattack.ReLogoObserver;

class UserObserver extends ReLogoObserver{

	@Setup
	def setup(){
		clearAll()
		setDefaultShape(Host,"computer")
		createHosts(numHosts){
			setxy(randomXcor(),randomYcor())
		}
		setDefaultShape(ZombieHost,"zombie")
		createZombieHosts(numZombieHosts){
			setxy(randomXcor(),randomYcor())
		}
	} 	
	
	@Go
	def go(){
		ask(hosts()){
			step()
		}
		ask(zombieHosts()){
			step()
		}
	}

	def remainingHosts(){
		count(hosts())
	}

}