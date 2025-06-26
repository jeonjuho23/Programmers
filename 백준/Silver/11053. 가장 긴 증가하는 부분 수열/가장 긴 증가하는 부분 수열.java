
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());

        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = stoi(st.nextToken());
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int maxDp = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxDp = Math.max(maxDp, dp[i]);
        }

        System.out.println(maxDp);

    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
