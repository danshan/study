package com.shanhh.study.lucene.demo2;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

public class IndexSearch {

    /**
     * 查询
     *
     * @throws Exception
     */
    public void search() throws Exception {

        Analyzer analyzer = new IKAnalyzer();
        String filePath = "/tmp/lucene/index/demo2";

        Directory dir = FSDirectory.open(new File(filePath));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);


        String keyword = "格式";
        BooleanQuery booleanQuery = new BooleanQuery();

        MultiFieldQueryParser keywordQueryParser =new MultiFieldQueryParser(new String[]{"filename", "content"}, analyzer);
        Query keywordQuery = keywordQueryParser.parse(keyword);

        booleanQuery.add(keywordQuery, BooleanClause.Occur.MUST);

        TopDocs topdocs = searcher.search(booleanQuery, 5);
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