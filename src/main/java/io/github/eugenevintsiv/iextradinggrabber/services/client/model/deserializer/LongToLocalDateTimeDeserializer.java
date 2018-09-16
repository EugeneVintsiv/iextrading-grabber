package io.github.eugenevintsiv.iextradinggrabber.services.client.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LongToLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        String date = parser.getText();
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        return Instant.ofEpochMilli(parser.getValueAsLong()).atZone(ZoneOffset.UTC).toLocalDateTime();
    }
}
