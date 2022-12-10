import java.util.*;

public class Main {

    public static void main(String[] args) {
        int years;
        String[] winners = new String[3];
        Map<String, Integer> hashMap = new HashMap<>();
        Scanner input = new Scanner(System.in);
        years = input.nextInt();
        input.nextLine();
        for(int i = 0; i < years; ++i){
            winners = input.nextLine().split(" ");
            Arrays.sort(winners);
            String win = toStr(winners);
            if(hashMap.containsKey(win))
                hashMap.merge(win, 1, Integer::sum);
            else hashMap.put(win, 1);
        }
        System.out.println(Collections.max(hashMap.values()));

    }
    public static String toStr(String[] y){
        String x = "";
        for(int i = 0; i < y.length; ++i) {
            x += y[i];
            if (i < y.length - 1)
                x += " ";
        }
        return x;
    }
}