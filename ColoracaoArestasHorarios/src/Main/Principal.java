package Main;

import java.io.*;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import Modelagem.*;

import javax.swing.*;

import static java.nio.file.Files.readAllLines;

public class Principal {

    public static void main(String[] args) {

        /* define grafo bipartido */

        /* turmas */

        ArrayList<String> values = new ArrayList<>();
        ArrayList<Professores> professores = new ArrayList<>();
        ArrayList<Turmas> turmas = new ArrayList<>();
        values  = LerArquivo();



        ArrayList<String> matriz = new ArrayList<>();

        Turmas turma1 = new Turmas(0);
        Turmas turma2 = new Turmas(1);
        Turmas turma3 = new Turmas(2);
        Turmas turma4 = new Turmas(3);

        turmas.add(turma1);
        turmas.add(turma2);
        turmas.add(turma3);
        turmas.add(turma4);

        /* professores */


        Professores prof1 = new Professores(0);
        Professores prof2 = new Professores(1);
        Professores prof3 = new Professores(2);

        professores.add(prof1);
        professores.add(prof2);
        professores.add(prof3);

        Grafo grafo = new Grafo();
        grafo.defineArestas(turmas, professores);
    }

    static ArrayList LerArquivo() {
        String path,st;
        ArrayList<String> values = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o caminho do arquivo:");
        path = sc.nextLine();
        File f = new File(path);
        try {
            BufferedReader buff = new BufferedReader(new FileReader(f));
            while((st = buff.readLine()) != null)
            values.add(st);
        }
        catch (IOException e1) {
            System.out.println("ERRO!");
        }
        return values;
    }
}

