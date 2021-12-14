package org.csystem.util.collection.dlinkedlist;

import org.csystem.collection.DLinkedList;
import org.csystem.factory.StringDataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_deleteItemTail_Size {
    private final List<String> m_list;
    private DLinkedList<String> m_testList;

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        return StringDataFactory.getData();
    }

    @Before
    public void setUp()
    {
        m_testList = new DLinkedList<>();

        for (var str : m_list)
            m_testList.addItemHead(str);
    }

    public Test_deleteItemTail_Size(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_deleteItemTailSize()
    {
        var size = m_testList.size();

        for (int i = 0; i < size; ++i) {
            m_testList.deleteItemTail();
            Assert.assertEquals("Not true", size - 1 - i , m_testList.size());
        }
    }
}
