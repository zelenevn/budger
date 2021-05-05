package domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BriefTest {
/*
    @Autowired
    AccountService service;*/

    @Test
    public void test(){
        assertThrows(ArithmeticException.class, ()->{
            Integer.divideUnsigned(42,1);
        });
    }
}
