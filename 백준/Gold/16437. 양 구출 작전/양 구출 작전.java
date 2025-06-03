import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 첫 시도는 Tree + DFS 하지만 로직 상 중복적으로 노드를 지나가게 되는 문제와 입력이 순서대로 되지 않는다는 문제가 있어 한계에 부딪힘
 */


public class Main {

    private static int N;
    private static List<List<Integer>> list;
    private static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());

        list = new ArrayList<>();
        list.add(0, null);
        for (int i = 1; i <= N; i++) {
            list.add(i, new ArrayList<>());
        }
        dp = new long[N + 1];
        StringTokenizer st;
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            char t = st.nextToken().charAt(0);
            int a = stoi(st.nextToken());
            int p = stoi(st.nextToken());

            list.get(p).add(i);
            if (t == 'W') {
                a *= -1;
            }
            dp[i] = a;
        }

        dfs(1, -1);

        System.out.println(dp[1]);
    }

    private static void dfs(int index, int prev) {
        for (int next : list.get(index)) {
            dfs(next, index);
        }

        if (prev != -1 && dp[index] > 0) {
            dp[prev] += dp[index];
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
