package com.andrielelange.ballitgame;
import com.andrielelange.ballitgame.model.Match;
import com.andrielelange.ballitgame.model.Team;
import com.andrielelange.ballitgame.model.Championship;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.lang.IllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import java.lang.IllegalArgumentException;

public class ChampionshipTests {
    Team team1 = new Team("Dell", "pelo poder da tecnologia para impulsionar o progresso humano", 1984);
    Team team2 = new Team("Dell Technologies Inc.", "Vai Dell", 2016);

    @Test
    public void testFase() throws Exception{
        Championship championship = new Championship();
        championship.addTeam(team1);
        championship.addTeam(team2);
        assertEquals(0, championship.getFase());
        championship.avancarFase();
        assertEquals(1, championship.getFase());
    }

    @Test
    public void testCacadastrado(){
        Championship championship = new Championship();
        Exception exception;
        try {
            for (int i = 0; i < 16; i++) {
                championship.addTeam(new Team("Dell", "pelo poder da tecnologia para impulsionar o progresso humano", 1984));
            }
        } catch (Exception e) {
            exception = e;
            assertEquals("Time já cadastrado.", exception.getMessage());
        }
    }

    @Test
    public void testMaximoTimes() {
        Championship championship = new Championship();
        StringBuilder sb = new StringBuilder();
        sb.append("Dell");

        Exception exception;
        try {
            for (int i = 0; i < 17; i++) {
                sb.append("a");
                championship.addTeam(new Team(sb.toString(), "pelo poder da tecnologia para impulsionar o progresso humano", 1984));
            }
        } catch (Exception e) {
            exception = e;
            assertEquals("Número máximo de times atingido.", exception.getMessage());
        }
    }

    @Test
    public void testNumeroTimes() throws Exception {
        Championship championship = new Championship();
        championship.addTeam(team1);
        championship.addTeam(team2);
        Exception exception;
        try {
            championship.iniciarCampeonato();
        } catch (Exception e) {
            exception = e;
            assertEquals("O número de times deve ser par e entre 8 e 16.", exception.getMessage());
        }
    }

    @Test
    public void testSortearTimes() throws Exception {
        Championship championship = new Championship();
        StringBuilder sb = new StringBuilder();
        sb.append("Dell");

        for (int i = 0; i < 16; i++) {
            sb.append("a");
            championship.addTeam(new Team(sb.toString(), "pelo poder da tecnologia para impulsionar o progresso humano", 1984));
        }
        championship.iniciarCampeonato();
        assertEquals(8, championship.getMatches().size());
        championship.avancarFase();
        assertEquals(8, championship.getMatches().size());
    }

    // testar vencedores
    // testar avançar fase
    // testar campeão
    // testar finalizado
    // testar empate
    // testar finalizar partida
    // testar finalizar campeonato
    // testar lista em ordem de campeos
 

}
