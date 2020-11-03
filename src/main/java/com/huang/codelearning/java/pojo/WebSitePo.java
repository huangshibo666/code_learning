package com.huang.codelearning.java.pojo;

import com.huang.codelearning.java.annotation.MyAnnotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * website entity
 */
@MyAnnotation(owner = "others")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebSitePo {
    private String title;
    private String url;
    private String keywords;
    private String functions;
}