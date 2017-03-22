package util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {FIELD, LOCAL_VARIABLE})
public @interface Verification {

    String createdBy() default "Marcus Schilling";

    Priority priority() default Priority.HIGH;

    public enum Priority {
        LOW, HIGH
    }

}
