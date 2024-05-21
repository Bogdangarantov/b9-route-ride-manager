package org.example.b9routeridemanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.b9routeridemanager.enums.TicketStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
    @ManyToOne
    @JoinColumn(name = "departure_id")
    private City departureCity;
    @ManyToOne
    @JoinColumn(name = "arrival_id")
    private City arrivalCity;
    @ManyToOne
    @JoinColumn(name = "passanger_id")
    User passanger;

    @Column(name = "seat_number")
    private String seatNumber;
    @Column(name = "date_of_selling")
    private LocalDate dateOfSelling;

    @Column(name = "ticket_status")
    private String status;
    private double price;


}
