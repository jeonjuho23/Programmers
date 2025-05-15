import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static boolean isAnswer = false;
    private static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int H = Integer.parseInt(input[2]);

        boolean[][] horizontal = new boolean[H+2][N+2];

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            horizontal[a][b] = true; // 올바른 위치 저장
        }

        for (int i = 0; i <= 3; i++) {
            answer = i;
            dfs(N, H, horizontal, 1, 0);
            if (isAnswer) break;
        }

        System.out.println(isAnswer ? answer : -1);
    }

    private static void dfs(int n, int h, boolean[][] horizontal, int startRow, int count) {
        if (isAnswer) return;
        if (count == answer) {
            if (check(n, h, horizontal)) isAnswer = true;
            return;
        }

        for (int i = startRow; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (!horizontal[i][j]
                    && (j == 1 || !horizontal[i][j-1])
                    && (j == n-1 || !horizontal[i][j+1])) {

                    horizontal[i][j] = true;
                    dfs(n, h, horizontal, i, count + 1);
                    horizontal[i][j] = false;
                }
            }
        }
    }

    private static boolean check(int n, int h, boolean[][] horizontal) {
        for (int i = 1; i <= n; i++) {
            int current = i;
            for (int r = 1; r <= h; r++) {
                if (horizontal[r][current]) { // 오른쪽 이동
                    current++;
                } else if (current > 1 && horizontal[r][current-1]) { // 왼쪽 이동
                    current--;
                }
            }
            if (current != i) return false;
        }
        return true;
    }
}
