package com.sprint.mission.discodeit.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class BinaryContentCreateRequestDto {
    private byte[] content;
    private String contentType;
    private UUID userId;
    private UUID messageId;
}
