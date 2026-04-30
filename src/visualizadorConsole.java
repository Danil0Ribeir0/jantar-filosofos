public class visualizadorConsole {
    public static final int PENSANDO = 0;
    public static final int TENTANDO = 1;
    public static final int COMENDO = 2;
    public static final int DEVOLVENDO = 3;
    public static final int MORTO = 4;

    private static int[] estados;

    public static synchronized void init(int numFilosofos) {
        estados = new int[numFilosofos];
    }

    public static synchronized void atualizar(int id, int novoEstado) {
        estados[id] = novoEstado;
        imprimirMesa();
    }

    private static void imprimirMesa() {
        StringBuilder sb = new StringBuilder();
        sb.append("ESTADO DA MESA: |");

        for (int i = 0; i < estados.length; i++) {
            switch (estados[i]) {
                case PENSANDO:
                    sb.append("\u001B[34m  PENSANDO  \u001B[0m");
                    break;
                case TENTANDO:
                    sb.append("\u001B[33m  TENTANDO  \u001B[0m");
                    break;
                case COMENDO:
                    sb.append("\u001B[32m  COMENDO   \u001B[0m");
                    break;
                case DEVOLVENDO:
                    sb.append("\u001B[36m DEVOLVENDO \u001B[0m");
                    break;
                case MORTO:
                    sb.append("\u001B[31m   MORTO    \u001B[0m");
                    break;
            }
            sb.append("|");
        }
        System.out.println(sb.toString());
    }
}