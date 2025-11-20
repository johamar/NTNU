package com.group7.krisefikser.mapper.article;

import com.group7.krisefikser.dto.response.article.NewsArticleResponse;
import com.group7.krisefikser.dto.response.article.ShortenedNewsArticleResponse;
import com.group7.krisefikser.model.article.NewsArticle;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T11:35:26+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class NewsArticleMapperImpl implements NewsArticleMapper {

    @Override
    public ShortenedNewsArticleResponse newsArticleToShortenedNewsArticleResponse(NewsArticle newsArticle) {
        if ( newsArticle == null ) {
            return null;
        }

        ShortenedNewsArticleResponse shortenedNewsArticleResponse = new ShortenedNewsArticleResponse();

        shortenedNewsArticleResponse.setId( newsArticle.getId() );
        shortenedNewsArticleResponse.setTitle( newsArticle.getTitle() );
        shortenedNewsArticleResponse.setPublishedAt( localDateTimeToString( newsArticle.getPublishedAt() ) );

        return shortenedNewsArticleResponse;
    }

    @Override
    public NewsArticleResponse newsArticleToNewsArticleResponse(NewsArticle newsArticle) {
        if ( newsArticle == null ) {
            return null;
        }

        NewsArticleResponse newsArticleResponse = new NewsArticleResponse();

        newsArticleResponse.setTitle( newsArticle.getTitle() );
        newsArticleResponse.setContent( newsArticle.getContent() );
        newsArticleResponse.setPublishedAt( localDateTimeToString( newsArticle.getPublishedAt() ) );

        return newsArticleResponse;
    }
}
