package controller;
import model.Championship;
import model.Match;
import model.Team;
import java.util.List;


public class ChampionshipController {
    private Championship championship;

    public ChampionshipController() {
        this.championship = new Championship();
    }

    public void cadastrarTime(String nome, String gritoDeGuerra, int anoFundacao) throws Exception {
        Team team = new Team(nome, gritoDeGuerra, anoFundacao);
        championship.addTeam(team);
    }

    public void iniciarCampeonato() throws Exception {
        championship.iniciarCampeonato();
    }

    public List<Match> getMatches() {
        return championship.getMatches();
    }

    public void avancarFase() {
        championship.avancarFase();
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
