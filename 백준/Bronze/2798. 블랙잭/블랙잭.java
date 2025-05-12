import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static int maxSum = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = stoi(input[0]);
        int M = stoi(input[1]);
        input = br.readLine().split(" ");
        int[] cards = new int[N];
        for (int i = 0; i < N; i++) {
            cards[i] = stoi(input[i]);
        }

        dfs(N, M, cards, 0, 0, new boolean[N]);

        System.out.println(maxSum);
    }

    private static void dfs(int n, int m, int[] cards, int cnt, int sum, boolean[] visited) {
        if (sum > m) {
            return;
        }
        if (cnt >= 3) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;

            dfs(n, m, cards, cnt + 1, sum + cards[i], visited);

            visited[i] = false;
        }
    }


    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
