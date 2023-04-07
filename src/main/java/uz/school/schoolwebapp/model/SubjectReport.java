package uz.school.schoolwebapp.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SubjectReport {
    private String subjectName;
    private Double minMark;
    private Double avgMark;
    private Double maxMark;
}