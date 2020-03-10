package io.github.shiyusen.jmu.command.handler;

import io.github.shiyusen.jmu.command.CommandExecuteHandler;
import io.github.shiyusen.jmu.util.ArrayUtil;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

/**
 * @author 石玉森
 * @create 2020-03-09 14:31
 **/

public class JstatCommandExecuteHandler extends CommandExecuteHandler {
    private final static String prefix = "java.lang.Thread.State: ";

    /**
     * Jstat 模板方法
     *
     * @param strings 命令
     */
    private void jstat(String[] strings) throws Exception {
        String s = execute(strings);
        assert s != null;
        BufferedReader reader = new BufferedReader(new StringReader(s));
        String[] keys = ArrayUtil.trim(reader.readLine().split("\\s+|\t"));
        String[] values = ArrayUtil.trim(reader.readLine().split("\\s+|\t"));
        // 特殊情况
//        if (strings[1].equals("-compiler")) {
//            for (int i = 0; i < 4; i++) {
//                list.add(new KVEntity(keys[i], values[i]));
//            }
//            return list;
//        }
//        // 正常流程
//        for (int i = 0; i < keys.length; i++) {
//            list.add(new KVEntity(keys[i], values[i]));
//        }
    }

    /**
     * 类加载信息
     * X轴为时间，Y轴为值的变化
     *
     * @param id
     */
    public void jstatClass(String id) throws Exception {
//        List<KVEntity> jstatClass = jstat(new String[]{"jstat", "-class", id});
//        List<KVEntity> jstatCompiler = jstat(new String[]{"jstat", "-compiler", id});
//        jstatClass.addAll(jstatCompiler);
    }

    /**
     * 堆内存信息
     * X轴为时间，Y轴为值的变化
     *
     * @param id
     */
    public void jstatGc(String id) throws Exception {
        jstat(new String[]{"jstat", "-gc", id});
    }

    /**
     * 堆内存百分比
     * 实时监控
     *
     * @param id
     */
    public void jstatUtil(String id) throws Exception {
        jstat(new String[]{"jstat", "-gcutil", id});
    }
}
