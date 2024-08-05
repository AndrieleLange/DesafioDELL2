package com.andrielelange.Testes;


import com.andrielelange.model.Team;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import java.io.IllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
public class TeamTest {
    @Test 
    public void testAno() throws IllegalArgumentException{
        Team team1;
        Team team2;
        Team team3;
        Team team4;
        Exception exception;

        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> team1 = new Team(null, "AA", 1));
        assertEquals("O nome e o grito de guerra não podem ser nulos", exception.getMessage());

        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> team2 = new Team("AA", null, 2024));
        assertEquals("O nome e o grito de guerra não podem ser nulos", exception.getMessage());
        
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> team3 = new Team("Andri", "VAI ANDRI", 2025));
        assertEquals("Ano de fundação não pode ser maior que o ano atual", exception.getMessage());

        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> team4 = new Team("Andri", "Vai Andri", -1));
        assertEquals("Ano de fundação não pode ser negativo", exception.getMessage());
    }

}
