package com.swift.sandhook.utils;

import com.swift.sandhook.SandHookConfig;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

public class ClassStatusUtils {
  static Field fieldStatusOfClass;
  
  static  {
    try {
      Field field = Class.class.getDeclaredField("status");
      fieldStatusOfClass = field;
      field.setAccessible(true);
    } catch (NoSuchFieldException noSuchFieldException) {}
  }
  
  public static int getClassStatus(Class paramClass, boolean paramBoolean) {
    byte b;
    if (paramClass == null)
      return 0; 
    int i = 0;
    try {
      b = fieldStatusOfClass.getInt(paramClass);
    } finally {
      paramClass = null;
    } 
    if (paramBoolean)
      i = (int)(toUnsignedLong(b) >> 28); 
    return i;
  }
  
  public static boolean isInitialized(Class paramClass) {
    Field field = fieldStatusOfClass;
    boolean bool1 = true;
    boolean bool2 = true;
    boolean bool3 = true;
    boolean bool = true;
    if (field == null)
      return true; 
    if (SandHookConfig.SDK_INT >= 30) {
      if (getClassStatus(paramClass, true) < 14)
        bool = false; 
      return bool;
    } 
    if (SandHookConfig.SDK_INT >= 28) {
      if (getClassStatus(paramClass, true) == 14) {
        bool = bool1;
      } else {
        bool = false;
      } 
      return bool;
    } 
    if (SandHookConfig.SDK_INT == 27) {
      if (getClassStatus(paramClass, false) == 11) {
        bool = bool2;
      } else {
        bool = false;
      } 
      return bool;
    } 
    if (getClassStatus(paramClass, false) == 10) {
      bool = bool3;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isStaticAndNoInited(Member paramMember) {
    boolean bool1 = paramMember instanceof java.lang.reflect.Method;
    boolean bool2 = false;
    if (!bool1)
      return false; 
    Class clazz = paramMember.getDeclaringClass();
    if (Modifier.isStatic(paramMember.getModifiers()) && !isInitialized(clazz))
      bool2 = true; 
    return bool2;
  }
  
  public static long toUnsignedLong(int paramInt) { return paramInt & 0xFFFFFFFFL; }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhoo\\utils\ClassStatusUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */