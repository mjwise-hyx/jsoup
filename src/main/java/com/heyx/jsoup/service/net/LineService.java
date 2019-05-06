package com.heyx.jsoup.service.net;

import com.heyx.jsoup.dao.net.LineRepo;
import com.heyx.jsoup.entity.net.Line;
import com.heyx.jsoup.entity.net.Node;
import com.heyx.jsoup.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineService extends BaseService<Line, String> {

    @Autowired
    LineRepo lineRepo;

    public List<Line> findAllByOutput(Node node) {
        return lineRepo.findAllByOutput(node);
    }
}
