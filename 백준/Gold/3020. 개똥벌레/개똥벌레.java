import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int H = stoi(st.nextToken());

        int[] down = new int[H + 2];
        int[] up = new int[H + 2];
        for (int i = 1; i <= N / 2; i++) {
            int a = stoi(br.readLine());
            int b = H - stoi(br.readLine()) + 1;
            down[a]++;
            up[b]++;
        }

        for (int i = 1; i <= H; i++) {
            down[i] += down[i - 1];
        }
        for (int i = H; i >= 1; i--) {
            up[i] += up[i + 1];
        }

        int min = Integer.MAX_VALUE;
        int minCount = 0;

        for (int i = 1; i < H + 1; i++) {
            int diff = down[H] - down[i - 1] + up[1] - up[i + 1];
            if (diff < min) {
                min = diff;
                minCount = 1;
            } else if (diff == min) {
                minCount++;
            }
        }

        System.out.println(min + " " + minCount);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
