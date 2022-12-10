import java.lang.reflect.Array;
import java.util.*;

class ParticipantComparator implements Comparator<Participant>{

    // Overriding compare()method of Comparator
    public int compare(Participant s1, Participant s2) {
        if (s1.trick_num > s2.trick_num)
            return 1;
        if (s1.trick_num < s2.trick_num)
            return -1;
        if (s1.penalty < s2.penalty)
            return 1;
        if (s1.penalty > s2.penalty)
            return -1;
        return 0;
    }
}

class Participant {
    public String name;
    public int trick_num, penalty;
    Participant (String name, String trick_num, String penalty) {
        this.name = name;
        this.trick_num = Integer.parseInt(trick_num);
        this.penalty = Integer.parseInt(penalty);
    }
}

public class CompetitionResult {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        HashMap<String, PriorityQueue<Participant>> res = new HashMap<>();
        HashMap<String, Integer> max_sizes = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            String spec_info = input.next();
            String[] info = spec_info.split(",");
            res.put(info[0], new PriorityQueue<>(new ParticipantComparator()));
            max_sizes.put(info[0], Integer.parseInt(info[1]));
        }

        int part_num = input.nextInt();
        for (int i = 0; i < part_num; ++i) {
            String part_info = input.next();
            String[] info = part_info.split(",");
            Participant cur_p = new Participant(info[0], info[2], info[3]);
            PriorityQueue<Participant> cur_queue = res.get(info[1]);
            cur_queue.add(cur_p);
            if (cur_queue.size() > max_sizes.get(info[1]))
                cur_queue.poll();
        }

        ArrayList<String> final_result = new ArrayList<>();

        for (PriorityQueue<Participant> queue: res.values()) {
            for (Participant p: queue) {
                final_result.add(p.name);
            }
        }
        Collections.sort(final_result);
        for (String p_name: final_result)
            System.out.println(p_name);

    }
}
