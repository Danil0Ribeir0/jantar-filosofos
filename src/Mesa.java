import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mesa {
    private final Lock[] garfos;
    private final Semaphore semaforoMesa;

    public Mesa(int numFilosofos) {
        garfos = new ReentrantLock[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            garfos[i] = new ReentrantLock(false);
        }

        semaforoMesa = new Semaphore(numFilosofos - 1, false);
    }

    public boolean pegarGarfos(int idFilosofo, long tempoMaximo) throws InterruptedException {
        if (!semaforoMesa.tryAcquire(tempoMaximo, TimeUnit.MILLISECONDS)) {
            return false;
        }

        int garfoEsquerdo = idFilosofo;
        int garfoDireito = (idFilosofo + 1) % garfos.length;

        if (garfos[garfoEsquerdo].tryLock(tempoMaximo, TimeUnit.MILLISECONDS)) {
            if (garfos[garfoDireito].tryLock(tempoMaximo, TimeUnit.MILLISECONDS)) {
                return true;
            } else {
                garfos[garfoEsquerdo].unlock();
                semaforoMesa.release();
                return false;
            }

        } else {
            semaforoMesa.release();
            return false;
        }
    }

    public void devolverGarfos(int idFilosofo) {
        int garfoEsquerdo = idFilosofo;
        int garfoDireito = (idFilosofo + 1) % garfos.length;

        garfos[garfoEsquerdo].unlock();
        garfos[garfoDireito].unlock();

        semaforoMesa.release();
    }
}