package com.heyx.jsoup.service;

import com.heyx.jsoup.dao.SocksRepo;
import com.heyx.jsoup.entity.Socks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-11 18:56
 * @email; 1064042411@qq.com
 */
@Service
public class SocksSerivce extends BaseService<Socks, String> {

    @Autowired
    SocksRepo socksRepo;

    public Socks findFirstByName(String name){
        return socksRepo.findFirstByName(name);
    }
}
