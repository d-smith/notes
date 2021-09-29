package com.ds.rxjava.samples;

import io.reactivex.rxjava3.core.Flowable;

public class Main {
    public static void main(String[] args) {
        Flowable.fromArray(args).subscribe(s -> System.out.println("Hello " + s + "!"));
    }
}
