package com.heyx.jsoup.service.dot;

import com.heyx.jsoup.dao.dot.AddressRepo;
import com.heyx.jsoup.entity.dot.Address;
import com.heyx.jsoup.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-11 20:10
 * @email; 1064042411@qq.com
 */
@Service
public class AddressService extends BaseService<Address, String> {
    @Autowired
    AddressRepo addressRepo;
}
