package net.zidargs.api.util;

import java.util.Map;

public record SearchRequestData(
        String[] sort,
        Integer page,
        Integer pageSize,
        Map<String, String> filter,
        String search
) {
}
