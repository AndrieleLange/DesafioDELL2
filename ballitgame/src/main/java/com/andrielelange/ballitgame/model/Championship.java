package com.andrielelange.ballitgame.model;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Championship {
    private Map<Team, Boolean> teams;
    //private List<Team> teams;
    private List<Match> matches;
    private int fase;

    public Championship() {
        this.teams = new HashMap<>();
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
        for (Map.Entry<Team, Boolean> entry : teams.entrySet()) {
            if (entry.getKey().getNome().toLowerCase().equals(team.getNome().toLowerCase())) {
                throw new Exception("Time já cadastrado.");
            }
        } 
        teams.put(team, true);
    }

    public void iniciarCampeonato() throws Exception {
        if (teams.size() < 8 || teams.size() % 2 != 0) {
            throw new Exception("O número de times deve ser par e entre 8 e 16.");
        }
        sortearTimes();
    }

    private void sortearTimes() {
        List<Team> teams = new ArrayList<>();
        for (Map.Entry<Team, Boolean> entry : this.teams.entrySet()) {
            if (entry.getValue()) {
                teams.add(entry.getKey());
            }
        }
        Collections.shuffle(teams, new Random());
        matches.clear();
        for (int i = 0; i < teams.size(); i += 2) {
            matches.add(new Match(teams.get(i), teams.get(i + 1)));
        }
    }

    public List<Match> getMatches() {
        return matches;
    }

    public Map<Team,Boolean> getTeams() {
        return teams;
    }

    public void registrarPerdedores(){
        for (Match match : matches) {
            if(match.isFinalizada()){
                Team perdedor = match.getPerdedor();
                teams.put(perdedor, false);
            }
        }
    }

    public void avancarFase() {
        if(!teams.isEmpty()){
        registrarPerdedores();
        fase++;
        sortearTimes();
    }
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

    public Team getPerdedor(){
        if(faseFinalizada()){
            return matches.get(0).getPerdedor();
        }
        return null;

    }

    public Team getCampeao() {
        if(faseFinalizada()) {
            return matches.get(0).getVencedor();
        }
        return null;
    }




    public void exibirResultadosFinais() {
        List<Team> teams = new ArrayList<>();
        for (Map.Entry<Team, Boolean> entry : this.teams.entrySet()) {
                teams.add(entry.getKey());
        }
        teams.sort((team1, team2) -> team2.getPontos() - team1.getPontos());
        System.out.println("Resultados Finais:");
        for (Team team : teams) {
            System.out.println("Time: " + team.getNome() + 
                               ", Blots: " + team.getBlots() + 
                               ", Plifs: " + team.getPlifs() + 
                               ", Advrunghs: " + team.getAdvrunghs() + 
                               ", Pontos: " + team.getPontos());
        }
        System.out.println("Campeão: " + getCampeao().getNome());
        System.out.println("Grito de Guerra do Campeão: " + getCampeao().getGritoDeGuerra());
    }
}

