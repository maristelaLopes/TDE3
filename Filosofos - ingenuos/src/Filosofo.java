import java.util.Random;

public class Filosofo {

    public String nome;
    public int estado; //0 pensado, 1 com fome, 2 comendo
    public boolean garfod;
    public boolean garfoe;
    public int rodada;

    Random random = new Random();

    public Filosofo(String nome) {
        this.nome= nome;
        this.estado = 0;
        this.garfod = false;
        this.garfoe = false;
        this.rodada =0;
    }

    public void mudarEstado() {
        while (true) {
            int est = random.nextInt(0, 1);
            if (est == 1) {
                //tentar pegar os dois garfos se conseguir come
            } else {
                pensar();
               //preciso fazer esperar uns 5 seg
                //Da pra fazer com import de threads ou da pra eu não esperar e só fazer um break em caso de deadlock
            }
        }
    }

    public void pensar(){
        System.out.println(this.nome + "está pensando");
    }

}
