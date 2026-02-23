import com.sprint.mission.discodit.entity.*;
import com.sprint.mission.discodit.service.*;
import com.sprint.mission.discodit.service.jcf.*;

import java.util.UUID;

public class Application {

    public static void main(String[] args) {

        // ===== User 엔티티 테스트 =====
        User user = new User("홍길동", "hong@codeit.com");

        System.out.println("User ID: " + user.getId());
        System.out.println("User 이름: " + user.getName());
        System.out.println("User 이메일: " + user.getEmail());

        // ===== Message 엔티티 테스트 =====
        UUID userId = UUID.randomUUID();
        UUID channelId = UUID.randomUUID();

        Message message =
                new Message("안녕하세요!", userId, channelId);

        System.out.println("Message ID: " + message.getId());
        System.out.println("작성자: " + message.getUserId());
        System.out.println("채널: " + message.getChannelId());
        System.out.println("내용: " + message.getContent());

        message.update("반갑습니다!");
        System.out.println("수정된 내용: " + message.getContent());

        // ===== UserService 테스트 =====
        UserService userService = new JCFUserService();

        User serviceUser =
                userService.createUser("김코드", "kim@codeit.com");

        System.out.println("등록된 User: " + serviceUser.getName());

        User foundUser =
                userService.getUser(serviceUser.getId());

        System.out.println("조회된 User: " + foundUser.getName());

        // ===== Channel 엔티티 테스트 =====
        Channel channel =
                new Channel("자유채팅방", "아무거나 이야기");

        System.out.println("ID: " + channel.getId());
        System.out.println("이름: " + channel.getName());
        System.out.println("설명: " + channel.getDescription());

        channel.update("공지채널", "공지 전용");

        System.out.println("=== 업데이트 후 ===");
        System.out.println("이름: " + channel.getName());
        System.out.println("설명: " + channel.getDescription());

        // ===== ChannelService =====
        ChannelService channelService =
                new JCFChannelService();

        Channel serviceChannel =
                channelService.createChannel(
                        "자유채널", "자유롭게 대화"
                );

        System.out.println(
                "등록된 Channel: " +
                        serviceChannel.getName()
        );

        // ===== MessageService =====
        MessageService messageService =
                new JCFMessageService();

        Message serviceMessage =
                messageService.createMessage(
                        serviceUser.getId(),
                        serviceChannel.getId(),
                        "안녕하세요!"
                );

        System.out.println(
                "등록된 메시지: " +
                        serviceMessage.getContent()
        );
    }
}