package org.loong.common.utils;

import java.util.Collection;
import java.util.Iterator;

/**
 * 字符串处理工具
 * @author 83624
 *
 */
public class StringUtils {

	/**
	 * 判断字符串是否为空
	 * @param arg
	 * @return
	 */
	public static boolean isEmpty(String arg){
		return arg==null?true:(arg.trim().equals("")?true:false);
	}

	/**
	 * 将对象转换成字符串，如果为空返回空字符串
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * 将对象转换成那个字符串，如果为空返回默认值
	 * @param obj
	 * @param def 默认值
	 * @return
	 */
	public static String toString(Object obj, String def) {
		return obj == null ? def : obj.toString();
	}
	
	/**
	 * 将对象转换成Long类型
	 * @param obj
	 * @param def 默认值
	 * @return
	 */
	public static Long toLong(Object obj, Long def) {
		Long rs = def;
		try{
			rs = obj == null ? def : Long.parseLong(obj.toString());
		}catch (Exception e) {
			rs = def;
		}
		return rs;
	}
	
	/**
	 * 将集合转换成字符串，用separator分割
	 * @param collection
	 * @param separator 分隔符
	 * @return
	 */
	public static String join(Collection<?> collection, String separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }
	
	/**
	 * 将集合转换成字符串，用separator分割
	 * @param collection
	 * @param separator 分隔符
	 * @return
	 */
	public static String join(Iterator<?> iterator, String separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return toString(first);
        }

        // two or more elements
        StringBuffer buf = new StringBuffer(256); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

	/**
	 * 字符串转整型，如果为空或格式不正确，则返回默认值
	 * @param obj
	 * @param def
	 * @return
	 */
	public static int toInt(Object obj, int def) {
		int rs = def;
		try{
			rs = obj == null ? def : Integer.parseInt(obj.toString());
		}catch (Exception e) {
			rs = def;
		}
		return rs;
	}
}
