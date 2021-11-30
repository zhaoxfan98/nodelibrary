package com.bjpowernode.util;

import java.lang.reflect.Field;

/**
 * Created on 2021/11/30.
 *
 * @author zhaoxfan
 */
public class BeanUtil {
    /**
     * 对象属性值拷贝
     * @param origin
     * @param dest
     */
    public static void populate(Object origin, Object dest) {
        //反射来解决问题
        //判断两个对象是否是同一类型
        try {
            if(origin.getClass() != dest.getClass()) {
                throw new RuntimeException("两个对象必须为相同类型");
            }
            Class<?> clazz = origin.getClass();
            //获取origin中的属性
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                //排除UID
                if ("serialVersionUID".equals(f.getName())) {
                    continue;
                }
                //打破封装
                f.setAccessible(true);
                //从dest对象中找到对应的属性值  然后赋值到origin相应的属性中
                f.set(origin, f.get(dest));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
