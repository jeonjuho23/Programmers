import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static final int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        boolean[][] map = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = chars[j] == '1';
            }
        }

        int count = 0;
        List<Integer> homeCount = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!map[i][j]) {
                    continue;
                }
                homeCount.add(bfs(N, map, i, j));
                count++;
            }
        }

        System.out.println(count);
        homeCount.sort(Integer::compareTo);
        homeCount.forEach(System.out::println);
    }

    private static int bfs(int n, boolean[][] map, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        int count = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            if (x < 0 || y < 0 || x >= n || y >= n || !map[x][y]) {
                continue;
            }

            map[x][y] = false;
            count++;
            for (int[] d : direction) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || !map[nx][ny]) {
                    continue;
                }

                queue.offer(new int[]{nx, ny});
            }
        }
        return count;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
