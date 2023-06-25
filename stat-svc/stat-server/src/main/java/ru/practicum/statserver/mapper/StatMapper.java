package ru.practicum.statserver.mapper;

import ru.practicum.statdto.HitDto;
import ru.practicum.statserver.model.StatRecord;

public class StatMapper {
    public static StatRecord toStatRecord(HitDto hitDto) {
        return StatRecord.builder()
                .app(hitDto.getApp())
                .uri(hitDto.getUri())
                .ip(hitDto.getIp())
                .created(hitDto.getTimestamp())
                .build();
    }
}
