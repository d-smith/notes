fn main() {

    // type inference
    let x = "hello, world";
    println!("{}",x);

    // mutable
    let mut y = 4;
    println!("y is {}",y);
    y = 5;
    println!("y is {}",y);

    // explicit typing types
    let a:i32 = 2;
    println!("a is {}", a);

    // tuples
    let b  = (1, 3.14, "xxx", 'x', true);
    println!("b.0 is {}", b.0);

    //destructuring
    let (v1, v2, _,_, v3) = b;
    println!("{} {} {}", v1, v2,v3);

    //array initialized with a number of the same elements
    let c:[i32;10] = [0;10];
    println!("{:?}", c);

    if true {
        println!("yep");
    } else {
        println!("nope");
    }

    let mut d = 1;
    loop {
        if d > 1000 {
            break;
        }

        d *= 2;
        println!("{}", d);
    }

    d = 1;
    while d < 1000 {
        d *= 2;
        println!("{}", d);
    }

    // Exclusive range
    for i in 0..10 {
        println!("{}", i)
    }

    //Exclusive range
    for i in 0..=9 {
        println!("{}", i)
    }

    //Iterators
    for val in c {
        println!("{}", val)
    }

    //Matching
    let x  = true;
    let y = true;

    match (x,y) {
        (true,true) => println!( "x & y is true"),
        (false,_) => println!("x & y is false"),
        (_,false) => println!("x & y is false"),
    }
}
