package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.*;
import com.sprint.mission.discodeit.repository.*;
import com.sprint.mission.discodeit.repository.file.*;
import com.sprint.mission.discodeit.service.*;
import com.sprint.mission.discodeit.service.basic.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Application {

    public static void main(String[] args) {
        // ==============================
        // 1. Repository, Service 준비
        // ==============================
        UserRepository userRepository = new FileUserRepository();
        ChannelRepository channelRepository = new FileChannelRepository();
        MessageRepository messageRepository = new FileMessageRepository();

        UserService userService = new BasicUserService(userRepository);
        ChannelService channelService = new BasicChannelService(channelRepository);
        MessageService messageService = new BasicMessageService(messageRepository);

        //============================
        // 2. User 생성 및 조회
        //============================
        User user = userService.create("홍길동", "hong@codeit.com", "hong1234");
        System.out.println("[User]생성: " + user.getId());
        Optional<User> findUser = userService.findById(user.getId());
        findUser.ifPresent(u-> System.out.println("[User] 조회 이름: " + u.getName()));

        //=============================
        //3. Channel 생성 및 조회
        //=============================
        Channel channel = channelService.create("일반채널", "자유롭게 대화하는 곳입니다.");
        System.out.println("[Channel] 생성: " + channel.getId());
        Optional<Channel> findChannel = channelService.findById(channel.getId());
        findChannel.ifPresent(c -> System.out.println("[Channel] 조회 이름: " + c.getName()));

        //=============================
        //4. Message 생성 및 조회
        //=============================
        Message message = messageService.create("안녕하세요!", channel.getId(), user.getId());
        System.out.println("[Message] 생성: " + message.getId());

        Optional<Message> findMessage = messageService.findById(message.getId());
        findMessage.ifPresent(m-> System.out.println("[Message] 내용: " + m.getContent()));

        //============================
        //5. 저체 조회
        //============================
        List<User> allUsers = userService.findAll();
        List<Channel> allChannels = (List<Channel>) channelService.findAll();
        List<Message> allMessages = messageService.findAll();

        System.out.println("[User] 전체 수: " + allUsers.size());
        System.out.println("[Channel] 전체 수: " + allChannels.size());
        System.out.println("[Message]전체 수: "+ allMessages.size());

        //=============================
        //6. 삭제 테이스
        //=============================
        userService.delete(user.getId());
        channelService.delete(channel.getId());
        messageService.delete(message.getId());

        System.out.println("[삭제 후 User] 전체 수: " + allUsers.size());
        System.out.println("[삭제 후 Channel] 전체 수: " + allChannels.size());
        System.out.println("[삭제 후 Message] 전체 수: " + allMessages.size());


    }
}