import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        char[] arr2 = str1.toCharArray();
        char[] arr1 = str2.toCharArray();

        int[][] LCS = new int[arr1.length + 1][arr2.length + 1];

        for (int i = 0; i <= arr1.length; i++) {
            for (int j = 0; j <= arr2.length; j++) {
                if (i == 0 || j == 0) {
                    LCS[i][j] = 0;
                    continue;
                }

                if (arr1[i - 1] == arr2[j - 1]) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                    continue;
                }

                LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
            }
        }

        System.out.println(LCS[arr1.length][arr2.length]);

    }
}
