import java.util.*;
public class Parse_code {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        ArrayList<HashMap<String, Integer>> mainList = new ArrayList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        String string = "";
        String[] strings = new String[2];
        ArrayList<Integer> list1;
        int value;
        while(true){
            string = input.nextLine();
            if(string == "")
                break;
            if(string == "{") {
                HashMap<String, Integer> hashMap1 = new HashMap<>(hashMap);
                mainList.add(hashMap1);
                hashMap = hashMap1;
                continue;
            }
            if(string == "}"){
                hashMap = mainList.get(mainList.size() - 2);
                mainList.remove(mainList.size() - 1);
                continue;
            }


            strings = string.split("=");
            if(!hashMap.containsKey(strings[0])) {
                hashMap.put(strings[0], 0);
            }
            if(Character.isDigit(strings[strings.length - 1].charAt(strings[strings.length - 1].length() - 1))) {
                hashMap.put(strings[0], Integer.parseInt(strings[strings.length - 1]));
            } else {
                if(!hashMap.containsKey(strings[strings.length - 1])){
                    hashMap.put(strings[strings.length - 1], 0);
                }
                value = hashMap.get(strings[strings.length - 1]);
                hashMap.put(strings[0], value);
                System.out.println(hashMap.get(strings[0]));
            }
        }
    }
}
