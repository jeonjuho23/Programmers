import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static final int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int R, C;
    private static char[][] map;
    private static boolean[] visited;
    private static int max = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = stoi(input[0]);
        C = stoi(input[1]);
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        visited = new boolean['Z' - 'A' + 1];
        dfs(0, 0, 0);

        System.out.println(max);
    }

    private static int getIndex(char start) {
        return start - 'A';
    }

    private static void dfs(int r, int c, int count) {
        char curr = map[r][c];
        if (visited[getIndex(curr)]) {
            max = Math.max(max, count);
            return;
        }

        visited[getIndex(curr)] = true;
        for (int[] direction : direction) {
            int newR = r + direction[0];
            int newC = c + direction[1];
            if (isNotAvailablePoint(newR, newC, R, C)) {
                continue;
            }
            dfs(newR, newC, count + 1);
        }
        visited[getIndex(curr)] = false;
    }

    private static boolean isNotAvailablePoint(int x, int y, int maxX, int maxY) {
        return x < 0 || x >= maxX || y < 0 || y >= maxY;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
