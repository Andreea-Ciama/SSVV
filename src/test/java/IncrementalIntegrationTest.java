

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

class IncrementalIntegrationTest {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    @Test
    void saveStudent() {
        assertEquals(service.saveStudent("10", "aa", 222), 0);
    }

    @Test
    void saveTema() {
        assertEquals(service.saveStudent("10", "aa", 222), 0);
        assertEquals(service.saveTema("23", "aa", 13,12), 0);
    }

    @Test
    void saveNota() {
        assertEquals(service.saveStudent("10", "aa", 222), 0);
        assertEquals(service.saveTema("23", "aa", 13,12), 0);
        assertEquals(service.saveNota("10","23",10,1,"Perfect!"),1);
    }
}