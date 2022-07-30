package com.swit.user.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;


public class SmsDto {
    @Data
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MessagesRequestDto {
        private String to;
        private String content;
    }

    @Data
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SmsRequestDto {
        private String type;
        private String contentType;
        private String countryCode;
        private String from;
        private String content;
        private List<MessagesRequestDto> messages;
    }

    @Data
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class SendSmsResponseDto {
        private String statusCode;
        private String statusName;
        private String requestId;
        private Timestamp requestTime;
    }
}
