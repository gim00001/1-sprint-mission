package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.UserStatusResponseDto;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import com.sprint.mission.discodeit.service.UserStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasicUserStatusService implements UserStatusService {
    private final UserStatusRepository userStatusRepository;

    @Override
    public UserStatusResponseDto create(UUID userId, Instant lastAccessAt) {
        UserStatus entity = new UserStatus(userId, lastAccessAt);
        userStatusRepository.save(entity);
        return toResponseDto(entity);
    }

    @Override
    public UserStatusResponseDto findById(UUID id) {
        UserStatus entity = userStatusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserStatus not found"));
        return toResponseDto(entity);
    }

    @Override
    public List<UserStatusResponseDto> findAll() {
        return null;
    }

    @Override
    public void updateLastAccess(UUID userId) {

    }

    @Override
    public void delete(UUID id) {

    }

    public UserStatusResponseDto toResponseDto(UserStatus entity) {
        UserStatusResponseDto dto = new UserStatusResponseDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setLastAccessAt(entity.getLastAccessAt());
        dto.setOnline(entity.isOnline());
        return dto;
    }
}
