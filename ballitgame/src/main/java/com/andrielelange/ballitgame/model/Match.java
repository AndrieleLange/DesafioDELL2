package com.andrielelange.ballitgame.model;

import java.util.Random;

    public class Match {
        // informações sobre os times que estão jogando
        private Team teamA;
        private Team teamB;
        private boolean finalizada;

    
        public Match(Team teamA, Team teamB) {
            // validações
            if(teamA == null && teamB == null){
                throw new NullPointerException("Os times não podem ser nulos.");
            }
            if(teamA.getNome().equals(teamB.getNome())){
                throw new IllegalArgumentException("Os times não podem ser iguais.");
            }
            if(teamA != null && teamB == null){
                this.teamA = teamA;
                this.finalizada = true;
            }else {
                this.teamA = teamA;
                this.teamB = teamB;
                this.finalizada = false;
            }
            
        }
    
        // getters
        public Team getTeamA() {
            return teamA;
        }
    
        public Team getTeamB() {
            if(teamB == null){
                return null;
            }
            return teamB;
        }
    
        public boolean isFinalizada() {
            return finalizada;
        }
    
        // "setters"
        public void registrarBlot(Team team) {
            if(team.equals(teamA)) {
                teamA.addBlot();
            } else if(team.equals(teamB)) {
                teamB.addBlot();
            }
        }
    
        public void registrarPlif(Team team) {
            if(team.equals(teamA)) {
                teamA.addPlif();
            } else if(team.equals(teamB)) {
                teamB.addPlif();
            }
        }
    
        public void registrarAdvrungh(Team team) {
            if(team.equals(teamA)) {
                teamA.addAdvrungh();
            } else if(team.equals(teamB)) {
                teamB.addAdvrungh();
            }
        }
    
        // se a partida estiver encerrada, não pode mais administrar essa partida
        public void encerrarPartida() {
            this.finalizada = true;
        }


        // decisão em caso de empate
        public Team decideGrusht(){
            Random random = new Random();
                        if (random.nextInt(2) == 0) {
                            teamA.addGrusht();
                            return teamA;
                        } else {
                            teamB.addGrusht();
                            return teamB;
                        }
        }

        public void exibirResultados(){
            if (!finalizada) {
                throw new IllegalStateException("A partida ainda não foi finalizada.");
            }
            System.out.println("Resultado da partida: ");
            System.out.println(teamA.getNome() + " " + teamA.getPontos() + " x " + teamB.getPontos() + " " + teamB.getNome());
        }

        public Team getPerdedor(){
            if(teamA.getPontos() < teamB.getPontos() || teamB == null){
                return teamA;
            } else if(teamB.getPontos() < teamA.getPontos()){
                return teamB;
            }
            return null;        
        }
    
        public Team getVencedor() {
            if(teamB == null){
                return teamA;
            }
            if (teamA.getPontos() > teamB.getPontos()) {
                return teamA;
            } else if (teamB.getPontos() > teamA.getPontos()) {
                return teamB;
            } else {
                System.out.println("Empate! Grusht decidirá o vencedor.");
                Team vencedor = decideGrusht();
                return vencedor;
            }
        }
    }
    