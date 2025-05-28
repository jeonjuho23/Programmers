import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int N;
    private static int M;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        List<Point> cheese = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(input[j]);
                if (map[i][j] == 1) {
                    cheese.add(new Point(i, j));
                }
            }
        }

        int time = 0;
        for (; !cheese.isEmpty(); time++) {
            // 빈 공간을 2로 채우는 BFS
            Queue<Point> queue = new LinkedList<>();
            queue.offer(new Point(0, 0));
            boolean[][] visited = new boolean[N][M];
            while (!queue.isEmpty()) {
                Point poll = queue.poll();
                int x = poll.x();
                int y = poll.y();

                if (visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                map[x][y] = 2;

                for (int[] direction : direction) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    if (isNotAvailablePoint(newX, newY)
                        || map[newX][newY] == 1
                        || visited[newX][newY]
                    ) {
                        continue;
                    }
                    queue.offer(new Point(newX, newY));
                }
            }

            // 모든 cheese 중 2와 접한 곳이 2개 이상인 곳들을 찾기
            List<Point> meltedCheese = new ArrayList<>();
            for (Point point : cheese) {
                int count = 0;
                int x = point.x();
                int y = point.y();
                for (int[] direction : direction ) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    if (isNotAvailablePoint(newX, newY)) {
                        continue;
                    }
                    if (map[newX][newY] == 2) {
                        count++;
                    }
                }
                if (count >= 2) {
                    meltedCheese.add(new Point(x, y));
                }
            }

            // 찾은 치즈들을 2로 변경
            for (Point point : meltedCheese) {
                map[point.x()][point.y()] = 2;
                cheese.remove(point);
            }
        }

        System.out.println(time);
    }

    private static void print(int[][] intMap) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(intMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isNotAvailablePoint(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }

    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int x() {
            return x;
        }
        public int y() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
