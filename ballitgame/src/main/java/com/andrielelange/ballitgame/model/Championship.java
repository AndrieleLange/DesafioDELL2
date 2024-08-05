package com.andrielelange.ballitgame.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Championship {
    private List<Team> teams;
    private List<Match> matches;
    private int fase;

    public Championship() {
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.fase = 0;
    }

    public int getFase() {
        return fase;
    }

    public void addTeam(Team team) throws Exception {
        if (teams.size() >= 16) {
            throw new Exception("Número máximo de times atingido.");
        }
        teams.add(team);
    }

    public void iniciarCampeonato() throws Exception {
        if (teams.size() < 8 || teams.size() % 2 != 0) {
            throw new Exception("O número de times deve ser par e entre 8 e 16.");
        }
        sortearTimes();
    }

    private void sortearTimes() {
        Collections.shuffle(teams, new Random());
        matches.clear();
        for (int i = 0; i < teams.size(); i += 2) {
            matches.add(new Match(teams.get(i), teams.get(i + 1)));
        }
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void avancarFase() {
        List<Team> vencedores = new ArrayList<>();
        for (Match match : matches) {
            vencedores.add(match.getVencedor());
        }
        teams = vencedores;
        fase++;
        sortearTimes();
    }

    public boolean faseFinalizada() {
        for (Match match : matches) {
            if (!match.isFinalizada()) {
                return false;
            }
        }
        return true;
    }

    public boolean campeonatoFinalizado() {
        return teams.size() == 1;
    }

    public Team getCampeao() {
        if(campeonatoFinalizado()) {
            return teams.get(0);
        }
        return null;
    }

    public void exibirResultadosFinais() {
        System.out.println("Resultados Finais:");
        for (Team team : teams) {
            System.out.println("Time: " + team.getNome() + 
                               ", Blots: " + team.getBlots() + 
                               ", Plifs: " + team.getPlifs() + 
                               ", Advrunghs: " + team.getAdvrunghs() + 
                               ", Pontos: " + team.getPontos());
        }
        System.out.println("Grito de Guerra do Campeão: " + getCampeao().getGritoDeGuerra());
    }
}

