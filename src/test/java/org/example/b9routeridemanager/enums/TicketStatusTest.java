package org.example.b9routeridemanager.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EnumSet;

class TicketStatusTest {

    @Test
    void testTicketStatusValues() {
        TicketStatus[] statuses = TicketStatus.values();
        assertEquals(2, statuses.length);
        assertTrue(EnumSet.of(TicketStatus.PURCHASED, TicketStatus.RETURNED).containsAll(Arrays.asList(statuses)));
    }

    @Test
    void testTicketStatusValueOf() {
        assertEquals(TicketStatus.PURCHASED, TicketStatus.valueOf("PURCHASED"));
        assertEquals(TicketStatus.RETURNED, TicketStatus.valueOf("RETURNED"));
    }
}
