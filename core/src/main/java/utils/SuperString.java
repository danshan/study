package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * The Class SuperString.
 * @author jack.zhang
 */
public abstract class SuperString {

    private static final Pattern XSS_PATTERN = Pattern.compile("(?i)[|;$@'\"<>()+,\\\\#]|%7C|%3B|%24|%40|%27|%22|%3C|%3E|%28|%29|%2B|%2C|%5C|%23");
    /**
     * The constant EMPTY.
     */
    public static final String EMPTY = "";
    /**
     * The constant NUMERIC_SHORT.
     */
    public static final int NUMERIC_SHORT = 0;
    /**
     * The constant NUMERIC_INT.
     */
    public static final int NUMERIC_INT = 1;
    /**
     * The constant NUMERIC_LONG.
     */
    public static final int NUMERIC_LONG = 2;
    /**
     * The constant NUMERIC_FLOAT.
     */
    public static final int NUMERIC_FLOAT = 3;
    /**
     * The constant NUMERIC_DOUBLE.
     */
    public static final int NUMERIC_DOUBLE = 4;

    /**
     * Not null.
     *
     * @param strTemp the str temp
     * @return the string
     */
    public static String notNull(String strTemp) {
        if (strTemp == null) {
            return "";
        } else {
            return strTemp;
        }
    }

    /**
     * Not null trim.
     *
     * @param strTemp the str temp
     * @return the string
     */
    public static String notNullTrim(String strTemp) {
        return notNull(strTemp).trim();
    }

    /**
     * Is blank.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isBlank(String str) {
        return (notNullTrim(str).length() == 0);
    }

    /**
     * Gets quote del.
     *
     * @param strTemp the str temp
     * @return the quote del
     */
    public static String getQuoteDel(String strTemp) {
        return notNull(strTemp).replace("\"", "");
    }

    /**
     * Gets int.
     *
     * @param strTemp the str temp
     * @return the int
     */
    public static int getInt(String strTemp) {
        return getInt(strTemp, 0);
    }

