import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int corrDigit;
        boolean isContains;
        int biggestXOR = 0;
        int corrXOR;
        Iterator<Integer> iterator;
        Set<Integer> set = new HashSet<>();
        Scanner input = new Scanner(System.in);
        int countDigit = Integer.parseInt(input.nextLine());
        set.add(Integer.parseInt(input.nextLine()));
        System.out.println(biggestXOR);
        for(int i = 1; i < countDigit; ++i){
            corrDigit = Integer.parseInt(input.nextLine());
            isContains = set.add(corrDigit);
            if(isContains){
                iterator = set.iterator();
                for(int j = 0; j < set.size(); ++j){
                    if((corrXOR = iterator.next() ^ corrDigit) > biggestXOR){
                        biggestXOR = corrXOR;
                    }
                }
            }
            System.out.println(biggestXOR);
        }
    }
}