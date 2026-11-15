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

    public Filosofo(String nome, ReentrantLock dir,ReentrantLock esq) {
        this.nome= nome;
        this.estado = 0;
        this.garfod = false;
        this.garfoe = false;
        this.vezescomeu =0;
        this.esq = esq;
        this.dir= dir;
    }

    public void run() {
        try {
            while (true) {
                mudarEstado(0); //0 é pensando
                Thread.sleep(tempoP);

                mudarEstado(1); //com fome
                esq.lock();
                garfoe= true;
                System.out.println(this.nome + " pegou o garfo esquerdo");

                Thread.sleep(tempoPegarGarfo);

                dir.lock();
                garfod= true;
                System.out.println(this.nome + " pegou o garfo direito");

                mudarEstado(2); //comendo
                vezescomeu +=1;
                Thread.sleep(tempoC);

                esq.unlock();
                dir.unlock();
                garfoe = false;
                garfod = false;
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
            System.out.println("-------------------------------------------");
            System.out.println(this.nome +" comeu "+ vezescomeu + " vezes");
            System.out.println("-------------------------------------------");
        }
    }

    public boolean travado() {
        if (estado == 1 && garfoe && !garfod){
            return true;
        }
        else{
            return false;
        }

    }
}
