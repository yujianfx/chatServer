package top.wangudiercai.chatserver;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@MapperScan(basePackages = "top.wangudiercai.chatserver.mapper")
@SpringBootApplication
public class ChatServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(ChatServerApplication.class, args);
  }
}
