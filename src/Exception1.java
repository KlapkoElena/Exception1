public class Exception1 {

    //Throwable класс является суперклассом всех ошибок и исключений на языке Java
    //throws -используется в сигнатуре методов для предупреждения, о том что метод может
    // выбросить исключение.
    public static void main(String[] args) throws Throwable {
    }

    public class App1 {
        // public static void main(String[] args) throws String {
    }

    //>> COMPILATION ERROR: Incompatible types: required 'java.lang.Throwable', found: 'java.lang.String'
    //------------------------------------------------------------------------------
    class App2 {
        public static void main(String[] args) {
            //catch  определяет блок кода, в котором происходит обработка исключения;
            try {
            } catch (Throwable t) {
            }
        }
    }
}

class App3 {
    public static void main(String[] args) {
        //   }try{
        //  } catch(String s);
    }
}

//>> COMPILATION ERROR: Incompatible types: required 'java.lang.Throwable', found: 'java.lang.String'
//----------------------------------------------------------------------------
//throw используется для возбуждения исключения
class App4 {
    public static void main(String[] args) {
//Error потомок Throwable
        throw new Error();
    }
}

class App5 {
    public static void main(String[] args) {
        //  throw new String("Hello");
    }
}

//>> COMPILATION ERROR: Incompatible types: required 'java.lang.Throwable', found: 'java.lang.String'
//Кроме того, throw требуется не-null аргумент, иначе NullPointerException в момент выполнения
class App6 {
    public static void main(String[] args) {
        throw null;
    }
}

//>> RUNTIME ERROR: Exception in thread "main" java.lang.NullPointerException
//--------------------------------------------------------------------------
//throw и new — это две независимых операции.
class App7 {
    public static void main(String[] args) {
        Error ref = new Error();//create a new exemple
        throw ref;
    }
//>> RUNTIME ERROR: Exception in thread "main" java.lang.Error
}

class App8 {
    public static void main(String[] args) {
        f(null);
    }

    public static void f(NullPointerException e) {
        try {
            throw e;
        } catch (NullPointerException npe) {
            f(npe);
        }
//>> RUNTIME ERROR: Exception in thread "main" java.lang.StackOverflowError
    }
}

//why we use System.err, not System.out,System.out output stream System.err - no
class App9 {
    public static void main(String[] args) {
        System.out.println("sout");
        throw new Error();
    }
//>> RUNTIME ERROR: Exception in thread "main" java.lang.Error
//>> sout
}

class App10 {
    public static void main(String[] args) {
        System.err.println("south");
        throw new Error();
    }
    //>> sout
    //>> RUNTIME ERROR: Exception in thread "main" java.lang.Error
}

class App11 {
    public double sqr(double arg) { //need double
        return arg * arg;        //double* double - its double
    }
}

class App12 {
    public double sqr(double arg) { //need double
        int k = 1; //we have int
        return k;  // mojno neyavno preobrazovat int v double
    }
}

class App13 { //вот так не пройдет (другой тип)
    //      public static double sqr(double arg) {
    //          return "hello!";
//>> COMPILATION ERROR: Incompatible types. Required: double. Found: java.lang.String
}

class App14 {
    //     public static double sqr ( double arg) {
//dont have return !!!!!!!!!!
//>> COMPILATION ERROR: Missing return statement
}

class App15 {
    public static double sqr(double arg) {
        //  if (System.currentTimeMillis() % 2 == 0) {
        return arg * arg; // если currentTimeMillis() - четное число, то все ОК
    }
    // а если нечетное, что нам возвращать?
//missing return statement !!!!!!!!!!
//>> COMPILATION ERROR: Missing return statement
}

class App16 {
    public static void main(String[] args) {
        //     double d = sqr(10.0); // ну, и чему равно d?
        //     System.out.println(d);
        //  }
        // public static double sqr(double arg) {
        // nothing
    }
//missing return statement!!!!!!!!
//>> COMPILATION ERROR: Missing return statement
}

class App17 {
    public static double sqr(double arg) {
        while (true) ; // Удивительно, но КОМПИЛИРУЕТСЯ!
// можно ничего не возвращать, а «повесить метод»
    }
}

