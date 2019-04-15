package com.heyx.jsoup.service;

import com.heyx.jsoup.dao.HistoryRepo;
import com.heyx.jsoup.entity.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-11 18:55
 * @email; 1064042411@qq.com
 */
@Service
public class HistoryService extends BaseService<History, String>{
    @Autowired
    HistoryRepo historyRepo;

    public boolean existsByCode(String code) {
        return historyRepo.existsByCode(code);
    }


}
