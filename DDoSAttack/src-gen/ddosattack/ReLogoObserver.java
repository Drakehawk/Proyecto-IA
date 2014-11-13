package ddosattack;

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import groovy.lang.Closure;
import repast.simphony.relogo.*;
import repast.simphony.relogo.builder.GeneratedByReLogoBuilder;
import repast.simphony.relogo.builder.ReLogoBuilderGeneratedFor;

@GeneratedByReLogoBuilder
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class ReLogoObserver extends BaseObserver{

	/**
	 * Makes a number of randomly oriented zombieHosts and then executes a set of commands on the
	 * created zombieHosts.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created zombieHosts
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.ZombieHost")
	public AgentSet<ddosattack.relogo.ZombieHost> createZombieHosts(int number, Closure closure) {
		AgentSet<ddosattack.relogo.ZombieHost> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.crt(number,closure,"ZombieHost");
		for (Turtle t : createResult){
			if (t instanceof ddosattack.relogo.ZombieHost){
				result.add((ddosattack.relogo.ZombieHost)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of randomly oriented zombieHosts and then executes a set of commands on the
	 * created zombieHosts.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created zombieHosts
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.ZombieHost")
	public AgentSet<ddosattack.relogo.ZombieHost> createZombieHosts(int number) {
		return createZombieHosts(number,null);
	}

	/**
	 * Makes a number of uniformly fanned zombieHosts and then executes a set of commands on the
	 * created zombieHosts.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created zombieHosts
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.ZombieHost")
	public AgentSet<ddosattack.relogo.ZombieHost> createOrderedZombieHosts(int number, Closure closure) {
		AgentSet<ddosattack.relogo.ZombieHost> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.cro(number,closure,"ZombieHost");
		for (Turtle t : createResult){
			if (t instanceof ddosattack.relogo.ZombieHost){
				result.add((ddosattack.relogo.ZombieHost)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of uniformly fanned zombieHosts and then executes a set of commands on the
	 * created zombieHosts.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created zombieHosts
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.ZombieHost")
	public AgentSet<ddosattack.relogo.ZombieHost> createOrderedZombieHosts(int number) {
		return createOrderedZombieHosts(number,null);
	}

	/**
	 * Queries if object is a zombieHost.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a zombieHost
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.ZombieHost")
	public boolean isZombieHostQ(Object o){
		return (o instanceof ddosattack.relogo.ZombieHost);
	}

	/**
	 * Returns an agentset containing all zombieHosts.
	 * 
	 * @return agentset of all zombieHosts
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.ZombieHost")
	public AgentSet<ddosattack.relogo.ZombieHost> zombieHosts(){
		AgentSet<ddosattack.relogo.ZombieHost> a = new AgentSet<ddosattack.relogo.ZombieHost>();
		for (Object e : this.getContext().getObjects(ddosattack.relogo.ZombieHost.class)) {
			if (e instanceof ddosattack.relogo.ZombieHost){
				a.add((ddosattack.relogo.ZombieHost)e);
			}
		}
		return a;
	}

	/**
	 * Returns the zombieHost with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.ZombieHost")
	public ddosattack.relogo.ZombieHost zombieHost(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), this);
		if (turtle instanceof ddosattack.relogo.ZombieHost)
			return (ddosattack.relogo.ZombieHost) turtle;
		return null;
	}

	/**
	 * Returns an agentset of zombieHosts on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of zombieHosts on patch p
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.ZombieHost")
	public AgentSet<ddosattack.relogo.ZombieHost> zombieHostsOn(Patch p){
		AgentSet<ddosattack.relogo.ZombieHost> result = new AgentSet<ddosattack.relogo.ZombieHost>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),this,"zombieHost")){
			if (t instanceof ddosattack.relogo.ZombieHost)
			result.add((ddosattack.relogo.ZombieHost)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of zombieHosts on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of zombieHosts on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.ZombieHost")
	public AgentSet<ddosattack.relogo.ZombieHost> zombieHostsOn(Turtle t){
		AgentSet<ddosattack.relogo.ZombieHost> result = new AgentSet<ddosattack.relogo.ZombieHost>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),this,"zombieHost")){
			if (tt instanceof ddosattack.relogo.ZombieHost)
			result.add((ddosattack.relogo.ZombieHost)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of zombieHosts on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of zombieHosts on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.ZombieHost")
	public AgentSet<ddosattack.relogo.ZombieHost> zombieHostsOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<ddosattack.relogo.ZombieHost>();
		}

		Set<ddosattack.relogo.ZombieHost> total = new HashSet<ddosattack.relogo.ZombieHost>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(zombieHostsOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(zombieHostsOn(p));
				}
			}
		}
		return new AgentSet<ddosattack.relogo.ZombieHost>(total);
	}

	/**
	 * Makes a number of randomly oriented userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserTurtle")
	public AgentSet<ddosattack.relogo.UserTurtle> createUserTurtles(int number, Closure closure) {
		AgentSet<ddosattack.relogo.UserTurtle> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.crt(number,closure,"UserTurtle");
		for (Turtle t : createResult){
			if (t instanceof ddosattack.relogo.UserTurtle){
				result.add((ddosattack.relogo.UserTurtle)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of randomly oriented userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserTurtle")
	public AgentSet<ddosattack.relogo.UserTurtle> createUserTurtles(int number) {
		return createUserTurtles(number,null);
	}

	/**
	 * Makes a number of uniformly fanned userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserTurtle")
	public AgentSet<ddosattack.relogo.UserTurtle> createOrderedUserTurtles(int number, Closure closure) {
		AgentSet<ddosattack.relogo.UserTurtle> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.cro(number,closure,"UserTurtle");
		for (Turtle t : createResult){
			if (t instanceof ddosattack.relogo.UserTurtle){
				result.add((ddosattack.relogo.UserTurtle)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of uniformly fanned userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserTurtle")
	public AgentSet<ddosattack.relogo.UserTurtle> createOrderedUserTurtles(int number) {
		return createOrderedUserTurtles(number,null);
	}

	/**
	 * Queries if object is a userTurtle.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userTurtle
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserTurtle")
	public boolean isUserTurtleQ(Object o){
		return (o instanceof ddosattack.relogo.UserTurtle);
	}

	/**
	 * Returns an agentset containing all userTurtles.
	 * 
	 * @return agentset of all userTurtles
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserTurtle")
	public AgentSet<ddosattack.relogo.UserTurtle> userTurtles(){
		AgentSet<ddosattack.relogo.UserTurtle> a = new AgentSet<ddosattack.relogo.UserTurtle>();
		for (Object e : this.getContext().getObjects(ddosattack.relogo.UserTurtle.class)) {
			if (e instanceof ddosattack.relogo.UserTurtle){
				a.add((ddosattack.relogo.UserTurtle)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userTurtle with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserTurtle")
	public ddosattack.relogo.UserTurtle userTurtle(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), this);
		if (turtle instanceof ddosattack.relogo.UserTurtle)
			return (ddosattack.relogo.UserTurtle) turtle;
		return null;
	}

	/**
	 * Returns an agentset of userTurtles on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of userTurtles on patch p
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserTurtle")
	public AgentSet<ddosattack.relogo.UserTurtle> userTurtlesOn(Patch p){
		AgentSet<ddosattack.relogo.UserTurtle> result = new AgentSet<ddosattack.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),this,"userTurtle")){
			if (t instanceof ddosattack.relogo.UserTurtle)
			result.add((ddosattack.relogo.UserTurtle)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of userTurtles on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserTurtle")
	public AgentSet<ddosattack.relogo.UserTurtle> userTurtlesOn(Turtle t){
		AgentSet<ddosattack.relogo.UserTurtle> result = new AgentSet<ddosattack.relogo.UserTurtle>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),this,"userTurtle")){
			if (tt instanceof ddosattack.relogo.UserTurtle)
			result.add((ddosattack.relogo.UserTurtle)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of userTurtles on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserTurtle")
	public AgentSet<ddosattack.relogo.UserTurtle> userTurtlesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<ddosattack.relogo.UserTurtle>();
		}

		Set<ddosattack.relogo.UserTurtle> total = new HashSet<ddosattack.relogo.UserTurtle>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(userTurtlesOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(userTurtlesOn(p));
				}
			}
		}
		return new AgentSet<ddosattack.relogo.UserTurtle>(total);
	}

	/**
	 * Makes a number of randomly oriented servers and then executes a set of commands on the
	 * created servers.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created servers
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Server")
	public AgentSet<ddosattack.relogo.Server> createServers(int number, Closure closure) {
		AgentSet<ddosattack.relogo.Server> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.crt(number,closure,"Server");
		for (Turtle t : createResult){
			if (t instanceof ddosattack.relogo.Server){
				result.add((ddosattack.relogo.Server)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of randomly oriented servers and then executes a set of commands on the
	 * created servers.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created servers
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Server")
	public AgentSet<ddosattack.relogo.Server> createServers(int number) {
		return createServers(number,null);
	}

	/**
	 * Makes a number of uniformly fanned servers and then executes a set of commands on the
	 * created servers.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created servers
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Server")
	public AgentSet<ddosattack.relogo.Server> createOrderedServers(int number, Closure closure) {
		AgentSet<ddosattack.relogo.Server> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.cro(number,closure,"Server");
		for (Turtle t : createResult){
			if (t instanceof ddosattack.relogo.Server){
				result.add((ddosattack.relogo.Server)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of uniformly fanned servers and then executes a set of commands on the
	 * created servers.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created servers
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Server")
	public AgentSet<ddosattack.relogo.Server> createOrderedServers(int number) {
		return createOrderedServers(number,null);
	}

	/**
	 * Queries if object is a server.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a server
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Server")
	public boolean isServerQ(Object o){
		return (o instanceof ddosattack.relogo.Server);
	}

	/**
	 * Returns an agentset containing all servers.
	 * 
	 * @return agentset of all servers
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Server")
	public AgentSet<ddosattack.relogo.Server> servers(){
		AgentSet<ddosattack.relogo.Server> a = new AgentSet<ddosattack.relogo.Server>();
		for (Object e : this.getContext().getObjects(ddosattack.relogo.Server.class)) {
			if (e instanceof ddosattack.relogo.Server){
				a.add((ddosattack.relogo.Server)e);
			}
		}
		return a;
	}

	/**
	 * Returns the server with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Server")
	public ddosattack.relogo.Server server(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), this);
		if (turtle instanceof ddosattack.relogo.Server)
			return (ddosattack.relogo.Server) turtle;
		return null;
	}

	/**
	 * Returns an agentset of servers on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of servers on patch p
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Server")
	public AgentSet<ddosattack.relogo.Server> serversOn(Patch p){
		AgentSet<ddosattack.relogo.Server> result = new AgentSet<ddosattack.relogo.Server>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),this,"server")){
			if (t instanceof ddosattack.relogo.Server)
			result.add((ddosattack.relogo.Server)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of servers on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of servers on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Server")
	public AgentSet<ddosattack.relogo.Server> serversOn(Turtle t){
		AgentSet<ddosattack.relogo.Server> result = new AgentSet<ddosattack.relogo.Server>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),this,"server")){
			if (tt instanceof ddosattack.relogo.Server)
			result.add((ddosattack.relogo.Server)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of servers on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of servers on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Server")
	public AgentSet<ddosattack.relogo.Server> serversOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<ddosattack.relogo.Server>();
		}

		Set<ddosattack.relogo.Server> total = new HashSet<ddosattack.relogo.Server>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(serversOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(serversOn(p));
				}
			}
		}
		return new AgentSet<ddosattack.relogo.Server>(total);
	}

	/**
	 * Makes a number of randomly oriented hosts and then executes a set of commands on the
	 * created hosts.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created hosts
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Host")
	public AgentSet<ddosattack.relogo.Host> createHosts(int number, Closure closure) {
		AgentSet<ddosattack.relogo.Host> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.crt(number,closure,"Host");
		for (Turtle t : createResult){
			if (t instanceof ddosattack.relogo.Host){
				result.add((ddosattack.relogo.Host)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of randomly oriented hosts and then executes a set of commands on the
	 * created hosts.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created hosts
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Host")
	public AgentSet<ddosattack.relogo.Host> createHosts(int number) {
		return createHosts(number,null);
	}

	/**
	 * Makes a number of uniformly fanned hosts and then executes a set of commands on the
	 * created hosts.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created hosts
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Host")
	public AgentSet<ddosattack.relogo.Host> createOrderedHosts(int number, Closure closure) {
		AgentSet<ddosattack.relogo.Host> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.cro(number,closure,"Host");
		for (Turtle t : createResult){
			if (t instanceof ddosattack.relogo.Host){
				result.add((ddosattack.relogo.Host)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of uniformly fanned hosts and then executes a set of commands on the
	 * created hosts.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created hosts
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Host")
	public AgentSet<ddosattack.relogo.Host> createOrderedHosts(int number) {
		return createOrderedHosts(number,null);
	}

	/**
	 * Queries if object is a host.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a host
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Host")
	public boolean isHostQ(Object o){
		return (o instanceof ddosattack.relogo.Host);
	}

	/**
	 * Returns an agentset containing all hosts.
	 * 
	 * @return agentset of all hosts
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Host")
	public AgentSet<ddosattack.relogo.Host> hosts(){
		AgentSet<ddosattack.relogo.Host> a = new AgentSet<ddosattack.relogo.Host>();
		for (Object e : this.getContext().getObjects(ddosattack.relogo.Host.class)) {
			if (e instanceof ddosattack.relogo.Host){
				a.add((ddosattack.relogo.Host)e);
			}
		}
		return a;
	}

	/**
	 * Returns the host with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Host")
	public ddosattack.relogo.Host host(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), this);
		if (turtle instanceof ddosattack.relogo.Host)
			return (ddosattack.relogo.Host) turtle;
		return null;
	}

	/**
	 * Returns an agentset of hosts on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of hosts on patch p
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Host")
	public AgentSet<ddosattack.relogo.Host> hostsOn(Patch p){
		AgentSet<ddosattack.relogo.Host> result = new AgentSet<ddosattack.relogo.Host>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),this,"host")){
			if (t instanceof ddosattack.relogo.Host)
			result.add((ddosattack.relogo.Host)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of hosts on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of hosts on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Host")
	public AgentSet<ddosattack.relogo.Host> hostsOn(Turtle t){
		AgentSet<ddosattack.relogo.Host> result = new AgentSet<ddosattack.relogo.Host>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),this,"host")){
			if (tt instanceof ddosattack.relogo.Host)
			result.add((ddosattack.relogo.Host)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of hosts on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of hosts on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.Host")
	public AgentSet<ddosattack.relogo.Host> hostsOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<ddosattack.relogo.Host>();
		}

		Set<ddosattack.relogo.Host> total = new HashSet<ddosattack.relogo.Host>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(hostsOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(hostsOn(p));
				}
			}
		}
		return new AgentSet<ddosattack.relogo.Host>(total);
	}

	/**
	 * Queries if object is a userLink.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userLink
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserLink")
	public boolean isUserLinkQ(Object o){
		return (o instanceof ddosattack.relogo.UserLink);
	}

	/**
	 * Returns an agentset containing all userLinks.
	 * 
	 * @return agentset of all userLinks
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserLink")
	public AgentSet<ddosattack.relogo.UserLink> userLinks(){
		AgentSet<ddosattack.relogo.UserLink> a = new AgentSet<ddosattack.relogo.UserLink>();
		for (Object e : this.getContext().getObjects(ddosattack.relogo.UserLink.class)) {
			if (e instanceof ddosattack.relogo.UserLink){
				a.add((ddosattack.relogo.UserLink)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            an integer
	 * @param otherEnd
	 *            an integer
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserLink")
	public ddosattack.relogo.UserLink userLink(Number oneEnd, Number otherEnd) {
		return (ddosattack.relogo.UserLink)(this.getNetwork("UserLink").getEdge(turtle(oneEnd),turtle(otherEnd)));
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            a turtle
	 * @param otherEnd
	 *            a turtle
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.UserLink")
	public ddosattack.relogo.UserLink userLink(Turtle oneEnd, Turtle otherEnd) {
		return userLink(oneEnd.getWho(), otherEnd.getWho());
	}

	/**
	 * Queries if object is a packageFlow.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a packageFlow
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.PackageFlow")
	public boolean isPackageFlowQ(Object o){
		return (o instanceof ddosattack.relogo.PackageFlow);
	}

	/**
	 * Returns an agentset containing all packageFlows.
	 * 
	 * @return agentset of all packageFlows
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.PackageFlow")
	public AgentSet<ddosattack.relogo.PackageFlow> packageFlows(){
		AgentSet<ddosattack.relogo.PackageFlow> a = new AgentSet<ddosattack.relogo.PackageFlow>();
		for (Object e : this.getContext().getObjects(ddosattack.relogo.PackageFlow.class)) {
			if (e instanceof ddosattack.relogo.PackageFlow){
				a.add((ddosattack.relogo.PackageFlow)e);
			}
		}
		return a;
	}

	/**
	 * Returns the packageFlow between two turtles.
	 * 
	 * @param oneEnd
	 *            an integer
	 * @param otherEnd
	 *            an integer
	 * @return packageFlow between two turtles
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.PackageFlow")
	public ddosattack.relogo.PackageFlow packageFlow(Number oneEnd, Number otherEnd) {
		return (ddosattack.relogo.PackageFlow)(this.getNetwork("PackageFlow").getEdge(turtle(oneEnd),turtle(otherEnd)));
	}

	/**
	 * Returns the packageFlow between two turtles.
	 * 
	 * @param oneEnd
	 *            a turtle
	 * @param otherEnd
	 *            a turtle
	 * @return packageFlow between two turtles
	 */
	@ReLogoBuilderGeneratedFor("ddosattack.relogo.PackageFlow")
	public ddosattack.relogo.PackageFlow packageFlow(Turtle oneEnd, Turtle otherEnd) {
		return packageFlow(oneEnd.getWho(), otherEnd.getWho());
	}

	/**
	 * Returns the value of the global variable numHosts.
	 *
	 * @return the value of the global variable numHosts
	 */
	@ReLogoBuilderGeneratedFor("global: numHosts")
	public Object getNumHosts(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("numHosts");
	}

	/**
	 * Sets the value of the global variable numHosts.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: numHosts")
	public void setNumHosts(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("numHosts",value);
	}

	/**
	 * Returns the value of the global variable numZombieHosts.
	 *
	 * @return the value of the global variable numZombieHosts
	 */
	@ReLogoBuilderGeneratedFor("global: numZombieHosts")
	public Object getNumZombieHosts(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("numZombieHosts");
	}

	/**
	 * Sets the value of the global variable numZombieHosts.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: numZombieHosts")
	public void setNumZombieHosts(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("numZombieHosts",value);
	}


}