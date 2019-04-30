package com.heyx.jsoup;

import com.heyx.jsoup.service.net.NodeService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NetworkTest {

    @Autowired
    NodeService nodeService;

}
