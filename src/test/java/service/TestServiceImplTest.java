package service;

import dao.TestDao;
import model.Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServiceImplTest {

    private TestDao testDao;
    private TestService testService;

    @BeforeEach
    void setUp() {
        testDao = mock(TestDao.class);
        testService = new TestServiceImpl(testDao);
    }

    @Test
    void shouldReturnList() throws IOException {
        when(testDao.getTest()).thenReturn(List.of(new Tests()));
        assertEquals(testService.getTestList().isEmpty(), false);
    }

}
