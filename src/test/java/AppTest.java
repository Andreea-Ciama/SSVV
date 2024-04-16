

import domain.Nota;
import domain.Student;
import domain.Tema;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceTest {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);


    @org.junit.jupiter.api.Test
    void saveStudent_studentId_0_saveSuccess() {
        assertEquals(service.saveStudent("0", "aa", 222), 0);
    }

    @org.junit.jupiter.api.Test
    void saveStudent_studentId_emptyString_saveFail() {
        assertEquals(service.saveStudent("", "aa", 222), 1);
    }

    @org.junit.jupiter.api.Test
    void saveStudent_studentId_maxInt_saveSuccess() {
        assertEquals(service.saveStudent("maxint", "aa", 222), 0);
    }

    @org.junit.jupiter.api.Test
    void saveStudent_studentId_10_saveSuccess() {
        assertEquals(service.saveStudent("10", "aa", 222), 0);
    }

    @org.junit.jupiter.api.Test
    void saveStudent_group_222_saveSuccess() {
        assertEquals(service.saveStudent("20", "aa", 222), 0);
    }

    @org.junit.jupiter.api.Test
    void saveStudent_group_111_saveSuccess() {
        assertEquals(service.saveStudent("20", "aa", 111), 0);
    }

    @org.junit.jupiter.api.Test
    void saveStudent_group_937_saveSuccess() {
        assertEquals(service.saveStudent("20", "aa", 937), 0);
    }

    @org.junit.jupiter.api.Test
    void saveStudent_group_maxInt_saveFail() {
        assertEquals(service.saveStudent("20", "aa", Integer.MAX_VALUE), 1);
    }

    @org.junit.jupiter.api.Test
    void saveStudent_group_negative1_saveFail() {
        assertEquals(service.saveStudent("20", "aa", -1), 1);
    }

    @org.junit.jupiter.api.Test
    void saveStudent_name_ab_saveSuccess() {
        assertEquals(service.saveStudent("20", "ab", 300), 0);
    }

    @org.junit.jupiter.api.Test
    void saveStudent_name_emptyString_saveFail() {
        assertEquals(service.saveStudent("20", "", 300), 1);
    }


    // Lab 3
    @org.junit.jupiter.api.Test
    void saveTema_startlineAfterDeadline_saveFail() {
        assertEquals(service.saveTema("20", "aa", 4, 5), 1);
    }

    @org.junit.jupiter.api.Test
    void saveTema_startlineBeforeDeadline_saveSuccess() {
        assertEquals(service.saveTema("21", "aa", 4,3), 0);
    }

    @org.junit.jupiter.api.Test
    void saveTema_startline_0_saveFail() {
        assertEquals(service.saveTema("22", "aa", 2, 0), 1);
    }

    @org.junit.jupiter.api.Test
    void saveTema_deadline_13_saveSuccess() {
        assertEquals(service.saveTema("23", "aa", 13,12), 0);
    }

    @org.junit.jupiter.api.Test
    void saveTema_deadline_15_saveFail() {
        assertEquals(service.saveTema("24", "aa", 15, 12), 1);
    }

    @org.junit.jupiter.api.Test
    void saveTema_startline_and_deadline_negative_saveFail() {
        assertEquals(service.saveTema("25", "aa", -2,-1), 1);
    }

    @org.junit.jupiter.api.Test
    void saveTema_startline_20_and_deadline_25_saveFail() {
        assertEquals(service.saveTema("26", "aa", 25, 20), 1);
    }

    @org.junit.jupiter.api.Test
    void saveTema_id_emptyString_saveFail() {
        assertEquals(service.saveTema("", "aa", 6,5), 1);
    }

    @org.junit.jupiter.api.Test
    void saveTema_descriere_emptyString_saveFail() {
        assertEquals(service.saveTema("27", "", 6, 5), 1);
    }
}
