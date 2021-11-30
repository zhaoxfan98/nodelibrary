package com.bjpowernode.util;

import java.lang.reflect.Field;

/**
 * Created on 2021/11/30.
 *
 * @author zhaoxfan
 */
public class BeanUtil {
    /**
     * ��������ֵ����
     * @param origin
     * @param dest
     */
    public static void populate(Object origin, Object dest) {
        //�������������
        //�ж����������Ƿ���ͬһ����
        try {
            if(origin.getClass() != dest.getClass()) {
                throw new RuntimeException("�����������Ϊ��ͬ����");
            }
            Class<?> clazz = origin.getClass();
            //��ȡorigin�е�����
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                //�ų�UID
                if ("serialVersionUID".equals(f.getName())) {
                    continue;
                }
                //���Ʒ�װ
                f.setAccessible(true);
                //��dest�������ҵ���Ӧ������ֵ  Ȼ��ֵ��origin��Ӧ��������
                f.set(origin, f.get(dest));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
