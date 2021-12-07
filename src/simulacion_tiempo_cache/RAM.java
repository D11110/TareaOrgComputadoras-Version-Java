package simulacion_tiempo_cache;

import java.util.ArrayList;

public class RAM {

    private ArrayList<Integer> datos = new ArrayList<>();
    private int cantValores;

    public RAM() {
    }
    

    public RAM(int cantValores) {
        this.cantValores = cantValores;
    }

    public ArrayList<Integer> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<Integer> datos) {
        this.datos = datos;
    }

    public int getCantValores() {
        return cantValores;
    }

    public void setCantValores(int cantValores) {
        this.cantValores = cantValores;
    }
}