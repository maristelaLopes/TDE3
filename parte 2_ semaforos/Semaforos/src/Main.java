

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    
    public static final int QTD_THREADS = 8;
    public static final int MAX_ITERACOES = 200000;
    
    static int contadorSemSincronia = 0;
    static int contadorComSincronia = 0;

    static Semaphore semaforo = new Semaphore(1);
    
    static List<Thread> listaThreads = new ArrayList<>();

    public static void main(String[] args) {

        for (int i = 0; i < QTD_THREADS; i++) {
            Trabalhador trab = new Trabalhador(i, 0);
            Thread t = new Thread(trab);
            listaThreads.add(t);
            t.start();
        }

        for (Thread t : listaThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("O valor esperado era: " + (QTD_THREADS * MAX_ITERACOES));
        System.out.println("Mas sem sincronizar deu erro e ficou: " + contadorSemSincronia);
        System.out.println("--------------------------------------------------");

       
        listaThreads.clear();

      
        for (int i = 0; i < QTD_THREADS; i++) {
            Trabalhador trab = new Trabalhador(i, 1);
            Thread t = new Thread(trab);
            listaThreads.add(t);
            t.start();
        }

        for (Thread t : listaThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Usando semaforo o resultado cravou em: " + contadorComSincronia);
    }
}