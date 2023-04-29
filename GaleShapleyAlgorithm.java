/*
Initialize each person to be free.
while (some man is free and hasn't proposed to every woman) {
		Choose such a man m
		w = 1st woman on m's list to whom m has not yet proposed
		if (w is free)
			assign m and w to be engaged
		else if (w prefers m to her fiance m')
			assign m and w to be engaged, and m' to be free
		else
			w rejects m	
}
*/

import java.util.Arrays;

public class GaleShapleyAlgorithm {

	public static void main(String[] args) {
		String[] players = { "Player A", "Player B", "Player C", "Player D" };
		String[] teams = { "Team W", "Team X", "Team Y", "Team Z" };

		String[][] playerPreferences = { { "Team X", "Team W", "Team Z", "Team Y" },
				{ "Team Z", "Team Y", "Team X", "Team W" }, { "Team Y", "Team X", "Team Z", "Team W" },
				{ "Team X", "Team Z", "Team W", "Team Y" } };

		String[][] teamPreferences = { { "Player B", "Player D", "Player C", "Player A" },
				{ "Player B", "Player D", "Player A", "Player C" }, { "Player A", "Player B", "Player C", "Player D" },
				{ "Player B", "Player C", "Player D", "Player A" } };

		long start = System.nanoTime();
		
		String[] matches = stableMatching(players, teams, playerPreferences, teamPreferences);
		
		long end = System.nanoTime();
		long total = end - start;

		for (int i = 0; i < matches.length; i++) {
			System.out.printf("%s is matched with %s\n", players[i], matches[i]);
		}
		
		System.out.printf("%nExecution time in nanoseconds: %d", total);
	}

	public static String[] stableMatching(String[] players, String[] teams, String[][] playerPreferences,
			String[][] teamPreferences) {
		int n = players.length;
		int[] currentTeam = new int[n];
		Arrays.fill(currentTeam, -1);

		int[] freePlayers = new int[n];
		for (int i = 0; i < n; i++) {
			freePlayers[i] = i;
		}

		int numFreePlayers = n;

		while (numFreePlayers > 0) {
			int player = freePlayers[--numFreePlayers];
			String team = null;

			for (String preference : playerPreferences[player]) {
				int index = indexOf(teams, preference);
				if (currentTeam[index] == -1) {
					team = preference;
					break;
				} else {
					int otherPlayer = currentTeam[index];
					if (indexOf(teamPreferences[index], players[player]) < indexOf(teamPreferences[index],
							players[otherPlayer])) {
						team = preference;
						freePlayers[numFreePlayers++] = otherPlayer;
						break;
					}
				}
			}

			if (team != null) {
				currentTeam[indexOf(teams, team)] = player;
			} else {
				freePlayers[numFreePlayers++] = player;
			}
		}

		String[] matches = new String[n];
		for (int i = 0; i < n; i++) {
			matches[i] = teams[currentTeam[i]];
		}

		return matches;
	}

	public static int indexOf(String[] arr, String element) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}

}
