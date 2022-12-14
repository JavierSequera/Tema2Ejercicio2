package Ejercicio4;

import java.util.concurrent.Semaphore;

public class CarniceriaDificil implements Runnable {
    Semaphore semaforo = new Semaphore(4);
    Semaphore semaforo2 = new Semaphore(2);
    @Override
    public void run() {
        try{
            Boolean carniceria = false;
            //Comprueba si existen permisos en el semáforo
            if(semaforo.availablePermits()>0){
                semaforo.acquire();
                Accion("carnicería");
                semaforo.release();
                carniceria = true;
            }else{ //Si no existen permisos se va al segundo semáforo
                semaforo2.acquire();
                Accion("charcutería");
                semaforo2.release();
            }
            //Si ha entrado se va a la charcutería
            if(carniceria){
                semaforo2.acquire();
                Accion("charcutería");
                semaforo2.release();
            }else{//Si no ha entrado se va a la carnicería
                semaforo.acquire();
                Accion("carnicería");
                semaforo.release();
            }
        }catch(Exception ex){
            System.out.println();
        }
    }

    //Método que muestra por pantalla donde está siendo atendido el hilo
    private static void Accion(String seccion){
        System.out.println("Soy el "+ Thread.currentThread().getName() +" y estoy siendo atendido en la "+seccion);
        try{
            Thread.sleep(10000);
        }catch(Exception ex){
            System.out.println(ex);
        }
        System.out.println("Soy el " +Thread.currentThread().getName() +" y acabo de salir de la "+seccion);
    }

    public static void main(String[] args) {
        CarniceriaDificil sb = new CarniceriaDificil();
        for(int i=0; i<10; i++) {
            Thread hilo = new Thread(sb);
            hilo.setName("hilo "+i);
            hilo.start();
        }
    }
}
