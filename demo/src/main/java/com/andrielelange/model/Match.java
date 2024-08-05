package model;

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
            team.addBlot();
        }
    
        public void registrarPlif(Team team) {
            team.addPlif();
        }
    
        public void registrarAdvrungh(Team team) {
            team.addAdvrungh();
        }
    
        public void encerrarPartida() {
            this.finalizada = true;
        }
    
        public Team getVencedor() {
            if (teamA.getPontos() > teamB.getPontos()) {
                return teamA;
            } else if (teamB.getPontos() > teamA.getPontos()) {
                return teamB;
            } else {
                return null; // Empate
            }
        }
    }
    