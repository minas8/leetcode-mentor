package com.minas.leetcode_mentor.config;

import com.minas.leetcode_mentor.domain.problem.DifficultyLevel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DifficultyLevelConverter implements Converter<String, DifficultyLevel> {

    @Override
    public DifficultyLevel convert(String source) {
        return DifficultyLevel.valueOf(source.toUpperCase());
    }
}
