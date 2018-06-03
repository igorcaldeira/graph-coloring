package Modelagem;

import java.util.ArrayList;

public class Turmas {

	public int id;
	public String numero_turma;
	public ArrayList<Integer> cores = new ArrayList<>();

	public Turmas(int id, String numero_turma) {
		super();
		this.id = id;
		this.numero_turma = numero_turma;
	}
	
}
