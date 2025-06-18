
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] map;

    private static int[] dc = {0, 1, 1};
    private static int[] dr = {1, 0, 1};

    public static void main(String[] args) throws Exception {

        init();
        solution();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
    }

    private static int stoi(String string) {
        return Integer.parseInt(string);
    }

    private static void solution() {

        int[][] sum = new int[N][M];
        sum[0][0] = map[0][0];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                for (int d = 0; d < 3; d++) {
                    int tr = i + dr[d];
                    int tc = j + dc[d];

                    if (tr >= N || tc >= M) continue;

                    sum[tr][tc] = Math.max(sum[tr][tc], sum[i][j] + map[tr][tc]);
                }
            }
        }

        System.out.println(sum[N-1][M-1]);
    }


}
