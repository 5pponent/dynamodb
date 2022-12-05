package prac.dynamodb.repository

import org.springframework.stereotype.Repository
import prac.dynamodb.entity.User
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.CreateTableEnhancedRequest
import software.amazon.awssdk.services.dynamodb.model.*
import java.lang.Exception
import javax.annotation.PostConstruct
import kotlin.streams.toList

@Repository
class UserRepository(private val dynamoDbEnhancedClient: DynamoDbEnhancedClient) {

    @PostConstruct
    fun tableInitializer() {
        val createTableRequest = CreateTableEnhancedRequest.builder()
            .provisionedThroughput(
                ProvisionedThroughput.builder()
                    .readCapacityUnits(10L) // RCU
                    .writeCapacityUnits(10L) // WCU
                    .build()
            )
            .build()
        try {
            table().deleteTable()
            table().createTable(createTableRequest)
        }
        catch (e: Exception) { println(e.message) }
    }

    // User 클래스와 DynamoDB 테이블 매핑
    private fun table(): DynamoDbTable<User> =
        dynamoDbEnhancedClient.table("user", TableSchema.fromBean(User::class.java))

    /**
     * @param item 해당 아이템의 Insert 수행, Partition Key 중복 시 Replace 수행
     */
    fun save(item: User) = table().putItem(item.beforeSave())

    /**
     * @param item 해당 아이템의 Insert 수행, Partition Key가 동일하면 Update 수행
     */
    fun update(item: User): User = table().updateItem(item)

    /**
     * @param item 해당 아이템을 삭제 후 반환
     */
    fun delete(item: User): User = table().deleteItem(item)

    /**
     * @param id 해당 Partition Key를 가진 데이터를 삭제 후 반환
     */
    fun deleteByPk(id: String): User = table().deleteItem(Key.builder().partitionValue(id).build())

    fun findAll(): List<User> = table().scan().items().stream().toList()
}