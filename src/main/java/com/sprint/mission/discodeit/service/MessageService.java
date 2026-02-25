package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    // Message 관련 서비스 메소드들 선언
    Message createMessage(
            UUID userId,
            UUID channelId,
            String content
    );
    Message getMessage(UUID id);

    List<Message> getAllMessageByChannel(UUID channelId);
    void updateMessage(UUID id, String newContent);
    void deleteMessage(UUID id);
}
