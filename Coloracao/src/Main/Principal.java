package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ModelagemCA.ArestaCA;
import ModelagemCA.Turmas;
import ModelagemCA.GrafoCA;
import ModelagemCA.Professores;
import ModelagemCV.GrafoCV;
import ModelagemCV.Vertice;

public class Principal {

    static int menu(){
        int op;
        do {
            System.out.println("===========================================//=============================================\n" +
                    "Digite a operação desejada:\n" +
                    "1 - Gerar horários por via de coloração de arestas:\n" +
                    "2 - Gerar horários por via de coloração de vértices:\n" +
                    "0 - Terminar e sair do programa:\n" +
                    "==========================================//============================================\n");
            Scanner sc = new Scanner(System.in);
            op = sc.nextInt();
        }while(op < 0 || op > 2 );
        return op;
    }

    public static void main(String[] args) {

        int op;
        ArrayList<String> values;
        values  = LerArquivo();
        do {
            op = menu();
            switch (op) {
                case 1:
                    GrafoCA grafoCA = new GrafoCA();
                    grafoCA.defineCA(values);
                    break;
                case 2:
                    ArrayList<Vertice> verticeCV;
                    GrafoCV grafoCV = new GrafoCV();
                    verticeCV = grafoCV.CriarVertices(values);
                    grafoCV.DefineHorariosCV(verticeCV);
                    break;
            }
        }while(op != 0);
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

