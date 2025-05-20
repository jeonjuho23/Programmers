import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = stoi(input[0]);
        int M = stoi(input[1]);
        boolean[][] maze = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                maze[i][j] = chars[j] == '1';
            }
        }

        int count = bfs(N, M, maze);
        System.out.println(count);
    }

    private static int bfs(int n, int m, boolean[][] maze) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int count = poll[2];

            if (x == n - 1 && y == m - 1) {
                return count;
            }

            if (isNotAvailable(n, m, maze, x, y)) {
                continue;
            }
            maze[x][y] = false;

            for (int[] d : direction) {
                int dx = x + d[0];
                int dy = y + d[1];

                if (isNotAvailable(n, m, maze, dx, dy)) {
                    continue;
                }

                queue.offer(new int[]{dx, dy, count + 1});
            }
        }

        return -1;
    }

    private static boolean isNotAvailable(int n, int m, boolean[][] maze, int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m || !maze[x][y];
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
