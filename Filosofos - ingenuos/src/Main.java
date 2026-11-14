import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{

    static List<String> nomes = Arrays.asList("Sócrates", "Platão", "Diógines", "Pitágoras", "Epicuro");

    public int rodadas;
    public static int f = 5;


    static void main(String[] args) {

        boolean[] garfoLivre = new boolean[f];
        Filosofo[] filosofos = new Filosofo[f];


        for(int i = 0; i < f; i++){
            filosofos[i] = new Filosofo(nomes.get(i));
            garfoLivre[i]= true;
            System.out.println(nomes.get(i));
        }


    }
}