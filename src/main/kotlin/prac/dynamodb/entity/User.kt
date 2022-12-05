package prac.dynamodb.entity

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey
import java.sql.Timestamp

@DynamoDbBean
data class User(
    @get:DynamoDbPartitionKey
    @get:DynamoDbAttribute("user_id")
    var id: String = "",

    @get:DynamoDbSortKey
    var name: String = "",

    var timestamp: String = ""
) {

    fun beforeSave(): User {
        this.timestamp = Timestamp(System.currentTimeMillis()).toString()
        return this
    }
}