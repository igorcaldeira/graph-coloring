package Modelagem;

public class Vertice extends Grafo{
    String professor,materia,periodo;
    int cor = -1;
    int grau;
    public Vertice(String professor,String materia,String periodo)
    {
        this.professor = professor;
        this.materia = materia;
        this. periodo = periodo;
    }
}
