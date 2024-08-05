package view;

import java.util.List;
import java.util.Scanner;
import controller.ChampionshipController;
import model.Match;
import model.Team;
import model.Championship;



public class UserInterface {
    private ChampionshipController controller;
    private Scanner scanner;

    public UserInterface() {
        this.controller = new ChampionshipController();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("1. Cadastrar Time");
            System.out.println("2. Iniciar Campeonato");
            System.out.println("3. Gerenciar Partida");
            System.out.println("4. Exibir Resultados Finais");
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
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
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
            System.out.println("Time cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void iniciarCampeonato() {
        try {
            controller.iniciarCampeonato();
            System.out.println("Campeonato iniciado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void gerenciarPartida() {
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
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        if (controller.campeonatoFinalizado()) {
            System.out.println("Campeão: " + controller.getCampeao().getNome());
        } else {
            controller.avancarFase();
        }
    }

    private void exibirResultadosFinais() {
        controller.exibirResultadosFinais();
    }
}
