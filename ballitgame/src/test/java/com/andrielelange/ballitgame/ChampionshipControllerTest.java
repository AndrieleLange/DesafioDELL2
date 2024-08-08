package com.andrielelange.ballitgame;
import com.andrielelange.ballitgame.model.Match;
import com.andrielelange.ballitgame.model.Team;
import com.andrielelange.ballitgame.controller.ChampionshipController;

import org.junit.jupiter.api.Test;
import java.lang.IllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Assertions;


public class ChampionshipControllerTest {
    Team team1 = new Team("Dell", "pelo poder da tecnologia para impulsionar o progresso humano", 1984);
    Team team2 = new Team("Dell Technologies Inc.", "Vai Dell", 2016);
    ChampionshipController championshipController = new ChampionshipController();


    @Test
    public void addTimeTest(){
        Exception exception;
        exception = Assertions.assertThrows(IllegalArgumentException.class,  () -> {
            championshipController.cadastrarTime(null, "AA", 1);});
        Assertions.assertEquals("O time não pode ser cadastrado por o nome e ou grito de guerra serem nulos.\n", exception.getMessage());

        exception = Assertions.assertThrows(IllegalArgumentException.class,  () -> {
            championshipController.cadastrarTime("null", "", 1);});
        Assertions.assertEquals("O time não pode ser cadastrado por o nome e ou grito de guerra serem nulos.\n", exception.getMessage());
    }

    // todos devem dar mensagem de erro pois não é aceitavel anos maiores que o atual, anos iguais ou menores que 0
    @Test
    public void testAno(){
        Exception exception;
        exception = Assertions.assertThrows(IllegalArgumentException.class,  () -> {
            championshipController.cadastrarTime("Andri", "AA", -10);});
        exception = Assertions.assertThrows(IllegalArgumentException.class,  () -> {
            championshipController.cadastrarTime("Andri", "AA", 0);});
        exception = Assertions.assertThrows(IllegalArgumentException.class,  () -> {
            championshipController.cadastrarTime("Andri", "AA", 2025);});
    }


    // ta dando erro mas ta certo
    @Test
    public void testNumeroTimes() throws Exception {
        championshipController.cadastrarTime("Dell", "pelo poder da tecnologia para impulsionar o progresso humano", 1984);
        championshipController.cadastrarTime("Dell Technologies Inc.", "Vai Dell", 2016);
        Exception exception = Assertions.assertThrows(Exception.class, ()-> championshipController.iniciarCampeonato());
        assertEquals("java.lang.Exception: O número de times deve ser par e entre 8 e 16.\n Número de times cadastrados: 2\n\n", exception);
    }


    
}
