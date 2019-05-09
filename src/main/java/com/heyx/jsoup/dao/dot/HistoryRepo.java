package com.heyx.jsoup.dao.dot;

import com.heyx.jsoup.dao.BaseRepo;
import com.heyx.jsoup.entity.dot.History;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: heyx
 * @create: 2019-04-11 18:55
 * @email; 1064042411@qq.com
 */
@Repository
public interface HistoryRepo extends BaseRepo<History,String> {

    boolean existsByCode(String code);
    Optional<History> findFirstByCode(String code);
    List<History> findAllByCodeBetween(String start, String end);
}
