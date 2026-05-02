public class Main {
    public static void main(String[] args) {
        int numFilosofos = 5;

        visualizadorConsole.init(numFilosofos);

        Mesa mesa = new Mesa(numFilosofos);
        Filosofo[] filosofos = new Filosofo[numFilosofos];

        System.out.println("Iniciando o Jantar dos Filósofos...\n");

        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Filosofo(i, mesa);
            filosofos[i].start();
        }
    }
}