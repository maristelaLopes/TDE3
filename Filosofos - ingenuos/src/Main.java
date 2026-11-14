import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Main{

    static List<String> nomes = Arrays.asList("Sócrates", "Platão", "Diógines", "Pitágoras", "Epicuro");

    public static int f = 5;


    public static void main(String[] args) throws InterruptedException {

        Filosofo[] filosofos = new Filosofo[f];
        ReentrantLock[] garfos = new ReentrantLock[f];

        for (int i = 0; i < f; i++) {
            garfos[i] = new ReentrantLock(); // criar os garfos,se não tiver isso eles ficam como null
        }

        for (int i = 0; i < f; i++) {
            filosofos[i] = new Filosofo(nomes.get(i), garfos[i], garfos[(i + 1) % f]);
        }

        for (int i = 0; i < f; i++) {
            filosofos[i].start();                     // inicia as threads
        }

        while (true) {
            Thread.sleep(2400);
            boolean deadlock = true;
            for (Filosofo f: filosofos){
                if (!f.travado()){
                    deadlock= false;
                    break;
                }
            }
            if (deadlock){
                System.out.println("------------------------------------------------");
                System.out.println("Deadlock");
                System.out.println("------------------------------------------------");
                for(Filosofo f: filosofos){
                    System.out.println(f.nome+ " comeu " + f.vezescomeu + " vezes");
                }
                System.exit(0);
            }

        }
    }
}