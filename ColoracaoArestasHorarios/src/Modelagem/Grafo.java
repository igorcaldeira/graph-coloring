package Modelagem;

import java.util.ArrayList;

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