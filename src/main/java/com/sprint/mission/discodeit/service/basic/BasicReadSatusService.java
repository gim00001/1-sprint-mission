package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.ReadStatusResponseDto;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import com.sprint.mission.discodeit.service.ReadStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasicReadSatusService implements ReadStatusService {
    private final ReadStatusRepository readStatusRepository;

    @Override
    public ReadStatusResponseDto create(UUID userId, UUID channelId, Instant lastReadAt) {
        ReadStatus entity = new ReadStatus(userId, channelId, lastReadAt);
        readStatusRepository.save(entity);
        return toResponseDto(entity);
    }

    @Override
    public ReadStatusResponseDto findById(UUID id) {
        ReadStatus entity = readStatusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ReadStatus not found"));
        return toResponseDto(entity);
    }

    @Override
    public List<ReadStatusResponseDto> findAllByUserId(UUID userId) {
        return readStatusRepository.findAllByUserId(userId).stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public void updateLastRead(UUID id, Instant lastReadAt) {
        ReadStatus entity = readStatusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ReadStatus not found"));
        entity.setLastReadAt(lastReadAt);
        readStatusRepository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        readStatusRepository.deleteById(id);
    }

    private ReadStatusResponseDto toResponseDto(ReadStatus entity) {
        ReadStatusResponseDto dto = new ReadStatusResponseDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setChannelId(entity.getChannelId());
        dto.setLastReadAt(entity.getLastReadAt());
        return dto;
    }

}
