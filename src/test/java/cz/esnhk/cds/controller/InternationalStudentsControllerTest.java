package cz.esnhk.cds.controller;

import cz.esnhk.cds.service.InternationalStudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InternationalStudentsControllerTest {

    @InjectMocks
    private InternationalStudentsController internationalStudentsController;

    @Mock
    private InternationalStudentService internationalStudentService;

    @Test
    public void testList() {

    }
}
