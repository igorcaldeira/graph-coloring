package ModelagemCV;

public class Vertice extends GrafoCV {
    String professor,materia,periodo;
    // O vertice começa "sem cor",representado como -1
    int cor = -1;
    //Cria um vertice com os dados da materia,professor e periodo
    public Vertice(String materia,String professor,String periodo)
    {
        this.materia = materia;
        this.professor = professor;
        this. periodo = periodo;
    }
}
