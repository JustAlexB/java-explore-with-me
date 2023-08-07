package ru.practicum.statserver.mapper;

import ru.practicum.statdto.HitDto;
import ru.practicum.statdto.StatParamDto;
import ru.practicum.statserver.model.StatRecord;

import java.time.LocalDateTime;

public class StatMapper {
    public static StatRecord toStatRecord(HitDto hitDto) {
        return StatRecord.builder()
                .app(hitDto.getApp())
                .uri(hitDto.getUri())
                .ip(hitDto.getIp())
                .created(hitDto.getTimestamp())
                .build();
    }

    public static StatParamDto toStatParam(LocalDateTime start, LocalDateTime end, String[] uris, boolean unique) {
        return StatParamDto.builder()
                .start(start)
                .end(end)
                .uris(uris)
                .unique(unique)
                .build();
    }
}
