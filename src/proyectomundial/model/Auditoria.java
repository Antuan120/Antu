
package proyectomundial.model;


public class Auditoria {
     int home;
     int selecciones;
     int resultados;
     int dashSelecciones;
     int dashResultados;
     

    public Auditoria(int home, int selecciones, int resultados, int dashSelecciones, int dashResultados) {
        this.home = home;
        this.selecciones = selecciones;
        this.resultados = resultados;
        this.dashSelecciones = dashSelecciones;
        this.dashResultados = dashResultados;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public void setSelecciones(int selecciones) {
        this.selecciones = selecciones;
    }

    public void setResultados(int resultados) {
        this.resultados = resultados;
    }

    public void setDashSelecciones(int dashSelecciones) {
        this.dashSelecciones = dashSelecciones;
    }

    public void setDashResultados(int dashResultados) {
        this.dashResultados = dashResultados;
    }

    public int getHome() {
        return home;
    }

    public int getSelecciones() {
        return selecciones;
    }

    public int getResultados() {
        return resultados;
    }

    public int getDashSelecciones() {
        return dashSelecciones;
    }

    public int getDashResultados() {
        return dashResultados;
    }
    
}
