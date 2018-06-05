package ModelagemCA;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GrafoCA {

	public void defineCA(ArrayList<String> values) {

		ArrayList<ArestaCA> listaAresta = new ArrayList<>();
		ArrayList<Professores> professores = new ArrayList<>();
		ArrayList<Turmas> turmas = new ArrayList<>();

		for (int i = 0; i < values.size(); i++) {
			String[] parts = values.get(i).split(";");

			String nome_professor = parts[1].trim();
			String nome_materia = parts[0].trim();
			String numero_periodo = parts[2].trim();

			/*tenta adicionar professor*/

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

			ArestaCA a = new ArestaCA(profIdJaCadastrado, turmIdJaCadastrado, nome_materia);
			listaAresta.add(a);
		}


		this.defineArestas(listaAresta, professores, turmas);
	}

	/* define as arestas e as cores da mesma */

	public void defineArestas(ArrayList<ArestaCA> listaAresta,  ArrayList<Professores> professores, ArrayList<Turmas>  turmas) {

		for (int i = 0; i < listaAresta.size(); i++) {

			ArestaCA a = listaAresta.get(i);

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

		/* cria uma pr� lista com v�rios hor�rios disponiveis na grade do curso*/
		ArrayList<String> disponibilidades = new ArrayList<>();

		String[] dias = {"Segunda", "Ter�a", "Quarta", "Quinta", "Sexta"};
		String[] horarios = {"Hor�rio de 07:00", "Hor�rio de 08:50", "Hor�rio de 10:40"};

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

			ArestaCA a = listaAresta.get(i);

			Turmas t = turmas.get(a.turma);
			Professores p = professores.get(a.professor);

			System.out.println(disponibilidades.get(listaAresta.get(i).idHorario)+" - "+a.nome_materia + " / Per�odo: " + t.numero_turma+"  ("+t.id+" - "+p.id+") ");

			System.out.println(" ");
		}
	}

}