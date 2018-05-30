package Modelagem;

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
}
