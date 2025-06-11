import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n, k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        k = stoi(st.nextToken());

        int[] arr = new int[n + 1];
        int[] dp = new int[k + 1];

        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            arr[i] = stoi(br.readLine());
            for (int j = arr[i]; j <= k; j++) {
                dp[j] += dp[j - arr[i]];
            }
        }

        System.out.println(dp[k]);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
