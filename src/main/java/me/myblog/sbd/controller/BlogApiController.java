package me.myblog.sbd.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.myblog.sbd.domain.Article;
import me.myblog.sbd.dto.AddArticleRequest;
import me.myblog.sbd.dto.ArticleResponse;
import me.myblog.sbd.dto.UpdateArticleRequest;
import me.myblog.sbd.service.BlogService;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

	private final BlogService blogService;

	@PostMapping("/api/articles")
	public ResponseEntity<Article> addArticle(@RequestBody
	AddArticleRequest request) {
		Article savedArticle = blogService.save(request);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(savedArticle);
	}

	@GetMapping("/api/articles")
	public ResponseEntity<List<ArticleResponse>> findAllArticles() {
		List<ArticleResponse> articles = blogService.findAll()
			.stream()
			.map(ArticleResponse::new)
			.toList();

		return ResponseEntity.ok()
			.body(articles);
	}

	@GetMapping("/api/articles/{id}")
	public ResponseEntity<ArticleResponse> findArticle(@PathVariable(name = "id")
	long id) {
		Article article = blogService.findById(id);

		return ResponseEntity.ok()
			.body(new ArticleResponse(article));
	}

	@DeleteMapping("/api/articles/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable(name = "id")
	long id) {
		blogService.delete(id);

		return ResponseEntity.ok()
			.build();
	}

	@PutMapping("/api/articles/{id}")
	public ResponseEntity<Article> updateArticle(@PathVariable(name = "id")
	long id,
		@RequestBody
		UpdateArticleRequest request) {
		Article updatedArticle = blogService.update(id, request);

		return ResponseEntity.ok()
			.body(updatedArticle);
	}
}
