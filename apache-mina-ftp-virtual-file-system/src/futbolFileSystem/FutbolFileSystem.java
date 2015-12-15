package futbolFileSystem;

import java.util.HashMap;

public final class FutbolFileSystem {

	private static int concurrencyCount = 0;
	private static HashMap<String, Team> teams = new HashMap<String, Team>();
	
	public static HashMap<String, Team> getAllTeams() {
		return teams;
	}
	
	public static Team getTeam(String t) {
		System.out.println("FS getTeam " + ++concurrencyCount + " \n"); 
		return teams.get(t);
	}
	
	public static void addTeam(Team t) {
		teams.put(t.getName(), t);
	}
	
	public static boolean deleteTeam(String t) {
		return teams.remove(t) != null;
	}
	
	public static boolean hasTeam(String t) {
		return getTeam(t) != null;
	}

	public static long count() {
		return teams.size();
	}
}
