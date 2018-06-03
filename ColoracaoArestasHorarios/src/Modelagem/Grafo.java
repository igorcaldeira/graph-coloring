package Modelagem;

import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.util.LinkedList;

public class Grafo {

	public void defineCA(ArrayList<String> values) {

		ArrayList<Aresta> listaAresta = new ArrayList<>();
        ArrayList<Professores> professores = new ArrayList<>();
        ArrayList<Turmas> turmas = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
        	String[] parts = values.get(i).split(";");
        	
    		String nome_professor = parts[1].trim();
    		String nome_materia = parts[0].trim();
    		String numero_periodo = parts[2].trim();
    		
    		/*tenta adicionar professor*/
    		
    		Integer id_prof = professores.size() + 0;
    		Professores prof = new Professores(id_prof, nome_professor);
    		boolean temProfessor = false;
    		int profIdJaCadastrado = id_prof;
    		for (int j = 0; j < professores.size(); j++) {
				if(professores.get(j).nome.equals(nome_professor)) {
					temProfessor = true;
					profIdJaCadastrado = professores.get(j).id;
				}
			}
    		if(!temProfessor) {
        		professores.add(prof);
    		}
    		
    		/*tenta adicionar turma*/
    		
            Integer id_turm = turmas.size() + 0;
    		Turmas turm = new Turmas(id_turm, numero_periodo);
    		boolean temTurma = false;
    		int turmIdJaCadastrado = id_turm;
    		for (int j = 0; j < turmas.size(); j++) {
				if(turmas.get(j).numero_turma.equals(numero_periodo)) {
					temTurma = true;
					turmIdJaCadastrado = turmas.get(j).id;
				}
			}
    		if(!temTurma) {
        		turmas.add(turm);
    		}
    		
    		/*adiciona aresta*/
    		
    		Aresta a = new Aresta(profIdJaCadastrado, turmIdJaCadastrado, nome_materia);
    		listaAresta.add(a);
		}

        
        this.defineArestas(listaAresta, professores, turmas);
	}
	
	/* define as arestas e as cores da mesma */
	
	public void defineArestas(ArrayList<Aresta> listaAresta,  ArrayList<Professores> professores, ArrayList<Turmas>  turmas) {

		for (int i = 0; i < listaAresta.size(); i++) {

			Aresta a = listaAresta.get(i);
			
			Turmas t = turmas.get(a.turma);
			Professores p = professores.get(a.professor);
			
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

		/* cria uma pré lista com vários horários disponiveis na grade do curso*/
		ArrayList<String> disponibilidades = new ArrayList<>();

		String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
		String[] horarios = {"Horário de 07:00", "Horário de 08:50", "Horário de 10:40"};
		
		String dia_string = "";
		String horario_string = "";
		
		for(int dia = 0; dia < 4; dia++) {
			dia_string = dias[dia];
			for(int horario = 0; horario < 3; horario++) {
				horario_string = horarios[horario];
				disponibilidades.add(dia_string +"-Feira / "+horario_string);
			}
		}

		System.out.println(" ");
		for (int i = 0; i < listaAresta.size(); i++) {

			Aresta a = listaAresta.get(i);

			Turmas t = turmas.get(a.turma);
			Professores p = professores.get(a.professor);

			System.out.println(disponibilidades.get(listaAresta.get(i).idHorario)+" - "+a.nome_materia + " / Período: " + t.numero_turma+"  ("+t.id+" - "+p.id+") ");
			
			System.out.println(" ");
		}
	}

	/**/
	/**/
	//================Metodo de coloraÃ§Ã£o de vertices=======================//
	/**/
	/**/
	
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

    public void AddVerticesCV(ArrayList<String> dados) {
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
        void greedyColoring(ArrayList<Vertice> vertex,ArrayList<ArestaCV> edge)
        {
            //int result[] = new int[vertex.size()];-- Vertices

            // Initialize all vertices as unassigned
            // Assign the first color to first vertex
            vertex.get(0).cor = 0;

            // A temporary array to store the available colors. False
            // value of available[cr] would mean that the color cr is
            // assigned to one of its adjacent vertices

            // Initially, all colors are available

            // Assign colors to remaining V-1 vertices
            for (int i = 1; i < vertex.size(); i++)
            {
                // Process all adjacent vertices and flag their colors
                // as unavailable
                Iterator<ArestaCV> it = edge.iterator();
                while (it.hasNext())
                {
                    ArestaCV ar = it.next();
                    for (Vertice v:vertex)
                    	if (vertex.get(i) != v)
                        	if (isAdjacent(vertex.get(i),v,ar))
                            	v.available = false;
                }

                // Find the first available color
                int cr;
                for (cr = 0; cr < vertex.size(); cr++){
                    if (vertex.get(cr).available)
                        break;
                }
                vertex.get(i).cor = cr;
                for(Vertice v:vertex)
                    v.available = true;

                // Reset the values back to true for the next iteration
            }

            // print the result
            for (int u = 0; u < vertex.size(); u++){
                System.out.println("Vertex " + vertex.get(u).materia + " --->  Color "
                        + vertex.get(u).cor);
        }
    }
}
//====================================================================================================================//