import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.*;

class MyDate {
    int year, month, day;

    MyDate(String date) {
        String[] date_info = date.split("\\.");
        this.day = Integer.parseInt(date_info[0]);
        this.month = Integer.parseInt(date_info[1]);
        this.year = Integer.parseInt(date_info[2]);
    }
    Boolean Less(MyDate other) {
        if (this.year == other.year) {
            if (this.month == other.month)
                return this.day < other.day;
            return this.month < other.month;
        }
        return this.year < other.year;
    }
}

class MyComparator implements Comparator<Object>{

    // Overriding compare()method of Comparator
    public int compare(Object s1, Object s2) {
        Map id_1 = (Map)s1;
        Map id_2 = (Map)s2;

        int first = Integer.parseInt(id_1.get("id").toString());
        int second = Integer.parseInt(id_2.get("id").toString());
        if (first > second)
            return 1;
        if (first < second)
            return -1;
        return 0;
    }
}

public class JSON_Y {
    public static void main(String[] args) {
        final int CONDITIONS_NUM = 5;

        Scanner input = new Scanner(System.in);
        String json_str = input.nextLine();
        Object obj = JSONValue.parse(json_str);
        JSONArray json = (JSONArray) obj;

        HashMap<String, String> conditions = new HashMap<>();
        JSONArray result = new JSONArray();

        for (int i = 0; i < CONDITIONS_NUM; ++i) {
            String cond = input.nextLine();
            String[] cond_info = cond.split(" ");
            conditions.put(cond_info[0], cond_info[1]);
        }

        for (Object item : json) {
            Map cur_item = (Map) item;

            String name = cur_item.get("name").toString().toLowerCase();

            if (!name.contains(conditions.get("NAME_CONTAINS").toLowerCase()))
                continue;

            Integer price = Integer.parseInt(cur_item.get("price").toString());
            if (price > Integer.parseInt(conditions.get("PRICE_LESS_THAN")))
                continue;
            if (price < Integer.parseInt(conditions.get("PRICE_GREATER_THAN")))
                continue;

            MyDate cur_date = new MyDate(cur_item.get("date").toString());
            MyDate before_date = new MyDate(conditions.get("DATE_BEFORE"));
            MyDate after_date = new MyDate(conditions.get("DATE_AFTER"));
            if (before_date.Less(cur_date))
                continue;
            if (cur_date.Less(after_date))
                continue;

            result.add(item);

        }
        Collections.sort(result, new MyComparator());
        System.out.println(result);
    }
}

