import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Main{

    static List<String> nomes = Arrays.asList("Sócrates", "Platão", "Diógines", "Pitágoras", "Epicuro");

    public static int f = 5;

    static void main(String[] args) throws InterruptedException {

        Filosofo[] filosofos = new Filosofo[f];
        ReentrantLock[] garfos = new ReentrantLock[f];

        for (int i = 0; i < f; i++) {
            garfos[i] = new ReentrantLock(); // criar os garfos,se não tiver isso eles ficam como null
        }

        for (int i=0; i<f;i++){
            int g1=i;
            int g2= (i+1) %f;
            int menor = Math.min(g1, g2);
            int maior = Math.max(g1,g2);
            filosofos[i]= new Filosofo(nomes.get(i), garfos[menor], garfos[maior]);
        }

        for (int i = 0; i < f; i++) {
            filosofos[i].start();  // inicia as threads
        }

    }
}
