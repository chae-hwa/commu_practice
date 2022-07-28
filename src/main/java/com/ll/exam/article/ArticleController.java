package com.ll.exam.article;

import com.ll.exam.Rq;
import com.ll.exam.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    private ArticleService articleService;

    public ArticleController() {
        articleService = new ArticleService();
    }

    public void showList(Rq rq) {
        List<ArticleDto> articleDtos = articleService.findAll();

        rq.setAttr("articles", articleDtos); // 서블릿이 jsp에게 넘겨줄 때 사용할 값을 보고(?)
        rq.view("usr/article/list");          // list.jsp에서 사용할 수 있게 된다.
    }

    public void showWrite(Rq rq) {
        rq.view("usr/article/write");
    }

    public void doWrite(Rq rq) {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        long id = articleService.write(title, body);

        rq.appendBody("%d번 게시물이 생성 되었습니다.".formatted(id));
    }

    public void showDetail(Rq rq) {
        long id = rq.getLongPathValueByIndex(1,0);

        if(id == 0){
            rq.appendBody("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if(articleDto == null){
            rq.appendBody("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/detail");
    }

    public void doDelete(Rq rq) {
        long id = rq.getLongPathValueByIndex(1,0);

        if(id == 0){
            rq.appendBody("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if(articleDto == null){
            rq.appendBody("해당 글이 존재하지 않습니다.");
            return;
        }

        articleService.delete(id);

        rq.appendBody("<div>%d번 게시물이 삭제되었습니다.</div>".formatted(id));
        rq.appendBody("<div><a href=\"/usr/article/list/free\">리스트 이동</a></div>".formatted(id));

    }

    public void showModify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1,0);

        if(id == 0){
            rq.appendBody("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if(articleDto == null){
            rq.appendBody("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/modify");
    }

    public void doModify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1,0);
        String title = rq.getParam("title","");
        String body = rq.getParam("title","");

        rq.appendBody("<div>id : %d</div>".formatted(id));
        rq.appendBody("<div>title : %s</div>".formatted(title));
        rq.appendBody("<div>body : %s</div>".formatted(body));
    }
}