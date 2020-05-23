package com.huisam.springstudy.aws;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000",
})
class ProductRepositoryTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Product.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);

    }

    @AfterEach
    void tearDown() {
        TableUtils.deleteTableIfExists(amazonDynamoDB, dynamoDBMapper.generateDeleteTableRequest(Product.class));
    }

    @Test
    @DisplayName("실제로 AWS에 저장되는지 테스트")
    void save_test() {
        /* given */
        Product product = Product.builder()
                .name("computer")
                .price(1000)
                .description("hi from mac")
                .build();

        /* when */
        repository.save(product);

        /* then */
        final Optional<Product> computer = repository.findById("computer");
        assertThat(computer.get()).isEqualTo(product);

    }
}