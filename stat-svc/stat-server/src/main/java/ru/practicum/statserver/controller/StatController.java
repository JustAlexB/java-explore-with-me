package ru.practicum.statserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.statdto.HitDto;
import ru.practicum.statdto.ViewDto;
import ru.practicum.statdto.StatParamDto;
import ru.practicum.statserver.mapper.StatMapper;
import ru.practicum.statserver.service.StatService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatController {
    private final StatService statService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStatistics(@RequestBody HitDto hitDto) {
        log.info("запись статистики по запросу app={}, url={}, ip={}, timestamp={}",
                hitDto.getApp(), hitDto.getUri(), hitDto.getIp(), hitDto.getTimestamp());
        statService.addStat(hitDto);
    }

    @GetMapping("/stats")
    public List<ViewDto> getStatistics(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                       @RequestParam(required = false) String[] uris,
                                       @RequestParam(defaultValue = "false") boolean unique) {
        log.info("запрос статистики для start={}, end={}, uris={} and unique={}", start, end, uris, unique);
        StatParamDto statParam = StatMapper.toStatParam(start, end, uris, unique);
        return statService.getStat(statParam);
    }
}
