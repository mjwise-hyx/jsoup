package com.heyx.jsoup.service.dot;

import com.heyx.jsoup.constant.NodeConst;
import com.heyx.jsoup.dao.dot.HistoryRepo;
import com.heyx.jsoup.entity.dot.History;
import com.heyx.jsoup.service.BaseService;
import com.heyx.jsoup.util.FormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public List<History> findAllByCodeBetween(String start, String end){
        return historyRepo.findAllByCodeBetween(start, end);
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

    public String convertToSampleMatrixWithStep(List<History> histories, int step) {
        String output = "";
        if (null == histories) {
            return output;
        }
        int size = histories.size();
        byte[] result = new byte[7 * step];
        for (int i = 0; i < size; i += step) {
            for (int j = 0; j < step; j++) {
                result = FormatUtils.byteMergerAll(result, convertToSampleMatrix(histories.get(i + j)));
            }
        }
        return FormatUtils.bytesTobit(result);
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

    public History convertToHistory(byte[] bytes, String code) {
        String num1 = String.valueOf(FormatUtils.byteToInt(bytes[0]));
        String num2 = String.valueOf(FormatUtils.byteToInt(bytes[1]));
        String num3 = String.valueOf(FormatUtils.byteToInt(bytes[2]));
        String num4 = String.valueOf(FormatUtils.byteToInt(bytes[3]));
        String num5 = String.valueOf(FormatUtils.byteToInt(bytes[4]));
        String num6 = String.valueOf(FormatUtils.byteToInt(bytes[5]));
        String blue = String.valueOf(FormatUtils.byteToInt(bytes[6]));
        return new History(code, num1, num2, num3, num4, num5, num6, blue);
    }

    public History convertToHistory(byte[][] bytes, String code) {
        String num1 = String.valueOf(FormatUtils.bytesToInt(bytes[0]));
        String num2 = String.valueOf(FormatUtils.bytesToInt(bytes[1]));
        String num3 = String.valueOf(FormatUtils.bytesToInt(bytes[2]));
        String num4 = String.valueOf(FormatUtils.bytesToInt(bytes[3]));
        String num5 = String.valueOf(FormatUtils.bytesToInt(bytes[4]));
        String num6 = String.valueOf(FormatUtils.bytesToInt(bytes[5]));
        String blue = String.valueOf(FormatUtils.bytesToInt(bytes[6]));
        return new History(code, num1, num2, num3, num4, num5, num6, blue);
    }

    public int compareResult(String result, String good){
        char[] resultChar = result.toCharArray();
        char[] goodChar = good.toCharArray();
        if (NodeConst.MIN_NODE_NUM != resultChar.length){
            return 0;
        }
        if (NodeConst.MIN_NODE_NUM != goodChar.length){
            return 0;
        }
        int rate = 0;
        for (int i = 0; i < NodeConst.MIN_NODE_NUM; i++) {
            if (resultChar[i] == goodChar[i]){
                rate ++;
            }
        }
        return rate;
    }

    public Optional<History> findFirstByCode(String code) {
        return historyRepo.findFirstByCode(code);
    }

    public String findGoodByCode(String code){
        if (StringUtils.isBlank(code)){
            return "";
        }
        Optional<History> oHistory = findFirstByCode(code);
        return oHistory.map(history -> FormatUtils.bytesTobit(convertToSampleMatrix(history))).orElse("");

    }
}
