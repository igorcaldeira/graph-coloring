package Modelagem;

public class Vertice extends Grafo{
    String professor,materia,periodo;
    int cor = -1;
    int grau;
    boolean available = true;
    public Vertice(String materia,String professor,String periodo)
    {
        this.materia = materia;
        this.professor = professor;
        this. periodo = periodo;
    }
}
