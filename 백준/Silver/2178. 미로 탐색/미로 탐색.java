
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int[][] d = {{0,1},{0,-1},{1,0},{-1,0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] maze = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(row[j]);
            }
        }

        // bfs
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        while(!queue.isEmpty()){
            int[] location = queue.poll();
            int r = location[0];
            int c = location[1];
            cnt = location[2];
            if(r>=N-1 && c>=M-1) break;
            for(int[] dd : d){
                int rr = r + dd[0];
                int cc = c + dd[1];
                if(rr >= 0 && cc >= 0 && rr < N && cc < M && !visited[rr][cc] && maze[rr][cc] == 1){
                    visited[rr][cc] = true;
                    queue.offer(new int[]{rr, cc, cnt+1});
                }
            }
        }

        System.out.println(cnt);
    }
}
