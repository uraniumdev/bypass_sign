package com.swift.sandhook.wrapper;

import android.text.TextUtils;
import com.swift.sandhook.SandHook;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.HookReflectClass;
import com.swift.sandhook.annotation.MethodParams;
import com.swift.sandhook.annotation.MethodReflectParams;
import com.swift.sandhook.annotation.Param;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HookWrapper {
  public static void addHookClass(ClassLoader paramClassLoader, Class<?> paramClass) throws HookErrorException {
    Class clazz = getTargetHookClass(paramClassLoader, paramClass);
    if (clazz != null) {
      Map map = getHookMethods(paramClassLoader, clazz, paramClass);
      try {
        fillBackupMethod(paramClassLoader, paramClass, map);
        iterator = map.values().iterator();
        return;
      } finally {
        paramClassLoader = null;
      } 
    } 
    throw new HookErrorException("error hook wrapper class :" + paramClass.getName());
  }
  
  public static void addHookClass(ClassLoader paramClassLoader, Class<?>... paramVarArgs) throws HookErrorException {
    int i = paramVarArgs.length;
    for (byte b = 0; b < i; b++)
      addHookClass(paramClassLoader, paramVarArgs[b]); 
  }
  
  public static void addHookClass(Class<?>... paramVarArgs) throws HookErrorException { addHookClass(null, paramVarArgs); }
  
  public static void checkSignature(Member paramMember, Method paramMethod, Class[] paramArrayOfClass) throws HookErrorException {
    if (Modifier.isStatic(paramMethod.getModifiers())) {
      if (paramMember instanceof java.lang.reflect.Constructor) {
        if (!paramMethod.getReturnType().equals(void.class))
          throw new HookErrorException("error return type! - " + paramMethod.getName()); 
      } else if (paramMember instanceof Method) {
        Class clazz = ((Method)paramMember).getReturnType();
        if (clazz != paramMethod.getReturnType() && !clazz.isAssignableFrom(clazz))
          throw new HookErrorException("error return type! - " + paramMethod.getName()); 
      } 
      Class[] arrayOfClass2 = paramMethod.getParameterTypes();
      Class[] arrayOfClass1 = arrayOfClass2;
      if (arrayOfClass2 == null)
        arrayOfClass1 = new Class[0]; 
      arrayOfClass2 = paramArrayOfClass;
      if (paramArrayOfClass == null)
        arrayOfClass2 = new Class[0]; 
      if (arrayOfClass2.length == 0 && arrayOfClass1.length == 0)
        return; 
      byte b1 = 0;
      if (!Modifier.isStatic(paramMember.getModifiers())) {
        b1 = 1;
        if (arrayOfClass1.length != 0) {
          if (arrayOfClass1[false] == paramMember.getDeclaringClass() || arrayOfClass1[0].isAssignableFrom(paramMember.getDeclaringClass())) {
            if (arrayOfClass1.length != arrayOfClass2.length + 1)
              throw new HookErrorException("hook method pars must match the origin method! " + paramMethod.getName()); 
          } else {
            throw new HookErrorException("first par must be this! " + paramMethod.getName());
          } 
        } else {
          throw new HookErrorException("first par must be this! " + paramMethod.getName());
        } 
      } else if (arrayOfClass1.length != arrayOfClass2.length) {
        throw new HookErrorException("hook method pars must match the origin method! " + paramMethod.getName());
      } 
      byte b2 = 0;
      while (b2 < arrayOfClass2.length) {
        if (arrayOfClass1[b2 + b1] == arrayOfClass2[b2] || arrayOfClass1[b2 + b1].isAssignableFrom(arrayOfClass2[b2])) {
          b2++;
          continue;
        } 
        throw new HookErrorException("hook method pars must match the origin method! " + paramMethod.getName());
      } 
      return;
    } 
    throw new HookErrorException("hook method must static! - " + paramMethod.getName());
  }
  
  private static Class classNameToClass(String paramString, ClassLoader paramClassLoader) throws ClassNotFoundException {
    byte b;
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 109413500:
        if (paramString.equals("short")) {
          b = 7;
          break;
        } 
      case 97526364:
        if (paramString.equals("float")) {
          b = 4;
          break;
        } 
      case 64711720:
        if (paramString.equals("boolean")) {
          b = 0;
          break;
        } 
      case 3327612:
        if (paramString.equals("long")) {
          b = 6;
          break;
        } 
      case 3052374:
        if (paramString.equals("char")) {
          b = 2;
          break;
        } 
      case 3039496:
        if (paramString.equals("byte")) {
          b = 1;
          break;
        } 
      case 104431:
        if (paramString.equals("int")) {
          b = 5;
          break;
        } 
      case -1325958191:
        if (paramString.equals("double")) {
          b = 3;
          break;
        } 
    } 
    switch (b) {
      default:
        if (paramClassLoader == null)
          return Class.forName(paramString); 
        break;
      case 7:
        return short.class;
      case 6:
        return long.class;
      case 5:
        return int.class;
      case 4:
        return float.class;
      case 3:
        return double.class;
      case 2:
        return char.class;
      case 1:
        return byte.class;
      case 0:
        return boolean.class;
    } 
    return Class.forName(paramString, true, paramClassLoader);
  }
  
  private static void fillBackupMethod(ClassLoader paramClassLoader, Class<?> paramClass, Map<Member, HookEntity> paramMap) {
    Class<?> clazz = null;
    try {
      Field[] arrayOfField = paramClass.getDeclaredFields();
    } finally {
      paramClass = null;
    } 
    if (paramMap.isEmpty())
      return; 
    int i = paramClass.length;
    for (byte b = 0; b < i; b++) {
      Class<?> clazz1 = paramClass[b];
      if (Modifier.isStatic(clazz1.getModifiers())) {
        HookMethodBackup hookMethodBackup = (HookMethodBackup)clazz1.getAnnotation(HookMethodBackup.class);
        if (hookMethodBackup != null)
          for (HookEntity hookEntity : paramMap.values()) {
            if (hookEntity.isCtor()) {
              str = "<init>";
            } else {
              str = hookEntity.target.getName();
            } 
            if (TextUtils.equals(str, hookMethodBackup.value()) && samePars(paramClassLoader, clazz1, hookEntity.pars)) {
              clazz1.setAccessible(true);
              if (hookEntity.backup == null) {
                hookEntity.backup = StubMethodsFactory.getStubMethod();
                hookEntity.hookIsStub = true;
                hookEntity.resolveDexCache = false;
              } 
              if (hookEntity.backup == null)
                continue; 
              try {
                if (clazz1.getType() == Method.class) {
                  clazz1.set(null, hookEntity.backup);
                  continue;
                } 
                if (clazz1.getType() == HookEntity.class)
                  clazz1.set(null, hookEntity); 
              } catch (IllegalAccessException str) {
                str.printStackTrace();
              } 
            } 
          }  
      } 
    } 
  }
  
  private static Map<Member, HookEntity> getHookMethods(ClassLoader paramClassLoader, Class paramClass1, Class<?> paramClass2) throws HookErrorException {
    HashMap hashMap = new HashMap();
    Class<?> clazz = null;
    try {
      Method[] arrayOfMethod = paramClass2.getDeclaredMethods();
    } finally {
      paramClass2 = null;
    } 
    throw new HookErrorException("error hook wrapper class :" + paramClass1.getName());
  }
  
  private static int getParsCount(Method paramMethod) {
    int i;
    Class[] arrayOfClass = paramMethod.getParameterTypes();
    if (arrayOfClass == null) {
      i = 0;
    } else {
      i = arrayOfClass.length;
    } 
    return i;
  }
  
  private static Class getRealParType(ClassLoader paramClassLoader, Class paramClass, Annotation[] paramArrayOfAnnotation, boolean paramBoolean) throws Exception {
    if (paramArrayOfAnnotation == null || paramArrayOfAnnotation.length == 0)
      return paramClass; 
    int i = paramArrayOfAnnotation.length;
    for (byte b = 0; b < i; b++) {
      Annotation annotation = paramArrayOfAnnotation[b];
      if (annotation instanceof Param) {
        Param param = (Param)annotation;
        if (TextUtils.isEmpty(param.value()))
          return paramClass; 
        Class clazz = classNameToClass(param.value(), paramClassLoader);
        if (paramBoolean || clazz.equals(paramClass) || paramClass.isAssignableFrom(clazz))
          return clazz; 
        throw new ClassCastException("hook method par cast error!");
      } 
    } 
    return paramClass;
  }
  
  private static Class getTargetHookClass(ClassLoader paramClassLoader, Class<?> paramClass) {
    HookClass hookClass = (HookClass)paramClass.getAnnotation(HookClass.class);
    HookReflectClass hookReflectClass = (HookReflectClass)paramClass.getAnnotation(HookReflectClass.class);
    if (hookClass != null)
      return hookClass.value(); 
    if (hookReflectClass != null) {
      if (paramClassLoader == null)
        try {
          return Class.forName(hookReflectClass.value());
        } catch (ClassNotFoundException paramClassLoader) {
          return null;
        }  
      return Class.forName(hookReflectClass.value(), true, paramClassLoader);
    } 
    return null;
  }
  
  private static boolean hasThisObject(Method paramMethod) {
    Annotation[][] arrayOfAnnotation = paramMethod.getParameterAnnotations();
    return (arrayOfAnnotation == null || arrayOfAnnotation.length == 0) ? false : isThisObject(arrayOfAnnotation[0]);
  }
  
  private static boolean isThisObject(Annotation[] paramArrayOfAnnotation) {
    if (paramArrayOfAnnotation == null || paramArrayOfAnnotation.length == 0)
      return false; 
    int i = paramArrayOfAnnotation.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfAnnotation[b] instanceof com.swift.sandhook.annotation.ThisObject)
        return true; 
    } 
    return false;
  }
  
  private static Class[] parseMethodPars(ClassLoader paramClassLoader, Field paramField) throws HookErrorException {
    MethodParams methodParams = (MethodParams)paramField.getAnnotation(MethodParams.class);
    MethodReflectParams methodReflectParams = (MethodReflectParams)paramField.getAnnotation(MethodReflectParams.class);
    if (methodParams != null)
      return methodParams.value(); 
    if (methodReflectParams != null) {
      if (methodReflectParams.value().length == 0)
        return null; 
      Class[] arrayOfClass = new Class[methodReflectParams.value().length];
      byte b = 0;
      while (b < methodReflectParams.value().length) {
        try {
          arrayOfClass[b] = classNameToClass(methodReflectParams.value()[b], paramClassLoader);
          b++;
        } catch (ClassNotFoundException paramClassLoader) {
          throw new HookErrorException("hook method pars error: " + paramField.getName(), paramClassLoader);
        } 
      } 
      return arrayOfClass;
    } 
    return null;
  }
  
  private static Class[] parseMethodPars(ClassLoader paramClassLoader, Method paramMethod) throws HookErrorException {
    MethodParams methodParams = (MethodParams)paramMethod.getAnnotation(MethodParams.class);
    MethodReflectParams methodReflectParams = (MethodReflectParams)paramMethod.getAnnotation(MethodReflectParams.class);
    if (methodParams != null)
      return methodParams.value(); 
    if (methodReflectParams != null) {
      if (methodReflectParams.value().length == 0)
        return null; 
      Class[] arrayOfClass = new Class[methodReflectParams.value().length];
      byte b = 0;
      while (b < methodReflectParams.value().length) {
        try {
          arrayOfClass[b] = classNameToClass(methodReflectParams.value()[b], paramClassLoader);
          b++;
        } catch (ClassNotFoundException paramClassLoader) {
          throw new HookErrorException("hook method pars error: " + paramMethod.getName(), paramClassLoader);
        } 
      } 
      return arrayOfClass;
    } 
    return (getParsCount(paramMethod) > 0) ? ((getParsCount(paramMethod) == 1) ? (hasThisObject(paramMethod) ? parseMethodParsNew(paramClassLoader, paramMethod) : null) : parseMethodParsNew(paramClassLoader, paramMethod)) : null;
  }
  
  private static Class[] parseMethodParsNew(ClassLoader paramClassLoader, Method paramMethod) throws HookErrorException {
    Class[] arrayOfClass1 = paramMethod.getParameterTypes();
    if (arrayOfClass1 == null || arrayOfClass1.length == 0)
      return null; 
    Annotation[][] arrayOfAnnotation = paramMethod.getParameterAnnotations();
    Class[] arrayOfClass2 = null;
    byte b1 = 0;
    for (byte b2 = 0;; b2++) {
      Annotation[] arrayOfAnnotation1;
      Class clazz;
      if (b2 < arrayOfAnnotation.length) {
        clazz = arrayOfClass1[b2];
        arrayOfAnnotation1 = arrayOfAnnotation[b2];
        if (b2 == 0) {
          if (isThisObject(arrayOfAnnotation1)) {
            arrayOfClass2 = new Class[arrayOfAnnotation.length - 1];
          } else {
            arrayOfClass2 = new Class[arrayOfAnnotation.length];
            try {
              arrayOfClass2[b1] = getRealParType(paramClassLoader, clazz, arrayOfAnnotation1, paramMethod.isAnnotationPresent(com.swift.sandhook.annotation.SkipParamCheck.class));
              b1++;
              continue;
            } catch (Exception paramClassLoader) {
              throw new HookErrorException("hook method <" + paramMethod.getName() + "> parser pars error", paramClassLoader);
            } 
          } 
          continue;
        } 
      } else {
        break;
      } 
      try {
        arrayOfClass2[b1] = getRealParType(paramClassLoader, clazz, arrayOfAnnotation1, paramMethod.isAnnotationPresent(com.swift.sandhook.annotation.SkipParamCheck.class));
        b1++;
        continue;
      } catch (Exception paramClassLoader) {
        throw new HookErrorException("hook method <" + paramMethod.getName() + "> parser pars error", paramClassLoader);
      } 
    } 
    return arrayOfClass2;
  }
  
  private static boolean samePars(ClassLoader paramClassLoader, Field paramField, Class[] paramArrayOfClass) {
    try {
      Class[] arrayOfClass3 = parseMethodPars(paramClassLoader, paramField);
      if (arrayOfClass3 == null && paramField.isAnnotationPresent(com.swift.sandhook.annotation.SkipParamCheck.class))
        return true; 
      Class[] arrayOfClass1 = paramArrayOfClass;
      if (paramArrayOfClass == null)
        arrayOfClass1 = new Class[0]; 
      Class[] arrayOfClass2 = arrayOfClass3;
      if (arrayOfClass3 == null)
        arrayOfClass2 = new Class[0]; 
      if (arrayOfClass1.length != arrayOfClass2.length)
        return false; 
      for (byte b = 0; b < arrayOfClass1.length; b++) {
        Class clazz1 = arrayOfClass1[b];
        Class clazz2 = arrayOfClass2[b];
        if (clazz1 != clazz2)
          return false; 
      } 
      return true;
    } catch (HookErrorException paramClassLoader) {
      return false;
    } 
  }
  
  public static class HookEntity {
    public Method backup;
    
    public boolean backupIsStub = true;
    
    public Method hook;
    
    public boolean hookIsStub = false;
    
    public int hookMode;
    
    public boolean initClass = true;
    
    public Class[] pars;
    
    public boolean resolveDexCache = true;
    
    public Member target;
    
    public HookEntity(Member param1Member) { this.target = param1Member; }
    
    public HookEntity(Member param1Member, Method param1Method1, Method param1Method2) {
      this.target = param1Member;
      this.hook = param1Method1;
      this.backup = param1Method2;
    }
    
    public HookEntity(Member param1Member, Method param1Method1, Method param1Method2, boolean param1Boolean) {
      this.target = param1Member;
      this.hook = param1Method1;
      this.backup = param1Method2;
      this.resolveDexCache = param1Boolean;
    }
    
    public Object callOrigin(Object param1Object, Object... param1VarArgs) throws Throwable { return SandHook.callOriginMethod(this.backupIsStub, this.target, this.backup, param1Object, param1VarArgs); }
    
    public boolean isCtor() { return this.target instanceof java.lang.reflect.Constructor; }
  }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\wrapper\HookWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */