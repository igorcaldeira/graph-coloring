package ModelagemCV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GrafoCV {
    //======================== Metodo para adicionar arestas no grafo ================================================//
    private ArrayList<ArestaCV> AddArestaCV (ArrayList<Vertice> ver)
    {
        ArrayList<ArestaCV> aresta = new ArrayList<>();
        //Verifica todas as interacoes entre todos os vertices do grafo
        for (int i = 0;i < ver.size(); i++)
        {
            for (int j = 0;j < ver.size(); j++)
            {
                if (i > j)
                    //Cria uma aresta se houver conflitos de periodo ou professor lecionando a materia
                    if (ver.get(i).professor.equals(ver.get(j).professor) || ver.get(i).periodo.equals(ver.get(j).periodo))
                    {
                        ArestaCV aux = new ArestaCV(ver.get(i),ver.get(j));
                        aresta.add(aux);
                    }
            }
        }
        return aresta;
    }
/*
*                               Metodo que cria vertices e arestas e faz a coloração de vertices.
*                               Heuristica utilizada: Heuristica Gulosa.
*
 */
    public ArrayList<Vertice> CriarVertices (ArrayList<String> dados) {
        ArrayList<Vertice> vertex = new ArrayList<>();
        ArrayList<ArestaCV> edge;
        String[] m;
        //Verifica os dados do arquivo
        for (String dado : dados) {
            // Separa os dados do arquivo e os coloca nos respectivos lugares do vertice
            m = dado.split(";");
            Vertice vaux = new Vertice(m[0], m[1], m[2]);
            vertex.add(vaux);
        }
        edge = AddArestaCV(vertex);
        ColoracaoGulosa(vertex,edge);
        return vertex;
    }

    //======================== Metodo para verificar se um vertice é adjacente a outro ===============================//
    boolean Adjacente(Vertice vertice1,Vertice vertice2, ArestaCV aresta){
        //Retorna true se o vertice de origem for igual a vertice1 e o de destino for igual a vertice2 ou vice-versa
        return ((aresta.origem == vertice1 && aresta.destino == vertice2 || aresta.destino == vertice1 && aresta.origem == vertice2));
    }
    void ColoracaoGulosa(ArrayList<Vertice> vertices,ArrayList<ArestaCV> arestaCVS) {
        // disponivel: um vetor temporário que define as cores disponiveis
        boolean disponivel[] = new boolean[vertices.size()];
        //Colore o primeiro vertice
        vertices.get(0).cor = 0;
        // Carrrega o vetor "Disponivel" com true, para demonstrar que todas as cores estao disponiveis
        Arrays.fill(disponivel, true);
        int cont=0;
        // Gera cores para os n-1 vertices
        for (int i = 1; i < vertices.size(); i++) {
            //Identifica todos os vertices adjacentes ao vertice escolhido e marca as suas cores como indisponiveis
            Iterator<ArestaCV> it = arestaCVS.iterator();
            while (it.hasNext()) {
                ArestaCV ar = it.next();
                for(Vertice v:vertices)
                    if (v != vertices.get(i)) {
                        if (Adjacente(vertices.get(i), v, ar)) {
                            if (v.cor != -1) {
                                disponivel[cont] = false;
                                cont++;
                            }
                        }
                    }
            }


            // Acha a primeira cor disponivel e colore o vertice escolhido
            int cr;
            for (cr = 0; cr < vertices.size(); cr++) {
                if (disponivel[cr])
                    break;
            }
            vertices.get(i).cor = cr;
            // Disponibiliza todas as cores novamente para uma nova iteração
            Arrays.fill(disponivel, true);
            cont = 0;
        }
    }
    //=============================== Metodo para impressao de resultados na tela ====================================//
    public static void DefineHorariosCV(ArrayList<Vertice> vertices){
        for (int i=0;i<vertices.size();i++){
            // Verifica o numero da cor e imprime na tela o horario correspondente
            switch (vertices.get(i).cor){
                case 0:
                    System.out.println("Materia:"+vertices.get(i).materia+" esta planejada para Segunda-feira as 7:00");
                    break;
                case 1:
                    System.out.println("Materia:" +vertices.get(i).materia+" esta planejada para Segunda-feira as 8:50");
                    break;
                case 2:
                    System.out.println("Materia:" +vertices.get(i).materia+" esta planejada para Segunda-feira as 10:40");
                    break;
                case 3:
                    System.out.println("Materia:" +vertices.get(i).materia+" esta planejada para Terca-feira as 7:00");
                    break;
                case 4:
                    System.out.println("Materia:" +vertices.get(i).materia+" esta planejada para Terca-feira as 8:50");
                    break;
                case 5:
                    System.out.println("Materia:" +vertices.get(i).materia+" esta planejada para Terca-feira as 10:40");
                    break;
                case 6:
                    System.out.println("Materia:" +vertices.get(i).materia+" esta planejada para Quarta-feira as 7:00");
                    break;
                case 7:
                    System.out.println("Materia:" +vertices.get(i).materia+" esta planejada para Quarta-feira as 8:50");
                    break;
                case 8:
                    System.out.println("Materia:" +vertices.get(i).materia+" esta planejada para Quarta-feira as 10:40");
                    break;
                case 9:
                    System.out.println("Materia:" +vertices.get(i).materia+" esta planejada para Quinta-feira as 7:00");
                    break;
                case 10:
                    System.out.println("Materia:"+vertices.get(i).materia+" esta planejada para Quinta-feira as 8:50");
                    break;
                case 11:
                    System.out.println("Materia:" +vertices.get(i).materia+" esta planejada para Quinta-feira as 10:40");
                    break;
                case 12:
                    System.out.println("Materia:" +vertices.get(i).materia+" esta planejada para Sexta-feira as 7:00");
                    break;
                case 13:
                    System.out.println("Materia:" +vertices.get(i).materia+" esta planejada para Sexta-feira as 8:50");
                    break;
                case 14:
                    System.out.println("Materia:" +vertices.get(i).materia+" esta planejada para Quinta-feira as 10:40");
                    break;
            }
        }
    }
}