class App18 {
    public static void main(String[] args) {
        double d = sqr(10.0);  // sqr - навсегда "повиснет", и
        System.out.println(d); // d - НИКОГДА НИЧЕГО НЕ БУДЕТ ПРИСВОЕНО!
    }

    public static double sqr(double arg) {
        while (true) ; // Вот тут мы на века "повисли"
    }
}

class App19 {
    public static double sqr(double arg) {
        if (System.currentTimeMillis() % 2 == 0) {
            return arg * arg; // ну ладно, вот твой double
        } else {
            while (true) ;     // а тут "виснем" навсегда
        }
    }
}

//механизм исключений позволяет НИЧЕГО НЕ ВОЗВРАЩАТЬ!
class App20 {
    public static double sqr(double arg) {
        throw new RuntimeException();
    }
}

class App21 {
    // public static double sqr(double arg) {// согласно объявлению метода ты должен вернуть double
    //    long time = System.currentTimeMillis();
    //    if (time % 2 == 0) {
    //   return arg * arg;             // ок, вот твой double
    //   } else if (time % 2 == 1) {
    //    {
    //  while (true) ;                 // не, я решил "повиснуть"
    //    } else {
    //     throw new RuntimeException(); // или бросить исключение
    //  missing return exeption
}

class App22 {
    public static void main(String[] args) {
        // sqr - "сломается" (из него "выскочит" исключение),
        double d = sqr(10.0);  // выполнение метода main() прервется в этой строчке и
        // d - НИКОГДА НИЧЕГО НЕ БУДЕТ ПРИСВОЕНО!
        System.out.println(d); // и печатать нам ничего не придется!
    }

    public static double sqr(double arg) {
        throw new RuntimeException(); // "бросаем" исключение
    }
    //>> RUNTIME ERROR: Exception in thread "main" java.lang.RuntimeException
}

//--------------------------------------------------------------
//return — выходим из ОДНОГО фрейма (из фрейма #4(метод h()))
class App23 {
    public static void main(String[] args) {
        System.err.println("#1.in");
        f(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println("#1.out"); // вернулись
    } // выходим из текущего фрейма, кончились инструкции

    public static void f() {
        System.err.println(".   #2.in");
        g(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   #2.out");  //вернулись
    } // выходим из текущего фрейма, кончились инструкции

    public static void g() {
        System.err.println(".   .   #3.in");
        h(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   .   #3.out"); // вернулись
    } // выходим из текущего фрейма, кончились инструкции

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.RETURN");
            return; // выходим из текущего фрейма по 'return'
        }
        System.err.println(".   .   .   #4.out"); // ПРОПУСКАЕМ
    }
 /*   >> #1.in
>> .   #2.in
>> .   .   #3.in
>> .   .   .   #4.in
>> .   .   .   #4.RETURN
>> .   .   #3.out
>> .   #2.out
>> #1.out*/

}

//------------------------------------------------------------------
//throw — выходим из ВСЕХ фреймов
class App24 {
    public static void main(String[] args) {
        System.err.println("#1.in");
        f(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println("#1.out"); // ПРОПУСТИЛИ!
    }

    public static void f() {
        System.err.println(".   #2.in");
        g(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   #2.out"); // ПРОПУСТИЛИ!
    }

    public static void g() {
        System.err.println(".   .   #3.in");
        h(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
    }

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.THROW");
            throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
        }
        System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
    }
/*    >> #1.in
>> .   #2.in
>> .   .   #3.in
>> .   .   .   #4.in
>> .   .   .   #4.THROW
>> RUNTIME ERROR: Exception in thread "main" java.lang.Error*/
}

//--------------------------------------------------------------------------
//При помощи catch мы можем остановить летящее исключение
class App25 {
    public static void main(String[] args) {
        System.err.println("#1.in");
        try {
            f(); // создаем фрейм, помещаем в стек, передаем в него управление
        } catch (Error e) { // "перехватили" "летящее" исключение
            System.err.println("#1.CATCH");  // и работаем
        }
        System.err.println("#1.out");  // работаем дальше
    }

