import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Filosofo extends Thread{

    public String nome;
    public int estado; //0 pensado, 1 com fome, 2 comendo
    public boolean garfod;
    public boolean garfoe;
    public int vezescomeu;
    ReentrantLock esq;
    ReentrantLock dir;

    static final int tempoP = 1000;
    static final int tempoPegarGarfo= 100;
    static final int tempoC = 500;

    Random random = new Random();

    public Filosofo(String nome, ReentrantLock dir,ReentrantLock esq) {
        this.nome= nome;
        this.estado = 0;
        this.garfod = false;
        this.garfoe = false;
        this.vezescomeu =0;
        this.esq = esq;
        this.dir= dir;
    }

    public void jantar() {
        try {
            while (true) {
                mudarEstado(0); //0 é pensando
                Thread.sleep(tempoP);

                mudarEstado(1);
                esq.lock();
                garfoe= true;
                System.out.println(this.nome + " pegou o garfo esquerdo");

                Thread.sleep(tempoPegarGarfo);

                dir.lock();





            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void mudarEstado(int estado) {
        this.estado = estado;
        if (this.estado == 0) {
            System.out.println(this.nome + " está pensando");
        } else if (this.estado == 1) {
            System.out.println(this.nome + " está com fome");
        } else {
            System.out.println(this.nome + " está comendo");
        }
    }
}
