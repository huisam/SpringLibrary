package com.huisam.springstudy.xmlunit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.huisam.springstudy.serializable.Payload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.ElementSelectors;
import org.xmlunit.matchers.CompareMatcher;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @DisplayName("xmlMapper Test")
    void xmlMapping_test() throws JsonProcessingException {
        /* given */
        Payload payload = new Payload("hi", "<go>nono</go>");
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(SerializationFeature.CLOSE_CLOSEABLE, true);

        /* when */
        final String string = xmlMapper.writeValueAsString(payload);

        /* then */
        assertThat(string).isEqualTo("<Payload><name>hi</name><data><go>nono</go></data></Payload>");
    }
}