    public static void f() {
        System.err.println(".   #2.in");
        g(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   #2.out"); // ПРОПУСТИЛИ!
    }

    public static void g() {
        System.err.println(".   .   #3.in");
        h(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
    }

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.THROW");
            throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
        }
        System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
    }
    /*>> #1.in
>> .   #2.in
>> .   .   #3.in
>> .   .   .   #4.in
>> .   .   .   #4.THROW
>> #1.CATCH
>> #1.out*/
}

//--------------------------------------------------
class App26 {
    public static void main(String[] args) {
        System.err.println("#1.in");
        f(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println("#1.out"); // вернулись и работаем
    }

    public static void f() {
        System.err.println(".   #2.in");
        try {
            g(); // создаем фрейм, помещаем в стек, передаем в него управление
        } catch (Error e) { // "перехватили" "летящее" исключение
            System.err.println(".   #2.CATCH");  // и работаем
        }
        System.err.println(".   #2.out");  // работаем дальше
    }

    public static void g() {
        System.err.println(".   .   #3.in");
        h(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
    }

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.THROW");
            throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
        }
        System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
    }

/*>> #1.in
>>.   #2.in
>>..   #3.in
>>...   #4.in
>>...   #4.THROW
>>.   #2.CATCH
>>.   #2.out
>> #1.out*/
}

//---------------------------------------------------
class App27 {
    public static void main(String[] args) {
        System.err.println("#1.in");
        f(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println("#1.out"); // вернулись и работаем
    }

    public static void f() {
        System.err.println(".   #2.in");
        g(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   #2.out"); // вернулись и работаем
    }

    public static void g() {
        System.err.println(".   .   #3.in");
        try {
            h(); // создаем фрейм, помещаем в стек, передаем в него управление
        } catch (Error e) { // "перехватили" "летящее" исключение
            System.err.println(".   .   #3.CATCH");  // и работаем
        }
        System.err.println(".   .   #3.out");  // работаем дальше
    }

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.THROW");
            throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
        }
        System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
    }
}

//-------------------------------------------------------------------
//полиморфная конструкция, т.е. catch по типу Parent перехватывает летящие экземпляры любого типа
class App28 {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {
                throw new RuntimeException();
            }
            System.err.print(" 1");
        } catch (Exception e) { // catch по Exception ПЕРЕХВАТЫВАЕТ RuntimeException
            System.err.print(" 2");
        }
        System.err.println(" 3");
    }
//>> 0 2 3
}

//в блоке catch мы будем иметь ссылку типа Exception на объект типа RuntimeException
class App29 {
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                RuntimeException re = (RuntimeException) e;
                System.err.print("Это RuntimeException на самом деле!!!");
            } else {
                System.err.print("В каком смысле не RuntimeException???");
            }
        }
        //>> Это RuntimeException на самом деле!!!
    }
}

//catch по потомку не может поймать предка
class App30 {
    public static void main(String[] args) throws Exception { // пока игнорируйте 'throws'
        try {
            System.err.print(" 0");
            if (true) {
                throw new Exception();
            }
            System.err.print(" 1");
        } catch (RuntimeException e) {
            System.err.print(" 2");
        }
        System.err.print(" 3");
    }
    //>> 0
//        >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Exception
}

//catch по одному «брату» не может поймать другого «брата»
// (Error и Exception не находятся в отношении предок-потомок, они из параллельных веток наследования от Throwable)
class App31 {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {
                throw new Error();
            }
            System.err.print(" 1");
        } catch (Exception e) {
            System.err.print(" 2");
        }
        System.err.print(" 3");
    }
    //>> 0
    //       >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error
}

class App32 {
    //что будет, если мы зашли в catch, и потом бросили исключение ИЗ catch?
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {
                throw new RuntimeException();
            }
            System.err.print(" 1");
        } catch (RuntimeException e) {     // перехватили RuntimeException
            System.err.print(" 2");
            if (true) {
                throw new Error();
            } // но бросили Error
        }
        System.err.println(" 3");          // пропускаем - уже летит Error
    }
    //>> 0 2
    //    >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error
}

class App33 {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {
                throw new RuntimeException();
            }
            System.err.print(" 1");
        } catch (RuntimeException e) { // перехватили RuntimeException
            System.err.print(" 2");
            if (true) {
                throw e;
            }       // и бросили ВТОРОЙ раз ЕГО ЖЕ
        }
        System.err.println(" 3");      // пропускаем - опять летит RuntimeException
    }
    //>> 0 2
    //   >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.RuntimeException
}

