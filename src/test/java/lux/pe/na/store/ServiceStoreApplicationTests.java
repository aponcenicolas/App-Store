package lux.pe.na.store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceStoreApplicationTests {

    @Test
    void contextLoads() {
        int num = 10;
        Assertions.assertEquals(100, num * num);
    }

}
