package uz.school.schoolwebapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.school.schoolwebapp.model.Mark;
import uz.school.schoolwebapp.model.Student;
import uz.school.schoolwebapp.model.Subject;
import uz.school.schoolwebapp.model.SubjectReport;
import uz.school.schoolwebapp.repository.MarkRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {

    @Mock
    private MarkRepository markRepository;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private ReportServiceImpl reportService;

    @Test
    public void testGetStatisticsPerStudent() {
        Long subjectId = 1L;
        Subject subject = new Subject();
        subject.setId(subjectId);
        subject.setName("Math");

        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setEmail("john.doe@example.com");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstName("Jane");
        student2.setLastName("Doe");
        student2.setEmail("jane.doe@example.com");

        Mark mark1 = new Mark();
        mark1.setStudent(student1);
        mark1.setValue(5);

        Mark mark2 = new Mark();
        mark2.setStudent(student1);
        mark2.setValue(4);

        Mark mark3 = new Mark();
        mark3.setStudent(student2);
        mark3.setValue(3);

        List<Mark> marks = Arrays.asList(mark1, mark2, mark3);

        when(markRepository.findBySubjectId(subjectId)).thenReturn(marks);
        when(subjectService.getById(subjectId)).thenReturn(subject);

        Map<Student, SubjectReport> expected = new HashMap<>();
        expected.put(student1, new SubjectReport(subject.getName(), 4.0, 4.5, 5.0));
        expected.put(student2, new SubjectReport(subject.getName(), 3.0, 3.0, 3.0));
        Map<Student, SubjectReport> actual = reportService.getStatisticsPerStudent(subject.getId());
        assertEquals(expected.size(), actual.size());

        for (Map.Entry<Student, SubjectReport> entry : expected.entrySet()) {
            Student expectedStudent = entry.getKey();
            SubjectReport expectedReport = entry.getValue();

            assertTrue(actual.containsKey(expectedStudent));
            SubjectReport actualReport = actual.get(expectedStudent);

            assertEquals(expectedReport.getSubjectName(), actualReport.getSubjectName());
            assertEquals(expectedReport.getMinMark(), actualReport.getMinMark(), 0.0);
            assertEquals(expectedReport.getAvgMark(), actualReport.getAvgMark(), 0.0);
            assertEquals(expectedReport.getMaxMark(), actualReport.getMaxMark(), 0.0);
        }
    }

}