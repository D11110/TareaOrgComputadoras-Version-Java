package simulacion_tiempo_cache;

import java.util.ArrayList;

public class Simulacion_Tiempo_Cache {

    RAM ram = new RAM();
    ArrayList<Integer> datosEnRam = new ArrayList<>();
    Cache cache = new Cache();
    boolean filaValidar[] = cache.getValidar();
    int filaEtiqueta[] = cache.getEtiqueta();
    boolean filaMod[] = cache.getMod();
    int datos[][] = cache.getValores();
    double tiempo = 0.0;

    public static void main(String[] args) {

    }

    public int leer(int posicion) {
        int dato = 0;
        int etiqueta = posicion / 64;
        int bloque = posicion / 8;
        int linea = bloque % 8;
        int palabra = posicion % 8;
        if (filaValidar[etiqueta]) {
            if (filaEtiqueta[linea] == etiqueta) {
                dato = datos[linea][palabra];
                tiempo += 0.01;
            } else if (filaMod[linea]) {
                for (int i = (filaEtiqueta[linea] * 64) + (linea * 8), j = 0; i <= (bloque * 8) + 7; i++, j++) {
                    datosEnRam.add(datos[linea][j]);
                }
                for (int i = (bloque * 8), j = 0; i <= (bloque * 8) + 7; i++, j++) {
                    datos[linea][j] = datosEnRam.get(i);
                }
                dato = datos[linea][palabra];
                filaMod[linea] = false;
                tiempo += 0.11;
            } else {
                for (int i = (bloque * 8), j = 0; i <= (bloque * 8) + 7; i++, j++) {
                    datos[linea][j] = datosEnRam.get(i);
                }
                dato = datos[linea][palabra];
                filaMod[linea] = false;
                tiempo += 0.11;
            }
        } else {
            filaValidar[linea] = true;
            for (int i = (bloque * 8), j = 0; i <= (bloque * 8) + 7; i++, j++) {
                datos[linea][j] = datosEnRam.get(i);
            }
            dato = datos[linea][palabra];
            tiempo += 0.11;
        }
        return dato;
    }

}
