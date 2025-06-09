import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = stoi(br.readLine());
        int[] arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] += arr[i - 1] + stoi(st.nextToken());
        }
        int[][] dp = new int[4][n + 1];
        int maxDrag = stoi(br.readLine());
        for (int i = 1; i < 4; i++) {
            for (int j = i * maxDrag; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - maxDrag] + arr[j] - arr[j - maxDrag]);
            }
        }

        System.out.println(dp[3][n]);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
