package cn.ctrlcv.eighteen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ljm19
 */
@MapperScan("cn.ctrlcv.eighteen.**.mapper")
@SpringBootApplication
public class EighteenApplication {

    public static void main(String[] args) {
        SpringApplication.run(EighteenApplication.class, args);
    }

}
