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
	def receivedPackets = 0
	def sentPackets = 0
	
	def step(){
		def winner = minOneOf(neighbors()){
			count(zombieHostsOn(it))
		}
		
		face(winner)
		label = "Received: " + receivedPackets + ", Sent: " + sentPackets
		
		if(receivedPackets < 10000){
			receivedPackets++
			sentPackets++
			if(receivedPackets > (sentPackets*10)){
				hatchZombieHosts(1)
				die()
			}
		}
	}
}
