package io.github.shiyusen.jmu.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.annotations.VisibleForTesting;
import io.github.shiyusen.jmu.models.JMonitorUI;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author 石玉森
 * at created 2020-03-06 16:40
 **/
public class JmuJacksonModule extends SimpleModule implements JacksonModuleRegistrar {
    public JmuJacksonModule() {
    }

    public void maybeRegisterModule(ObjectMapper objectMapper) {
        if (objectMapper.findMixInClassFor(JMonitorUI.class) == null) {
            objectMapper.registerModule(this);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        }

    }

    public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.setMixInAnnotations(JMonitorUI.class, io.github.shiyusen.jmu.config.JmuJacksonModule.CustomizedSwaggerSerializer.class);

    }

    @JsonAutoDetect
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private interface PropertyExampleSerializerMixin {
        @JsonSerialize(
                using = io.github.shiyusen.jmu.config.JmuJacksonModule.PropertyExampleSerializerMixin.PropertyExampleSerializer.class
        )
        Object getExample();

        public static class PropertyExampleSerializer extends StdSerializer<Object> {
            private static final Pattern JSON_NUMBER_PATTERN = Pattern.compile("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?");

            public PropertyExampleSerializer() {
                this(Object.class);
            }

            PropertyExampleSerializer(Class<Object> t) {
                super(t);
            }

            public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
                if (this.canConvertToString(value)) {
                    String stringValue = value instanceof String ? ((String) value).trim() : value.toString().trim();
                    if (this.isStringLiteral(stringValue)) {
                        String cleanedUp = stringValue.replaceAll("^\"", "").replaceAll("\"$", "").replaceAll("^'", "").replaceAll("'$", "");
                        gen.writeString(cleanedUp);
                    } else if (this.isNotJsonString(stringValue)) {
                        gen.writeRawValue(stringValue);
                    } else {
                        gen.writeString(stringValue);
                    }
                } else {
                    gen.writeObject(value);
                }

            }

            private boolean canConvertToString(Object value) {
                return value instanceof Boolean || value instanceof Character || value instanceof String || value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Long || value instanceof Float || value instanceof Double || value instanceof Void;
            }

            @VisibleForTesting
            boolean isStringLiteral(String value) {
                return value.startsWith("\"") && value.endsWith("\"") || value.startsWith("'") && value.endsWith("'");
            }

            @VisibleForTesting
            boolean isNotJsonString(String value) {
                return value.startsWith("{") || value.startsWith("[") || "true".equals(value) || "false".equals(value) || JSON_NUMBER_PATTERN.matcher(value).matches();
            }

            public boolean isEmpty(SerializerProvider provider, Object value) {
                return this.internalIsEmpty(value);
            }

            public boolean isEmpty(Object value) {
                return this.internalIsEmpty(value);
            }

            private boolean internalIsEmpty(Object value) {
                return value == null || value.toString().trim().length() == 0;
            }
        }
    }

    @JsonAutoDetect
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties({"responseSchema"})
    private class ResponseSerializer {
        private ResponseSerializer() {
        }
    }

    @JsonAutoDetect
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private class CustomizedSwaggerSerializer {
        private CustomizedSwaggerSerializer() {
        }
    }
}
