package uz.school.schoolwebapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectReport {
    private String subjectName;
    private Double minMark;
    private Double avgMark;
    private Double maxMark;
}