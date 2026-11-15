

import java.util.ArrayList;

public class Main {

    static ArrayList<Thread> threads = new ArrayList<>();
    
    static Object trava1 = new Object();
    static Object trava2 = new Object();

    public static int modo = 1; // tem o modo 1 e 2, 1 - deadlock, 2- arrumado.

    public static void main(String[] args) {
        
        for (int i = 0; i < 2; i++) {
            Processo p = new Processo(i);
            Thread t = new Thread(p);
            threads.add(t);
            t.start();
        }

    }
}
