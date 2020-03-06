package io.github.shiyusen.jmu.business.test.controller;

import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.spring.web.PropertySourcedMapping;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 石玉森
 * @create 2020-03-06 16:27
 **/

@Controller
@ApiIgnore
public class JvmController {
    public static final String DEFAULT_URL = "/v2/api-docs";
    private static final Logger LOGGER = LoggerFactory.getLogger(JvmController.class);
    private static final String HAL_MEDIA_TYPE = "application/hal+json";
    private final String hostNameOverride;
    private final JsonSerializer jsonSerializer;

    @Autowired
    public JvmController(Environment environment, JsonSerializer jsonSerializer) {
        this.hostNameOverride = environment.getProperty("jmu.host", "DEFAULT");
        this.jsonSerializer = jsonSerializer;
    }

    @RequestMapping(
            value = {"/v2/api-docs"},
            method = {RequestMethod.GET},
            produces = {"application/json", "application/hal+json"}
    )
    @PropertySourcedMapping(
            value = "${springfox.documentation.swagger.v2.path}",
            propertyKey = "springfox.documentation.swagger.v2.path"
    )
    @ResponseBody
    public ResponseEntity<Json> getDocumentation(@RequestParam(value = "group", required = false) String swaggerGroup, HttpServletRequest servletRequest) {
        String groupName = (String) Optional.fromNullable(swaggerGroup).or("default");
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    private String hostName(UriComponents uriComponents) {
        if ("DEFAULT".equals(this.hostNameOverride)) {
            String host = uriComponents.getHost();
            int port = uriComponents.getPort();
            return port > -1 ? String.format("%s:%d", host, port) : host;
        } else {
            return this.hostNameOverride;
        }
    }
}
