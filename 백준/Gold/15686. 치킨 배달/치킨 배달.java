import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * public class Main { public static void main(String[] args) throws Exception { } } private static
 * int stoi(String s) { return Integer.parseInt(s); }
 **/

public class Main {

    private static int minChickenDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = stoi(input[0]);
        int M = stoi(input[1]);

        List<int[]> home = new ArrayList<>();
        List<int[]> chicken = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                int num = stoi(input[j - 1]);
                if (num == 1) {
                    home.add(new int[]{i, j});
                    continue;
                }
                if (num == 2) {
                    chicken.add(new int[]{i, j});
                    continue;
                }
                // 빈칸
            }
        }

        dfs(M, home, chicken, new ArrayList<>(chicken), -1);

        System.out.println(minChickenDistance);
    }

    private static void dfs(
        int M, List<int[]> home, List<int[]> chicken,
        List<int[]> selectedChicken, int lastIndex
    ) {
        if (selectedChicken.size() <= M && !selectedChicken.isEmpty()) {
            int cityChickenDistance = calculateCityChickenDistance(selectedChicken, home);
            minChickenDistance = Math.min(minChickenDistance, cityChickenDistance);
            return;
        }

        for (int i = lastIndex + 1; i < chicken.size(); i++) {
            selectedChicken.remove(chicken.get(i));
            dfs(M, home, chicken, selectedChicken, i);
            selectedChicken.add(chicken.get(i));
        }
    }

    private static int calculateCityChickenDistance(List<int[]> chicken, List<int[]> home) {
        List<Integer> chickenDistances = new ArrayList<>();
        for (int[] homePoint : home) {
            int chickenDistance = calculateChickenDistanceByHome(chicken, homePoint);
            chickenDistances.add(chickenDistance);
        }
        return chickenDistances.stream().mapToInt(Integer::intValue).sum();
    }

    private static int calculateChickenDistanceByHome(List<int[]> chicken, int[] homePoint) {
        int min = Integer.MAX_VALUE;
        for (int[] chickenPoint : chicken) {
            int chickenDistance = calculateChickenDistance(
                chickenPoint[0], chickenPoint[1], homePoint[0], homePoint[1]);
            min = Math.min(min, chickenDistance);
        }
        return min;
    }

    private static int calculateChickenDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