class App34 {
    //И мы не попадем в другие секции catch, если они есть
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {
                throw new RuntimeException();
            }
            System.err.print(" 1");
        } catch (RuntimeException e) {     // перехватили RuntimeException
            System.err.print(" 2");
            if (true) {
                throw new Error();
            } // и бросили новый Error
        } catch (Error e) { // хотя есть cath по Error "ниже", но мы в него не попадаем
            System.err.print(" 3");
        }
        System.err.println(" 4");

        //>> 0 2
        //    >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error

    }

    class App35 {
        //можно строить вложенные конструкции, но вот пример, «исправляющий» эту ситуацию
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {
                    throw new RuntimeException();
                }
                System.err.print(" 1");
            } catch (RuntimeException e) { // перехватили RuntimeException
                System.err.print(" 2.1");
                try {
                    System.err.print(" 2.2");
                    if (true) {
                        throw new Error();
                    } // и бросили новый Error
                    System.err.print(" 2.3");
                } catch (Throwable t) {            // перехватили Error
                    System.err.print(" 2.4");
                }
                System.err.print(" 2.5");
            } catch (Error e) { // хотя есть cath по Error "ниже", но мы в него не попадаем
                System.err.print(" 3");
            }
            System.err.println(" 4");

            //>> 0 2.1 2.2 2.4 2.5 4
        }
    }
}

class App36 {
    public static void main(String[] args) {
        // нельзя ставить потомка после предка! (RuntimeException после Exception)
        try {
        } catch (Exception e) {
            //  } catch (RuntimeException e) {
        }
    }
}

//>> COMPILATION ERROR: Exception 'java.lang.RuntimeException' has alredy been caught
class App37 {
    //Ставить брата после брата — можно (RuntimeException после Error)
    public static void main(String[] args) {
        try {
        } catch (Error e) {
        } catch (RuntimeException e) {
        }
    }
}

class App38 {
    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (RuntimeException e) {
            System.err.println("catch RuntimeException");
        } catch (Exception e) {
            System.err.println("catch Exception");
        } catch (Throwable e) {
            System.err.println("catch Throwable");
        }
        System.err.println("next statement");
    }

//>> catch Exception
    //       >> next statement
}

class App39 {
    public static void main(String[] args) {
        try {
            Throwable t = new Exception(); // ссылка типа Throwable указывает на объект типа Exception
            throw t;
        } catch (RuntimeException e) {
            System.err.println("catch RuntimeException");
        } catch (Exception e) {
            System.err.println("catch Exception");
        } catch (Throwable e) {
            System.err.println("catch Throwable");
        }
        System.err.println("next statement");
    }
//>> catch Exception
    //>> next statement
}

//try + finally
//finally-секция получает управление, если try-блок завершился успешно
class App40 {
    public static void main(String[] args) {
        try {
            System.err.println("try");
        } finally {
            System.err.println("finally");
        }
    }
//>> try
//>> finally
}

class App41 {
    //finally-секция получает управление, даже если try-блок завершился исключением
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } finally {
            System.err.println("finally");
        }
    }
//>> finally
//>> Exception in thread "main" java.lang.RuntimeException
}

class App42 {
    //finally-секция получает управление, даже если try-блок завершился директивой выхода из метода
    public static void main(String[] args) {
        try {
            return;
        } finally {
            System.err.println("finally");
        }
    }
//>> finally
}

class App43 {
    //finally-секция НЕ вызывается только если мы «прибили» JVM
    public static void main(String[] args) {
        try {
            System.exit(42);
        } finally {
            System.err.println("finally");
        }
    }
//>> Process finished with exit code 42
}

class App44 {
    //System.exit(42) и Runtime.getRuntime().exit(42) — это синонимы
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exit(42);
        } finally {
            System.err.println("finally");
        }
    }
//>> Process finished with exit code 42
}

class App45 {
    //И при Runtime.getRuntime().halt(42) — тоже не успевает зайти в finally
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().halt(42);
        } finally {
            System.err.println("finally");
        }
    }
//>> Process finished with exit code 42
}

class App46 {
    public static void main(String[] args) {
        try {
            System.err.println("try");
            if (true) {
                throw new RuntimeException();
            }
        } finally {
            System.err.println("finally");
        }
        System.err.println("more");
    }
//>> try
//>> finally
// >> Exception in thread "main" java.lang.RuntimeException
}

