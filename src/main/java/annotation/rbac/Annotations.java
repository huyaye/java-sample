package annotation.rbac;

import java.lang.annotation.*;

public class Annotations {
    @Repeatable(PermissionsContainer.class)
    @Target({ElementType.TYPE})
    public @interface Permissions {
        Role role();
        OperationType[] allowed();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface PermissionsContainer {
        Permissions[] value();
    }
}