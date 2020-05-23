package com.huisam.springstudy.aws;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "name")
@DynamoDBTable(tableName = "Product")
public class Product {

    @Id
    @DynamoDBHashKey
    private String name;

    @DynamoDBRangeKey
    private Integer price;

    @DynamoDBAttribute
    private String description;


}
