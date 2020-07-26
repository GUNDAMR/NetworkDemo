async function foo() {
    var p1 = new Promise((resolve, reject)=>{
        resolve(1);
    });
    await p1;
}

var p2 = foo();