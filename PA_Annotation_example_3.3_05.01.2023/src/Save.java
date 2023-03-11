import java.lang.annotation.*;

@Inherited//говорит о том, что новая созданная аннотация будет передаваться по наследству
@Target(ElementType.FIELD)//указывает к каким членам класса мы можем аннтоцацию применять, для - поля
@Retention(RetentionPolicy.RUNTIME) // тип аннотации, в данном случае РАНТАЙМ (это значит она будет доступна для Рефлексии)
public @interface Save { //создали аннотацию
}
