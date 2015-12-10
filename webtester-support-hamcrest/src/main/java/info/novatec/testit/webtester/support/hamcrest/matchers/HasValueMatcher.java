package info.novatec.testit.webtester.support.hamcrest.matchers;

import java.util.Objects;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import info.novatec.testit.webtester.api.pageobjects.traits.HasValue;


public class HasValueMatcher<T extends HasValue> extends TypeSafeMatcher<T> {

    private final String value;

    public HasValueMatcher(String value) {
        this.value = value;
    }

    @Override
    protected boolean matchesSafely(HasValue hasValue) {
        return Objects.equals(hasValue.getValue(), value);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("value to be '" + value + '\'');
    }

    @Override
    protected void describeMismatchSafely(HasValue hasValue, Description mismatchDescription) {
        mismatchDescription.appendText("was '" + hasValue.getValue() + '\'');
    }

}
