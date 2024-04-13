package net.zidargs.api.servlet.test;

import java.util.Date;

public record TestEntityData(
        String key,
        String displayName,
        Date date
) {
}
