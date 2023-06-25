package ru.practicum.statserver.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "statistics", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class StatRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String app;
    @Column(nullable = false)
    private String uri;
    @Column(nullable = false)
    private String ip;
    @Column(nullable = false)
    private LocalDateTime created;
}
