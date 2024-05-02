package org.Team3.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlertTest {

    @Mock
    Alert alert = new Alert();


    @Test
    void testAlertProperties(){
        assertEquals(0, alert.getId());
        assertNull(alert.getMessage());
        assertNull(alert.getType());
        assertEquals(0, alert.getProd_id());
        assertNull(alert.getTimestamp());
    }
}