import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static int maxSum = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        String[] inputArr = br.readLine().split(" ");
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = stoi(inputArr[i]);
        }

        dfs(N, A, 0, 0, 0, new boolean[N]);

        System.out.println(maxSum);
    }

    private static void dfs(int n, int[] a, int cnt, int sum, int last, boolean[] visited) {
        if (cnt >= n) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            visited[i] = true;

            int tempSum = sum;
            if (cnt != 0) {
                tempSum += Math.abs(last - a[i]);
            }
            dfs(n, a, cnt + 1, tempSum, a[i], visited);
            visited[i] = false;
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
