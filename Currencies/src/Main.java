import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] corrLine = input.nextLine().split(" ");
        int a,b,c;
        a = Integer.parseInt(corrLine[0]);
        b = Integer.parseInt(corrLine[1]);
        c = Integer.parseInt(corrLine[2]);
        int x,y,z;
        corrLine = input.nextLine().split(" ");
        x = Integer.parseInt(corrLine[0]);
        y = Integer.parseInt(corrLine[1]);
        z = Integer.parseInt(corrLine[2]);

        System.out.println(get_count(x / a, y / b, z / c));
    }
    public static int get_count(int x_n, int y_n, int z_n) {
        int max_x = y_n + z_n;
        int max_y = x_n + z_n;
        int max_z = x_n + y_n;
        int result = 0;
        for (int i = -x_n; i <= max_x; ++i) {
            if (max_y >= -i && -y_n <= -i ) {
                result += Math.max(0, Math.min(-i + y_n, max_z)) + Math.max(0, Math.min(i + max_y, z_n)) + 1;
                //System.out.println("here " + i + ' ' + result);
            } else if (-y_n <= -i) {
                result += Math.max(0, Math.min(max_y + y_n + 1, max_z + i + max_y + 1));
                //System.out.println(i +' ' + result);
            } else {
                result += Math.max(0, Math.min(max_y + y_n + 1, y_n + z_n - i + 1));
                //System.out.println("third " +i +' ' + result);
            }

        }
        return result;
    }
}
