import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Integer.parseInt(input.nextLine());
        BigDecimal result = new BigDecimal(0.0);
        BigDecimal squared_sin = new BigDecimal(Math.pow(Math.sin(Math.PI / n), 2));
        double alpha = 2 * Math.PI / n;


        if (n % 3 == 0) {
            result = result.add(get_square(n / 3, n / 3, n / 3, alpha, squared_sin));
            result = result.add(best_triangle((n - 3) / 3, alpha, squared_sin).multiply(new BigDecimal(3)));
        }
        else if (n % 3 == 1) {
            result = result.add(get_square(n / 3, n / 3, n / 3 + 1, alpha, squared_sin));
            result = result.add(best_triangle((n - 3) / 3, alpha, squared_sin).multiply(new BigDecimal(2)));
            result = result.add(best_triangle((n - 3) / 3 + 1, alpha, squared_sin));
        } else {
            result = result.add(get_square(n / 3, n / 3 + 1, n / 3 + 1, alpha, squared_sin));
            result = result.add(best_triangle((n - 3) / 3, alpha, squared_sin).multiply(new BigDecimal(2)));
            result = result.add(best_triangle((n - 3) / 3 - 1, alpha, squared_sin));
        }
        System.out.println(result);
    }

    public static BigDecimal best_triangle(int points_count, double alpha, BigDecimal squared_sin) {
        if (points_count < 3) return new BigDecimal(0);
        if (points_count % 2 == 1) {
            return get_square(points_count / 2, points_count / 2, points_count - 1,
                    alpha, squared_sin);
        } else {
            return get_square(points_count / 2, points_count / 2 - 1, points_count - 1,
                    alpha, squared_sin);
        }
    }

    public static BigDecimal get_square(int count_a, int count_b, int count_c,
                                   double alpha, BigDecimal squared_sin) {
        BigDecimal up = new BigDecimal(Math.sin(count_a * alpha / 2) *
                Math.sin(count_b * alpha / 2) * Math.sin(count_c * alpha / 2) / 2);
        System.out.println(up + " " + squared_sin);
        return up.divide(squared_sin, RoundingMode.HALF_UP);
    }
}
