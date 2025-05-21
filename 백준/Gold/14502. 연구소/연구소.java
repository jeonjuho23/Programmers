import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static final int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int VIRUS = 2;
    private static final int MAX_NEW_WALL = 3;

    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = stoi(input[0]);
        int M = stoi(input[1]);

        List<int[]> empty = new ArrayList<>();
        List<int[]> virus = new ArrayList<>();
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(input[j]);
                if (map[i][j] == EMPTY) {
                    empty.add(new int[]{i, j});
                } else if (map[i][j] == VIRUS) {
                    virus.add(new int[]{i, j});
                }
            }
        }

        dfs(map, empty, virus, N, M, 3, 0, 0);

        System.out.println(max);
    }

    private static void dfs(
        int[][] map, List<int[]> empty, List<int[]> virus, int n, int m,
        int newWall, int count, int index
    ) {
        if (count >= newWall) {
            int[][] copyMap = copyArrays(map);
            int emptyCount = bfs(copyMap, virus, n, m, empty.size() - newWall);
            max = Math.max(max, emptyCount);
            return;
        }

        for (int i = index; i < empty.size(); i++) {
            int[] emptyPoint = empty.get(i);
            map[emptyPoint[0]][emptyPoint[1]] = WALL;
            dfs(map, empty, virus, n, m, newWall, count + 1, i + 1);
            map[emptyPoint[0]][emptyPoint[1]] = EMPTY;
        }
    }

    private static int[][] copyArrays(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, copy[i], 0, arr[i].length);
        }
        return copy;
    }

    private static int bfs(
        int[][] map, List<int[]> virus, int n, int m, int emptyCount
    ) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        for (int[] point : virus) {
            queue.offer(point);
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int r = poll[0];
            int c = poll[1];


            if (map[r][c] == WALL || visited[r][c]) {
                continue;
            }
            if (map[r][c] == EMPTY) {
                emptyCount--;
                map[r][c] = VIRUS;
            }

            for (int[] d : direction) {
                int dr = r + d[0];
                int dc = c + d[1];

                if (dr < 0 || dc < 0 || dr >= n || dc >= m || map[dr][dc] != EMPTY) {
                    continue;
                }
                queue.offer(new int[]{dr, dc});
            }
        }
        return emptyCount;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
