package com.shanhh.study.lucene.demo1;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;

public class IndexSearch {

    /**
     * 查询
     *
     * @throws Exception
     */
    public void search() throws Exception {
        String filePath = "/tmp/lucene/index/demo1";
        Directory dir = FSDirectory.open(new File(filePath));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        Term term = new Term("content", "add");
        TermQuery query = new TermQuery(term);
        TopDocs topdocs = searcher.search(query, 5);
        ScoreDoc[] scoreDocs = topdocs.scoreDocs;
        System.out.println("查询结果总数: " + topdocs.totalHits);
        System.out.println("最大的评分: " + topdocs.getMaxScore());
        for (int i = 0; i < scoreDocs.length; i++) {
            int doc = scoreDocs[i].doc;
            Document document = searcher.doc(doc);
            System.out.println("content: " + document.get("content"));
            System.out.println("id:" + scoreDocs[i].doc + " scores:" + scoreDocs[i].score + " index:" + scoreDocs[i].shardIndex);
        }
        reader.close();
    }

    public static void main(String[] args) throws Exception {
        IndexSearch search = new IndexSearch();
        search.search();
    }
}  