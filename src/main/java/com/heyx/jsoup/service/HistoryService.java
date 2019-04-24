package com.heyx.jsoup.service;

import com.heyx.jsoup.dao.HistoryRepo;
import com.heyx.jsoup.entity.History;
import com.heyx.jsoup.util.FormatUtils;
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


    public byte[][] convertToMatrix(History history) {
        byte[][] result = new byte[7][4];
        result[0] = FormatUtils.intToByte(Integer.parseInt(history.getNum1()));
        result[1] = FormatUtils.intToByte(Integer.parseInt(history.getNum2()));
        result[2] = FormatUtils.intToByte(Integer.parseInt(history.getNum3()));
        result[3] = FormatUtils.intToByte(Integer.parseInt(history.getNum4()));
        result[4] = FormatUtils.intToByte(Integer.parseInt(history.getNum5()));
        result[5] = FormatUtils.intToByte(Integer.parseInt(history.getNum6()));
        result[6] = FormatUtils.intToByte(Integer.parseInt(history.getBule()));
        return result;
    }

    public byte[] convertToSampleMatrix(History history) {
        byte[] result = new byte[7];
        result[0] = FormatUtils.convertToByte(Integer.parseInt(history.getNum1()));
        result[1] = FormatUtils.convertToByte(Integer.parseInt(history.getNum2()));
        result[2] = FormatUtils.convertToByte(Integer.parseInt(history.getNum3()));
        result[3] = FormatUtils.convertToByte(Integer.parseInt(history.getNum4()));
        result[4] = FormatUtils.convertToByte(Integer.parseInt(history.getNum5()));
        result[5] = FormatUtils.convertToByte(Integer.parseInt(history.getNum6()));
        result[6] = FormatUtils.convertToByte(Integer.parseInt(history.getBule()));
        return result;
    }
}
