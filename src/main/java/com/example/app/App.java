package com.example.app;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        System.out.println( "Hello World!" );
        Thread.sleep(Long.MAX_VALUE);
    }

    public static int addNumbers(int a, int b) {
        return a + b;
    }
}
