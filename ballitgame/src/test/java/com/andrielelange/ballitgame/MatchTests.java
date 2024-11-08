package com.andrielelange.ballitgame;
import com.andrielelange.ballitgame.model.Match;
import com.andrielelange.ballitgame.model.Team;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.lang.IllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatchTests {
    Team team1 = new Team("Dell", "pelo poder da tecnologia para impulsionar o progresso humano", 1984);
    Team team2 = new Team("Dell Technologies Inc.", "Vai Dell", 2016);

        @Test
    public void testNomeNulo(){
        Exception exception;
        exception = Assertions.assertThrows(NullPointerException.class,  () -> {
            new Match(null, null);});
        Assertions.assertEquals("Os times não podem ser nulos.", exception.getMessage());

        // recebo uma mensagem de erro onde não deveria receber
        exception = Assertions.assertThrows(NullPointerException.class,  () -> {
            new Match(team1, null);});
            // isso tá errado, deve ser possível criar um match com apenas um time
            // Assertions.assertEquals("Os times não podem ser nulos.", exception.getMessage());
    }


    @Test
    public void testTimesIguais(){
        assertThrows(IllegalArgumentException.class,  () -> {
            new Match(team1, team1);});
    }

    @Test
    public void testGetTeamA(){
        Match match = new Match(team1, team2);
        assertEquals(team1, match.getTeamA());
        assertEquals(team2, match.getTeamB());
    }

    @Test
    public void testIsFinalizada(){
        Match match = new Match(team1, team2);
        assertEquals(false, match.isFinalizada());
    }

    @Test
    public void testRegistrarBlot(){
        Match match = new Match(team1, team2);
        match.registrarBlot(team1);
        assertEquals(1, team1.getBlots());
        match.registrarBlot(team2);
        assertEquals(1, team2.getBlots());
    }

    @Test
    public void testRegistrarPlif(){
        Match match = new Match(team1, team2);
        match.registrarPlif(team1);
        assertEquals(1, team1.getPlifs());
        match.registrarPlif(team2);
        assertEquals(1, team2.getPlifs());
    }

    @Test
    public void testRegistrarAdvrungh(){
        Match match = new Match(team1, team2);
        match.registrarAdvrungh(team1);
        assertEquals(1, team1.getAdvrunghs());
        match.registrarAdvrungh(team2);
        assertEquals(1, team2.getAdvrunghs());
    }

    @Test
    public void testEncerrarPartida(){
        Match match = new Match(team1, team2);
        assertEquals(false, match.isFinalizada());
        match.encerrarPartida();
        assertEquals(true, match.isFinalizada());
    }

    @Test
    public void testGrusht(){
        new Match(team1, team2);
        assertEquals(0, team1.getGrusht());
        assertEquals(0, team2.getGrusht());
        team1.addGrusht();
        assertEquals(1, team1.getGrusht());
        team2.addGrusht();
        assertEquals(1, team2.getGrusht());
    }

    @Test
    public void testResultado(){
        Match match = new Match(team1, team2);
        match.registrarBlot(team1);
        match.registrarPlif(team1);
        match.registrarAdvrungh(team1);
        match.registrarBlot(team2);
        match.registrarPlif(team2);
        match.encerrarPartida();
        assertEquals(team2, match.getVencedor());
        assertEquals(true, match.isFinalizada());
    }

    @Test
    public void testExibirResultadoPartidaNFinalizada(){
        Match match = new Match(team2, team1);
        assertThrows(IllegalStateException.class,  () -> {
            match.exibirResultados();});
    }
    
}
