package com.bytedusk.kuafu.bcel;

import com.bytedusk.kuafu.util.RessCryptoUtils;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FindMethodInvoke {

    public static void main(String args[]) throws IOException {
        Class targetClazz = RessCryptoUtils.class;
        String targetMethodName = "main";

        String classPath = (targetClazz
                .getResource("")+targetClazz.getSimpleName()+".class").substring(6);
        System.out.println(classPath);

        ClassParser classParser = new ClassParser(classPath);
        JavaClass javaClass = classParser.parse();

        //获取方法
        Method javaMethod = Arrays.stream(javaClass.getMethods())
                .filter(method -> method.getName().contains(targetMethodName)).findAny().get();
        //invokestatic用来调用静态方法；invokespecial用来调用私有方法，父类方法(super.)，类构造器方法；
        // invokeinterface调用接口方法；
        // invokedynamic方法动态执行；invokevirtual调用所有虚方法
        Pattern METHOD_CALL_PATTERN = Pattern.compile("invoke(static|special|virtual)\t(\\S+)");

        Map<String, String> callerMap = new HashMap<>();
        if (javaMethod.isAbstract() || javaMethod.isNative()) {
            // No code to consider
            return;
        }

        String code = javaMethod.getCode().toString();
        Matcher matcher = METHOD_CALL_PATTERN.matcher(code);
        while (matcher.find()) {
            String completMethod = Arrays.stream(matcher.group().split("\t")).skip(1).findAny().get();
            callerMap.put(completMethod.substring(completMethod.lastIndexOf(".")+1),
                    completMethod.substring(0, completMethod.lastIndexOf(".")));
        }
        callerMap.forEach((k,v)->System.out.println(k+":"+v));

        //字符串类与方法转换为实际类与方法对象
        Map<java.lang.reflect.Method, Class> methodClassMap = new HashMap<>();
        callerMap.forEach((k,v)->{
            Class clazz = null;
            java.lang.reflect.Method method = null;
            try {
                if(!(k.contains("<")||k.contains(">"))){
                    clazz = Class.forName(v);
                    method = Arrays.stream(clazz.getDeclaredMethods())
                            .filter(method1 -> method1.getName().contains(k))
                            .findAny().get();
                    methodClassMap.put(method, clazz);
                }
            } catch (Exception e) {
                System.out.println(v+":"+k);
                e.printStackTrace();
            }

        });

        //生成mock代码
        methodClassMap.forEach((k,v)->{
            System.out.print("Mockito.doNothing().when("+v+")."+k.getName()+"(");
            String methodParam = Arrays.stream(new String[k.getParameterCount()])
                    .map(x->"Mock.any()").collect(Collectors.joining(","));
            System.out.println(methodParam+");");
        });
    }
}
