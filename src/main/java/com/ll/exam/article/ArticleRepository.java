package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleRepository {
    private static List<ArticleDto> datum; // articleDto를 담는 리스트 -> datum
    private static long lastId;

    static {
        datum = new ArrayList<>();
        lastId = 0;
        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1,10).forEach(id -> {
            String title = "제목%d".formatted(id);
            String body = "제목%d".formatted(id);
            write(title,body);
        });
    }

    public static long write(String title, String body) {
        long id = ++lastId;
        ArticleDto newArticleDto = new ArticleDto(id, title, body); // ArticleDto 타입의 new ArticleDto 생성자를 통해서 객체를 초기화

        datum.add(newArticleDto);

        return id;
    }

    public static List<ArticleDto> findAll() {
        return datum;
    }

    public static ArticleDto findById(long id) {
        for ( ArticleDto articleDto : datum ) {
            if ( articleDto.getId() == id ) {
                return articleDto;
            }
        }

        return null;
    }

    public void delete(long id) {
        ArticleDto articleDto = findById(id);

        if(articleDto == null) return;

        datum.remove(articleDto);
    }


    public void modity(long id, String title, String body) {
        ArticleDto articleDto = findById(id);

        if(articleDto == null) return;

        articleDto.setTitle(title);
        articleDto.setBody(body);
    }
}