package com.priyanka.pagepulse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AuditRequest {

    @NotBlank(message = "URL is required")
    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "Please enter a valid URL starting with http:// or https://"
    )
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}