class App47 {
    public static void main(String[] args) {
        try {
            System.err.println("try");
            throw new RuntimeException();
        } finally {
            System.err.println("finally");
        }
        // System.err.println("more"); //отказывается  компилировать
    }
}
//>> COMPILER ERROR: Unrechable statement

class App48 {
    //И finally-секция не может «предотвратить» выход из метода, если try-блок вызвал return
    // («more» — не выводится в консоль)
    public static void main(String[] args) {
        try {
            System.err.println("try");
            if (true) {
                return;
            }
        } finally {
            System.err.println("finally");
        }
        System.err.println("more");
    }
//>> try
//>> finally
}

class App49 {
    //Однако finally-секция может «перебить» throw/return при помощи другого throw/return
    public static void main(String[] args) {
        System.err.println(f());
    }

    public static int f() {
        try {
            return 0;
        } finally {
            return 1;
        }
    }
//>> 1
}

class App50 {
    public static void main(String[] args) {
        System.err.println(f());
    }

    public static int f() {
        try {
            throw new RuntimeException();
        } finally {
            return 1;
        }
    }
}

//>> 1
class App51 {
    public static void main(String[] args) {
        System.err.println(f());
    }

    public static int f() {
        try {
            return 0;
        } finally {
            throw new RuntimeException();
        }
    }
}//>> Exception in thread "main" java.lang.RuntimeException

class App52 {
    public static void main(String[] args) {
        System.err.println(f());
    }

    public static int f() {
        try {
            throw new Error();
        } finally {
            throw new RuntimeException();
        }
    }
}//>> Exception in thread "main" java.lang.RuntimeException

//finally-секция может быть использована для завершающего действия, которое гарантированно
// будет вызвано (даже если было брошено исключение или автор использовал return) по окончании работы
//// open some resource
// try {
//    // use resource
//} finally {
//    // close resource
//}------------------------------
//Например для освобождения захваченной блокировки
//Lock lock = new ReentrantLock();
//...
//lock.lock();
//try {
//    // some code
//} finally {
//    lock.unlock();
//}--------------------------------
//Или для закрытия открытого файлового потока
//InputStream input = new FileInputStream("...");
//try {
//    // some code
//} finally {
//    input.close();
//}
class App53 {
    public static void main(String[] args) {
        System.err.println(f());
    }

    public static int f() {//не работает свой велосипед
        //   long rnd = System.currenttimeMillis();
        //  boolean finished = false;
        //  try {
        //   if (rnd % 3 == 0) {
        throw new Error();
        //   } else if (rnd % 3 == 1) {
        //     throw new RuntimeException();
        //    } else {
        // nothing
        //    }
        //   finished = true;
        // } finally {
        //  if (finished) {
        // не было исключений
        //    } else {
        // было исключение, но какое?
        // }
        //  }
    }
}

//--------------------Не рекомендуемые практики----------------------
// try + catch + finally
class App54 { //dont have exeption
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            // nothing
            System.err.print(" 1");
        } catch (Error e) {
            System.err.print(" 2");
        } finally {
            System.err.print(" 3");
        }
        System.err.print(" 4");
    }
}

//>> 0 1 3 4
class App55 {
    //Есть исключение и есть подходящий catch
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {
                throw new Error();
            }
            System.err.print(" 1");
        } catch (Error e) {
            System.err.print(" 2");
        } finally {
            System.err.print(" 3");
        }
        System.err.print(" 4");
    }
}

//>> 0 2 3 4
class App56 {
    //Есть исключение но нет подходящего catc
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {
                throw new RuntimeException();
            }
            System.err.print(" 1");
        } catch (Error e) {
            System.err.print(" 2");
        } finally {
            System.err.print(" 3");
        }
        System.err.print(" 4");
    }
}

//>> 0 3
//>> RUNTIME ERROR: Exception in thread "main" java.lang.RuntimeException
// Вложенные try + catch + finally
class App57 {
    //неограниченное вложение
    public static void main(String[] args) {
        if (args.length > 1) {
            if (args.length > 2) {
                if (args.length > 3) {
                    //   ...
                }
            }
        }
    }
}

