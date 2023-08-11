package ru.practicum.statserver.service;

import ru.practicum.statdto.HitDto;
import ru.practicum.statdto.StatParamDto;
import ru.practicum.statdto.ViewDto;

import java.util.List;

public interface StatService {
    void addStat(HitDto statHit);

    List<ViewDto> getStat(StatParamDto statParam);
}
