package com.andrielelange.ballitgame.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.andrielelange.ballitgame.controller.ChampionshipController;
import com.andrielelange.ballitgame.model.Match;

public class UserInterface {
    private ChampionshipController controller;
    private Scanner scanner;

    public UserInterface() {
        this.controller = new ChampionshipController();
        this.scanner = new Scanner(System.in);
    }


    // primeira tela do sistema
    public void telaInicial(){
        System.out.println("\nBem vindo ao BallitGame! \n\n");
        System.out.println("O que deseja fazer? \n\n");
        System.out.println("1. Cadastrar times");
        System.out.println("2. Iniciar campeonato com times pré-cadastrados");
        System.out.println("0. Sair");
        int escolha = scanner.nextInt();
        scanner.nextLine();  // Consumir nova linha
        switch (escolha) {
            case 1:
                cadastrarTime();
                break;
            case 2:
                try {
                    leitorTXT();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 0:
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida!");
                telaInicial();
        }
    }

    // a tela genérica que aparece quase sempre (e a mais importante ao meu ver)
    public void iniciar() {
        while (true) {
            System.out.println("1. Cadastrar Time");
            System.out.println("2. Iniciar Campeonato");
            System.out.println("3. Gerenciar Partida");
            System.out.println("4. Exibir Resultados Finais");
            System.out.println("5. Voltar para tela inicial");
            System.out.println("6. Estatisticas do campeonato");
            System.out.println("0. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();  // Consumir nova linha
            switch (escolha) {
                case 1:
                    cadastrarTime();
                    break;
                case 2:
                    iniciarCampeonato();
                    break;
                case 3:
                    gerenciarPartida();
                    break;
                case 4:
                    exibirResultadosFinais();
                    break;
                case 5:
                    telaInicial();
                    break;
                case 6:
                    controller.getEstatisticas();
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    iniciar();
            }
        }
    }

 

    // opção 1 do menu do método iniciar
    private void cadastrarTime() {
        System.out.print("Nome do time: ");
        String nome = scanner.nextLine();
        System.out.print("Grito de guerra: ");
        String gritoDeGuerra = scanner.nextLine();
        System.out.print("Ano de fundação: ");
        int anoFundacao = scanner.nextInt();
        scanner.nextLine();  // Consumir nova linha

        try {
            controller.cadastrarTime(nome, gritoDeGuerra, anoFundacao);
            System.out.println("Time cadastrado com sucesso!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        iniciar();
    }

    // opção 2 do menu do método iniciar
    private void iniciarCampeonato() {
        try {
        
            controller.iniciarCampeonato();
            System.out.println("Campeonato iniciado com sucesso!");
            gerenciarPartida();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        iniciar();
    }

    // opção 3 do menu do método iniciar e a lógica do gerenciamento de partidas
    private void gerenciarPartida() {
        if(controller.campeonatoFinalizado()){
            System.out.println("Campeonato finalizado, você não pode gerenciar partidas \n\n" );
            iniciar();
        }
        List<Match> matches = controller.getMatches();
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            if (!match.isFinalizada()) {
                System.out.println("Partida " + (i + 1) + ": " + match.getTeamA().getNome() + " vs " + match.getTeamB().getNome());
            }
        }

        System.out.print("Escolha a partida para gerenciar: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();  // Consumir nova linha

        if(matches.get(escolha - 1).isFinalizada()){
            System.out.println("Partida já finalizada!");
            gerenciarPartida();
        }
        Match match = matches.get(escolha - 1);
        while (!match.isFinalizada()) {
            System.out.println(match.getTeamA().getNome() + " vs " + match.getTeamB().getNome());
            System.out.println(match.getTeamA().getNome() + " Pontos: " + match.getTeamA().getPontos());
            System.out.println(match.getTeamB().getNome() + " Pontos: " + match.getTeamB().getPontos());
            System.out.println("1. Registrar Blot para " + match.getTeamA().getNome());
            System.out.println("2. Registrar Blot para " + match.getTeamB().getNome());
            System.out.println("3. Registrar Plif para " + match.getTeamA().getNome());
            System.out.println("4. Registrar Plif para " + match.getTeamB().getNome());
            System.out.println("5. Registrar Advrungh para " + match.getTeamA().getNome());
            System.out.println("6. Registrar Advrungh para " + match.getTeamB().getNome());
            System.out.println("7. Encerrar partida");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir nova linha
            switch (opcao) {
                case 1:
                    match.registrarBlot(match.getTeamA());
                    break;
                case 2:
                    match.registrarBlot(match.getTeamB());
                    break;
                case 3:
                    match.registrarPlif(match.getTeamA());
                    break;
                case 4:
                    match.registrarPlif(match.getTeamB());
                    break;
                case 5:
                    match.registrarAdvrungh(match.getTeamA());
                    break;
                case 6:
                    match.registrarAdvrungh(match.getTeamB());
                    break;
                case 7:
                    match.encerrarPartida();
                    if (match.getTeamB() != null && match.getTeamA().getPontos() == match.getTeamB().getPontos()) {
                        waitForGrusht(match);
                    }
                    if (controller.campeonatoFinalizado()) {
                        System.out.println("Campeão: " + controller.getCampeao().getNome());
                        System.exit(0);
                    }   
                    if (controller.faseFinalizada()) {
                        controller.avancarFase();
                    }
                    gerenciarPartida();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    gerenciarPartida();
            }
        }

        if (controller.campeonatoFinalizado()) {
            System.out.println("Campeão: " + controller.getCampeao().getNome());
        } else {
            controller.avancarFase();
        }
    }


    // ppara caso de empate, fazer com que o programa espere o 1 minuto da torcida para decidir quem ganhou
    private void waitForGrusht(Match match) {
        System.out.println("Empate! Grusht decidirá o vencedor.");
        System.out.println("A torcida de " + match.getTeamA().getNome() + " grita: " + match.getTeamA().getGritoDeGuerra());
        System.out.println("A torcida de " + match.getTeamB().getNome() + " grita: " + match.getTeamB().getGritoDeGuerra());
        
       Timer timer = new Timer();
                timer.schedule(new TimerTask(){
                    @Override
                    public void run() {
                        System.out.println("Grusht decidido!");
                    }
                }, 60000);

                try {
                    Thread.sleep(60100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        match.decideGrusht();
        timer.cancel(); // boa prática cancelar as coisas depois de usar
                
        System.out.println("Vencedor: " + (match.getVencedor().getNome()));
    }

    private void exibirResultadosFinais() {
        controller.exibirResultadosFinais();
    }

    //leitor de arquivo que adiciona os times no campeonato
    public void leitorTXT() throws NumberFormatException, Exception{
        Path path = Paths.get("ballitgame/teste.txt");

        try {
            List<String> linhas = Files.readAllLines(path);
            for (String linha : linhas) {
                String[] partes = linha.split(";");
                controller.cadastrarTime(partes[0], partes[1], Integer.parseInt(partes[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        iniciarCampeonato();

    }
    
}
