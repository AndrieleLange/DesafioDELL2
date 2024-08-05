package com.andrielelange.ballitgame.model;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
    public class Match {
        private Team teamA;
        private Team teamB;
        private boolean finalizada;
    
        public Match(Team teamA, Team teamB) {
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

        public Team grusht(){
            Random random = new Random();
            int sorteio = random.nextInt(2);
            if(sorteio == 0){
                teamA.addGrusht();
                return teamA;
            } else {
                teamB.addGrusht();
                return teamB;
            } 

        }
    
        public Team getVencedor() {
            if (teamA.getPontos() > teamB.getPontos()) {
                return teamA;
            } else if (teamB.getPontos() > teamA.getPontos()) {
                return teamB;
            } else {
                System.out.println("Empate! Grusht decidirá o vencedor.");
                Timer timer = new Timer();
                timer.schedule(new TimerTask(){
                    @Override
                    public void run() {
                        System.out.println("Grusht decidindo...");
                    }
                }, 1000);

                try {
                    Thread.sleep(1100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return grusht();
            }
        }
    }
    