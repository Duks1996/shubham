package com.busreservation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stop_order")
public class StopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "stoporder", nullable = false)
    private Long stoporder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "stop_id", nullable = false)
    private Stop stop;

}