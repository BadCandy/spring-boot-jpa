package me.christ9979.springbootjpa1.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    /**
     * Spring Data에서 제공하는 converter 중 DomainClassConverter가 있다.
     * DomainClassConverter는 ToIdConverter와 ToEntityConverter를 제공한다.
     * jpa를 사용하면 자동으로 등록이 되며, entity의 id를 받으면 자동으로 entity를 가져와
     * 컨버팅 해준다.
     *
     * 여담으로 jpa와 상관이 없지만 converter와 비슷한 formatter가 있는데
     * formmater는 String 타입을 어떠한 entity로, 어떤 entity를 String 타입으로
     * 변경할 때 사용한다.
     * 즉 converter는 다용도의 타입을 사용할 수 있지만, formmater는 String에 특화되어 있다.
     */
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") Post post) {
        return post.getTitle();
    }

    /**
     * HandlerMethodArgumentResolver
     * 스프링 MVC 핸들러 메소드의 매개변수로 받을 수 있는 객체를
     * 확장하고 싶을 때 사용하는 인터페이스
     *
     * 다음과 같이 Pagable을 받아서 페이징 기능을 구현할 수 있다.
     */
    @GetMapping("/posts")
    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    /**
     * Hateoas 기능을 사용하기 위한 핸들러
     */
    @GetMapping("/posts/hateoas")
    public PagedResources<Resource<Post>> getPosts(Pageable pageable, PagedResourcesAssembler<Post> assembler) {
        return assembler.toResource(postRepository.findAll(pageable));
    }
}
