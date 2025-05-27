import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * public class Main { public static void main(String[] args) throws Exception { } } private static
 * int stoi(String s) { return Integer.parseInt(s); }
 **/

public class Main {

    private static final int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int N;
    private static boolean[][] isLands;
    private static int[][] islandsMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        isLands = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                isLands[i][j] = stoi(input[j]) == 1;
            }
        }

        // BFS로 섬 구분해서 islandsMap 만들기
        List<List<int[]>> islandsList = new ArrayList<>();
        islandsMap = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        int islandCount = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean isLand = isLands[i][j];
                if (!isLand || visited[i][j]) {
                    continue;
                }
                islandsList.add(new ArrayList<>());
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{i, j});
                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    int currX = curr[0];
                    int currY = curr[1];
                    if (visited[currX][currY]) {
                        continue;
                    }
                    islandsList.get(islandCount - 1).add(new int[]{currX, currY});
                    visited[currX][currY] = true;
                    islandsMap[currX][currY] = islandCount;
                    for (int[] direction : direction) {
                        int newX = currX + direction[0];
                        int newY = currY + direction[1];
                        if (isNotAvailablePoint(newX, newY)
                            || visited[newX][newY]
                            || !isLands[newX][newY]
                        ) {
                            continue;
                        }
                        queue.add(new int[]{newX, newY});
                    }
                }
                islandCount++;
            }
        }

        // 각각의 섬 위치에서 다른 섬 찾기
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < islandsList.size(); i++) {
            Queue<int[]> queue = new LinkedList<>();
            visited = new boolean[N][N];
            for (int[] islandPoint : islandsList.get(i)) {
                queue.offer(new int[]{islandPoint[0], islandPoint[1], 0});
            }
            int currIsland = i + 1;
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int currX = curr[0];
                int currY = curr[1];
                int currCount = curr[2];

                if (visited[currX][currY]) {
                    continue;
                }
                visited[currX][currY] = true;

                for (int[] d : direction) {
                    int newX = currX + d[0];
                    int newY = currY + d[1];
                    if (isNotAvailablePoint(newX, newY)
                        || visited[newX][newY]
                    ) {
                        continue;
                    }
                    if (isLands[newX][newY] && currIsland != islandsMap[newX][newY]) {
                        minDistance = Math.min(minDistance, currCount);
                        break;
                    }
                    queue.offer(new int[]{newX, newY, currCount + 1});
                }
            }
        }

        System.out.println(minDistance);
    }

    private static boolean isNotAvailablePoint(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
