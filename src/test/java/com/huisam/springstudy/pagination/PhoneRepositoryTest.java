package com.huisam.springstudy.pagination;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PhoneRepositoryTest {

    @Autowired
    private PhoneRepository phoneRepository;

    @BeforeEach
    void setUp() {
        final List<Phone> phones = List.of(new Phone(1L, "애플폰", 3000), new Phone(2L, "삼성폰", 4000),
                new Phone(3L, "엘지폰", 5000), new Phone(4L, "중국폰", 1000));
        phoneRepository.saveAll(phones);
    }

    @AfterEach
    void tearDown() {
        phoneRepository.deleteAll();
    }

    @Test
    @DisplayName("Paging이 올바르게 되는지 테스트")
    void paging_test() {
        /* given */
        final PageRequest pageRequest = PageRequest.of(0, 4);
        /* when */
        final List<Phone> pages = phoneRepository.findAll(pageRequest).getContent();
        /* then */
        assertThat(pages).extracting("name")
                .containsExactly("애플폰", "삼성폰", "엘지폰", "중국폰");
    }
}