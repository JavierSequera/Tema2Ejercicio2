package Ejercicio6;

public class Mesa {
    public Boolean[] palillos;

    public int segundoPalillo(int palillo){
        if(palillo+1 == this.palillos.length){
            return 0;
        }else{
            return palillo + 1;
        }
    }

    public synchronized void cogerPalillos(int filosofo){

        while(palillos[filosofo] || palillos[segundoPalillo(filosofo)]){
            try {
                wait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        palillos[filosofo] = true;
        palillos[segundoPalillo(filosofo)] = true;
    }

    public synchronized void dejarPalillos(int filosofo){
        palillos[filosofo] = false;
        palillos[segundoPalillo(filosofo)] = false;
        System.out.println("El "+Thread.currentThread().getName() +" ha terminado de comer" + ", palillos libres: "+filosofo +" y "+segundoPalillo(filosofo));
        notifyAll();
    }

}
