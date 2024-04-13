package net.zidargs.api.util.test;

import net.zidargs.api.servlet.test.TestEntityData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestRecordListBuilder {

    public static List<TestEntityData> buildTestRecordList() {
        List<TestEntityData> entityDataList = new ArrayList<>();
        entityDataList.add(new TestEntityData(
                "0001",
                "Foobar",
                new Date()
        ));
        entityDataList.add(new TestEntityData(
                "0002",
                "Barfoo",
                new Date()
        ));
        return entityDataList;
    }

}
