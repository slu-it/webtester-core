package info.novatec.testit.webtester.internal.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Describes a possible optimization using Java 8 features. These optimizations
 * should be implemented as soon as WebTester support for Java 7 ends.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD })
public @interface Java8FeaturePossibility {
    String value();
}
