package ru.practicum.statserver.service;

import ru.practicum.statdto.HitDto;
import ru.practicum.statdto.ViewDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatService {
    void addStat(HitDto statHit);

    List<ViewDto> getStat(LocalDateTime start, LocalDateTime end, String[] uris, boolean unique);
}
