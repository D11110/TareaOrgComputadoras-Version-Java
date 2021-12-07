package simulacion_tiempo_cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Simulacion_Tiempo_Cache {

    static RAM ram = new RAM();
    static ArrayList<Integer> datosEnRam = new ArrayList<>();
    static Cache cache = new Cache();
    static boolean filaValidar[] = cache.getValidar();
    static int filaEtiqueta[] = cache.getEtiqueta();
    static boolean filaMod[] = cache.getMod();
    static int datos[][] = cache.getValores();
    static double tiempo = 0.0;

    public static void main(String[] args) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("C:\\Users\\Juda\\Desktop\\TareaOrgComputadoras-Version-Java\\datos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String enArchivo;
            while ((enArchivo = br.readLine()) != null) {
                //  System.out.println(enArchivo);
                datosEnRam.add(Integer.parseInt(enArchivo));
            }
        } catch (IOException | NumberFormatException e) {
        } finally {

            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e2) {
            }
        }

//        for (int i = 0; i < datosEnRam.size(); i++) {
//          //  System.out.println(datosEnRam.get(i));
//        }
        int n = 4096;
        for (int i = 0; i <= n - 2; i++) {
            for (int j = i + 1; j <= n - 1; j++) {
                if (leer(i) > leer(j)) {
                    int temp = leer(i);
                    escribir(i, leer(j));
                    escribir(j, temp);
                }
            }
        }

    }

    public static int leer(int posicion) {
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

    public static void escribir(int indice, int valor) {
        int dato = 0;
        int etiqueta = indice / 64;
        int bloque = indice / 8;
        int linea = bloque % 8;
        int palabra = indice % 8;
        if (filaValidar[etiqueta]) {
            if (filaEtiqueta[linea] == etiqueta) {
                datos[linea][palabra] = valor;
                dato = datos[linea][palabra];
                filaMod[linea] = true;
            }
            if (filaMod[linea]) {
                for (int i = (filaEtiqueta[linea] * 64) + (linea * 8), j = 0; i <= (bloque * 8) + 7; i++, j++) {
                    datosEnRam.add(datos[linea][j]);
                }
                for (int i = (bloque * 8), j = 0; i <= (bloque * 8) + 7; i++, j++) {
                    datos[linea][j] = datosEnRam.get(i);
                }
                filaMod[linea] = true;
                datos[linea][palabra] = valor;
                dato = datos[linea][palabra];
            }
            // transferir ram a la cache falta
            for (int i = (bloque * 8), j = 0; i <= (bloque * 8) + 7; i++, j++) {
                datos[linea][j] = datosEnRam.get(i);
            }
            filaMod[linea] = true;
            datos[linea][palabra] = valor;
            dato = datos[linea][palabra];

        }
        //transferir ram a la cache
        for (int i = (bloque * 8), j = 0; i <= (bloque * 8) + 7; i++, j++) {
            datos[linea][j] = datosEnRam.get(i);
        }
        filaValidar[linea] = true;
        filaMod[linea] = true;
        datos[linea][palabra] = valor;
        dato = datos[linea][palabra];
    }

}
