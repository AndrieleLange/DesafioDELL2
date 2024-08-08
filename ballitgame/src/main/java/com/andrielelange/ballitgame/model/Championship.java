package com.andrielelange.ballitgame.model;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Championship {
    // se for falso, o time foi eliminado
    // se for verdadeiro, o time ainda está no campeonato
    // foi criado para não ter que remover times da lista
    private Map<Team, Boolean> teams;

    private Team campeao;
    private List<Match> matches; // partidas da fase atual
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
        // verificando se já não existe um time com o mesmo nome
        for (Map.Entry<Team, Boolean> entry : teams.entrySet()) {
            if (entry.getKey().getNome().toLowerCase().equals(team.getNome().toLowerCase())) {
                throw new Exception("Time já cadastrado.");
            }
            if(entry.getKey().getGritoDeGuerra().toLowerCase().equals(team.getGritoDeGuerra().toLowerCase())){
                throw new Exception("Grito de guerra já cadastrado.");
            }
        } 
        // se tudo estiver certo, o time é adicionado no map 
        teams.put(team, true);
    }


    public void iniciarCampeonato() throws Exception {
        if (teams.size() < 8 || teams.size() % 2 != 0) {
            throw new Exception("O número de times deve ser par e entre 8 e 16.\n Número de times cadastrados: " + teams.size() + "\n\n"); 
        }
        sortearTimes();
    }

    private void sortearTimes() {
        // lista para sortear os times e com apenas os times que poderão jogar
        List<Team> teams = new ArrayList<>();
        // iterando sobre o map para utilizar apenas os times que podem jogar (igual a true)
        for (Map.Entry<Team, Boolean> entry : this.teams.entrySet()) {
            if (entry.getValue()) {
                teams.add(entry.getKey());
            }
        }
        // fazendo um random na lista para sortear os times antes de criar as partidas
        Collections.shuffle(teams, new Random());
        //limpando a lista de partidas (caso tenha ocorrido uma anterior a esta)
        matches.clear();
        for (int i = 0; i < teams.size(); i += 2) {
            if(teams.size() % 2 != 0){
                if (i == teams.size() - 1){
                    matches.add(new Match(teams.get(i), null)); // a tentativa de continuar o campeonato com times impares
                }
            }
            matches.add(new Match(teams.get(i), teams.get(i + 1)));
        }  
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        for (Map.Entry<Team, Boolean> entry : this.teams.entrySet()) {
            if (entry.getValue()) {
                teams.add(entry.getKey());
            }
        }
        return teams;
    }

    // mudar o estado do time para falso (eliminado)
    public void registrarPerdedores(){
        for (Match match : matches) {
            if(match.isFinalizada()){
                Team perdedor = match.getPerdedor();
                teams.put(perdedor, false);
            }
        }
    }

    public void avancarFase() {
        registrarPerdedores();
        if (campeonatoFinalizado()) {
            System.out.println("Campeonato finalizado! Campeão: " + campeao.getNome());
        } else {
            sortearTimes();
            fase++;
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


    // Se houver apenas um time ativo, o campeonato foi finalizado
    public boolean campeonatoFinalizado() {
        int timesAtivos = 0;
        for (Map.Entry<Team, Boolean> entry : teams.entrySet()) {
            if (entry.getValue()) {
                timesAtivos++;
            }
        }
        if(timesAtivos == 1){
            campeao = getCampeao();
        }
        return timesAtivos == 1;
    }

    // para quando o campeonato for finalizado, consiga pegar que foi o campeão
    public Team getCampeao() {
        if(faseFinalizada()) {
            return matches.get(0).getVencedor();
        }
        return null;
    }

    public void exibirResultados() {
        for (Match match : matches) {
            match.exibirResultados();
        }
    }   

    // o que foi pedido na questão 5 do desafio
    public void exibirResultadosFinais() {
        // para conseguir ordenar pelo numero de pontos
        List<Team> teams = new ArrayList<>();
        for (Map.Entry<Team, Boolean> entry : this.teams.entrySet()) {
                teams.add(entry.getKey());
        }

        // ordenado pelo numero de pontos de forma decrescente 
        Collections.sort(teams, (team1, team2) -> team2.getPontos() - team1.getPontos());

        System.out.println("Resultados Finais:");
        for (Team team : teams) {
            System.out.println("Time: " + team.getNome() + 
                               ", Blots: " + team.getBlots() + 
                               ", Plifs: " + team.getPlifs() + 
                               ", Advrunghs: " + team.getAdvrunghs() + 
                               ", Pontos: " + team.getPontos());
        }
        System.out.println("Campeão: " + campeao.getNome());
        System.out.println("Grito de Guerra do Campeão: " + campeao.getGritoDeGuerra());
        System.out.println("");
    }
}

