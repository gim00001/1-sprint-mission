package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.UserStatusResponseDto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface UserStatusService {
    UserStatusResponseDto create(UUID userId, Instant lastAccessAt);
    UserStatusResponseDto findById(UUID id);
    List<UserStatusResponseDto> findAll();
    void updateLastAccess(UUID userId), Instant lastAccessAt;
    void delete(UUID id);
}
