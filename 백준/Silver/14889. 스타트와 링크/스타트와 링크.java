import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = stoi(input[j]);
            }
        }

        dfs(N, arr, 0, 0, new boolean[N]);

        System.out.println(minDiff);
    }

    private static void dfs(int n, int[][] arr, int index, int cnt, boolean[] visited) {
        if (cnt >= n / 2) {
            int diff = calDiff(n, arr, visited);
            minDiff = Math.min(minDiff, diff);
            return;
        }

        for (int i = index; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;

            dfs(n, arr, i + 1, cnt + 1, visited);

            visited[i] = false;
        }
    }

    private static int calDiff(int n, int[][] arr, boolean[] visited) {
        int startTeamSum = 0;
        int linkTeamSum = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (visited[i] && visited[j]) {
                    startTeamSum += arr[i][j] + arr[j][i];
                } else if (!visited[i] && !visited[j]) {
                    linkTeamSum += arr[i][j] + arr[j][i];
                }
            }
        }

        return Math.abs(startTeamSum - linkTeamSum);
    }


    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
