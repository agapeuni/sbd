package me.myblog.sbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.myblog.sbd.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {
}

