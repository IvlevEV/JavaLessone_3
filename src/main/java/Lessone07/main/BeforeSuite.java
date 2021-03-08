package Lessone07.main;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Retention – сообщает, где будет использоваться аннотация:
 * RetentionPolicy.SOURCE – используется на этапе компиляции и должна отбрасываться компилятором;
 * RetentionPolicy.CLASS – будет записана в .class-файл, но не будет доступна во время выполнения;
 * RetentionPolicy.RUNTIME – будет записана в .class-файл и доступна во время выполнения через Reflection.
 * @Target – к какому типу данных можно подключить эту аннотацию:
 * ElementType.METHOD – метод;
 * ElementType.FIELD – поле;
 * ElementType.CONSTRUCTOR – конструктор;
 * ElementType.PACKAGE – пакет;
 * ElementType.PARAMETER – параметр;
 * ElementType.TYPE – тип;
 * ElementType.LOCAL_VARIABLE – локальная переменная и т.д.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BeforeSuite {
}
