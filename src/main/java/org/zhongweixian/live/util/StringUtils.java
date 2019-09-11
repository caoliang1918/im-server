package org.zhongweixian.live.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by caoliang1918@gmail.com on 2017/4/27.
 */
public class StringUtils {

    static Map<String, Object> map = new HashMap<>();

    public StringUtils() {
    }

    public static Map<String, Object> getMap() {
        return map;
    }

    public static boolean isEmpty(Object object) {
        return object == null ? true : ("".equals(object) ? true : "null".equals(object));
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    public static String decode(String strIn, String sourceCode, String targetCode) {
        String temp = code2code(strIn, sourceCode, targetCode);
        return temp;
    }

    private static String code2code(String strIn, String sourceCode, String targetCode) {
        String strOut = null;
        if (strIn != null && !"".equals(strIn.trim())) {
            try {
                byte[] e = strIn.getBytes(sourceCode);

                for (int i = 0; i < e.length; ++i) {
                    System.out.print(e[i] + "  ");
                }

                strOut = new String(e, targetCode);
                return strOut;
            } catch (Exception var6) {
                var6.printStackTrace();
                return null;
            }
        } else {
            return strIn;
        }
    }

    public static int getInt(String s, int defval) {
        if (s != null && s != "") {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException var3) {
                return defval;
            }
        } else {
            return defval;
        }
    }

    public static int getInt(String s) {
        if (s != null && s != "") {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException var2) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static int getInt(String s, Integer df) {
        if (s != null && s != "") {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException var3) {
                return 0;
            }
        } else {
            return df.intValue();
        }
    }

    public static Integer[] getInts(String[] s) {
        Integer[] integer = new Integer[s.length];
        for (int i = 0; i < s.length; ++i) {
		    integer[i] = Integer.valueOf(Integer.parseInt(s[i]));
		}
		return integer;
    }

    public static double getDouble(String s, double defval) {
        if (s != null && s != "") {
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException var4) {
                return defval;
            }
        } else {
            return defval;
        }
    }

    public static double getDou(Double s, double defval) {
        return s == null ? defval : s.doubleValue();
    }

    public static Short getShort(String s) {
        return isNotEmpty(s) ? Short.valueOf(Short.parseShort(s)) : null;
    }

    public static int getInt(Object object, int defval) {
        if (isEmpty(object)) {
            return defval;
        } else {
            try {
                return Integer.parseInt(object.toString());
            } catch (NumberFormatException var3) {
                return defval;
            }
        }
    }

    public static int getInt(BigDecimal s, int defval) {
        return s == null ? defval : s.intValue();
    }

    public static Integer[] getIntegerArry(String[] object) {
        int len = object.length;
        Integer[] result = new Integer[len];

        try {
            for (int e = 0; e < len; ++e) {
                result[e] = new Integer(object[e].trim());
            }

            return result;
        } catch (NumberFormatException var4) {
            return null;
        }
    }

    public static String getString(String s) {
        return getString(s, "");
    }

    public static String getString(Object object) {
        return isEmpty(object) ? "" : object.toString().trim();
    }

    public static String getString(int i) {
        return String.valueOf(i);
    }

    public static String getString(float i) {
        return String.valueOf(i);
    }

    public static String getString(String s, String defval) {
        return isEmpty(s) ? defval : s.trim();
    }

    public static String getString(Object s, String defval) {
        return isEmpty(s) ? defval : s.toString().trim();
    }

    public static long stringToLong(String str) {
        Long test = new Long(0L);

        try {
            test = Long.valueOf(str);
        } catch (Exception var3) {
            ;
        }

        return test.longValue();
    }

    public static String getIp() {
        String ip = null;

        try {
            InetAddress e = InetAddress.getLocalHost();
            ip = e.getHostAddress();
        } catch (UnknownHostException var2) {
            var2.printStackTrace();
        }

        return ip;
    }

    public static String getIpAddrByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")){
            return "127.0.0.1";
        }
        return ip;
    }

    public static String getRealIp() throws SocketException {
        String localip = null;
        String netip = null;
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;

        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();

            while (address.hasMoreElements()) {
                ip = (InetAddress) address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                }

                if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    localip = ip.getHostAddress();
                }
            }
        }

        return netip != null && !"".equals(netip) ? netip : localip;
    }


    public static boolean isIn(String substring, String[] source) {
        if (source != null && source.length != 0) {
            for (int i = 0; i < source.length; ++i) {
                String aSource = source[i];
                if (aSource.equals(substring)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }


 


    @SuppressWarnings("unused")
	private static long getIpNum(final String ipAddress) {
        String[] ip = ipAddress.split("\\.");
        long a = (long) Integer.parseInt(ip[0]);
        long b = (long) Integer.parseInt(ip[1]);
        long c = (long) Integer.parseInt(ip[2]);
        long d = (long) Integer.parseInt(ip[3]);
        long ipNum = a * 256L * 256L * 256L + b * 256L * 256L + c * 256L + d;
        return ipNum;
    }

    @SuppressWarnings("unused")
	private static boolean isInner(long userIp, long begin, long end) {
        return userIp >= begin && userIp <= end;
    }

    public static String[] formatArray(String[] arr) {
        StringBuffer sb = new StringBuffer();
        String[] var5 = arr;
        int var4 = arr.length;

        for (int var3 = 0; var3 < var4; ++var3) {
            String k = var5[var3];
            if (k != null && !"null".equalsIgnoreCase(k) && k.length() > 0) {
                sb.append(k).append(",");
            }
        }

        return sb.toString().split(",");
    }

    public static String[] removeElement(String[] array, String str) {
        if (array != null && array.length > 0 && isNotEmpty(str)) {
            LinkedList<Object> list = new LinkedList<Object>();
            for (int i = 0; i < array.length; ++i) {
                if (!str.equals(array[i])) {
                    list.add(array[i]);
                }
            }
            array = (String[]) list.toArray(new String[list.size()]);
        }
        return array;
    }

    /**
     * 数组中是否包含某个元素
     * @param array
     * @param str
     * @return
     */
    public static boolean containsElement(String[] array, String str) {
        for (String s:array){
            if (s.equals(str)){
                return true;
            }
        }
        return false;
    }
}
