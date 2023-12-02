package com.xiaoxin.community;

import com.xiaoxin.community.service.AlphaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionalTest {
    @Autowired
    private AlphaService alphaService;
    @Test
    public void testTranscational(){
        System.out.println(alphaService.save1());
    }

}
