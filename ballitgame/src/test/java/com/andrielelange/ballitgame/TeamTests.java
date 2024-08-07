package com.andrielelange.ballitgame;
import com.andrielelange.ballitgame.model.Team;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.lang.IllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;

class TeamTests {

    @Test
    public void testNome(){
        Exception exception;
        exception = Assertions.assertThrows(IllegalArgumentException.class,  () -> {
            Team team1 = new Team(null, "AA", 1);});
        
        exception = Assertions.assertThrows(IllegalArgumentException.class,  () -> { 
            Team team1 = new Team("", "AA", 1);});
    }

    @Test
    public void testAno(){
        Exception exception;
        exception = Assertions.assertThrows(IllegalArgumentException.class,  () -> {
            Team team1 = new Team("Andri", "AA", -10);});
        exception = Assertions.assertThrows(IllegalArgumentException.class,  () -> {
            Team team1 = new Team("Andri", "AA", 0);});
        exception = Assertions.assertThrows(IllegalArgumentException.class,  () -> {
            Team team1 = new Team("Andri", "AA", 2025);});
    }

    @Test
    public void testGrito(){
        Exception exception;
        exception = Assertions.assertThrows(IllegalArgumentException.class,  () -> {
            Team team1 = new Team("null", null, 1);});

        exception = Assertions.assertThrows(IllegalArgumentException.class,  () -> { 
            Team team1 = new Team("null", "", 1);});
    }


    @Test
    public void testPontos(){
        Team team1 = new Team("Dell", "pelo poder da tecnologia para impulsionar o progresso humano", 1984);
        assertEquals(50, team1.getPontos());
        team1.addBlot();
        assertEquals(55, team1.getPontos());
        team1.addPlif();
        assertEquals(56, team1.getPontos());
        team1.addAdvrungh();
        assertEquals(46, team1.getPontos());
        team1.addGrusht();
        assertEquals(49, team1.getPontos());
    }
    
}