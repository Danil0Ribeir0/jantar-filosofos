public class Main {
    public static void main(String[] args) {
        int numFilosofos = 5;

        visualizadorConsole.init(numFilosofos);

        Mesa mesa = new Mesa(numFilosofos);
        Filosofo[] filosofos = new Filosofo[numFilosofos];

        System.out.println("Iniciando o Jantar dos Filósofos...\n");
        System.out.println("Legenda: \u001B[34m[PENSANDO]\u001B[0m | \u001B[33m[TENTANDO]\u001B[0m | \u001B[32m[COMENDO]\u001B[0m | \u001B[36m[DEVOLVENDO]\u001B[0m | \u001B[31m[MORTO]\u001B[0m\n");

        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Filosofo(i, mesa);
            filosofos[i].start();
        }
    }
}