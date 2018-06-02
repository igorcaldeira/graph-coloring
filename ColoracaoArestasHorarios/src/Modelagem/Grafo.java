package Modelagem;

import java.util.ArrayList;

public class Grafo {

	public void defineCA(ArrayList<String> values) {

        ArrayList<Professores> professores = new ArrayList<>();
        ArrayList<Turmas> turmas = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
			System.out.println(values.get(i));
		}
        
        Turmas turma1 = new Turmas(0);
        Turmas turma2 = new Turmas(1);
        Turmas turma3 = new Turmas(2);
        Turmas turma4 = new Turmas(3);

        turmas.add(turma1);
        turmas.add(turma2);
        turmas.add(turma3);
        turmas.add(turma4);

        Professores prof1 = new Professores(0);
        Professores prof2 = new Professores(1);
        Professores prof3 = new Professores(2);

        professores.add(prof1);
        professores.add(prof2);
        professores.add(prof3);

        this.defineArestas(turmas, professores);
	}
	
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

    private Vertice SetSaturationMax(ArrayList<Vertice> v, ArrayList<ArestaCV> edge)
    {
        int maior = -1;
        ArrayList<Integer> sat = new ArrayList<>();
        Vertice ve = v.get(0);
        for (ArestaCV edi:edge)
        {
            for (int i=0;i < v.size();i++) {
                for (int j=0;j < v.size();j++) {
                    if ((edi.saida == v.get(i) && edi.destino == v.get(j)) || (edi.saida == v.get(j) && edi.destino == v.get(i))) {
                        if (v.get(i).cor != v.get(j).cor) {
                            sat.add(v.get(i).cor);
                        }
                    }
                }
				v.get(i).saturation = sat.size();
				if (sat.size() == maior){
					ve = GetVMG(v);
				}
				else {
					if (sat.size() > maior) {
						maior = sat.size();
						ve = v.get(i);
					}
				}
            }
        }
        return ve;
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
	    ApplyCV(vertex,edge);
    }

    private Vertice GetVMG (ArrayList<Vertice> vertex)
	{
		int maior = -1;
		Vertice ve = null;
		for (int i = 0;i < vertex.size();i++) {
			if (vertex.get(i).grau > maior) {
				maior = vertex.get(i).grau;
				ve = vertex.get(i);
			}
		}
		return ve;
	}

	private void ApplyCV (ArrayList<Vertice> vertex,ArrayList<ArestaCV> edge)
    {
		Vertice ve;
		int corat = 0;
        ArrayList<Vertice> veaux = new ArrayList<>();
		ArrayList<Vertice> veaux2 = new ArrayList<>();
		veaux2.addAll(vertex);

        for (int i = 0;i < vertex.size();i++) {
            ve = GetVMG(veaux2);
            veaux2.remove(ve);
            veaux.add(ve);
        }
        for (int i = 0;i < veaux.size();i++)
        {
            for (int k = 0;k < veaux.size();k++)
            {
                ve = SetSaturationMax(veaux,edge);
                for (int j = 0;j < veaux.size();j++){
                    if (veaux.get(j).equals(ve)){
                        corat++;
                        veaux.get(i).cor = corat;
                    }
                }
            }
        }
    }
}
//====================================================================================================================//