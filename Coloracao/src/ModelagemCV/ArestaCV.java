package ModelagemCV;


public class ArestaCV extends GrafoCV {
    Vertice origem,destino;
    //Cria uma aresta com vertice de origem e vertice de destino
    public ArestaCV(Vertice origem,Vertice destino)
    {
        this.origem = origem;
        this.destino = destino;
    }
}
