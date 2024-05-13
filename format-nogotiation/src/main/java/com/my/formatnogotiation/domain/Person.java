package com.my.formatnogotiation.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@JacksonXmlRootElement  // 可以写出为xml文档
@Data
@AllArgsConstructor
public class Person {
    private Long id;
    private String userName;
    private String email;
    private Integer age;
}