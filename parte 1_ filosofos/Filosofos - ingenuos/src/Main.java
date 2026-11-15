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
            filosofos[i].start(); // metódo da biblioteca padrão do java, inicia diferente de uma função, como se fossem treads mesmo
        }
    }
}