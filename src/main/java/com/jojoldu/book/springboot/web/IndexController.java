package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        // postsService.findAllDesc()에서 가져온 결과를 posts로 index.mustache에 전달

        //SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = postsService.findById(id);
        model.addAttribute("post", responseDto);
        return "posts-update";
    }
}
