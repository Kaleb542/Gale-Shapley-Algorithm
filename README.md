# Gale-Shapley-Algorithm
This is an implementation of the Gale-Shapley algorithm in Java. This algorithm solves the stable marriage problem, which is the problem of finding a stable matching between two sets of equal size.

In this implementation, there are two groups of people: players and teams. Each player has a list of preferred teams, and each team has a list of preferred players. The algorithm finds a stable matching between the players and the teams.

## Algorithm Overview
The algorithm works as follows:

1. Initialize each person to be free.
2. While there exists a man who is free and hasn't proposed to every woman, choose such a man, `m`.
3. Choose the first woman on `m`'s list to whom `m` has not yet proposed, `w`.
4. If `w` is free, assign `m` and `w` to be engaged.
5. If `w` prefers `m` to her fiance `m'`, assign `m` and `w` to be engaged and `m'` to be free.
6. Otherwise, `w` rejects `m`.
7. Repeat steps 2-6 until there are no more free men.

## Code Description
There are two classes provided: `GaleShapleyAlgorithm` and `GaleShapleyAlgorithmReversed`.

`GaleShapleyAlgorithm` implements the algorithm as described above with players proposing to teams.

`GaleShapleyAlgorithmReversed` implements the algorithm with teams proposing to players. The two classes are identical except for the order in which the proposals are made.

Both classes have a `stableMatching` method that takes in the following arguments:

- `players`: an array of player names
- `teams`: an array of team names
- `playerPreferences`: a 2D array where playerPreferences[i] is an array of the preferences of player i
- `teamPreferences`: a 2D array where teamPreferences[i] is an array of the preferences of team i

The `stableMatching` method returns an array of strings where `matches[i]` is the team that player i is matched with.

## Example Usage
`String[] players = { "Player A", "Player B", "Player C", "Player D" };`
`String[] teams = { "Team W", "Team X", "Team Y", "Team Z" };`

`String[][] playerPreferences = { { "Team X", "Team W", "Team Z", "Team Y" },
                                 { "Team Z", "Team Y", "Team X", "Team W" },
                                 { "Team Y", "Team X", "Team Z", "Team W" },
                                 { "Team X", "Team Z", "Team W", "Team Y" } };`

`String[][] teamPreferences = { { "Player B", "Player D", "Player C", "Player A" },
                               { "Player B", "Player D", "Player A", "Player C" },
                               { "Player A", "Player B", "Player C", "Player D" },
                               { "Player B", "Player C", "Player D", "Player A" } };`

`String[] matches = GaleShapleyAlgorithm.stableMatching(players, teams, playerPreferences, teamPreferences);`

`for (int i = 0; i < matches.length; i++) {
    System.out.printf("%s is matched with %s\n", players[i], matches[i]);
}`

## Performance
The algorithm has a time complexity of O(n^2), where n is the number of players/teams. The implementation provided here should work well for small to moderate-sized problems
