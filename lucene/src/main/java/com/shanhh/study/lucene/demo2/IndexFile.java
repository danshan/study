package com.shanhh.study.lucene.demo2;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 从txt文件创建索引
 *
 * @author dan.shan
 * @since 2014-12-12 2:24 PM
 */
public class IndexFile {

    private Directory directory;

    private String indexPath = "/tmp/lucene/index/demo2"; // 建立索引文件的目录

    private String dirPath = "demo2"; // txt资源目录

    private Analyzer analyzer = new IKAnalyzer();

    private IndexWriter indexWriter;

    public void init() {
        try {
            directory = FSDirectory.open(new File(indexPath));
            indexWriter = getIndexWriter(directory);
        } catch (Exception e) {
            System.out.println("索引打开异常！");
        }
    }

    /**
     * 获得所有txt文件
     *
     * @param dirPath
     * @return
     */
    public List<File> getFileList(String dirPath) {
        File[] files = new File(this.getClass().getResource("/").getPath() + dirPath).listFiles();
        List<File> fileList = new ArrayList<File>();
        for (File file : files) {
            if (isTxtFile(file.getName())) {
                fileList.add(file);
            }
        }
        return fileList;
    }

    /**
     * 创建索引
     *
     * @throws Exception
     */
    public void createIndex() throws Exception {
        List<File> fileList = getFileList(dirPath);
        Document document = null;
        for (File file : fileList) {
            document = fileToDocument(file);
            indexWriter.addDocument(document);
            System.out.println("filename: " + document.get("filename"));
            indexWriter.commit();
        }
        closeWriter();
    }

    /**
     * 判断是否是txt文件
     *
     * @param fileName
     * @return
     */
    public boolean isTxtFile(String fileName) {
        if (fileName.lastIndexOf(".txt") > 0) {
            return true;
        }
        return false;
    }

    /**
     * 将文件转换成Document对象
     *
     * @param file
     * @return
     * @throws Exception
     */
    public Document fileToDocument(File file) throws Exception {
        Document document = new Document();
        document.add(new TextField("filename", file.getName(), Field.Store.YES));
        document.add(new TextField("content", getFileContent(file), Field.Store.YES));
        document.add(new LongField("size", file.getTotalSpace(), Field.Store.YES));
        return document;
    }

    /**
     * 获得indexwriter对象
     *
     * @param dir
     * @return
     * @throws Exception
     */
    public IndexWriter getIndexWriter(Directory dir) throws Exception {
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_2, analyzer);
        return new IndexWriter(dir, iwc);
    }

    /**
     * 关闭indexwriter对象
     *
     * @throws Exception
     */
    public void closeWriter() throws Exception {
        if (indexWriter != null) {
            indexWriter.close();
        }
    }

    /**
     * 读取文件内容
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String getFileContent(File file) throws Exception {
        Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb = sb.append("\n").append(line);
        }
        br.close();
        reader.close();
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        IndexFile index = new IndexFile();
        index.init();
        index.createIndex();
    }

}
