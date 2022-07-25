import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;

public class Exception2 {
    public class Ap1 {
        //Мы не можем бросать, но не предупредить
        public static void main(String[] args) {
            //throw new Exception(); // тут ошибка компиляции
        }
    }
    //>> COMPILATION ERROR: unhandled exception: java.lang.Exception
}

class Ap2 {
    //Мы не можем бросать, но предупредить о «меньшем»
    public static void main(String[] args) throws IOException {
        //throw new Exception(); // тут ошибка компиляции
    }
}

//>> COMPILATION ERROR: unhandled exception: java.lang.Exception
class Ap3 {
    //Мы можем предупредить точно о том, что бросаем
    public static void main(String[] args) throws Exception { // предупреждаем о Exception
        throw new Exception(); // и кидаем Exception
    }
}

class Ap4 {
    //Мы можем предупредить о большем, чем мы бросаем
    public static void main(String[] args) throws Throwable { // предупреждаем "целом" Throwable
        throw new Exception(); // а кидаем только Exception
    }
}

class Ap5 {
    public static void main(String[] args) throws Exception { // пугаем, но ничего не бросаем
    }
}

class Ap6 {
    public static void main(String[] args) {
        //  f(); // тут ошибка компиляции
    }

    public static void f() throws Exception {
    }
}

//>> COMPILATION ERROR: unhandled exception: java.lang.Exception
class Ap7 {
    // они пугают целым Throwable
    public static void main(String[] args) throws Throwable {
        f();
    }

    // хотя мы пугали всего-лишь Exception
    public static void f() throws Exception {
    }
}

//public class InternetDownloader {
//public static byte[] (String url) throws IOException {
//    return "<html><body>Nothing! It's stub!</body></html>".getBytes();
//}
//}
//throws с непроверяемым (unckecked) исключением
class Ap8 {
    public static void main(String[] args) {
        f();
    }

    public static void f() throws RuntimeException {
    }
}
//Множественные исключения

class Ap9 {
    // пугаем ОБОИМИ исключениями
    public static void main(String[] args) throws EOFException, FileNotFoundException {
        if (System.currentTimeMillis() % 2 == 0) {
            throw new EOFException();
        } else {
            throw new FileNotFoundException();
        }
    }
}

class Ap10 {
    // пугаем ОБОИМИ исключениями
    public static void main(String[] args) throws EOFException, FileNotFoundException {
        //f0();
        // f1();
    }
    //public static void f0() throws EOFException {...}
    //public static void f1() throws FileNotFoundException {...}
}

class Ap11 {
    // пугаем ПРЕДКОМ исключений
    public static void main(String[] args) throws IOException {
        if (System.currentTimeMillis() % 2 == 0) {
            throw new EOFException();
        } else {
            throw new FileNotFoundException();
        }
    }
}

class Ap12 {
    // пугаем ПРЕДКОМ исключений
    public static void main(String[] args) throws IOException {
        //    f0();
        //    f1();
    }
    // public static void f0() throws EOFException {...}
    // public static void f1() throws FileNotFoundException {...}
}

class Ap13 {
    public static void main(String[] args) throws IOException, InterruptedException {
        //     f0();
        ///    f1();
        //   f2();
    }
    //  public static void f0() throws EOFException {...}
    //  public static void f1() throws FileNotFoundException {...}
    //  public static void f2() throws InterruptedException {...}
}

class Ap14 {
    //Или catch, или throws
    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (Exception e) {
            // ...
        }
    }
}

class Ap15 {
    //или так (ставим catch по предку и точно перехватываем)
    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (Throwable e) {
            // ...
        }
    }
}

class Ap16 {
    //Но если перехватили только потомка, то не выйдет
    public static void main(String[] args) {
        try {
            //  throw new Throwable(); тут ошибка кампиляции
        } catch (Exception e) {
            // ...
        }
    }
}

//>> COMPILATION ERROR: unhandled exception: java.lang.Throwable
class Ap17 {
    public static void main(String[] args) {
        try {
            //  throw new Exception(); перехватывать брата нельзя,ошибка кампиляции
        } catch (Error e) {
            // ...
        }
    }
}//>> COMPILATION ERROR: unhandled exception: java.lang.Exception

class Ap18 {
    // EOFException перехватили catch-ом, им не пугаем
    public static void main(String[] args) throws FileNotFoundException {
        try {
            if (System.currentTimeMillis() % 2 == 0) {
                throw new EOFException();
            } else {
                throw new FileNotFoundException();
            }
        } catch (EOFException e) {
            //...
        }
    }
}

//Поведение компилятора/JVM
//Необходимо понимать, что
//— проверка на cheched исключения происходит в момент компиляции (compile-time checking)
//— перехват исключений (catch) происходит в момент выполнения (runtime checking)
class Ap19 {
    // пугаем Exception
    public static void main(String[] args) throws Exception {
        Throwable t = new Exception(); // и лететь будет Exception
        //  throw t; // но тут ошибка компиляции
    }
}

//>> COMPILATION ERROR: unhandled exception: java.lang.Throwable
class Ap20 {
    public static void main(String[] args) throws Exception {
        Object ref = "Hello!";  // ref указывает на строку
        //  char c = ref.charAt(0); // но тут ошибка компиляции
    }
}

//>> COMPILATION ERROR: Cannot resolve method 'charAt(int)'
class Ap21 {
    // пугаем Exception
    public static void main(String[] args) throws Exception {
        try {
            Throwable t = new Exception(); // и лететь будет Exception
            // throw t; // но тут ошибка компиляции
        } catch (Exception e) {
            System.out.println("Перехвачено!");
        }
    }
}

//>> COMPILATION ERROR: unhandled exception: java.lang.Throwable
class Ap22 {
    // ТЕПЕРЬ пугаем Throwable
    public static void main(String[] args) throws Throwable {
        try {
            Throwable t = new Exception(); // а лететь будет Exception
            throw t;
        } catch (Exception e) { // и мы перехватим Exception
            System.out.println("Перехвачено!");     //>> Перехвачено!
        }
    }
}
//Overriding и throws
//При переопределении (overriding) список исключений потомка не обязан совпадать с таковым у предка.
//Но он должен быть «не сильнее» списка предка:

 class Parent {
    // предок пугает IOException и InterruptedException
    public void f() throws IOException, InterruptedException {
    }

    class Child extends Parent {
        // а потомок пугает только потомком IOException
        @Override
        public void f() throws FileNotFoundException {
        }
    }

    public class Parent1 {
        public void f() throws IOException, InterruptedException {}
    }

    class ChildB extends Parent {
        @Override
        public void f() {
        }
    }
    //>> COMPILATION ERROR: overridden method does not throw 'java.lang.Exception'

    //Можно сужать тип, но не расширять
    class Parent2 {
        // предок пугает Exception
        public void f() throws Exception {
        }
    }
    // тут ошибка компиляции в Java, но, ДОПУСТИМ, ее нет
    public class Child5 extends Parent {
        // потомок расширил Exception до Throwable
        //public void f() throws Throwable {}
    }


    class Demo {
        public static void test(Parent ref) {
            // все компилируется, Parent.f() пугает Exception и мы его ловим catch
            try {
                ref.f();
            } catch (Exception e) {
            }
        }
    }

    class App {
        public void main(String[] args) {
            // все компилируется, Demo.test хотел Parent и мы дали ему подтип - Child
            Demo.test(new Child());
        }
    }
}