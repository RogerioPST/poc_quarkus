package rogerio.pst;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.inject.Stereotype;
import jakarta.transaction.Transactional;

//@QuarkusTest -> Adds the annotations that this meta-annotation might “inherit”
@QuarkusTest
@Transactional
//Stereotype -> Sets the annotation as a stereotype (meta-annotation)
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//If you then apply this annotation to a class, it will be like you had applied both the @QuarkusTest and the @Transactional annotations:
public @interface TransactionalQuarkusTest {
}
