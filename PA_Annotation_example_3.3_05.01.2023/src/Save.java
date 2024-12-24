import java.lang.annotation.*;

@Inherited // вказує, що новостворена анотація буде передаватися по спадщині
@Target(ElementType.FIELD) // вказує, до яких членів класу можна застосовувати анотацію, в даному випадку - до полів
@Retention(RetentionPolicy.RUNTIME) // тип анотації, в даному випадку RUNTIME (це означає, що вона буде доступна для рефлексії)
public @interface Save { // створили анотацію
}
