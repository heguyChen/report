package com.dcseat.report.util;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.*;

public class CollectionUtils {

    public static <T> void copy(List<T> src, List<T> target, String... propertyName) {
        if (src == null || target == null || propertyName.length == 0) return;
        // 目标集合展开
        for (T t : target) {
            // 源数据集合展开
            for (T s_t : src) {
                // 批量配置参数
                for (int i = 0; i < propertyName.length; i++) {
                    String p = propertyName[i];
                    // 对象匹配
                    if (t.equals(s_t)) {
                        try {

                            forceSetFieldValue(s_t, t, p);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    /**
     * 对list集合中的元素按照多个属性名称排序,多个属性的排序方式采用统一方式处理。
     * list元素的属性可以是数字（byte、short、int、long、float、double等，支持正数、负数、0）、
     * char、String、java.util.Date
     *
     * @param list  需要排序的集合
     * @param sortName list集合中需要排序的元素属性名称，动态数组
     * @param isAsc true升序，false降序
     */
    public static <T> void sort(List<T> list, final boolean isAsc, final String... sortName) {
        Collections.sort(list, new Comparator<T>() {
            public int compare(T a, T b) {
                int ret = 0;
                try {
                    for (int i = 0; i < sortName.length; i++) {
                        ret = CollectionUtils.compareObject(sortName[i], isAsc, a, b);
                        if (0 != ret) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ret;
            }
        });
    }

    /**
     * 给list的每个属性都指定是升序还是降序
     * 如果
     * @param list
     * @param sortNameArray  参数数组
     * @param sortDirectionArray      每个属性对应的升降序数组， true升序，false降序
     */
    public static <T> void sort(List<T> list, final String[] sortNameArray, final boolean[] sortDirectionArray) {
        if (sortNameArray.length != sortDirectionArray.length) {
            throw new RuntimeException("属性数组元素个数和升降序数组元素个数不相等");
        }
        Collections.sort(list, new Comparator<T>() {
            public int compare(T t1, T t2) {
                int ret = 0;
                try {
                    //循环比较对象
                    for (int i = 0; i < sortNameArray.length; i++) {
                        ret = compareObject(sortNameArray[i], sortDirectionArray[i], t1, t2);
                        if (0 != ret) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ret;
            }
        });
    }
    /**
     * 对2个对象按照指定属性名称进行排序
     *
     * @param sortName 排序的属性名称
     * @param isAsc true升序，false降序
     * @param t1
     * @param t2
     * @return
     * @throws Exception
     */
    private static <T> int compareObject(final String sortName, final boolean isAsc, T t1, T t2) throws Exception {
        int ret;
        Object value1 = forceGetFieldValue(t1, sortName);
        Object value2 = forceGetFieldValue(t2, sortName);
        String str1 = value1.toString();
        String str2 = value2.toString();
        //为数字类型时转换成指定长度的字符串
        if (value1 instanceof Number && value2 instanceof Number) {
            //获取需要比较的属性值最大长度，保持数据精度
            int maxlen = Math.max(str1.length(), str2.length());
            str1 = addZero2Str((Number) value1, maxlen);
            str2 = addZero2Str((Number) value2, maxlen);
        } else if (value1 instanceof Date && value2 instanceof Date) {
            //把时间转换成数字
            long time1 = ((Date) value1).getTime();
            long time2 = ((Date) value2).getTime();
            //获取需要比较的属性值最大长度，保持数据精度 把时间转换成数字再转换成字符串
            int maxlen = Long.toString(Math.max(time1, time2)).length();
            str1 = addZero2Str(time1, maxlen);
            str2 = addZero2Str(time2, maxlen);
        }
        //排序方式
        if (isAsc) {
            ret = str1.compareTo(str2);
        } else {
            ret = str2.compareTo(str1);
        }
        return ret;
    }

    /**
     * 给数字对象按照指定长度在左侧补0，并转换成字符串。
     *
     * 使用案例: addZero2Str(11,4) 返回 "0011", addZero2Str(-18,6)返回 "-000018"
     *
     * @param numObj 数字对象
     * @param length 指定的长度
     * @return
     */
    public static String addZero2Str(Number numObj, int length) {
        NumberFormat nf = NumberFormat.getInstance();
        // 设置是否使用分组
        nf.setGroupingUsed(false);
        // 设置最大整数位数
        nf.setMaximumIntegerDigits(length);
        // 设置最小整数位数
        nf.setMinimumIntegerDigits(length);
        return nf.format(numObj);
    }

    /**
     * 通过反射方式获取指定对象的指定属性值（去除private,protected的限制）
     *
     * @param obj 属性名称所属的对象
     * @param fieldName 属性名称
     * @return
     * @throws Exception
     */
    public static Object forceGetFieldValue(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        Object object = null;
        boolean accessible = field.isAccessible();
        if (!accessible) {
            // 如果是private,protected修饰的属性，需要修改为可以访问的
            field.setAccessible(true);
            object = field.get(obj);
            // 还原private,protected属性的访问性质
            field.setAccessible(accessible);
            return object;
        }
        object = field.get(obj);
        return object;
    }

    /**
     * 通过反射方式设置指定对象的指定属性值（去除private,protected的限制）
     *
     * @param src       属性来源的对象
     * @param target    属性目标的对象
     * @param fieldName 属性名称
     * @return
     * @throws Exception
     */
    public static void forceSetFieldValue(Object src, Object target, String fieldName) throws Exception {
        Field targetField = target.getClass().getDeclaredField(fieldName);
        Field srcField = src.getClass().getDeclaredField(fieldName);
        boolean accessible = targetField.isAccessible();
        if (!accessible) {
            // 如果是private,protected修饰的属性，需要修改为可以访问的
            targetField.setAccessible(true);
            srcField.setAccessible(true);
            targetField.set(target, srcField.get(src));
            // 还原private,protected属性的访问性质
            targetField.setAccessible(accessible);
            srcField.setAccessible(accessible);
            return;
        }
        targetField.set(target, srcField.get(src));
    }
}
