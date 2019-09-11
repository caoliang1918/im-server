package org.zhongweixian.live.util;

import java.util.Random;

/**
 * @author: caoliang1918@aliyun.com
 * @date: 2017/12/11 22:09
 */
public enum RandCodeImageEnum {
    /**
     * 数字大小写
     */
    ALL_CHAR("0123456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    /**
     * 大小写
     */
    LETTER_CHAR("abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ"),
    /**
     * 小写
     */
    LOWER_CHAR("abcdefghijklmnopqrstuvwxyz"),
    /**
     * 数字
     */
    NUMBER_CHAR("0123456789"),
    /**
     * 去掉相似的数字大小写
     */
    UPPER_CHAR("23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ");

    private String charStr;

    RandCodeImageEnum(String charStr) {
        this.charStr = charStr;
    }

    public String generateStr(int codeLength) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        String sourseStr = this.getCharStr();

        for(int i = 0; i < codeLength; ++i) {
            sb.append(sourseStr.charAt(random.nextInt(sourseStr.length())));
        }

        return sb.toString();
    }

    public String getCharStr() {
        return this.charStr;
    }
}
