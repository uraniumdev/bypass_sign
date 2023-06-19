package com.swift.sandhook.blacklist;

import com.swift.sandhook.SandHookConfig;
import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Set;

public class HookBlackList {
  public static Set<Class> classBlackList;
  
  public static Set<String> methodBlackList = new HashSet();
  
  public static Set<String> methodUseInHookBridge;
  
  public static Set<String> methodUseInHookStub;
  
  static  {
    classBlackList = new HashSet();
    methodUseInHookBridge = new HashSet();
    methodUseInHookStub = new HashSet();
    methodBlackList.add("java.lang.reflect.Method.invoke");
    methodBlackList.add("java.lang.reflect.AccessibleObject.setAccessible");
    methodUseInHookBridge.add("java.lang.Class.getDeclaredField");
    methodUseInHookBridge.add("java.lang.reflect.InvocationTargetException.getCause");
    methodUseInHookStub.add("java.lang.Object.equals");
    methodUseInHookStub.add("java.lang.Class.isPrimitive");
  }
  
  public static final boolean canNotHook(Member paramMember) {
    if (classBlackList.contains(paramMember.getDeclaringClass()))
      return true; 
    String str = paramMember.getDeclaringClass().getName() + "." + paramMember.getName();
    return methodBlackList.contains(str);
  }
  
  public static final boolean canNotHookByBridge(Member paramMember) {
    String str = paramMember.getDeclaringClass().getName() + "." + paramMember.getName();
    return methodUseInHookBridge.contains(str);
  }
  
  public static final boolean canNotHookByStub(Member paramMember) {
    if (SandHookConfig.SDK_INT >= 29 && Thread.class.equals(paramMember.getDeclaringClass()))
      return true; 
    String str = paramMember.getDeclaringClass().getName() + "." + paramMember.getName();
    return methodUseInHookStub.contains(str);
  }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\blacklist\HookBlackList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */