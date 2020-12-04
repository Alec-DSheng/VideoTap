package org.nee.ny.video.recording.utils;

/**
 * @Author: alec
 * Description:
 * @date: 17:40 2020-11-18
 */
public class CodeUtil {

    public static String padLeft(String oriStr,int len, char alexIn){
        StringBuilder targetStr = new StringBuilder();
        int strLen = oriStr.length();
        if(strLen < len){
            for(int i = 0;i<len-strLen;i++){
                targetStr.append(alexIn);
            }
        }
        targetStr.insert(len-strLen, oriStr);
        return targetStr.toString();
    }
}
