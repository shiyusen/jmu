package io.github.shiyusen.jmu.models;

import java.util.List;
import java.util.Map;

/**
 * @author 石玉森
 * @create 2020-03-06 17:15
 **/

public class JMonitorUI {
    protected String swagger = "2.0";
    protected String info;
    protected String host;
    protected String basePath;
    protected List<String> tags;
    protected List<String> schemes;
    protected List<String> consumes;
    protected List<String> produces;
    protected List<String> security;
    protected Map<String, String> paths;
    protected Map<String, String> securityDefinitions;
    protected Map<String, String> definitions;
    protected Map<String, String> parameters;
    protected Map<String, String> responses;
    protected Map<String, Object> vendorExtensions;

    public JMonitorUI() {
    }


}
