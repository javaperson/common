package com.github.javaperson.common.util;

import com.google.common.base.Throwables;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * Created by Administrator on 2014/10/30.
 */
public class CodecUtils {
    public byte[] decodeHex(char[] data) {
        try {
            return Hex.decodeHex(data);
        } catch (DecoderException e) {
            Throwables.propagate(e);
        }
        return new byte[0];
    }
}
