package ddosattack.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import net.sf.cglib.proxy.MethodProxy.CreateInfo;
import repast.simphony.relogo.Plural;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import ddosattack.ReLogoTurtle;

class ZombieHost extends ReLogoTurtle {
	def receivedPackets = 0
	def sentPackets = 0
	
	def step(){
		def winner = maxOneOf(neighbors()) {
			count(hostsOn(it))
		}
		
		face(winner)
		
		if(count(hostsHere())>0){
			def infectee = infect(oneOf(hostsHere()))
			infect(infectee)
			createInfectionTo(infectee)
		}
	}
	
	def infect(host){
		host.receivedPackets += 10
	}
}
