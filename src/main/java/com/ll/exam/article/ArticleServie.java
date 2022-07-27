package com.ll.exam.article;

public class ArticleServie {

    private ArticleRepository articleRepository;

    ArticleServie(){
        articleRepository = new ArticleRepository();
    }

    public long write(String title, String body) {
        return articleRepository.write(title, body);
    }
}
