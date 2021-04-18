public class Test {
//    内部类测试
    // 静态嵌套类
    public static class StaticClass{
        public void greet(){
            System.out.println("我是静态嵌套类");
        }
    }
    // 非静态嵌套类
    public class NotStaticClass{
        public void greet(){
            System.out.println("我是非静态嵌套类");
            StaticClass staticClass = new StaticClass();
        }
    }

    public static void main(String[] args) {

    }

}
