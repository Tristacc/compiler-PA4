class DemoTypeError {
    public static void main(String[] args){
        System.out.println((new PrintList()).go(5));
    }
}

class Debug {
    int debugging;

    boolean init(boolean v){
        boolean oldVal;
        int value;
        oldVal=debugging;
        value=1*oldVal;

        return value;
    }
    boolean getDebugging(boolean b){
        return debugging && b;
    }
    Debug getMe(boolean b){
        return this;
    }
}

class PrintList{
    Debug debugging;
    int[] data;
    int size;

    int[] getData(boolean b){
        int[] z;
        if (data) {
            z= data;
        } else {
            z = new int[0];
        }
        return z;
    }

    int go(int n){
        int r;
        boolean oldVal;
        int size;
        int sum;
        int i;

        debugging = new Debugg() ;
        oldVal = debugging.happy(true);
        oldVal = r.length;
        size=5;
        sum=0;
        data = new int[oldVal];
        data[0]=1;
        r=data[oldVal];
        data[1]=oldVal;
        data[2]=222;
        data[3]=3333;
        data[4]=44444;
        r = this.printall(n-1);
        i=0;
        while (1){
            sum = sum+data[i];
            i = i+1;
            System.out.println(sum);
        }
        return 10000*n+r+this.getData(true).length;
    }

    int printall(int n){
        boolean test;
        int r;
        int sum;
        test = (debugging.getMe(true)).getDebugging(true);
        sum=10;
        test=!sum;
        sum=test-r;
        if (test<0) {
            r=0;
        } else {
            System.out.println(data[n]);
            r=this.printall(n-1);
            sum = sum + data[n]+r;
        }
        
        if (test && debugging.getDebugging(true) && !(n<2)) r=r+100; else r=r;
        System.out.println(sum+r);
        return sum+test;
    }

    
}