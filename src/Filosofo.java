import java.util.Random;

public class Filosofo extends Thread {
    private final int id;
    private final Mesa mesa;
    private final Random random;

    public Filosofo(int id, Mesa mesa) {
        this.id = id;
        this.mesa = mesa;
        this.random = new Random();
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " começou a PENSAR.");
        Thread.sleep(random.nextInt(3000) + 1000);
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " conseguiu COMER.");
        Thread.sleep(random.nextInt(4000) + 2000);
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();

                System.out.println("Filósofo " + id + " está TENTANDO pegar os garfos.");

                long TEMPO_MAXIMO_FOME = 8000;
                boolean conseguiuComer = mesa.pegarGarfos(id, TEMPO_MAXIMO_FOME);

                if (!conseguiuComer) {
                    System.out.println("ALERTA: Filósofo " + id + " esperou tempo demais e MORREU DE FOME (Starvation)!");
                    break;
                }

                comer();

                System.out.println("Filósofo " + id + " está DEVOLVENDO os garfos.");
                mesa.devolverGarfos(id);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Filósofo " + id + " foi interrompido.");
        }
    }
}