class App58 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; i++) {
                for (int k = 0; k < 10; k++) {
                    // ...
                }
            }
        }
    }
}

class App59 {
    //try-cacth-finally тоже допускает неограниченное вложение
    public static void main(String[] args) {
        try {
            try {
                try {
                    //  ...
                } catch (Exception e) {
                } finally {
                }
            } catch (Exception e) {
            } finally {
            }
        } catch (Exception e) {
        } finally {
        }
    }
}

class App60 {
    public static void main(String[] args) {
        try {
            try {
                //  ...
            } catch (Exception e) {
                //  ...
            } finally {
                //  ...
            }
        } catch (Exception e) {
            try {
                //   ...
            } catch (Exception i) {
                // ...
            } finally {
                //  ...
            }
        } finally {
            try {
                //  ...
            } catch (Exception e) {
                //  ...
            } finally {
                //  ...
            }
        }
    }
}

class App61 {
    //Вложенный try-catch-finally без исключения
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            try {
                System.err.print(" 1");
                // НИЧЕГО
                System.err.print(" 2");
            } catch (RuntimeException e) {
                System.err.print(" 3"); // НЕ заходим - нет исключения
            } finally {
                System.err.print(" 4"); // заходим всегда
            }
            System.err.print(" 5");     // заходим - выполнение в норме
        } catch (Exception e) {
            System.err.print(" 6");     // НЕ заходим - нет исключения
        } finally {
            System.err.print(" 7");     // заходим всегда
        }
        System.err.print(" 8");         // заходим - выполнение в норме
    }
}

//>> 0 1 2 4 5 7 8
class App62 {
    //Вложенный try-catch-finally с исключением, которое ПЕРЕХВАТИТ ВНУТРЕННИЙ catch
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            try {
                System.err.print(" 1");
                if (true) {
                    throw new RuntimeException();
                }
                System.err.print(" 2");
            } catch (RuntimeException e) {
                System.err.print(" 3"); // ЗАХОДИМ - есть исключение
            } finally {
                System.err.print(" 4"); // заходим всегда
            }
            System.err.print(" 5");     // заходим - выполнение УЖЕ в норме
        } catch (Exception e) {
            System.err.print(" 6");     // не заходим - нет исключения, УЖЕ перехвачено
        } finally {
            System.err.print(" 7");     // заходим всегда
        }
        System.err.print(" 8");         // заходим - выполнение УЖЕ в норме
    }
}

//>> 0 1 3 4 5 7 8
class App63 {
    //Вложенный try-catch-finally с исключением, которое ПЕРЕХВАТИТ ВНЕШНИЙ catch
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            try {
                System.err.print(" 1");
                if (true) {
                    throw new Exception();
                }
                System.err.print(" 2");
            } catch (RuntimeException e) {
                System.err.print(" 3"); // НЕ заходим - есть исключение, но НЕПОДХОДЯЩЕГО ТИПА
            } finally {
                System.err.print(" 4"); // заходим всегда
            }
            System.err.print(" 5");     // не заходим - выполнение НЕ в норме
        } catch (Exception e) {
            System.err.print(" 6");     // ЗАХОДИМ - есть подходящее исключение
        } finally {
            System.err.print(" 7");     // заходим всегда
        }
        System.err.print(" 8");         // заходим - выполнение УЖЕ в норме
    }
}

//>> 0 1 4 6 7 8
class App64 {
    //Вложенный try-catch-finally с исключением, которое НИКТО НЕ ПЕРЕХВАТИТ
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            try {
                System.err.print(" 1");
                if (true) {
                    throw new Error();
                }
                System.err.print(" 2");
            } catch (RuntimeException e) {
                System.err.print(" 3"); // НЕ заходим - есть исключение, но НЕПОДХОДЯЩЕГО ТИПА
            } finally {
                System.err.print(" 4"); // заходим всегда
            }
            System.err.print(" 5");     // НЕ заходим - выполнение НЕ в норме
        } catch (Exception e) {
            System.err.print(" 6");     // не заходим - есть исключение, но НЕПОДХОДЯЩЕГО ТИПА
        } finally {
            System.err.print(" 7");     // заходим всегда
        }
        System.err.print(" 8");         // не заходим - выполнение НЕ в норме
    }
}
//>> 0 1 4 7
//>> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error

