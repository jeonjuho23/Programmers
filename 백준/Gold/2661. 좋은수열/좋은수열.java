import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static final int[] numbers = {1, 2, 3};

    private static String min = "";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());

        dfs(N, 0, new StringBuilder());

        System.out.println(min);
    }

    private static void dfs(int n, int cnt, StringBuilder numberString) {
        if (cnt >= n) {
            min = numberString.toString();
            return;
        }

        for (int number : numbers) {
            if (!min.isBlank()) return;
            numberString.append(number);
            if (isGoodSequence(numberString.toString())) {
                dfs(n, cnt + 1, numberString);
            }
            numberString.deleteCharAt(numberString.length() - 1);
        }
    }

    private static boolean isGoodSequence(String string) {
        int size = string.length();
        for (int i = size - 1; i > (size%2>0?size/2:size/2-1); i--) {
            String substring = string.substring(i, size);
            String currentSubstring = string.substring(i - substring.length(), i);
            if (substring.equals(currentSubstring)) {
                return false;
            }
        }
        return true;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static long stol(String s) {
        return Long.parseLong(s);
    }
}
