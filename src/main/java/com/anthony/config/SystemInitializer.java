package com.anthony.config;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
public class SystemInitializer {
    private static SystemInitializer instance = new SystemInitializer();

    private SystemInitializer() {
        loadParameter();
    }

    public static SystemInitializer getInstance() {
        return instance;
    }


    public Properties loadParameter() {

        final String propertiesFileName = "pdc.properties";
        Properties properties = new Properties();
        System.out.println("开始读取配置文件:" + propertiesFileName);
        try (FileInputStream in = new FileInputStream(propertiesFileName)) {
            properties.load(in);
            System.out.println("读取成功，准备解析");
            Iterator it = properties.entrySet().iterator();
            HashMap<String, String> proMap = new HashMap<>();
//转换properties到map
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String methodName = entry.getKey().toString().substring(0, 1).toUpperCase() + entry.getKey().toString().substring(1);
                methodName = "set" + methodName;
                proMap.put(methodName, entry.getValue().toString());
            }

            //遍历参数类的方法 调用set方法
            Class<?> classInfo = SystemConfigParameter.class;
            Method[] methods = classInfo.getMethods();
            for (int i = 0; i != methods.length; ++i) {
                Class<?> para[] = methods[i].getParameterTypes();
                String methodName = methods[i].getName();
                if ("wait".equals(methodName) || "equals".equals(methodName))
                    continue;
                if (1 == para.length) {
                    Class<?> paraClass = para[0];

                    Constructor<?> constructor = paraClass.getConstructor(String.class);
                    Object[] paramter = new Object[1];
                    paramter[0] = constructor.newInstance(proMap.get(methodName));
                    methods[i].invoke(SystemConfigParameter.getInstance(), paramter);
                } else {
                    continue;
                }
            }
//
        } catch (FileNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("配置文件文件不存在，创建默认配置文件");
            createPropertiesFile(propertiesFileName);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private void createPropertiesFile(String propertiesFileName) {
        File file = new File(propertiesFileName);
        try {
            System.out.println("开始创建配置文件");
            file.createNewFile();
            System.out.println("配置文件创建成功，准备当前默认配置");
            HashMap<String, String> properties = getCurrentProperties();
            System.out.println("写入当前配置到配置文件中");
            FileWriter fileWriter = new FileWriter(file);
            for (Map.Entry<String, String> e : properties.entrySet()) {
                fileWriter.write(e.getKey() + "=" + e.getValue());
            }
            fileWriter.close();
            System.out.println("写入完成");
        } catch (IOException e) {
            System.out.println("配置文件创建失败");
            e.printStackTrace();
        }
    }

    private HashMap<String, String> getCurrentProperties() {
        HashMap<String, String> properties = new HashMap<>();
        Class<?> classInfo = SystemConfigParameter.class;

        Field[] field = classInfo.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            String attrName = field[i].getName();
            if ("instance".equals(attrName))
                continue;
            //首字母大写
//            char[] cs = attrName.toCharArray();
//            cs[0] -= 32;
//            String attrNameUp = String.valueOf(cs);
            String attrNameUp = attrName.substring(0, 1).toUpperCase() + attrName.substring(1);
            String methodName = "get" + attrNameUp;
            Method method;
            try {
                method = classInfo.getMethod(methodName);
                System.out.println("调用" + methodName);
                properties.put(attrName, method.invoke(SystemConfigParameter.getInstance()).toString());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    public void showParaMeter() {
        Class<?> classInfo = SystemConfigParameter.class;
        Method[] methods = classInfo.getMethods();
        for (int i = 0; i != methods.length; ++i) {
            Class<?> para[] = methods[i].getParameterTypes();
            String methodName = methods[i].getName();
            System.out.println(methodName);
//            if("wait".equals(methodName)||"equals".equals(methodName))
//                continue;
//            if (0 == para.length) {
//                Class<?> paraClass = para[0];
//
//                Constructor<?> constructor = paraClass.getConstructor(String.class);
//                Object[] paramter=new Object[1];
//                paramter[0]=constructor.newInstance(proMap.get(methodName));
//                methods[i].invoke(SystemConfigParameter.getInstance(),paramter);
//        }
//        else{
//            continue;
//
        }
//    catch (FileNotFoundException|NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {
//        System.out.println("配置文件文件不存在，创建默认配置文件");
//        createPropertiesFile(propertiesFileName);
//        e.printStackTrace();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SystemInitializer systemInitializer = SystemInitializer.getInstance();
        System.out.println(SystemConfigParameter.getInstance().getTokenExpireTime());
        systemInitializer.showParaMeter();
    }
}
