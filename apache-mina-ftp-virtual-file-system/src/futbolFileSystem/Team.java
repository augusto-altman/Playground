package futbolFileSystem;

import java.util.HashMap;

public class Team extends Futbol { //Team POJO

	private HashMap<String, Player> players;
	
	public Team(String name, HashMap<String, Player> players) {
		super(name);
		this.players = players;
	}

	public Team(String name) {
		super(name);
		this.players = new HashMap<String, Player>();
	}
	
	public HashMap<String, Player> getAllPlayers() {
		return players;
	}
	
	public Player getPlayer(String p) {
		return players.get(p);
	}
	
	public void addPlayer(Player p) {
		this.players.put(p.getName(), p);
	}
	
	public boolean deletePlayer(String p) {
		return this.players.remove(p) != null;
	}
	
	public boolean hasPlayer(String p) {
		return this.getPlayer(p) != null;
	}
	
	public int count() {
		return this.players.size();
	}

	@Override
	public FutbolTypes getType() {
		return FutbolTypes.TEAM;
	}
}
