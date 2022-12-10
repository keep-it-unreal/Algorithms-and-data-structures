import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.nextLine();
        String name = input.nextLine();
        String letters = input.nextLine();
        boolean beforeTab = true;
        int countBadWords = 0;
        char prevLetter = letters.charAt(0);

        int j = 1;
        for(int i = 1; i < name.length(); ++i){
            if(name.charAt(i) == ' '){
                beforeTab = true;
                prevLetter = ' ';
                continue;
            }
            if(letters.charAt(j) == prevLetter && beforeTab){
                ++countBadWords;
                beforeTab = false;
            }
            prevLetter = letters.charAt(j);
            ++j;
        }
        System.out.println(countBadWords);
    }
}