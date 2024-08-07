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
import com.andrielelange.ballitgame.model.Team;
import com.andrielelange.ballitgame.*;



public class UserInterface {
    private ChampionshipController controller;
    private Scanner scanner;
    private Team vencedor;

    public UserInterface() {
        this.controller = new ChampionshipController();
        this.scanner = new Scanner(System.in);
    }

    public void telaInicial(){
        System.out.println("Bem vindo ao BallitGame! \n\n");
        System.out.println("O que deseja fazer? \n\n");
        System.out.println("1. Cadastrar times");
        System.out.println("2. Iniciar campeonato com times pré-cadastrados");
        System.out.println("0. Sair");
        int escolha = scanner.nextInt();
        scanner.nextLine();  // Consumir nova linha
        switch (escolha) {
            case 2:
                try {
                    leitorTXT();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 1:
                cadastrarTime();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida!");
                telaInicial();
        }
    }

    public void iniciar() {
        while (true) {
            System.out.println("1. Cadastrar Time");
            System.out.println("2. Iniciar Campeonato");
            System.out.println("3. Gerenciar Partida");
            System.out.println("4. Exibir Resultados Finais");
            System.out.println("5. Voltar para tela inicial");
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
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    iniciar();
            }
        }
    }

 

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

    private void waitForGrusht(Match match) {
        System.out.println("Empate! Grusht decidirá o vencedor.");
       Timer timer = new Timer();
                timer.schedule(new TimerTask(){
                    @Override
                    public void run() {
                        System.out.println("Grusht decidindo...");
                    }
                }, 60000);

                try {
                    Thread.sleep(60100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                match.decideGrusht();
                timer.cancel();

        System.out.println("Vencedor: " + (match.getTeamA().getPontos() > match.getTeamB().getPontos() ? match.getTeamA().getNome() : match.getTeamB().getNome()));
    }

    private void exibirResultadosFinais() {
        controller.exibirResultadosFinais();
        System.out.println("Campeão: " + controller.getCampeao().getNome());
        System.out.println("Grito de guerra: " + controller.getCampeao().getGritoDeGuerra());
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
