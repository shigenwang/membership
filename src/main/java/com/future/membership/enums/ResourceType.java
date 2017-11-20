package com.future.membership.enums;

public enum ResourceType {
	menu("菜单"), button("按钮");

    private final String info;
    private ResourceType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
