package org.revo.RemotoConnect;

import static org.revo.RemotoConnect.OperationData.execute;

/**
 * Created by ashraf on 9/3/2015.
 */
public class Main {


    public static void main(String[] args) {
        Apply("GetDataFuture",  new GetDataFutureImpl());
        Apply("DataFutureTranslate", new GetDataFutureTranslateImpl());

    }
    public static void Apply(String from, OperationData operationData) {
        System.out.println("*********    " + from + "    *********");
        execute("sequentialSocial", () -> operationData.sequentialSocial());
        execute("parallelSocial", () -> operationData.parallelSocial());
        execute("futureSocial", () -> operationData.futureSocial());
        execute("futureSocialExecutor", () -> operationData.futureSocialExecutor());
        System.out.println("*********                                *********\n\n\n\n");
    }


}
