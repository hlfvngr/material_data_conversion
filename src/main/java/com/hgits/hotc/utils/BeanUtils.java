package com.hgits.hotc.utils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class BeanUtils {

    private static final String DATE_WITH_NANO_PATTERN = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}";

    public static <T> List<T> transferTo(File file, Class<T> clazz) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        List<T> result = new ArrayList<>();

        Map<String, Method> methodMap = getSetMethodFrom(clazz);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

            String fieldListStr = reader.readLine();

            if(fieldListStr == null || fieldListStr.length() == 0) {
                throw new RuntimeException("表头字段为空！");
            }

            String[] fields = fieldListStr.split("\t");

            String line = null;

            while(null != (line = reader.readLine())){
                String[] values = line.split("\t");
                T t = clazz.newInstance();

                for (int i = 0; i < fields.length && i < values.length; i++) {
                    String field = fields[i];
                    String setMethodName = "set" + field.toLowerCase();
                    if(methodMap.containsKey(setMethodName)){
                        Method method = methodMap.get(setMethodName);
                        //此处需要对参数进行处理
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        Object val = null;
                        if(parameterTypes[0].isAssignableFrom(String.class)) {
                            if (values[i] != null && values[i].matches(DATE_WITH_NANO_PATTERN)){
                                val = values[i].substring(0, values[i].indexOf('.'));
                            } else {
                                val = values[i];
                            }
                        } else if (values[i] != null && values[i].length() > 0) {
                            if (parameterTypes[0].isAssignableFrom(Date.class)){
                                val = DateUtils.parse(values[i]);
                            } else if (parameterTypes[0].isAssignableFrom(Byte.class)){
                                val = Byte.valueOf(values[i]);
                            } else if (parameterTypes[0].isAssignableFrom(Short.class)){
                                val = Short.valueOf(values[i]);
                            } else if (parameterTypes[0].isAssignableFrom(Integer.class)){
                                val = Integer.valueOf(values[i]);
                            } else if (parameterTypes[0].isAssignableFrom(Long.class)){
                                val = Long.valueOf(values[i]);
                            }
                        }

                        method.invoke(t, val);
                    }
                }
                result.add(t);
            }
        } catch ( IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }


    public static Map<String, Method> getSetMethodFrom(Class<?> clazz){
        if(clazz == null ) return Collections.emptyMap();

        Map<String, Method> methodMap = new HashMap<>();

        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if(methods[i].getName().startsWith("set")) {
                methodMap.put(methods[i].getName().toLowerCase(), methods[i]);
            }
        }
        return methodMap;
    }

    public static String capitalCamel(String s){
        if(s == null || s.length() == 0 || !s.matches("[A-Za-z]\\w*")){
            throw new RuntimeException("字段名不符合要求！");
        }
        char first = s.charAt(0);

        if('a' <= first && first <= 'z') {
            return (char)(first - 32) + s.substring(1);
        }
        return s;
    }
}
