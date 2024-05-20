package org.example.b9routeridemanager.repositories;

import org.example.b9routeridemanager.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("select t from Ticket t where t.passanger.id =:id")
    Set<Ticket> getTicketByUserId(Long id);
}
