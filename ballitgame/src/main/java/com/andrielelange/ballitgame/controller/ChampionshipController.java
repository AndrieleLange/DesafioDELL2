package com.andrielelange.ballitgame.controller;

import com.andrielelange.ballitgame.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChampionshipController {
    // resgistrar os times por fases do campeonato para a feature extra
    private Map<Integer, List<Team>> championshipControl;
    private Championship championship;

    public ChampionshipController() {
        this.championship = new Championship();
        this.championshipControl = new HashMap<>();
    }

    public void cadastrarTime(String nome, String gritoDeGuerra, int anoFundacao) throws Exception {
        Team team = new Team(nome, gritoDeGuerra, anoFundacao);
        championship.addTeam(team);
    }

    public void iniciarCampeonato() throws Exception {
        championship.iniciarCampeonato();
        championshipControl.put(championship.getFase(), championship.getTeams());
    }

    public List<Match> getMatches() {
        return championship.getMatches();
    }

    public void getEstatisticas() {
        championship.estatisticas();
    }


    public void avancarFase() {
        championship.avancarFase();
        championshipControl.put(championship.getFase(), championship.getTeams());
    }

    public boolean faseFinalizada() {
        return championship.faseFinalizada();
    }

    public boolean campeonatoFinalizado() {
        return championship.campeonatoFinalizado();
    }

    public Team getCampeao() {
        return championship.getCampeao();
    }


    public void toStringControl(){
        for(Map.Entry<Integer, List<Team>> entry : championshipControl.entrySet()) {
            System.out.println("Fase " + entry.getKey() );
            for (Team team : entry.getValue()) {
                System.out.println(team.getNome());
            }
            System.out.println("\n");
        }
    }

    public void exibirResultadosFinais() {
        championship.exibirResultadosFinais();
    }
}
