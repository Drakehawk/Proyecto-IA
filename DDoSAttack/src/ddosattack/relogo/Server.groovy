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

class Server extends ReLogoTurtle {
	def received = 0
	def sent = 0
	def active = true
	
	def step(){
		def winner = minOneOf(neighbors()){
			count(zombieHostsOn(it))
		}
		
		face(winner)
		
		label = "Received: " + received + "\nSent: " + sent + "\nActive service:" + active
		
		if(received > sent * 100){
			active = false
			label = "Service unavailable!"
		}
		
	}
}
