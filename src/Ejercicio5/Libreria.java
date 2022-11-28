package Ejercicio5;

import java.util.ArrayList;
import java.util.List;

public class Libreria implements Runnable{

    public static List<Libro>ListaLibro = new ArrayList<>();


    @Override
    public void run() {

        int primerLibro = (int)(Math.random() * 9)+0;
        int segundoLibro = (int)(Math.random() * 9)+0;
        int tiempoEspera = (int)(Math.random() * 2)+3;
        Libro libro1 = ListaLibro.get(primerLibro);
        Libro libro2 = ListaLibro.get(segundoLibro);
        try {
            synchronized (libro1) {
                synchronized (libro2) {
                    while (libro1.isEnPosesion() && libro2.isEnPosesion()) {
                        libro1.wait();
                        libro2.wait();
                    }

                    libro1.setEnPosesion(true);
                    libro2.setEnPosesion(true);

                    System.out.println(Thread.currentThread().getName() + " ha escogido el libro " + primerLibro);
                    System.out.println(Thread.currentThread().getName() + " ha escogido el libro " + segundoLibro);

                    Thread.sleep(tiempoEspera*1000);

                    libro1.setEnPosesion(false);
                    libro2.setEnPosesion(false);
                    libro1.notifyAll();
                    libro2.notifyAll();

                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            ListaLibro.add(new Libro());
        }

        for (int i = 1; i < 5; i++) {
            Libreria ewn = new Libreria();

            Thread hilo = new Thread(ewn);
            hilo.setName("Lector " + i);
            hilo.start();
        }
    }
}
