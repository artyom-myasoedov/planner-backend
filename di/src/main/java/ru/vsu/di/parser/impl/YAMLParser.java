package ru.vsu.di.parser.impl;

import org.yaml.snakeyaml.Yaml;
import ru.vsu.di.parser.Parser;

import java.util.Map;

public class YAMLParser implements Parser {

    @Override
    public Map<String, Object> parseMaps(String properties) {
        Yaml yaml = new Yaml();
        return yaml.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(properties));
    }
}
