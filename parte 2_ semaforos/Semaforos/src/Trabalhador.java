
public class Trabalhador implements Runnable {

    private int id;
    private int tipo; 

    public Trabalhador(int id, int tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        if (this.tipo == 0) {
           
            for (int i = 0; i < Main.MAX_ITERACOES; i++) {
                Main.contadorSemSincronia++;
            }
        } else {
            
            for (int i = 0; i < Main.MAX_ITERACOES; i++) {
                try {
                    Main.semaforo.acquire(); 
                    Main.contadorComSincronia++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Main.semaforo.release(); 
                }
            }
        }
    }
}