package com.huisam.springstudy.xmlunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.ElementSelectors;
import org.xmlunit.matchers.CompareMatcher;

import static org.hamcrest.MatcherAssert.assertThat;

public class XmlUnitTest {
    @Test
    @DisplayName("xml이 같은지 실패하는 테스트")
    void xml_failure_unit_test() {
        /* given */
        String expectXml = "<a> <b>gogo</b> <c>123</c> </a>";
        String actualXml = "<a>  <c><ab>123</ab></c>  <b>gogo</b> </a>";
        /* then */
        assertThat(expectXml, CompareMatcher.isSimilarTo(actualXml)
                .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
                .ignoreWhitespace());
    }

    @Test
    @DisplayName("xml이 같은지 성공하는 테스트")
    void xml_success_unit_test() {
        /* given */
        String expectXml = "<a> <b>gogo</b> <c>123</c> </a>";
        String actualXml = "<a>  <c>123</c>  <b>gogo</b> </a>";
        /* then */
        assertThat(expectXml, CompareMatcher.isSimilarTo(actualXml)
                .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
                .ignoreWhitespace());
    }
}
