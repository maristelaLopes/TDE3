

public class Processo implements Runnable {

    public int nome;

    public Processo(int nome) {
        this.nome = nome;
    }

    @Override
    public void run() {
        if (Main.modo == 1) {
            
            if (this.nome == 0) {
                synchronized (Main.trava1) {
                    System.out.println("processo 0 pegou a trava 1");
                    
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    synchronized (Main.trava2) {
                        System.out.println("processo 0 terminou");
                    }
                }
            } else {
                synchronized (Main.trava2) {
                    System.out.println("processo 1 pegou a trava 2");
                    
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    synchronized (Main.trava1) {
                        System.out.println("processo 1 terminou");
                    }
                }
            }
        } else {
            
            if (this.nome == 0) {
                synchronized (Main.trava1) {
                    System.out.println("processo 0 pegou a trava 1");
                    
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    synchronized (Main.trava2) {
                        System.out.println("processo 0 terminou");
                    }
                }
            } else {
                synchronized (Main.trava1) {
                    System.out.println("processo 1 pegou a trava 1 ");
                    
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    synchronized (Main.trava2) {
                        System.out.println("processo 1 terminou");
                    }
                }
            }
        }
    }
}
