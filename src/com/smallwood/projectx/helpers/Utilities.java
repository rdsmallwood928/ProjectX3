package com.smallwood.projectx.helpers;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by bigwood928 on 4/18/14.
 */
public class Utilities {

    public static String exceptionToString(Exception e) {
        StringBuilder builder = new StringBuilder();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        e.printStackTrace(ps);
        try {
            builder.append(baos.toString("ISO-8859-1"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return builder.toString();
    }
}
