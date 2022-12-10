import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
class Result{
    boolean inside = false;
    int first_int = 0;
    int second_int = 0;
    Result(Boolean inside, int value) {
        this.inside = inside;
        this.first_int = value;
        this.second_int = value;
    }
    Result(int first_int, int second_int) {
        this.first_int = first_int;
        this.second_int = second_int;
    }
}

class Sequence()



public class SuperSequence {

    public static Result geIntersection(ArrayList<Integer> first, ArrayList<Integer> second) {
        ArrayList<Integer> prev = new ArrayList<Integer>(second.size());
        for (int i = 0; i < second.size(); ++i) {
            prev.add(0);
        }
        System.out.println(prev);
        ArrayList<Integer> cur = new ArrayList<Integer>(second.size());
        int second_int = 0;
        for (int i = 0; i < first.size(); ++i) {
            cur.add(0, (first.get(i) == second.get(0)) ? 1 : 0);
            for (int j = 1; j < second.size(); ++j) {
                if (first.get(i) == second.get(j)) {
                    int value = prev.get(j-1) + 1;
                    cur.add(j, value);
                    if (value == Math.min(second.size(), first.size())) {
                        return new Result(true, value);
                    }
                }
                else cur.add(j, 0);
            }
            System.out.println(cur);
            second_int = Math.max(second_int, cur.get(cur.size()-1));
            prev = cur;
            cur = new ArrayList<Integer>(second.size());
        }
        return new Result(Collections.max(prev), second_int);

    }

    public static void main(String[] args) {
        ArrayList a = new ArrayList<Integer>(Arrays.asList(3,6,7,9));
        ArrayList b = new ArrayList<Integer>(Arrays.asList(7,9,3));
        ArrayList c = new ArrayList<Integer>(Arrays.asList(1,2));

        Result f_s = geIntersection(a,b);
        Result f_t = geIntersection(a,c);
        Result s_t = geIntersection(b,c);

        int f_s_t = f_s.first_int + s_t.second_int;



        Result res = geIntersection(a, b);
        System.out.println(res.inside + " " + res.first_int + " " + res.second_int);
    }
}
