
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static final int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int N;
    private static char[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 구별 O
        int r_g_b = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    continue;
                }
                dfs(i, j);
                r_g_b++;
            }
        }

        // 구별 X
        int rg_b = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    continue;
                }
                rg_b_dfs(i, j);
                rg_b++;
            }
        }

        System.out.println(r_g_b + " " + rg_b);
    }

    private static void rg_b_dfs(int x, int y) {
        visited[x][y] = true;
        for (int[] distance : direction) {
            int nx = x + distance[0];
            int ny = y + distance[1];
            if (isNotAvailablePoint(nx, ny, N, N)
                || visited[nx][ny]
            ) {
                continue;
            }
            if (map[nx][ny] != map[x][y]) {
                if (Math.abs(map[nx][ny] - map[x][y]) == Math.abs('R' - 'G')) {
                    rg_b_dfs(nx, ny);
                } else {
                    continue;
                }
            }
            rg_b_dfs(nx, ny);
        }
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;
        for (int[] distance : direction) {
            int nx = x + distance[0];
            int ny = y + distance[1];
            if (isNotAvailablePoint(nx, ny, N, N)
                || visited[nx][ny]
                || map[nx][ny] != map[x][y]
            ) {
                continue;
            }
            dfs(nx, ny);
        }
    }

    private static void print(char[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
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