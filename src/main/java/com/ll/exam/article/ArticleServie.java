package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;

import java.util.List;

public class ArticleServie {

    private ArticleRepository articleRepository;

    ArticleServie(){
        articleRepository = new ArticleRepository();
    }

    public long write(String title, String body) {
        return articleRepository.write(title, body);
    }

    public List<ArticleDto> findAll() {
        return articleRepository.findAll();
    }
}
