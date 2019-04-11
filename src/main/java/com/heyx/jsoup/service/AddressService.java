package com.heyx.jsoup.service;

import com.heyx.jsoup.dao.AddressRepo;
import com.heyx.jsoup.entity.Address;
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
