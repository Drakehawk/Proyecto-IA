package ddosattack.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Plural;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import ddosattack.ReLogoTurtle;

class Host extends ReLogoTurtle {
	def received = 0
	def sent = 0
	
	def step(){
		def winner = minOneOf(neighbors()){
			count(zombieHostsOn(it))
		}
		
		face(winner)
		label = "Received: " + received + "\nSent: " + sent
		
		received++
		sent++
		if(received > sent){
			hatchZombieHosts(1)
			die()
		}
		
	}
}
