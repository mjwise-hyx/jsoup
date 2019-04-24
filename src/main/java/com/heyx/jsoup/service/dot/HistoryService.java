package com.heyx.jsoup.service.dot;

import com.heyx.jsoup.dao.dot.HistoryRepo;
import com.heyx.jsoup.entity.dot.History;
import com.heyx.jsoup.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-11 18:55
 * @email; 1064042411@qq.com
 */
@Service
public class HistoryService extends BaseService<History, String> {
    @Autowired
    HistoryRepo historyRepo;

    public boolean existsByCode(String code) {
        return historyRepo.existsByCode(code);
    }


}
