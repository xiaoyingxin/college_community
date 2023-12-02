package com.xiaoxin.community;

import com.xiaoxin.community.util.SensitiveFilter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SensitiveTest {
    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitive(){
        System.out.println(sensitiveFilter.filter("这里可以赌博,可以嫖娼,可以吸毒,可以开票,哈哈哈!"));
    }

}
