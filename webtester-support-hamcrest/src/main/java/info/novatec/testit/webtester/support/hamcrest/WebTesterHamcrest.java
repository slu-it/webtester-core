package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.Matcher;

import info.novatec.testit.webtester.api.pageobjects.traits.HasValue;
import info.novatec.testit.webtester.support.hamcrest.matchers.HasValueMatcher;


public final class WebTesterHamcrest {

    public static <T extends HasValue> Matcher<T> hasValue(String value){
        return new HasValueMatcher<>(value);
    }

    private WebTesterHamcrest() {
        // utility constructor
    }

}
