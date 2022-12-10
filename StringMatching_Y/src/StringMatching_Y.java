import java.util.HashMap;
import java.util.Scanner;

public class StringMatching_Y {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String A = input.next();
        String B = input.next();

        char[] match_res = new char[A.length()];
        HashMap<Character, Integer> A_chars =  new HashMap<>();

        for (int i = 0; i < A.length(); ++i) {
            if (A.charAt(i) == B.charAt(i)) {
                match_res[i] = 'P';
            } else {
                A_chars.merge(A.charAt(i), 1, Integer::sum);
            }
        }

        for (int i = 0; i < A.length(); ++i) {
            if (match_res[i] == 'P')
                continue;

            int cur_count = A_chars.getOrDefault(B.charAt(i), 0);
            if (cur_count > 0) {
                match_res[i] = 'S';
                A_chars.put(B.charAt(i), cur_count - 1);
            }
            else match_res[i] = 'I';
        }

        for (char i: match_res) {
            System.out.print(i);
        }
    }

}
