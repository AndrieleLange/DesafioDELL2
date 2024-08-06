package com.andrielelange.ballitgame;
import com.andrielelange.ballitgame.model.Match;
import com.andrielelange.ballitgame.model.Team;
import com.andrielelange.ballitgame.controller.*;

import com.andrielelange.ballitgame.model.Championship;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.lang.IllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import java.lang.IllegalArgumentException;


public class ChampionshipControllerTest {
    Team team1 = new Team("Dell", "pelo poder da tecnologia para impulsionar o progresso humano", 1984);
    Team team2 = new Team("Dell Technologies Inc.", "Vai Dell", 2016);

    @Test
    public void testNomeNulo(){
        Exception exception;
        exception = Assertions.assertThrows(NullPointerException.class,  () -> {
            Match match = new Match(null, team1);});
        Assertions.assertEquals("Os times não podem ser nulos.", exception.getMessage());

        exception = Assertions.assertThrows(NullPointerException.class,  () -> {
            Match match = new Match(team1, null);});
    }

    @Test
    public void testTimesIguais(){
        Exception exception;
        exception = Assertions.assertThrows(IllegalArgumentException.class,  () -> {
            Match match = new Match(team1, team1);});
        Assertions.assertEquals("Os times não podem ser iguais.", exception.getMessage());
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
    }

    @Test
    public void testRegistrarAdvrungh(){
        Match match = new Match(team1, team2);
        match.registrarAdvrungh(team1);
    }


    
}
