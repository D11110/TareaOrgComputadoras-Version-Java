package simulacion_tiempo_cache;

public class Cache {
    
    //Los 3 arreglos separados son como la metada por decirlo de alguna manera

    private boolean validar[] = new boolean[64];
    private boolean mod[] = new boolean[64];
    private int etiqueta[] = new int[64];
    
    //La matriz de valores es donde vamos almacenando los datos de la RAM

    private int valores[][] = new int[64][8];

    public Cache() {
    }

    public Cache(boolean[] validar, boolean[] mod, int[] etiqueta, int[][] valores) {
        this.validar = validar;
        this.mod = mod;
        this.etiqueta = etiqueta;
        this.valores = valores;
    }

    public boolean[] getValidar() {
        return validar;
    }

    public void setValidar(boolean[] validar) {
        this.validar = validar;
    }

    public boolean[] getMod() {
        return mod;
    }

    public void setMod(boolean[] mod) {
        this.mod = mod;
    }

    public int[] getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(int[] etiqueta) {
        this.etiqueta = etiqueta;
    }

    public int[][] getValores() {
        return valores;
    }

    public void setValores(int[][] valores) {
        this.valores = valores;
    }

    public int[] filaDeCache(int pos) { //Este metodo nos retornara una sola fila de la matriz de valores para luego comprar con la RAM y ver si fue modificada o no
        return this.valores[pos];
    }

}
