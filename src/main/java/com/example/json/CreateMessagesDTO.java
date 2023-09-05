package com.example.json;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 创建消息对象
 *
 * @author aaron.huang
 * @version 1.0.0
 * @since 2023-08-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessagesDTO implements Serializable {
    private String title;

    private CoverDTO cover;

    private String content;

    @JSONField(name = "external_link_url")
    private String externalLinkUrl;

    @JSONField(name = "article_type")
    private Integer articleType;

    @JSONField(name = "content_source_url")
    private String contentSourceUrl;

    private String author;

    @JSONField(name = "i18n_title")
    private I18nTitleDTO i18nTitle;

    @JSONField(name = "i18n_content")
    private I18nContentDTO i18nContent;

    @JSONField(name = "i18n_author")
    private I18nAuthorDTO i18nAuthor;

    @JSONField(name = "article_config")
    private ArticleConfigDTO articleConfig;

    private String digest;

    @JSONField(name = "i18n_digest")
    private I18nDigestDTO i18nDigest;

    @NoArgsConstructor
    @Data
    public static class CoverDTO implements Serializable {
        private static final long serialVersionUID = -7602249691901754314L;

        private String originCover;

        private String messageCover;

        private String articleCover;
    }

    @NoArgsConstructor
    @Data
    public static class I18nTitleDTO implements Serializable {
        private static final long serialVersionUID = 829795043918900705L;

        private String zhCn;

        private String enUs;

        private String jaJp;
    }

    @NoArgsConstructor
    @Data
    public static class I18nContentDTO implements Serializable {
        private static final long serialVersionUID = -6129067726644999263L;

        private String zhCn;

        private String enUs;

        private String jaJp;
    }

    @NoArgsConstructor
    @Data
    public static class I18nAuthorDTO implements Serializable {
        private static final long serialVersionUID = -8331593989623114501L;

        private String zhCn;

        private String enUs;

        private String jaJp;
    }

    @NoArgsConstructor
    @Data
    public static class ArticleConfigDTO implements Serializable {
        private static final long serialVersionUID = 6305374415866727159L;

        private Boolean allowComment;

        private Boolean allowForward;

        private Integer shareType;

        private Integer commentDisplayType;
    }

    @NoArgsConstructor
    @Data
    public static class I18nDigestDTO implements Serializable {
        private static final long serialVersionUID = -5642551125558224699L;

        private String zhCn;

        private String enUs;

        private String jaJp;
    }
}
