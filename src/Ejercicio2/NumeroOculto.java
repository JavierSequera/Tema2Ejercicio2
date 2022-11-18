package Ejercicio2;

public class NumeroOculto extends Thread{
    public static int numAleatorio;
    public static boolean acertado;

    @Override
    public void run() {
        int max = 100;
        int min = 1;
        int range = max - min + 1;
        int numero;
        int res = 0;
        int ronda = 1;

        // Bucle que valida si se ha encontrado el número
        while (res == 0){
            System.out.println("El "+this.getName() +" no ha acertado en la ronda "+ronda);
            numero = (int)(Math.random() * range) + min;
            res = propuestaNum(numero);
            ronda++;
            //Pausa de 1 segundo para que todos los hilos realicen la búsqueda a la vez y al mismo tiempo
            try{
                Thread.sleep(1000);
            }catch (Exception ex){

            }
        }
        //Si el resultado es -1 es que el mismo hilo ha encontrado el núemro
        if (res == -1){
            acertado = true;
            System.out.println("El número ha sido acertado por el "+this.getName() +" en la ronda "+ronda);
        }

    }


    //Método que comprueba si se ha adivinado el número o el mismo hilo lo ha adivinado
    public static synchronized int propuestaNum(int numero){
        int res = 0;
        if (numero == numAleatorio){
            res = -1;
        }
        if (acertado){
            res = 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int max = 100;
        int min = 1;
        int range = max - min + 1;
        //Creación del número aleatorio del 1 al 100
        numAleatorio = (int)(Math.random() * range) + min;
        System.out.println("El numero es "+numAleatorio);
        //Lanzamiento de los 10 hilos
        for (int i = 1; i <= 10; i++) {
            Thread hilo = new NumeroOculto();
            hilo.setName("Hilo "+i);
            hilo.start();
        }
    }
}
