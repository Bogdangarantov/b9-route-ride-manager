package org.example.b9routeridemanager.repositories;

import org.example.b9routeridemanager.entities.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        Ticket ticket1 = new Ticket();
        // Set ticket1 fields

        Ticket ticket2 = new Ticket();
        // Set ticket2 fields

        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);
    }

    @Test
    void testGetTicketByUserId() {
        // Assuming ticket1 and ticket2 have the same userId
        Long userId = 1L; // Set appropriate userId
        Set<Ticket> tickets = ticketRepository.getTicketByUserId(userId);
        assertThat(tickets).hasSize(2);
    }
}
