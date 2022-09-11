package com.wen.result;

import java.util.List;

public class Permissions {
    @Override
    public String toString() {
        return "Permissions{" +
                "permissions=" + permissions +
                '}';
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Permissions() {
    }

    public Permissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    private List<Permission> permissions;
}
