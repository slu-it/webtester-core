package info.novatec.testit.webtester.utils.converters;

import org.openqa.selenium.By;

import info.novatec.testit.webtester.api.pageobjects.ByConverter;


/**
 * Using the name of the tag of the element.
 *
 * @since 0.9.9
 */
public class TagNameByConverter implements ByConverter {

    @Override
    public By toBy(String value) {
        return By.tagName(value);
    }

    @Override
    public String getValueFormat() {
        return "Tag Name";
    }

}
