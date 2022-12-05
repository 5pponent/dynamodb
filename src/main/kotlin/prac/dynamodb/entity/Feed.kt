package prac.dynamodb.entity

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

@DynamoDbBean
data class Feed(
    @get:DynamoDbPartitionKey
    @get:DynamoDbAttribute("feed_id")
    var id: String = "",

    var content: String = "",
) {
}