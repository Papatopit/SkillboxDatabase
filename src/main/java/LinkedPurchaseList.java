import Key.KeyLinkedPurchaseList;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "LinkedPurchaselist")
public class LinkedPurchaseList
{
    @EmbeddedId
    private KeyLinkedPurchaseList id;
    @Column(name = "student_id", insertable = false, updatable = false)
    private Integer studentId;
    @Column(name = "course_id", insertable = false, updatable = false)
    private Integer courseId;

    public KeyLinkedPurchaseList getId() {
        return id;
    }

    public void setId(KeyLinkedPurchaseList id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
