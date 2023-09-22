package com.post.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.post.interfaces.Sender;
import com.post.interfaces.Serialized;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.HttpHostConnectException;
import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
@Slf4j
public class ApacheKafkaAPI implements Sender {
    @Override
    public void postRequest(String jsonMessage, String endpoint) throws IOException {

        String baseURI = "http://localhost:8080/api/v1/reports/";
        String kafkaAPI = baseURI + endpoint;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(kafkaAPI);
            httpPost.setHeader("Content-Type", "application/json; utf-8");


            StringEntity stringEntity = new StringEntity(jsonMessage);
            httpPost.setEntity(stringEntity);


            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int httpResponseCode = response.getCode();
                if (httpResponseCode == 200)
                    log.info("Response code: " + httpResponseCode + " - POST request was successful");

            } catch (HttpResponseException | HttpHostConnectException e) {
                log.error("POST request was unsuccessful for reason -> " + e.getMessage(), e.getCause());
            }
        }
    }

    @Override
    public String serializeToJSON(Serialized dto) {
        String jsonInputString = "";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            jsonInputString = objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.getMessage();
        }
        return jsonInputString;
    }
}
