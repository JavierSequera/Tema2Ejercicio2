package Ejercicio6;

public class Filósofo extends Thread {

    private Mesa mesa;
    private int numeroFilosofo;


    public Filósofo (Mesa mesa, int numeroFilosofo){
        this.mesa = mesa;
        this.numeroFilosofo = numeroFilosofo;
    }

    public synchronized void run(){
        while(true){
            System.out.println("El "+Thread.currentThread().getName() +" está pensando");
            try{
                Thread.sleep(3000);
            }catch (Exception e){
                System.out.println(e);
            }
            this.mesa.cogerPalillos(this.numeroFilosofo);
            System.out.println("El "+Thread.currentThread().getName() +" está comiendo");
            try{
                Thread.sleep(3000);
            }catch (Exception e){
                System.out.println(e);
            }
            this.mesa.dejarPalillos(this.numeroFilosofo);
        }
    }


    public static void main(String[] args) {
        Mesa mesa = new Mesa();
        mesa.palillos = new Boolean[]{false, false, false, false, false};
        for (int i = 0; i < 5; i++) {
        Filósofo f = new Filósofo(mesa, i);
        Thread h = new Thread(f);
        h.setName("filosofo "+i);
        h.start();
        }

    }
}
