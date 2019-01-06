package net.teengamingnights.assemblymod.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static<T, S> Map<T, S> mapOf(Map.Entry<T, S>... entries) {
        return Arrays.stream(entries).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
