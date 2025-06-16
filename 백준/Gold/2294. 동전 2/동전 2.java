import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final int BIG_VALUE = 100001;

    private static int n, k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        k = stoi(st.nextToken());

        int[] coins = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            coins[i] = stoi(br.readLine());
        }

        int[] dp = new int[k + 1];
        dp[0] = 0;
        for (int i = 1; i <= k; i++) {
            dp[i] = BIG_VALUE;
        }
        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        if (dp[k] == BIG_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(dp[k]);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
