import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassSearchSave {
    private static final File file = new File("C:\\Users\\Admin\\Desktop\\Новый текстовый документ.txt");
//    public static boolean searchAnnotationsSaveForClasses(Class<?>... listClasses) throws InvocationTargetException, IllegalAccessException { //на вход
//        // метода передаем список классов, в которых будем искать аннотацию
//        try {
//            for (Class<?> clazz : listClasses) { //проходимся по каждому классу
//                Method[] methods = clazz.getDeclaredMethods(); //получаем список методов каждого класса
//                for (Method method : methods) { //проходим по каждому методу
//                    if (method.isAnnotationPresent(Save.class)) { //если метод isAnnotationPresent аннотирован аннотацией Save
//                        //то мы этот метод invoke() - вызываем с помощью Рефлексии
//                        Boolean b = (Boolean) method.invoke(null); //null передается, посколько методы принидутельно статические
//                        if (!b) { //если метод вернул false - то останавливаемся
//                            return false; //если вернул true - то Save прошел(присутствует) и идем дальше
//                        }
//                    }
//                }
//            }
//            return true; //все Save прошли успешно
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public static void searchAnnotationsSaveForFields(Class clazz) throws Exception, IOException {
        try {
            Field[] fields = clazz.getDeclaredFields(); //получауем список полей класса
            for (Field field : fields) { //проходимся по каждому полю класса
                if (field.isAnnotationPresent(Save.class)) { //если поле класса помечено аннотацией Save, то
                    save(field); //для этого поля вызываем метод сохранить
                    load(field); //для этого поля вызываем метод загрузить
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save(Field field) throws Exception { //метод для сохранения
        //буферизируем звпись в файл
        try (FileWriter fw = new FileWriter(file, true); //будем писать с добавлением в файл новых данных
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter writer = new PrintWriter(bw + " ")) {

            writer.write(String.valueOf(field));
            writer.flush(); //пишем если есть еще незаписанные данные

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load(Field field) throws Exception { //метод для загрузки
        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
            String str = String.valueOf(field.isAnnotationPresent(Save.class));
            str = reader.readLine();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
