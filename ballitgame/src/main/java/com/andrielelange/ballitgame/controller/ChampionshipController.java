package com.andrielelange.ballitgame.controller;

import com.andrielelange.ballitgame.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChampionshipController {
    private Map<Integer, Championship> championshipControl;
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
        championshipControl.put(championship.getFase(), championship);
    }

    public List<Match> getMatches() {
        return championship.getMatches();
    }


    public void avancarFase() {
        championship.avancarFase();
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

    public void exibirResultadosFinais() {
        championship.exibirResultadosFinais();
    }
}
