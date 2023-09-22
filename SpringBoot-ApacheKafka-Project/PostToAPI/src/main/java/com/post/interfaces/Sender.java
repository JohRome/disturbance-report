package com.post.interfaces;

import java.io.IOException;
public interface Sender {
    void postRequest(String jsonMessage, String endpoint) throws IOException;
    String serializeToJSON(Serialized dto);
}
