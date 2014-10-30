package com.github.javaperson.common.util;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2014/10/30.
 */
public class ByteUtils {
    public static String toHexString(byte[] bytes, String separator) {
        checkNotNull(separator);

        if (GuavaUtils.isNullOrEmpty(bytes)) {
            return "";
        }
        StringBuffer buf = new StringBuffer();
        buf.append(separator);
        for (int i = 0; i < bytes.length; i++) {
            short b = byteToShort(bytes[i]);
            String hex = Integer.toString(b, 16);
            if (b < 16) {
                buf.append('0');
            }
            buf.append(hex);
            if (i < bytes.length - 1) {
                buf.append(separator);
            }
        }
        return buf.toString();
    }

    private static short byteToShort(byte b) {
        return b < 0 ? (short) (256 + b) : (short) b;
    }
}
