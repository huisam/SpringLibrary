package com.huisam.springstudy.aws;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;


class S3RepositoryTest {

    private final S3Repository s3Repository = new S3Repository();


    @BeforeEach
    void setUp() {
        s3Repository.init();
    }

    @RepeatedTest(value = 1)
    @DisplayName("s3에 제대로 저장하고 꺼내오는지 확인")
    void test_s3() {
        /* given */
        final String id = "test";
        final String object = "huisam";

        /* when */
        s3Repository.save(object, id);
        final String result = s3Repository.findById(id);

        /* then */
        assertThat(result).isEqualTo(object);
    }

    @AfterEach
    void tearDown() {
        s3Repository.deleteAll();
    }
}