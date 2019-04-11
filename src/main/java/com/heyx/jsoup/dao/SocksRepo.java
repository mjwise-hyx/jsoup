package com.heyx.jsoup.dao;

import com.heyx.jsoup.entity.Socks;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-11 18:55
 * @email; 1064042411@qq.com
 */
@Repository
public interface SocksRepo extends BaseRepo<Socks,String> {
    Socks findFirstByName(String name);
}
