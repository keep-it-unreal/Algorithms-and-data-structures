import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int firstValue = 0;
        int secondValue;
        Scanner input = new Scanner(System.in);
        int value = input.nextInt();
        int sqrt = (int)Math.sqrt(value);

        for (int i = 2; i <= sqrt; ++i){
            if(value % i == 0){
                firstValue = i;
                break;
            }
        }
        if(firstValue > 0){
            firstValue = value / firstValue;
            secondValue = value - firstValue;
        } else{
            firstValue = 1;
            secondValue = value - 1;
        }
        System.out.println(firstValue + " " + secondValue);
    }
}