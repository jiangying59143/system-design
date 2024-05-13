package com.my.formatnogotiation.yaml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.my.formatnogotiation.domain.Person;
import org.junit.jupiter.api.Test;

public class test {

    @Test
    public void generateYaml() throws JsonProcessingException {
        Person person = new Person(1L, "张三", "aaa@qq.com", 18);

        YAMLFactory factory = new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        ObjectMapper mapper = new ObjectMapper(factory);

        String s = mapper.writeValueAsString(person);
        System.out.println(s);
    }
}
