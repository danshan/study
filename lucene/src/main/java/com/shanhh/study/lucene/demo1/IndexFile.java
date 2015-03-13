package com.shanhh.study.lucene.demo1;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;

public class IndexFile {

    protected String[] ids = {"1", "2"};

    protected String[] content = {"Amsterdam has lost of add  cancals", "i love  add this girl"};

    protected String[] city = {"Amsterdam", "Venice"};

    private Directory dir;

    /**
     * 初始添加文档
     *
     * @throws Exception
     */
    public void init() throws Exception {
        String pathFile = "/tmp/lucene/index/demo1";
        dir = FSDirectory.open(new File(pathFile));
        IndexWriter writer = getWriter();
        for (int i = 0; i < ids.length; i++) {
            Document doc = new Document();
            doc.add(new StringField("id", ids[i], Field.Store.YES));
            doc.add(new TextField("content", content[i], Field.Store.YES));
            doc.add(new StringField("city", city[i], Field.Store.YES));
            writer.addDocument(doc);
        }
        System.out.println("init ok?");
        writer.close();
    }

    /**
     * 获得IndexWriter对象
     *
     * @return
     * @throws Exception
     */
    public IndexWriter getWriter() throws Exception {
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_2, analyzer);
        return new IndexWriter(dir, iwc);
    }

    public static void main(String[] args) throws Exception {
        IndexFile index = new IndexFile();
        index.init();
    }

}  