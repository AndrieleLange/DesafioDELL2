package com.andrielelange;

import java.util.concurrent.Flow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualizacaoS extends JFrame  implements ActionListener {

    public VisualizacaoS() { //vicualização inicial 
        setTitle("BALLIT CHAMPIONSHIP");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenu menuTime = new JMenu("Info");
        JMenuItem item1 = new JMenuItem("Sobre");
        JMenuItem item2 = new JMenuItem("Ajuda");

        menuTime.add(item1);
        menuTime.add(item2);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuTime);

        setJMenuBar(menuBar);

        item1.addActionListener(this);
        item2.addActionListener(this);

        
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(new Color(255, 245, 227)); // Um tom de azul claro


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton iniciar = new JButton("Iniciar");
        JButton cadastrarTime = new JButton("Cadastrar Time");

        iniciar.addActionListener(null);
        cadastrarTime.addActionListener(null);

        buttonPanel.add(iniciar);
        buttonPanel.add(cadastrarTime);

        container.add(buttonPanel, BorderLayout.SOUTH);

        ImageIcon icon = new ImageIcon(getClass().getResource("Ballit Championship.png"));
        JLabel label = new JLabel(icon);
        container.add(label, BorderLayout.CENTER);
    

        setVisible(true);
    }

        public void menu() {

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(new Color(255, 245, 227)); // Um tom de azul claro


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton iniciar = new JButton("Iniciar");
        JButton cadastrarTime = new JButton("Cadastrar Time");

        iniciar.addActionListener(this);
        cadastrarTime.addActionListener(this);

        buttonPanel.add(iniciar);
        buttonPanel.add(cadastrarTime);

        container.add(buttonPanel, BorderLayout.SOUTH);

        ImageIcon icon = new ImageIcon(getClass().getResource("Ballit Championship.png"));
        JLabel label = new JLabel(icon);
        container.add(label, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public void telaCadastro()  {
        getContentPane().removeAll();
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(new Color(255, 245, 227)); // Um tom de azul claro

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel nome = new JLabel("Nome do Time");
        JTextField nomeField = new JTextField();

        JLabel grito = new JLabel("Grito de Guerra");
        JTextField gritoField = new JTextField();

        JLabel ano = new JLabel("Ano de Fundação");
        JTextField anoField = new JTextField();

        JButton cadastrar = new JButton("Cadastrar");
        JButton cancelar = new JButton("Cancelar");

        panel.add(nome);
        panel.add(nomeField);
        panel.add(grito);
        panel.add(gritoField);
        panel.add(ano);
        panel.add(anoField);

        container.add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        buttonPanel.add(cadastrar);
        buttonPanel.add(cancelar);

        container.add(buttonPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    public void telaSobre() {
        getContentPane().removeAll();
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(new Color(255, 245, 227)); // Um tom de azul claro

        JLabel sobre = new JLabel("Ballit Championship é um campeonato de Blot, Plif e Advrungh.");
        JLabel sobre2 = new JLabel("Cada time tem 50 pontos e pode ganhar ou perder pontos durante as partidas.");
        JLabel sobre3 = new JLabel("Blot: +5 pontos, Plif: +1 ponto, Advrungh: -10 pontos.");
        JLabel sobre4 = new JLabel("O time que tiver mais pontos no final da partida vence.");

        container.add(sobre, BorderLayout.NORTH);
        container.add(sobre2, BorderLayout.NORTH);
        container.add(sobre3, BorderLayout.NORTH);
        container.add(sobre4, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    public void telaAjuda() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(new Color(255, 245, 227)); // Um tom de azul claro

        JLabel ajuda = new JLabel("Para iniciar o campeonato, clique em Iniciar.");
        JLabel ajuda2 = new JLabel("Para cadastrar um time, clique em Cadastrar Time.");
        JLabel ajuda3 = new JLabel("Para saber mais sobre o campeonato, clique em Info e depois em Sobre.");
        JLabel ajuda4 = new JLabel("Para obter ajuda, clique em Info e depois em Ajuda.");

        container.add(ajuda, BorderLayout.NORTH);
        container.add(ajuda2, BorderLayout.CENTER);
        container.add(ajuda3, BorderLayout.CENTER);
        container.add(ajuda4, BorderLayout.CENTER);
        revalidate();
        repaint();
    }


    /*
    public void telaCampeonato() {
    getContentPane().removeAll();
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(new Color(255, 245, 227)); // Um tom de azul claro

        JLabel campeonato = new JLabel("Campeonato Ballit Championship");
        JLabel campeonato2 = new JLabel("Fase 1");
        JLabel campeonato3 = new JLabel("Time 1 VS Time 2");
        JLabel campeonato4 = new JLabel("Time 3 VS Time 4");
        JLabel campeonato5 = new JLabel("Time 5 VS Time 6");
        JLabel campeonato6 = new JLabel("Time 7 VS Time 8");

        container.add(campeonato, BorderLayout.NORTH);
        container.add(campeonato2, BorderLayout.CENTER);
        container.add(campeonato3, BorderLayout.CENTER);
        container.add(campeonato4, BorderLayout.CENTER);
        container.add(campeonato5, BorderLayout.CENTER);
        container.add(campeonato6, BorderLayout.CENTER);
    }

    public void telaVencedor() {
    getContentPane().removeAll();
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(new Color(255, 245, 227)); // Um tom de azul claro

        JLabel vencedor = new JLabel("O grande campeão do Ballit Championship é o Time 1!");
        JLabel vencedor2 = new JLabel("Parabéns ao Time 1 pelo excelente desempenho!");

        container.add(vencedor, BorderLayout.CENTER);
        container.add(vencedor2, BorderLayout.CENTER);
    }

    public void telaPartida() {
    getContentPane().removeAll();
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(new Color(255, 245, 227)); // Um tom de azul claro

        JLabel partida = new JLabel("Partida");
        JLabel partida2 = new JLabel("Time 1 VS Time 2");

        container.add(partida, BorderLayout.CENTER);
        container.add(partida2, BorderLayout.CENTER);
    }

    public void telaResultado() {
    getContentPane().removeAll();
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(new Color(255, 245, 227)); // Um tom de azul claro

        JLabel resultado = new JLabel("Resultado");
        JLabel resultado2 = new JLabel("Time 1 50 x 40 Time 2");

        container.add(resultado, BorderLayout.CENTER);
        container.add(resultado2, BorderLayout.CENTER);
    }
*/

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Iniciar")) {
            // telaCampeonato();
        } else if (e.getActionCommand().equals("Cadastrar Time")) {
            telaCadastro();
        } else if (e.getActionCommand().equals("Sobre")) {
            telaSobre();
        } else if (e.getActionCommand().equals("Ajuda")) {
            telaAjuda();
        }
    }





    public static void main(String[] args) {
         new VisualizacaoS();
        }
    
}
