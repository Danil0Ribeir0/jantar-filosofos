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
        visualizadorConsole.atualizar(id, visualizadorConsole.PENSANDO);
        Thread.sleep(random.nextInt(3000) + 1000);
    }

    private void comer() throws InterruptedException {
        visualizadorConsole.atualizar(id, visualizadorConsole.COMENDO);
        Thread.sleep(random.nextInt(4000) + 2000);
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();

                visualizadorConsole.atualizar(id, visualizadorConsole.TENTANDO);

                long TEMPO_MAXIMO_FOME = 8000;
                boolean conseguiuComer = mesa.pegarGarfos(id, TEMPO_MAXIMO_FOME);

                if (!conseguiuComer) {
                    visualizadorConsole.atualizar(id, visualizadorConsole.MORTO);
                    System.out.println("\nALERTA: Filósofo " + id + " esperou tempo demais e MORREU DE FOME (Starvation)!");
                    break;
                }

                comer();

                visualizadorConsole.atualizar(id, visualizadorConsole.DEVOLVENDO);
                mesa.devolverGarfos(id);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("\nFilósofo " + id + " foi interrompido.");
        }
    }
}