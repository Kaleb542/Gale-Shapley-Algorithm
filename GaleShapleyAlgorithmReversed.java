import java.util.Arrays;

public class GaleShapleyAlgorithmReversed {

	public static void main(String[] args) {
		String[] teams = { "Team W", "Team X", "Team Y", "Team Z" };
		String[] players = { "Player A", "Player B", "Player C", "Player D" };

		String[][] teamPreferences = { { "Player B", "Player D", "Player C", "Player A" },
				{ "Player B", "Player D", "Player A", "Player C" }, { "Player A", "Player B", "Player C", "Player D" },
				{ "Player B", "Player C", "Player D", "Player A" } };

		String[][] playerPreferences = { { "Team X", "Team W", "Team Z", "Team Y" },
				{ "Team Z", "Team Y", "Team X", "Team W" }, { "Team Y", "Team X", "Team Z", "Team W" },
				{ "Team X", "Team Z", "Team W", "Team Y" } };

		long start = System.nanoTime();
		
		String[] matches = stableMatching(teams, players, teamPreferences, playerPreferences);

		long end = System.nanoTime();
		long total = end - start;
		
		for (int i = 0; i < matches.length; i++) {
			System.out.printf("%s is matched with %s\n", teams[i], matches[i]);
		}
		
		System.out.printf("%nExecution time in nanoseconds: %d", total);

	}

	public static String[] stableMatching(String[] teams, String[] players, String[][] teamPreferences,
			String[][] playerPreferences) {
		int n = teams.length;
		int[] currentPlayer = new int[n];
		Arrays.fill(currentPlayer, -1);

		int[] freeTeams = new int[n];
		for (int i = 0; i < n; i++) {
			freeTeams[i] = i;
		}

		int numFreeTeams = n;

		while (numFreeTeams > 0) {
			int team = freeTeams[--numFreeTeams];
			String player = null;

			for (String preference : teamPreferences[team]) {
				int index = indexOf(players, preference);
				if (currentPlayer[index] == -1) {
					player = preference;
					break;
				} else {
					int otherTeam = currentPlayer[index];
					if (indexOf(playerPreferences[index], teams[team]) < indexOf(playerPreferences[index],
							teams[otherTeam])) {
						player = preference;
						freeTeams[numFreeTeams++] = otherTeam;
						break;
					}
				}
			}

			if (player != null) {
				currentPlayer[indexOf(players, player)] = team;
			} else {
				freeTeams[numFreeTeams++] = team;
			}
		}

		String[] matches = new String[n];
		for (int i = 0; i < n; i++) {
			matches[i] = players[currentPlayer[i]];
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
