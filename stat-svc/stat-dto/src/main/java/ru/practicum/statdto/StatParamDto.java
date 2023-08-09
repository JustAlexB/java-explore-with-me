package ru.practicum.statdto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StatParamDto {
    private LocalDateTime start;
    private LocalDateTime end;
    private String[] uris;
    private boolean unique;
}
