package io.github.shiyusen.jmu.command;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author 石玉森
 * @create 2020-03-09 12:38
 **/
@Slf4j
public  class CommandExecuteHandler {

    public String execute(String[] commond) {
        String result;
        try {
            Process p = Runtime.getRuntime().exec(commond);

            /* 为"错误输出流"单独开一个线程读取之,否则会造成标准输出流的阻塞 */
            Thread t = new Thread(new InputStreamRunnable(p.getErrorStream(), "ErrorStream"));
            t.start();

            /* "标准输出流"就在当前方法中读取 */
            result = inputStream(p.getInputStream());
        } catch (Exception e) {
            log.error("command exec error.msg={}", e.getMessage(), e);
            result = e.getMessage();
        }
        return result;
    }

    public String inputStream(InputStream inputStream) {
        BufferedInputStream bis = new BufferedInputStream(inputStream);

        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;

        try {
            inputStreamReader = new InputStreamReader(bis, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            bufferedReader.close();
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    class InputStreamRunnable implements Runnable {
        private BufferedReader bReader = null;

        InputStreamRunnable(InputStream is, String type) {
            try {
                bReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), "UTF-8"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public void run() {
            String line;
            int num = 1;
            try {
                while ((line = bReader.readLine()) != null) {
                    log.error("---->" + String.format("%02d", num++) + " " + line);
                }
                bReader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
