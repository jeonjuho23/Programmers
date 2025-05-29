import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * public class Main { public static void main(String[] args) throws Exception { } } private static
 * int stoi(String s) { return Integer.parseInt(s); }
 **/

public class Main {

    private static final int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int N;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = stoi(input[0]);
        map = new int[N][N];
        int maxHeight = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(input[j]);
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int maxSafeArea = Integer.MIN_VALUE;
        for (int height = 0; height <= maxHeight; height++) {
            visited = new boolean[N][N];
            int currSafeArea = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] || map[i][j] <= height) {
                        continue;
                    }
                    dfs(i, j, height);
                    currSafeArea++;
                }
            }
            maxSafeArea = Math.max(maxSafeArea, currSafeArea);
        }

        System.out.println(maxSafeArea);
    }

    private static void dfs(int x, int y, int height) {
        visited[x][y] = true;

        for (int[] direction : direction) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (isNotAvailablePoint(newX, newY, N, N)
                || visited[newX][newY]
                || map[newX][newY] <= height
            ) {
                continue;
            }
            dfs(newX, newY, height);
        }
    }

    private static void print(int[][] intMap) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(intMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isNotAvailablePoint(int x, int y, int maxX, int maxY) {
        return x < 0 || x >= maxX || y < 0 || y >= maxY;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
