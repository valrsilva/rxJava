import java.util.ArrayList;
import java.util.List;

/**
 * Created by valdeci on 09/09/2016.
 */
public class DataGenerator {

    public static List<Integer> generateFibonaciNumbers(){

        // TODO Auto-generated method stub
        ArrayList a =new ArrayList();

        a.add(0);// enter the 1st elemnt of the list
        a.add(1);// 2nd elemnt
        int currIndex=1;

        while(currIndex<10)//--- i set the limit as first 50 items
        {
            a.add(((Integer)a.get(currIndex))+((Integer)a.get(currIndex-1)));
            currIndex++;
        }

        return a;
    }

    public static List<String> generateAlphabet(){

        ArrayList a = new ArrayList();

        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        a.add("e");
        a.add("f");
        a.add("g");
        a.add("h");
        a.add("i");
        a.add("j");

        return a;

    }

    public static List<String> generateWords(){

        ArrayList a = new ArrayList();

        a.add("airplane");
        a.add("beattle");
        a.add("cat");
        a.add("dog");
        a.add("energy");
        a.add("fire");

        return a;

    }
    public static List<String> generateAlphabetDuplicado(){

        ArrayList a = new ArrayList();
        a.addAll(generateAlphabet());
        a.addAll(generateAlphabet());
        a.addAll(generateAlphabet());
        return a;

    }
}
