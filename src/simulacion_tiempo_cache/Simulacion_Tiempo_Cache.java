package simulacion_tiempo_cache;

public class Simulacion_Tiempo_Cache {

    RAM ram = new RAM();
    Cache cache = new Cache();

    public static void main(String[] args) {
        double tiempo = 0.0;

    }

    public int leer(int posicion) {
        int dato = 0;
        boolean filaValidar[] = cache.getValidar();
        int filaEtiqueta[] = cache.getEtiqueta();
        if (filaValidar[posicion]) {
            
        }
        return dato;
    }

}
