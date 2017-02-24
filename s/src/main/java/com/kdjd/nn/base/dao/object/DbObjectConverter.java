package com.kdjd.nn.base.dao.object;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DbObjectConverter {

	/**
	 * bean转为DBObject
	 * 
	 * @param bean
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public static <T> DBObject bean2DBObject(T bean) throws IllegalArgumentException, IllegalAccessException {
		if (bean == null) {
			return null;
		}
		DBObject dbObject = new BasicDBObject();
		// 获取对象对应类中的所有属性域
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			// 获取属性名
			String varName = field.getName();
			// 修改访问控制权限
			boolean accessFlag = field.isAccessible();
			if (!accessFlag) {
				field.setAccessible(true);
			}
			Object param = field.get(bean);
			if (param == null) {
				continue;
			} else if (param instanceof Integer) {// 判断变量的类型
				int value = ((Integer) param).intValue();
				dbObject.put(varName, value);
			} else if (param instanceof String) {
				String value = (String) param;
				dbObject.put(varName, value);
			} else if (param instanceof Double) {
				double value = ((Double) param).doubleValue();
				dbObject.put(varName, value);
			} else if (param instanceof Float) {
				float value = ((Float) param).floatValue();
				dbObject.put(varName, value);
			} else if (param instanceof Long) {
				long value = ((Long) param).longValue();
				dbObject.put(varName, value);
			} else if (param instanceof Boolean) {
				boolean value = ((Boolean) param).booleanValue();
				dbObject.put(varName, value);
			} else if (param instanceof Date) {
				Date value = (Date) param;
				dbObject.put(varName, value);
			} else if (param instanceof List) {
				List<Object> list = (List<Object>) param;
				dbObject.put(varName, list);
			} else if (param instanceof Map) {
				Map<Object, Object> map = (Map<Object, Object>) param;
				dbObject.put(varName, map);
			}
			// 恢复访问控制权限
			field.setAccessible(accessFlag);
		}
		return dbObject;
	}

	/**
	 * 将DBObject转换成Bean对象
	 * 
	 */
	public static <T> T dbObject2Bean(DBObject dbObject, T bean)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (bean == null) { // 测试已通过
			return null;
		}
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			String varName = field.getName();
			Object object = dbObject.get(varName);
			if (object != null) {
				DbObjectConverter.setProperty(bean, varName, object);
			}
		}
		return bean;
	}

	// 取出Mongo中的属性值，为bean赋值
	public static <T> void setProperty(T bean, String varName, T object) {
		varName = varName.substring(0, 1).toUpperCase() + varName.substring(1);
		try {
			String type = object.getClass().getName();
			// 类型为String
			if (type.equals("java.lang.String")) {
				Method m = bean.getClass().getMethod("get" + varName);
				String value = (String) m.invoke(bean);
				if (value == null) {
					m = bean.getClass().getMethod("set" + varName, String.class);
					m.invoke(bean, object);
				}
			}
			// 类型为Integer
			if (type.equals("java.lang.Integer")) {
				Method m = bean.getClass().getMethod("get" + varName);
				String value = (String) m.invoke(bean);
				if (value == null) {
					m = bean.getClass().getMethod("set" + varName, Integer.class);
					m.invoke(bean, object);
				}
			}
			// 类型为Boolean
			if (type.equals("java.lang.Boolean")) {
				Method m = bean.getClass().getMethod("get" + varName);
				String value = (String) m.invoke(bean);
				if (value == null) {
					m = bean.getClass().getMethod("set" + varName, Boolean.class);
					m.invoke(bean, object);
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
