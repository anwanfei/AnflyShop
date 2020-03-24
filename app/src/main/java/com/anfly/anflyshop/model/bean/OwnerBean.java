package com.anfly.anflyshop.model.bean;

public class OwnerBean<T> {
    private String name;
    private int icon;
    private Class<T> aClass;

    public OwnerBean(String name, int icon, Class<T> aClass) {
        this.name = name;
        this.icon = icon;
        this.aClass = aClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class<T> getaClass() {
        return aClass;
    }

    public void setaClass(Class<T> aClass) {
        this.aClass = aClass;
    }
}
