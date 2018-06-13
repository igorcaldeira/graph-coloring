package ModelagemCA;



import ModelagemCV.Vertice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GrafoCA {

	/* recebe os valores do arquivos*/
	public void defineCA(ArrayList<String> values) {

		/* lista cada valor do arquivo como uma aresta, e define uma lista de turmas e professores*/
		ArrayList<ArestaCA> listaAresta = new ArrayList<>();
		ArrayList<Professores> professores = new ArrayList<>();
		ArrayList<Turmas> turmas = new ArrayList<>();

		for (int i = 0; i < values.size(); i++) {
			String[] parts = values.get(i).split(";");

			// interpreta o arquivo e suas informações
			String nome_professor = parts[1].trim();
			String nome_materia = parts[0].trim();
			String numero_periodo = parts[2].trim();

			/*tenta adicionar professor com o id (posição na lista) e com o nome do mesmo*/

			Integer id_prof = professores.size() + 0;
			Professores prof = new Professores(id_prof,nome_professor);
			boolean temProfessor = false;
			int profIdJaCadastrado = id_prof;
			for (int j = 0; j < professores.size(); j++) {
				if(professores.get(j).nome.equals(nome_professor)) {
					temProfessor = true;
					profIdJaCadastrado = professores.get(j).id;
				}
			}
			//não cadastra valores repetidos
			if(!temProfessor) {
				professores.add(prof);
			}

			/*tenta adicionar turma com o id (posição na lista) e com o número do mesmo*/

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
			//não cadastra valores repetidos
			if(!temTurma) {
				turmas.add(turm);
			}

			/*adiciona aresta com os dados levantados*/
			ArestaCA a = new ArestaCA(profIdJaCadastrado, turmIdJaCadastrado, nome_materia);
			listaAresta.add(a);
		}


		this.defineArestas(listaAresta, professores, turmas);
	}

	/* define as arestas e as cores da mesma */

	public void defineArestas(ArrayList<ArestaCA> listaAresta,  ArrayList<Professores> professores, ArrayList<Turmas>  turmas) {

		// percorre cada aresta
		for (int i = 0; i < listaAresta.size(); i++) {

			ArestaCA a = listaAresta.get(i);

			// resgata a turma e o professor daquela aresta, pois os mesmos guardam as cores disponíveis
			Turmas t = turmas.get(a.turma);
			Professores p = professores.get(a.professor);

			/* se os dois vertices nao tiveram arestas coloridas ainda é definida a primeira cor para eles*/
			if(t.cores.isEmpty() && p.cores.isEmpty()) {
				t.cores.add(0);
				p.cores.add(0);
				listaAresta.get(i).idHorario = 0;
			}else{

				// tenta atribuir cada cor em ordem
				for (int j = 0; j <= 5; j++) {

					int corAnalisada = j;

					//flags de disponibilidade
					boolean disponivelT = false;
					boolean disponivelP = false;

					//analisa as cores disponiveis para turma
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

					//analisa as cores disponiveis para o professor
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

					// se a cor estiver disponível em ambos lados, é adicionada a aresta, pois representa o horário
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

		/* cria uma prï¿½ lista com vï¿½rios horï¿½rios disponiveis na grade do curso*/
		ArrayList<String> disponibilidades = new ArrayList<>();

		String[] dias = {"Segunda", "Terï¿½a", "Quarta", "Quinta", "Sexta"};
		String[] horarios = {"Horï¿½rio de 07:00", "Horï¿½rio de 08:50", "Horï¿½rio de 10:40"};

		String dia_string = "";
		String horario_string = "";

		// cria vários horários disponíveis
		for(int dia = 0; dia < 4; dia++) {
			dia_string = dias[dia];
			for(int horario = 0; horario < 3; horario++) {
				horario_string = horarios[horario];
				disponibilidades.add(dia_string +"-Feira / "+horario_string);
			}
		}

		System.out.println(" ");
		//para cada aesta atribui uma disponibilidade e relaciona o nome da materia, professor e turma
		//esta é a ultima saída do programa
		for (int i = 0; i < listaAresta.size(); i++) {

			ArestaCA a = listaAresta.get(i);

			Turmas t = turmas.get(a.turma);
			Professores p = professores.get(a.professor);

			System.out.println(disponibilidades.get(listaAresta.get(i).idHorario)+" - "+a.nome_materia + " / Perï¿½odo: " + t.numero_turma+"  ("+t.id+" - "+p.id+") ");

			System.out.println(" ");
		}
	}
}