package com.kdjd.nn.base.filter;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerialContext;

public class SimpleTailorFilter implements PropertyPreFilter {

	private final Class<?> clazz;
	private boolean include;
	private final Set<String> includes = new HashSet<String>();
	private final Set<String> excludes = new HashSet<String>();
	private int maxLevel = 0;

	public SimpleTailorFilter(String... properties) {
		this(null, true, properties);
	}

	public SimpleTailorFilter(Class<?> clazz, boolean include, String... properties) {
		super();
		this.clazz = clazz;
		for (String item : properties) {
			if (item != null) {
				if (include) {
					this.includes.add(item);
				} else {
					this.excludes.add(item);
				}
			}
		}
	}

	/**
	 * @since 1.2.9
	 */
	public int getMaxLevel() {
		return maxLevel;
	}

	/**
	 * @since 1.2.9
	 */
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public Set<String> getIncludes() {
		return includes;
	}

	public Set<String> getExcludes() {
		return excludes;
	}

	public boolean apply(JSONSerializer serializer, Object source, String name) {
		if (source == null) {
			return true;
		}

		if (clazz != null && !clazz.isInstance(source)) {
			return true;
		}

		if (this.excludes.contains(name)) {
			return false;
		}

		if (maxLevel > 0) {
			int level = 0;
			SerialContext context = serializer.getContext();
			while (context != null) {
				level++;
				if (level > maxLevel) {
					return false;
				}
				context = context.parent;
			}
		}

		if (includes.size() == 0 || includes.contains(name)) {
			return true;
		}

		return false;
	}

	public boolean isInclude() {
		return include;
	}

	public void setInclude(boolean include) {
		this.include = include;
	}

}
