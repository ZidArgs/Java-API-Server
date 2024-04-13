package net.zidargs.api.util;

import java.util.List;

public record SearchResponseData<T>(
        Boolean success,
        Boolean error,
        SearchRequestData params,
        Integer length,
        List<T> records
) {
}
