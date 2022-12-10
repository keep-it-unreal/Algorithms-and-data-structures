import javax.print.attribute.HashAttributeSet;
import java.util.*;

//Из тинькофф: задача на граф: добраться до конечного аэропорта максимально длинным путем, вернуть длину пути и чет/нечет аэропортов. Можно менять
//значения аэропорта с 0 на 1 (четное/нечет) и наоборот. Из четного аэропорта
//нельзя пойти нечетным путем и наоборот
// Если получается оборвать все пути - вернуть -1 и наиболее длинный путь.
//На вход подается количество вершин и количество путей первой строкой. Затем на каждой строке выводится 3 числа:
// первое - аэропорт вылета, второе - аэропорт назначения, третий - четность пути.
//


public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Airport> map = new HashMap();
        Airport currAirport, dest;
        Scanner input = new Scanner(System.in);
        String[] currLine = input.nextLine().split(" ");
        int flightsValue = Integer.parseInt(currLine[1]);
        for(int i = 0; i < flightsValue; ++i){
            currLine = input.nextLine().split(" ");
            if(map.containsKey(Integer.parseInt(currLine[0]))){
                currAirport = map.get(Integer.parseInt(currLine[0]));
                if(map.containsKey(Integer.parseInt(currLine[1]))) {
                    currAirport.setDestinations(map.get(Integer.parseInt(currLine[1])), Integer.parseInt(currLine[2]));
                } else {
                    dest = new Airport(Integer.parseInt(currLine[1]));
                    map.put(Integer.parseInt(currLine[1]), dest);
                    currAirport.setDestinations(dest, Integer.parseInt(currLine[2]));
                }
            } else{
                currAirport = new Airport(Integer.parseInt(currLine[0]));
                if(map.containsKey(Integer.parseInt(currLine[1]))) {
                    currAirport.setDestinations(map.get(Integer.parseInt(currLine[1])), Integer.parseInt(currLine[2]));
                } else {
                    dest = new Airport(Integer.parseInt(currLine[1]));
                    map.put(Integer.parseInt(currLine[1]), dest);
                    currAirport.setDestinations(dest, Integer.parseInt(currLine[2]));
                }
            }
        }
    }
    public static boolean dfs(Airport cur_air, int n) {
        boolean has_zero =

    }
}

class Airport{
    int number;
    int countFlight;
    boolean isPass;
    HashMap<Airport, Integer> destinations;

    public Airport(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCountFlight() {
        return countFlight;
    }

    public void setCountFlight(int countFlight) {
        this.countFlight = countFlight;
    }

    public boolean isPass() {
        return isPass;
    }

    public void setPass(boolean pass) {
        isPass = pass;
    }

    public HashMap<Airport, Integer> getDestinations() {
        return destinations;
    }

    public void setDestinations(Airport dest, Integer parity) {
        destinations.put(dest, parity);
    }
}