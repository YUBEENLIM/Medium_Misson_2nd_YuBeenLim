package com.ll.medium.global.init;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.repository.MemberRepository;
import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class NotProd {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public NotProd(MemberRepository memberRepository, PostRepository postRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }
    @PostConstruct
    public void initializeSampleData() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .username("user" + i)
                    .password("password" + i)
                    .isPaid(i % 2 == 0)
                    .build();
            memberRepository.save(member);
        });

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Post post = Post.builder()
                    .title("Title " + i)
                    .body("Content " + i)
                    .isPaid(i % 2 == 0)
                    .build();
            postRepository.save(post);
        });
    }
}
