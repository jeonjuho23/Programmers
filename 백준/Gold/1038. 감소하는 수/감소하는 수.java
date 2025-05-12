
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static int N;
    static List<Long> decreasingNumbers;

    public static void main(String[] args) throws Exception {

        init();
        solution();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        decreasingNumbers = new ArrayList<>();

        // 모든 감소하는 수를 탐색하여 저장
        setDecreasingNumbers();
    }

    private static void setDecreasingNumbers() {
        // 재귀를 통해서 모든 경우 탐색 및 저장
        for (int i = 0; i < 10; i++) {
            re(i, 1);
        }

        // 정렬을 통해 확실하게 순서를 설정
        decreasingNumbers.sort(Comparator.naturalOrder());
    }

    private static void re(long num, int idx) {
        if(idx>10) return;

        decreasingNumbers.add(num);
        for (int i = 0; i < num % 10; i++) {
            re((num * 10) + i, idx + 1);
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void solution() {
        if(N >= decreasingNumbers.size()) {
            System.out.println("-1");
            return;
        }
        long answer = decreasingNumbers.get(N);
        System.out.println(answer);
    }
}
