package Modelagem;

import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.util.LinkedList;

public class Grafo {

	/* define as arestas e as cores da mesma */
	public void defineArestas(ArrayList<Turmas> turmas, ArrayList<Professores> professores) {

		ArrayList<Aresta> listaAresta = new ArrayList<Aresta>();
		
		for (Turmas t : turmas) {
			for (Professores p : professores) {
				/* condicional por materias a serem dadas */
				Aresta aresta = new Aresta(p.id, t.id);
				listaAresta.add(aresta);
			}
		}
		
		for (int i = 0; i < listaAresta.size(); i++) {

			Turmas t = turmas.get(listaAresta.get(i).idTurma);
			Professores p = professores.get(listaAresta.get(i).idProfessor);
			
			/* os dois vertices nao tiveram arestas coloridas ainda*/
			
			if(t.cores.isEmpty() && p.cores.isEmpty()) {
				t.cores.add(0);
				p.cores.add(0);
				listaAresta.get(i).idHorario = 0;
			}else{
				
				for (int j = 0; j <= 5; j++) {
				
					int corAnalisada = j;
					
					boolean disponivelT = false;
					boolean disponivelP = false;
	
					if(t.cores.isEmpty()) {
						disponivelT = true;
					}else {
	
						boolean achouCor = false;
						
						for (int corTurma : t.cores) {
							if(corTurma == corAnalisada) {
								achouCor = true;
							}
						}
						
						if(!achouCor) {
							disponivelT = true;
						}
					}
					
					if(p.cores.isEmpty()) {
						disponivelP = true;
					}else {
						
						boolean achouCor = false;
						
						for (int corProf : p.cores) {
							if(corProf == corAnalisada) {
								achouCor = true;
							}
						}	
						
						if(!achouCor) {
							disponivelP = true;
						}
					}
					
					if(disponivelT && disponivelP) {
						t.cores.add(corAnalisada);
						p.cores.add(corAnalisada);
						listaAresta.get(i).idHorario = corAnalisada;
						break;
					}
				}
			}
			
		}
		
		/* exibe resultados */

		for (int i = 0; i < listaAresta.size(); i++) {

			Turmas t = turmas.get(listaAresta.get(i).idTurma);
			Professores p = professores.get(listaAresta.get(i).idProfessor);
			
			if(listaAresta.get(i).idHorario == 0) {
				System.out.println("("+t.id+"+"+p.id+") aresta cor vermelha - horario 7 horas");
			}else if(listaAresta.get(i).idHorario == 1) {
				System.out.println("("+t.id+"+"+p.id+") aresta cor azul - horario 8 horas");
			}else if(listaAresta.get(i).idHorario == 2) {
				System.out.println("("+t.id+"+"+p.id+") aresta cor verde - horario 9 horas");
			}else if(listaAresta.get(i).idHorario == 3) {
				System.out.println("("+t.id+"+"+p.id+") aresta cor amarelo - horario 10 horas");
			}else if(listaAresta.get(i).idHorario == 4) {
				System.out.println("("+t.id+"+"+p.id+") aresta cor roxo - horario 11 horas");
			}else{
				System.out.println("("+t.id+"+"+p.id+") error");
			}
			System.out.println(" ");
		}
	}
//========================================Metodo de coloração de vertices=============================================//
    private void setGrau(ArrayList<Vertice> v,ArrayList<ArestaCV> a) {
	    for (int i = 0;i < a.size();i++)
        {
            for (int j = 0;j < v.size();j++)
            {
                if (a.get(i).saida == v.get(j)){
                    v.get(j).grau++;
                    v.get(i).grau++;
                }
            }
        }
    }

    private ArrayList<ArestaCV> AddArestaCV (ArrayList<Vertice> ver)
    {
        ArrayList<ArestaCV> a = new ArrayList<>();
        for (int i = 0;i < ver.size(); i++)
        {
            for (int j = 0;j < ver.size(); j++)
            {
                if (i > j)
                    if (ver.get(i).professor.equals(ver.get(j).professor) || ver.get(i).periodo.equals(ver.get(j).periodo))
                    {
                        ArestaCV aux = new ArestaCV(ver.get(i),ver.get(j));
                        a.add(aux);
                    }
            }
        }
        return a;
    }

    public void AddVertices (ArrayList<String> dados) {
	    ArrayList<Vertice> vertex = new ArrayList<>();
	    ArrayList<ArestaCV> edge;
		String[] m;
		for (String dado : dados) {
			m = dado.split(";");
			Vertice vaux = new Vertice(m[0], m[1], m[2]);
			vertex.add(vaux);
		}
	    edge = AddArestaCV(vertex);
	    setGrau(vertex,edge);
        greedyColoring(vertex,edge);
    }


    boolean isAdjacent(Vertice vertex1,Vertice vertex2, ArestaCV edge){
		return ((edge.saida == vertex1 && edge.destino == vertex2 || edge.destino == vertex1 && edge.saida == vertex2));
    }
        void greedyColoring(ArrayList<Vertice> vertex,ArrayList<ArestaCV> edge) {
			//int result[] = new int[vertex.size()];-- Vertices

			// Initialize all vertices as unassigned
			// Assign the first color to first vertex
			// A temporary array to store the available colors. False
			// value of available[cr] would mean that the color cr is
			// assigned to one of its adjacent vertices
			boolean available[] = new boolean[vertex.size()];
			vertex.get(0).cor = 0;
			// Initially, all colors are available
			Arrays.fill(available, true);
			// Assign colors to remaining V-1 vertices
			for (int i = 1; i < vertex.size(); i++) {
            	/*boolean verificador = true;
            	for (int j=0;j<vertex.size();j++)
            		for (Vertice v:vertex)
            			for(ArestaCV a:edge)
            				if (isAdjacent(vertex.get(i),v,a))
            					if (v.cor != -1) {
									verificador = false;
									break;
								}
            	if (verificador)
            		vertex.get(i).cor = 0;*/

				// Process all adjacent vertices and flag their colors
				// as unavailable
				Iterator<ArestaCV> it = edge.iterator();
				while (it.hasNext()) {
					int cont=0;
					ArestaCV ar = it.next();
					for(Vertice v:vertex)
						if (v != vertex.get(i)) {
							if (isAdjacent(vertex.get(i), v, ar)) {
								if (v.cor != -1) {
									available[cont] = false;
									cont++;
								}
							}
						}
				}


			// Find the first available color
			int cr;
			for (cr = 0; cr < vertex.size(); cr++) {
				if (available[cr])
					break;
			}
			vertex.get(i).cor = cr;
			Arrays.fill(available, true);
		}
                // Reset the values back to true for the next iteration

            // print the result
            for (int u = 0; u < vertex.size(); u++){
		System.out.println("Vertex " + vertex.get(u).materia + " --->  Color "
				+ vertex.get(u).cor);
			}
        }
}
//====================================================================================================================//