    /**
     * Gets int.
     *
     * @param strTemp the str temp
     * @param def the def
     * @return the int
     */
    public static int getInt(String strTemp, int def) {
        strTemp = notNull(strTemp);
        if (strTemp.equals("")) {
            return def;
        }
        try {
            return (int) Math.floor(Double.parseDouble(strTemp));
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * Gets long.
     *
     * @param strTemp the str temp
     * @return the long
     */
    public static long getLong(String strTemp) {
        return getLong(strTemp, 0L);
    }

    /**
     * Gets long.
     *
     * @param strTemp the str temp
     * @param def the def
     * @return the long
     */
    public static long getLong(String strTemp, long def) {
        strTemp = notNull(strTemp);
        if (strTemp.equals("")) {
            return def;
        }
        try {
            return (long) Math.floor(Double.parseDouble(strTemp));
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * Gets float.
     *
     * @param strTemp the str temp
     * @return the float
     */
    public static float getFloat(String strTemp) {
        strTemp = notNull(strTemp);
        if (strTemp.equals("")) {
            return 0f;
        }
        try {
            return Float.parseFloat(strTemp);
        } catch (Exception e) {
            return 0f;
        }
    }

    /**
     * Gets double.
     *
     * @param strTemp the str temp
     * @return the double
     */
    public static double getDouble(String strTemp) {
        return getDouble(strTemp, 0D);
    }

    /**
     * Gets double.
     *
     * @param strTemp the str temp
     * @param defaultVal the default val
     * @return the double
     */
    public static double getDouble(String strTemp, double defaultVal) {
        strTemp = notNull(strTemp);
        strTemp = strTemp.replace(",", "");
        if (strTemp.equals("")) {
            return 0d;
        }
        try {
            return Double.parseDouble(strTemp);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /**
     * Gets rand string.
     *
     * @param minlen the minlen
     * @param maxlen the maxlen
     * @return the rand string
     */
    public static String getRandString(int minlen, int maxlen) {
        String s = "";
        if (minlen > maxlen) {
            minlen = maxlen;
        }
        for (; s.length() < maxlen; s = ("" + Math.random()).substring(2) + s) {
            ;
        }
        s = s.substring(s.length() - maxlen);
        int n = maxlen - minlen;
        if (n > 0) {
            n = (int) Math.round(n * Math.random());
        }
        if (n > 0) {
            s = s.substring(n);
        }
        return s;
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str the str
     * @param intType the int type
     * @return 是否为数字 boolean
     */
    public static boolean isNumeric(String str, int intType) {
        try {
            switch (intType) {
                case NUMERIC_SHORT:
                    Short.parseShort(str);
                    break;
                case NUMERIC_INT:
                    Integer.parseInt(str);
                    break;
                case NUMERIC_LONG:
                    Long.parseLong(str);
                    break;
                case NUMERIC_FLOAT:
                    Float.parseFloat(str);
                    break;
                case NUMERIC_DOUBLE:
                    Double.parseDouble(str);
                    break;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 替换掉本网站模板文本中的变量${}
     *
     * @param text 要替换的文本全文
     * @param varname 变量名 $(name) name为变量名
     * @param value 替换的值
     * @return 替换后的文本 string
     */
    public static String replaceTemplateTag(String text, String varname, String value) {
        if (text == null) {
            return null;
        }
        return text.replace("${" + varname + "}", value);
    }

    /**
     * Replace template tag.
     *
     * @param text the text
     * @param map the map
     * @return the string
     */
    public static String replaceTemplateTag(String text, Map<String, String> map) {
        for (String o : map.keySet()) {
            String key = o;
            if (key != null && map.containsKey(key)) {
                text = replaceTemplateTag(text, key, map.get(key));
            }
        }
        return text;
    }

    /**
     * Clear text.
     *
     * @param text the text
     * @return the string
     */
    public static String clearText(String text) {
        return text.replace("'", "")
                .replace("\"", "")
                .replace("\r", "")
                .replace("\n", "")
                .replace("\t", "");
    }

    /**
     * Str to list.
     *
     * @param str the str
     * @param seperator the seperator
     * @return the list
     */
    public static List<String> strToList(String str, String seperator) {
        List<String> list = new ArrayList<String>();
        if (str == null || str.length() == 0 || seperator == null) return list;
        list.addAll(Arrays.asList(str.split(seperator)));
        return list;
    }

    /**
     * prefix of ascii string of native character
     */
    private static String PREFIX = "\\u";

    /**
     * Native to ascii string. It's same as execut native2ascii.exe.
     *
     * @param str native string
     * @return ascii string
     */
    public static String native2Ascii(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(char2Ascii(chars[i]));
        }
        return sb.toString();
    }

    /**
     * Native character to ascii string.
     *
     * @param c native character
     * @return ascii string
     */
    private static String char2Ascii(char c) {
        if (c > 255) {
            StringBuilder sb = new StringBuilder();
            sb.append(PREFIX);
            int code = (c >> 8);
            String tmp = Integer.toHexString(code);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            code = (c & 0xFF);
            tmp = Integer.toHexString(code);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            return sb.toString();
        } else {
            return Character.toString(c);
        }
    }

    /**
     * Ascii to native string. It's same as execut native2ascii.exe -reverse.
     *
     * @param str ascii string
     * @return native string
     */
    public static String ascii2Native(String str) {
        StringBuilder sb = new StringBuilder();
        int begin = 0;
        int index = str.indexOf(PREFIX);
        while (index != -1) {
            sb.append(str.substring(begin, index));
            sb.append(ascii2Char(str.substring(index, index + 6)));
            begin = index + 6;
            index = str.indexOf(PREFIX, begin);
        }
        sb.append(str.substring(begin));
        return sb.toString();
    }

    /**
     * Ascii to native character.
     *
     * @param str ascii string
     * @return native character
     */
    private static char ascii2Char(String str) {
        if (str.length() != 6) {
            throw new IllegalArgumentException(
                    "Ascii string of a native character must be 6 character.");
        }
        if (!PREFIX.equals(str.substring(0, 2))) {
            throw new IllegalArgumentException(
                    "Ascii string of a native character must start with \"\\u\".");
        }
        String tmp = str.substring(2, 4);
        int code = Integer.parseInt(tmp, 16) << 8;
        tmp = str.substring(4, 6);
        code += Integer.parseInt(tmp, 16);
        return (char) code;
    }

    /**
     * Judge xss injection.
     *
     * @param string the string
     * @return the boolean 如果有Xss风险则返回true
     */
    public static boolean isXssInjection(String string) {
        return XSS_PATTERN.matcher(string).find();
    }

    /**
     * Check white space.
     *
     * @param string the string
     * @return the boolean 如果字符串为空或者"",或者" fuck "都返回 true
     */
    public static boolean constainWhiteSpace(String string) {
        return string == null || string.isEmpty() || string.contains(" ");
    }
}