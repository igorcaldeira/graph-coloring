package ModelagemCA;

import java.util.ArrayList;

public class Professores {


	public int id;
	public String nome;
	public ArrayList<Integer> cores = new ArrayList<>();

	public Professores(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
}
