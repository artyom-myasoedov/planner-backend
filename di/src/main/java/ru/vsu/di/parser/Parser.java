package ru.vsu.di.parser;

import java.util.Map;

public interface Parser {

    Map<String, Object> parseMaps(String properties);
}
