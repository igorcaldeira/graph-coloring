package Modelagem;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
    void getGrau(ArrayList<Vertice> v,ArrayList<ArestaCV> a) {
	    for (int i = 0;i < a.size();i++)
        {
            for (int j = 0;j < a.size();j++)
            {
                if (a.get(i).saida == v.get(j) || a.get(i).destino == v.get(j)){
                    v.get(j).grau++;
                }
            }
        }
    }
    public ArrayList<ArestaCV> AddArestaCV (ArrayList<Vertice> ver)
    {
        ArrayList<ArestaCV> a = new ArrayList<>();
        for (int i = 0;i < ver.size(); i++)
        {
            for (int j = 0;j < ver.size(); j++)
            {
                if (i != j)
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
	    for (int i = 0; i < dados.size(); i++) {
	        m = dados.get(i).split(";");
	        Vertice vaux = new Vertice(m[0], m[1], m[2]);
	        vertex.add(vaux);
	    }
	    edge = AddArestaCV(vertex);
	    getGrau(vertex,edge);
    }

 /*   public void ApplyCV (Grafo graph)
    {
        Grafo sub = new Grafo();
        sub = graph;
    }*/
}
//====================================================================================================================//