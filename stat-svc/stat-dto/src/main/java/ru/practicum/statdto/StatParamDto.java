package ru.practicum.statdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatParamDto {
    private LocalDateTime start;
    private LocalDateTime end;
    private String[] uris;
    private boolean unique;
}
