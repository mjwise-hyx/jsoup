package com.heyx.jsoup.dao.net;

import com.heyx.jsoup.dao.BaseRepo;
import com.heyx.jsoup.entity.net.Line;
import com.heyx.jsoup.entity.net.Node;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineRepo extends BaseRepo<Line, String> {
    List<Line> findAllByOutput(Node node);
}
