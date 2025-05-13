
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[] numbers;

    private static int max;
    private static int min;

    // + - * /
    private static int[] operatorCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = stoi(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        operatorCount = new int[4];
        for (int i = 0; i < 4; i++) {
            operatorCount[i] = stoi(st.nextToken());
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        dfs(1, numbers[0]);

        System.out.println(max+"\n"+min);
    }

    private static void dfs(int index, int resultNumber) {
        if (index >= N) {

            max = Math.max(max, resultNumber);
            min = Math.min(min, resultNumber);
            return;
        }

        int number = numbers[index];
        for (int i = 0; i < 4; i++) {
            if (operatorCount[i] <= 0) continue;

            operatorCount[i]--;

            int res = calculator(i, number, resultNumber);
            dfs(index + 1, res);

            operatorCount[i]++;
        }
    }

    private static int calculator(int operatorIndex, int number, int resultNumber) {
        if (operatorIndex == 0) {
            return resultNumber + number;
        } else if (operatorIndex == 1) {
            return resultNumber - number;
        } else if (operatorIndex == 2) {
            return resultNumber * number;
        } else {
            return resultNumber / number;
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
