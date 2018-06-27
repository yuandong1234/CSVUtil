package com.yuong.csv;

import android.util.Log;

import com.yuong.csv.file.FileStorageManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by yuandong on 2018/6/27.
 */

public class CSVFactory {

    private String fileName;
    private List<Object> headTitleList;
    private List<List<Object>> dataList;
    private boolean isAppend;


    private CSVFactory(Builder builder) {
        this.fileName = builder.fileName;
        this.headTitleList = builder.headTitleList;
        this.dataList = builder.dataList;
        this.isAppend = builder.isAppend;
    }

    /**
     * 生成csv表格文件
     */
    public void create() {
        File file = null;
        BufferedWriter writer = null;
        if (fileName == null || fileName.equals("")) return;

        try {

            file = FileStorageManager.getInstance().getFileByName(fileName);

            // GB2312使正确读取分隔符","
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,isAppend), "UTF-8"), 1024);

            //写入头部
            if (headTitleList != null && headTitleList.size() > 0) {
                writeRow(headTitleList, writer);
            }
            //写入数据
            if (dataList != null && dataList.size() > 0) {
                for (List<Object> row : dataList) {
                    writeRow(row, writer);
                }
            }
            writer.flush();
            Log.i("CSVFactory","Data generation completed");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写入数据
     *
     * @param row
     * @param writer
     * @throws IOException
     */
    private void writeRow(List<Object> row, BufferedWriter writer) throws IOException {
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.
                    append("\"").
                    append(data).
                    append("\",").
                    toString();
            writer.write(rowStr);
        }
        writer.newLine();
    }

    public static class Builder {
        private String fileName;
        private List<Object> headTitleList;
        private List<List<Object>> dataList;
        private boolean isAppend;

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder headTitleList(List<Object> headTitleList) {
            this.headTitleList = headTitleList;
            return this;
        }

        public Builder dataList(List<List<Object>> dataList) {
            this.dataList = dataList;
            return this;
        }

        public Builder append(boolean append) {
            isAppend = append;
            return this;
        }

        public CSVFactory Build() {
            return new CSVFactory(this);
        }
    }

}
