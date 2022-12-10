import java.util.*;
public class Main {
    int x1, y1, x2, y2;

    public static void main(String[] args) {
        int biggestX2, biggestY2, lesserX1, lesserY1, biggestSide;

        Main firstArea = new Main();
        Main secondArea = new Main();

        Scanner input = new Scanner(System.in);
        firstArea.x1 = input.nextInt();
        firstArea.y1 = input.nextInt();
        firstArea.x2 = input.nextInt();
        firstArea.y2 = input.nextInt();

        secondArea.x1 = input.nextInt();
        secondArea.y1 = input.nextInt();
        secondArea.x2 = input.nextInt();
        secondArea.y2 = input.nextInt();

        if(firstArea.x1 < secondArea.x1)
            lesserX1 = firstArea.x1;
        else lesserX1 = secondArea.x1;

        if(firstArea.y1 < secondArea.y1)
            lesserY1 = firstArea.y1;
        else lesserY1 = secondArea.y1;

        if(firstArea.x2 > secondArea.x2)
            biggestX2 = firstArea.x2;
        else biggestX2 = secondArea.x2;

        if(firstArea.y2 > secondArea.y2)
            biggestY2 = firstArea.y2;
        else biggestY2 = secondArea.y2;

        biggestSide = biggestSide(lesserX1, lesserY1, biggestX2, biggestY2);
        System.out.println(biggestSide * biggestSide);
    }
    public static int biggestSide(int x1, int y1, int x2, int y2){
        if((x2 - x1) > (y2 - y1))
            return(x2 - x1);
        else return(y2 - y1);

    }
}