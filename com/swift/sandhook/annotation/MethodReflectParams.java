package com.swift.sandhook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface MethodReflectParams {
  public static final String BOOLEAN = "boolean";
  
  public static final String BYTE = "byte";
  
  public static final String CHAR = "char";
  
  public static final String DOUBLE = "double";
  
  public static final String FLOAT = "float";
  
  public static final String INT = "int";
  
  public static final String LONG = "long";
  
  public static final String SHORT = "short";
  
  String[] value();
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\annotation\MethodReflectParams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */