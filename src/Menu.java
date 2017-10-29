import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Menu extends JFrame{
    public Menu(){
        //inicializacaoJanela();
        //setLayout(new BorderLayout());

        //add(BorderLayout.CENTER, new MenuPrincipal());
        //setVisible(true);

        //int p = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite o numero de pontos a ser criado: "));
        int p = 20;
        //JOptionPane.showMessageDialog(this, "Numero de pontos = " + p);
        ArrayList<Ponto> pontos = sorteiaPontos(p);

        Collections.sort(pontos);
        if(!forcaBruta(pontos).equals(closestPair(pontos))){
            System.out.println("Erro");
            System.out.println(pontos);
            System.out.println("Força Bruta:\t" + forcaBruta(pontos));
            System.out.println("Closest Pair:\t" + closestPair(pontos )+ "\n");
        }
    }

    public ArrayList<Ponto> sorteiaPontos(int n_Pontos){
        int i = 0;
        ArrayList pontos = new ArrayList<Ponto>();
        Random random = new Random();

        while(i < n_Pontos){
            Ponto p = new Ponto(random.nextInt(201), random.nextInt(201));
            if(!pontos.contains(p)) {
                pontos.add(p);
                i++;
            }
        }
        return pontos;
    }

    public static ArrayList<Ponto> forcaBruta(ArrayList<Ponto> pontos){
        double distancia = 0;
        double menorDistancia = 999999999;
        ArrayList<Ponto> pontosMaisProximos = new ArrayList<Ponto>();
        Collections.sort(pontos);
        for (Ponto pontoA : pontos) {
            for(Ponto pontoB : pontos){
                if(!pontoA.equals(pontoB)) {
                    distancia = calculaDistancia(pontoA, pontoB);
                    if (distancia < menorDistancia) {
                        menorDistancia = distancia;
                        pontosMaisProximos = new ArrayList<Ponto>();
                        pontosMaisProximos.add(pontoA);
                        pontosMaisProximos.add(pontoB);
                    }
                }
            }
        }

        return pontosMaisProximos;
    }

    public static ArrayList<Ponto> closestPair(ArrayList<Ponto> pontos){
        ArrayList<Ponto> pontosMaisProximos = new ArrayList<Ponto>();

        if(pontos.size() > 2){
            ArrayList<Ponto> pontosEsquerda = new ArrayList<Ponto>();
            ArrayList<Ponto> pontosDireita = new ArrayList<Ponto>();

            //Collections.sort(pontos);

            int limite;

            limite = pontos.size() / 2;

            //if(pontos.size() % 2 != 0)
             //   limite;
            int i = 0;
            while(i <= limite){
                pontosEsquerda.add(pontos.get(i));
                i++;
            }
            pontosDireita.add(pontos.get(limite));
            while (i < pontos.size()){
                pontosDireita.add(pontos.get(i));
                i++;
            }
            ArrayList<Ponto> Esquerda = closestPair(pontosEsquerda);
            ArrayList<Ponto> Direita = closestPair(pontosDireita);
            //System.out.println(Esquerda);
            //System.out.println(Direita);
            if(calculaDistancia(Esquerda) < calculaDistancia(Direita))
                pontosMaisProximos = Esquerda;
            else
                pontosMaisProximos = Direita;

            double menorDistancia = calculaDistancia(pontosMaisProximos);
            ArrayList<Ponto> dentroDoTunel = new ArrayList<Ponto>();

            i = 0;
            while(i < pontosEsquerda.size()){
                if((pontos.get(limite).getX() - menorDistancia <= pontosEsquerda.get(i).getX()) &&
                        pontosEsquerda.get(i) != pontos.get(limite)){
                    dentroDoTunel.add(pontosEsquerda.get(i));
                }
                i++;
            }

            i = 0;
            while (i < pontosDireita.size()){
                if((pontos.get(limite).getX() + menorDistancia >= pontosDireita.get(i).getX()) &&
                        pontosDireita.get(i) != pontos.get(limite))
                    dentroDoTunel.add(pontosDireita.get(i));
                i++;
            }

            dentroDoTunel.add(pontos.get(limite));
            ArrayList<Ponto> pontosMaisProximosDentroDoTunel = new ArrayList<Ponto>();
            pontosMaisProximosDentroDoTunel = forcaBruta(dentroDoTunel);
            if(dentroDoTunel.size() > 1 ) {
                if (calculaDistancia(pontosMaisProximosDentroDoTunel) < calculaDistancia(pontosMaisProximos))
                    return pontosMaisProximosDentroDoTunel;
                else
                    return pontosMaisProximos;
            }
            else
                return pontosMaisProximos;
        }
        else{
            return pontos;
        }
    }

    public static double calculaDistancia(ArrayList<Ponto> pontos){
        return Math.sqrt(Math.pow(pontos.get(0).getX() - pontos.get(1).getX(), 2) +
                Math.pow(pontos.get(0).getY() - pontos.get(1).getY(), 2));
    }

    public static double calculaDistancia(Ponto pontoA, Ponto pontoB){
        return Math.sqrt(Math.pow(pontoB.getX() - pontoA.getX(), 2) +
                Math.pow(pontoB.getY() - pontoA.getY(), 2));
    }

    public void inicializacaoJanela(){
        setTitle("Trabalho de PAA 2017/2 - Eduardo Noronha e Lucas Bataglia");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}