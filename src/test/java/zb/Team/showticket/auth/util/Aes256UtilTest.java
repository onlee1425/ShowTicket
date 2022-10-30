package zb.Team.showticket.auth.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Aes256UtilTest {

    @Test
    void encrypt(){
        String enc = Aes256Util.encrypt("hello");
        assertEquals(Aes256Util.decrypt(enc),"hello");

    }

}