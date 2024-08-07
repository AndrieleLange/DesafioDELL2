package com.andrielelange.ballitgame.model;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
    public class Match {
        private Team teamA;
        private Team teamB;
        private boolean finalizada;
        private boolean grushtDecidido = false;
        private final Object lock = new Object();
    
        public Match(Team teamA, Team teamB) {
            if(teamA == null && teamB == null){
                throw new NullPointerException("Os times não podem ser nulos.");
            }
            if(teamA.getNome().equals(teamB.getNome())){
                throw new IllegalArgumentException("Os times não podem ser iguais.");
            }
            if(teamA == null ^ teamB == null){
                this.teamA = teamA;
                this.teamB = teamB;
                this.finalizada = true;
            }
            this.teamA = teamA;
            this.teamB = teamB;
            this.finalizada = false;
        }
    
        public Team getTeamA() {
            return teamA;
        }
    
        public Team getTeamB() {
            return teamB;
        }
    
        public boolean isFinalizada() {
            return finalizada;
        }
    
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
    
        public void encerrarPartida() {
            this.finalizada = true;
        }


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


        // errado
        public Team getPerdedor(){
            if(teamA.getPontos() < teamB.getPontos() || teamB == null){
                return teamA;
            } else if(teamB.getPontos() < teamA.getPontos()){
                return teamB;
            }
            return null;        
        }
    
        public Team getVencedor() {
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
    