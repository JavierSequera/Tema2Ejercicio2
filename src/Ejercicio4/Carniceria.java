package Ejercicio4;

import java.util.concurrent.Semaphore;

public class Carniceria implements Runnable {

    Semaphore semaforo = new Semaphore(4);
    @Override
    public  void run() {
        try{
            semaforo.acquire();
            System.out.println("Soy el "+ Thread.currentThread().getName() +" y estoy siendo atendido.");
            Thread.sleep(((int)Math.random() * 10000) + 1000);
            System.out.println("Soy el " +Thread.currentThread().getName() +" y acabo de salir de la carnicería");
            semaforo.release();
        }catch(Exception ex){

        }
    }

    public static void main(String[] args) {
        Carniceria sb = new Carniceria();
        for(int i=0; i<10; i++) {
            Thread hilo = new Thread(sb);
            hilo.setName("hilo "+i);
            hilo.start();
        }
    }
}
