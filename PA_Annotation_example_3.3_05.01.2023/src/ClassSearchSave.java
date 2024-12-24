import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassSearchSave {
    private static final File file = new File("C:\\Users\\Admin\\Desktop\\Новый текстовый документ.txt");

    //    public static boolean searchAnnotationsSaveForClasses(Class<?>... listClasses) throws InvocationTargetException, IllegalAccessException { 
    //на вхід передаємо список класів, в яких будемо шукати анотацію
    //        try {
    //            for (Class<?> clazz : listClasses) { //проходимо по кожному класу
    //                Method[] methods = clazz.getDeclaredMethods(); //отримуємо список методів кожного класу
    //                for (Method method : methods) { //проходимо по кожному методу
    //                    if (method.isAnnotationPresent(Save.class)) { //якщо метод позначено анотацією Save
    //                        //то ми цей метод invoke() - викликаємо через рефлексію
    //                        Boolean b = (Boolean) method.invoke(null); //null передається, оскільки методи статичні
    //                        if (!b) { //якщо метод повернув false - зупиняємось
    //                            return false; //якщо повернув true - то Save пройшов (присутня) і йдемо далі
    //                        }
    //                    }
    //                }
    //            }
    //            return true; //всі Save пройшли успішно
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            return false;
    //        }
    //}

    public static void searchAnnotationsSaveForFields(Class clazz) throws Exception, IOException {
        try {
            Field[] fields = clazz.getDeclaredFields(); //отримуємо список полів класу
            for (Field field : fields) { //проходимо по кожному полю класу
                if (field.isAnnotationPresent(Save.class)) { //якщо поле класу позначене анотацією Save, то
                    save(field); //для цього поля викликаємо метод збереження
                    load(field); //для цього поля викликаємо метод завантаження
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save(Field field) throws Exception { //метод для збереження
        //буферизуємо запис у файл
        try (FileWriter fw = new FileWriter(file, true); //будемо писати з додаванням до файлу нових даних
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter writer = new PrintWriter(bw + " ")) {

            writer.write(String.valueOf(field));
            writer.flush(); //пишемо, якщо є ще незаписані дані

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load(Field field) throws Exception { //метод для завантаження
        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
            String str = String.valueOf(field.isAnnotationPresent(Save.class));
            str = reader.readLine();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
