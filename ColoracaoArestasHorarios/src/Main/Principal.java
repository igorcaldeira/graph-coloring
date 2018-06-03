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

        ArrayList<String> values = new ArrayList<>();
        ArrayList<Professores> professores = new ArrayList<>();
        ArrayList<Turmas> turmas = new ArrayList<>();
        values  = LerArquivo();
        Grafo grafo = new Grafo();

        /**/
        /* metodologia para coloracao de vertices */
        /**/
        
//        grafo.AddVerticesCV(values);

        /**/
        /* metodologia para coloracao de arestas*/
        /**/
        grafo.defineCA(values);
    }

    static ArrayList LerArquivo() {
        String path,st;
        ArrayList<String> values = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o caminho do arquivo:");

        path = sc.nextLine();
        path = "";
        if(path.equals("")) {
        	path = "ColoracaoArestasHorarios/Teste.txt";
        }
       
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

