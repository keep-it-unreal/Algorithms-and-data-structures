import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;



public class Super_sequence {

    class Result{
        boolean inside = false;
        int first_int = 0;
        int second_int = 0;
        Result(Boolean inside) {
            this.inside = inside;
        }
        Result(int first_int, int second_int) {
            this.first_int = first_int;
            this.second_int = second_int;
        }

    }

    public static Result geIntersection(ArrayList<Integer> first, ArrayList<Integer> second) {
        ArrayList<Integer> prev = new ArrayList<Integer>(second.size());
        ArrayList<Integer> cur = prev;
        int second_int = 0;
        for (int i = 0; i < first.size(); ++i) {
            cur.add(0, (first.get(i) == second.get(0)) ? 1 : 0);
            for (int j = 1; j < second.size(); ++j) {
                if (first.get(i) == second.get(j)) {
                    int value = prev.get(j-1) + 1;
                    cur.add(j, value);
                    if (value == Math.min(second.size(), first.size())) {
                        return new Result(true);
                    }
                }
            }
            second_int = Math.max(second_int, cur.get(-1));
            prev = cur;
        }
        return new Result(Collections.max(cur), second_int);

    }

    public static void main(String[] args) {
        ArrayList a = new ArrayList<Integer>(Arrays.asList(3,6,7,9));
        ArrayList b = new ArrayList<Integer>(Arrays.asList(7,9,1));
        Result res = geIntersection(a, b);
        System.out.println(res.inside + " " + res.first_int + " " + res.second_int);
    }
}
