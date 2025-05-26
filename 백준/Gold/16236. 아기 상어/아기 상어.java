import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int EMPTY = 0;
    private static final int BABY_SHARK = 9;

    private static Shark shark;
    private static int[][] map;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                int num = stoi(input[j]);
                map[i][j] = num;
                if (num == BABY_SHARK) {
                    shark = new Shark(i, j);
                }
            }
        }

        int time = 0;
        while (true) {
            // 먹을 수 있는 물고기 찾기
            Optional<Fish> optionalFish = bfs();

            // 없다면 엄마 부르기!
            if (optionalFish.isEmpty()) {
                break;
            }

            // 있으면 냠냠
            Fish fish = optionalFish.get();
            int spendTime = shark.eat(fish);
            time += spendTime;
        }

        System.out.println(time);
    }

    private static Optional<Fish> bfs() {
        Queue<Fish> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];

        queue.offer(new Fish(shark.x, shark.y, 0));

        List<Fish> eatableFish = new ArrayList<>();
        while (!queue.isEmpty()) {
            Fish fish = queue.poll();
            int x = fish.x;
            int y = fish.y;

            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;

            if (!eatableFish.isEmpty() && eatableFish.get(0).distance != fish.distance) {
                break;
            }
            if (map[x][y] > EMPTY && map[x][y] < shark.size && map[x][y] != BABY_SHARK) {
                eatableFish.add(fish);
            }

            for (int[] direction : direction) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (isNotAvailablePoint(newX, newY)
                    || visited[newX][newY]
                    || map[newX][newY] > shark.size
                ) {
                    continue;
                }

                queue.offer(new Fish(newX, newY, fish.distance + 1));
            }
        }

        if (eatableFish.isEmpty()) {
            return Optional.empty();
        }

        Collections.sort(eatableFish);
        return Optional.of(eatableFish.get(0));
    }

    private static boolean isNotAvailablePoint(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static class Fish implements Comparable<Fish> {
        int x;
        int y;
        int distance;

        @Override
        public String toString() {
            return "Fish{" +
                "x=" + x +
                ", y=" + y +
                ", distance=" + distance +
                '}';
        }

        Fish(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Fish o) {
            int diff = this.distance - o.distance;
            if (diff != 0) {
                return diff;
            }
            diff = this.x - o.x;
            if (diff != 0) {
                return diff;
            }
            return this.y - o.y;
        }
    }
    private static class Shark {
        int x;
        int y;
        int size;
        int eatingCount;

        Shark(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = 2;
            this.eatingCount = 0;
        }

        public int eat(Fish fish) {
            map[x][y] = EMPTY;
            this.x = fish.x;
            this.y = fish.y;
            map[x][y] = BABY_SHARK;
            eatingCount += 1;
            if (size <= eatingCount) {
                size += 1;
                eatingCount = 0;
            }
            return fish.distance;
        }
    }
}
