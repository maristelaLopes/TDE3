import java.util.ArrayList;
public class Main{

    static ArrayList<Filosofo> filosofos = new ArrayList<>();
    static ArrayList<String> nomes= new ArrayList<>();


    public int rodadas;
    public static int f = 5;
    public int  garfos = f-1;


    static void main(String[] args) {

        for(int i = 0; i < f; i++){
            Filosofo filosofo = new Filosofo(i);
            filosofos.add(filosofo);
        }

    }
}