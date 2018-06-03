package Modelagem;

public class Aresta {

	public int professor;
	public int turma;
	public String nome_materia;
	public int idHorario = -1;
	
	public Aresta(int professor, int turma, String nome_materia) {
		super();
		this.professor = professor;
		this.turma = turma;
		this.nome_materia = nome_materia;
	}
	
}
