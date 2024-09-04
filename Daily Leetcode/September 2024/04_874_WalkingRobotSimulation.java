/*
 * 874. Walking Robot Simulation
 * 
 * A robot on an infinite XY-plane starts at point (0, 0) facing north. The
 * robot can receive a sequence of these three possible types of commands:
 * -2: Turn left 90 degrees.
 * -1: Turn right 90 degrees.
 * 1 <= k <= 9: Move forward k units, one unit at a time.
 * Some of the grid squares are obstacles. The ith obstacle is at grid point
 * obstacles[i] = (xi, yi). If the robot runs into an obstacle, then it will
 * instead stay in its current location and move on to the next command.
 * Return the maximum Euclidean distance that the robot ever gets from the
 * origin squared (i.e. if the distance is 5, return 25).
 * 
 * Note:
 * 
 * North means +Y direction.
 * East means +X direction.
 * South means -Y direction.
 * West means -X direction.
 * There can be obstacle in [0,0].
 * 
 * Example 1:
 * Input: commands = [4,-1,3], obstacles = []
 * Output: 25
 * Explanation: The robot starts at (0, 0):
 * 1. Move north 4 units to (0, 4).
 * 2. Turn right.
 * 3. Move east 3 units to (3, 4).
 * The furthest point the robot ever gets from the origin is (3, 4), which
 * squared is 32 + 42 = 25 units away.
 * 
 * Example 2:
 * Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * Output: 65
 * Explanation: The robot starts at (0, 0):
 * 1. Move north 4 units to (0, 4).
 * 2. Turn right.
 * 3. Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at
 * (1, 4).
 * 4. Turn left.
 * 5. Move north 4 units to (1, 8).
 * The furthest point the robot ever gets from the origin is (1, 8), which
 * squared is 12 + 82 = 65 units away.
 * 
 * Example 3:
 * Input: commands = [6,-1,-1,6], obstacles = []
 * Output: 36
 * Explanation: The robot starts at (0, 0):
 * 1. Move north 6 units to (0, 6).
 * 2. Turn right.
 * 3. Turn right.
 * 4. Move south 6 units to (0, 0).
 * The furthest point the robot ever gets from the origin is (0, 6), which
 * squared is 62 = 36 units away.
 * 
 * Constraints:
 * 
 * 1 <= commands.length <= 104
 * commands[i] is either -2, -1, or an integer in the range [1, 9].
 * 0 <= obstacles.length <= 104
 * -3 * 104 <= xi, yi <= 3 * 104
 * The answer is guaranteed to be less than 231.
 */

//Simple Simulation
//T.C : O(m + n * maxValCommand), m = size of obstacles, n = size of commands
//S.C : O(m)
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Use HashSet to store obstacle positions as strings
        HashSet<String> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            String key = obs[0] + "_" + obs[1];
            obstacleSet.add(key);
        }

        int x = 0;
        int y = 0;
        int maxDistance = 0;

        // Initially pointing North
        int[] dir = { 0, 1 }; // North

        // Process each command
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == -2) { // turn left 90 degrees
                dir = new int[] { -dir[1], dir[0] };
            } else if (commands[i] == -1) { // turn right 90 degrees
                dir = new int[] { dir[1], -dir[0] };
            } else { // move forward step by step
                for (int step = 0; step < commands[i]; step++) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    String nextKey = newX + "_" + newY;

                    // If there's an obstacle, stop moving in this direction
                    if (obstacleSet.contains(nextKey)) {
                        break;
                    }

                    // Move to the new position
                    x = newX;
                    y = newY;
                }
            }

            // Update the maximum distance from the origin
            maxDistance = Math.max(maxDistance, x * x + y * y);
        }

        return maxDistance;
    }
}