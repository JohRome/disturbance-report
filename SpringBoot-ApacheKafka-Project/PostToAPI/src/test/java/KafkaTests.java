import com.post.api.ApacheKafkaAPI;
import com.post.consumer.ConsoleConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static com.utils.JSONFormatter.prettyJSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes= {ConsoleConsumer.class})
public class KafkaTests {

    private ApacheKafkaAPI apacheKafkaAPITest;
    private ConsoleConsumer consumerTest;

    @Test
    void postRequestShouldReturn200() throws IOException {

        //given
        apacheKafkaAPITest = new ApacheKafkaAPI();
        String jsonMessage = "\"id: 1, name: \"Test\", address: \"Test Strase 1\"\"";
        String endpoint = "publish";

        //when
        apacheKafkaAPITest.postRequest(jsonMessage, endpoint);

        //then
        assertEquals(200, apacheKafkaAPITest.getHttpResponseCode());
    }

    @Test
    void consumerShouldPrintAllMessagesInTopic() {

        // given
        consumerTest = new ConsoleConsumer();
        String[] expectedMessage = consumerTest.getMessage();

        // when
        consumerTest.printAllMessagesInTopic("disturbance-reports", "all-messages");
        String[] actualMessage = consumerTest.getMessage();

        // then
        assertEquals(expectedMessage[0], actualMessage[0]);
        assertEquals(expectedMessage.length, actualMessage.length);
        
        System.out.println("TESTS IF THE MESSAGE IS THE SAME");
        for (int i = 0; i < expectedMessage.length; i++) {
            System.out.println(expectedMessage[i]);
            System.out.println(actualMessage[i]);
        }
    }
}
