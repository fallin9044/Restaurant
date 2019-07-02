package restaurant.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionUtils {

	public static void setAttribute(Object obj,String fieldName,Object fieldValue) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		System.out.println("设置对象中某个属性的值");
		Class clazz = obj.getClass();
		Field fd = clazz.getDeclaredField(fieldName);
		fd.setAccessible(true);
		fd.set(obj, fieldValue);
	}
	
	public static<T> Class<T> getSuperGenericType(Class clazz){  
		System.out.println("获取对象的父类的泛型");
        return getSuperClassGenricType(clazz, 0);  
    }
	
	public static Class getSuperClassGenricType(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}

		if (!(params[index] instanceof Class)) {
			return Object.class;
		}

		return (Class) params[index];
	}

}
