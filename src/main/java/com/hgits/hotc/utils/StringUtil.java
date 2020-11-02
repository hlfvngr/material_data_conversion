package com.hgits.hotc.utils;



/**
 * @ClassName:StringUtil
 * @author:huanghaifeng
 * @description:字符串工具类
 * @Date:2017年12月22日 下午1:48:23
 */
public class StringUtil {
    public static String getEntryOrExit(String number) {
        int index = 0;
        char strs[] = number.toCharArray();
        int len = number.length();
        for (int i = 0; i < len; i++) {
            if ('0' != strs[i]) {
                index = i;
                break;
            }
        }
        number = number.substring(index, number.length());
        return number;
    }

   /* *//**
     * 前面补齐某字符
     *
     * @param fillWith 填补字符
     * @param length   长度
     * @param orin     原字符串
     * @return
     */
    public static String format(char fillWith, Integer length, String orin) {
        StringAlign align = new StringAlign();
        align.setFill(fillWith);
        align.setJust(StringAlign.JUST_RIGHT);
        align.setMaxChars(length);
        return align.format(orin);
    }

    public static void main(String[] args) {

    }

}
